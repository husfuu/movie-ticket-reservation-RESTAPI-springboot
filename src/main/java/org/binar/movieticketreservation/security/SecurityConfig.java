package org.binar.movieticketreservation.security;

import org.binar.movieticketreservation.entity.ERole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(
                authenticationManagerBean());
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // default /login is public
        http.authorizeRequests().antMatchers("/api/v1/login/**").permitAll();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/users/**").hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/v1/users/**").hasAuthority(ERole.ROLE_CUSTOMER.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAuthority(ERole.ROLE_CUSTOMER.name())
                .antMatchers(HttpMethod.POST, "/api/v1/transactions").hasAuthority(ERole.ROLE_CUSTOMER.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/transactions/**").hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/v1/films/**").hasAuthority(ERole.ROLE_CUSTOMER.name())
                .antMatchers(HttpMethod.GET, "/api/v1/upcoming_films/**").hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/v1/films/**").hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/films/**").hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/v1/films_provided/**").hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/films/**").hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/v1/schedules").hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/v1/invoices/**").hasAuthority(ERole.ROLE_CUSTOMER.name());
//                .anyRequest().authenticated();

        authenticationFilter.setFilterProcessesUrl("/api/v1/login");
        http.addFilter(authenticationFilter);
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
