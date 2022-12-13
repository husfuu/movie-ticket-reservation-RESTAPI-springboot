package org.binar.movieticketreservation.service.serviceimpl;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.binar.movieticketreservation.dto.request.TransactionRequestDTO;
import org.binar.movieticketreservation.entity.Film;
import org.binar.movieticketreservation.entity.Schedule;
import org.binar.movieticketreservation.entity.Studio;
import org.binar.movieticketreservation.entity.Transaction;
import org.binar.movieticketreservation.entity.TransactionStatus;
import org.binar.movieticketreservation.entity.Users;
import org.binar.movieticketreservation.exception.TransactionServiceException;
import org.binar.movieticketreservation.repository.FilmRepository;
import org.binar.movieticketreservation.repository.ScheduleRepository;
import org.binar.movieticketreservation.repository.StudioRepository;
import org.binar.movieticketreservation.repository.TransactionRepository;
import org.binar.movieticketreservation.repository.UserRepository;
import org.binar.movieticketreservation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {

        private final TransactionRepository transactionRepository;
        private final UserRepository userRepository;
        private final FilmRepository filmRepository;
        private final StudioRepository studioRepository;
        private final ScheduleRepository scheduleRepository;

        @Autowired
        public TransactionServiceImpl(
                        TransactionRepository transactionRepository,
                        UserRepository userRepository,
                        FilmRepository filmRepository,
                        StudioRepository studioRepository,
                        ScheduleRepository scheduleRepository) {
                this.transactionRepository = transactionRepository;
                this.userRepository = userRepository;
                this.filmRepository = filmRepository;
                this.studioRepository = studioRepository;
                this.scheduleRepository = scheduleRepository;
        }

        @Override
        public String saveTransaction(TransactionRequestDTO transactionRequestDTO) {
                try {
                        Transaction transaction = new Transaction();
                        Users user = userRepository.findById(transactionRequestDTO.getUserId())
                                        .orElseThrow(() -> new Exception("user not found"));
                        transaction.setUsers(user);
                        Film film = filmRepository.findById(transactionRequestDTO.getFilmId())
                                        .orElseThrow(() -> new Exception("film not found"));
                        transaction.setFilm(film);
                        Studio studio = studioRepository.findById(transactionRequestDTO.getStudioId())
                                        .orElseThrow(() -> new Exception("studio not found"));
                        transaction.setStudio(studio);
                        Schedule schedule = scheduleRepository.findById(transactionRequestDTO.getScheduleId())
                                        .orElseThrow(() -> new Exception("schedule not found"));
                        transaction.setSchedule(schedule);

                        transactionRepository.save(transaction);
                        log.debug("TransactionService: create transaction success");

                        return "success to create new transaction";
                } catch (Exception e) {
                        log.error("Exception occurred while create new transaction {}", e.getMessage());
                        throw new TransactionServiceException("Exception occurred while create new transaction");
                }
        }

        @Override
        public String updateStatusTransaction(TransactionStatus tStatus, String transactionId) {
                try {
                        Transaction transaction = transactionRepository.findById(transactionId)
                                        .orElseThrow(() -> new Exception("transaction not found"));
                        transaction.setTransactionStatus(tStatus);
                        log.debug("TransactionService: updateTransactionStatus success");

                        return "success to update transaction status";
                } catch (Exception e) {
                        log.error("Exception occurred while update transaction status {}", e.getMessage());
                        throw new TransactionServiceException("Exception occurred while update transaction status");
                }
        }
}
