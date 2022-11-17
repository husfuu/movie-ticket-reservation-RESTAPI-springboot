## Run Database in Docker

# Entity

![Alt text](/image/entity-movie-ticket-reservation.png "Entity")

# Setup

### Run Database in Docker

```
$ docker run --rm \
--name movie-ticket-reservation-db \
-e POSTGRES_DB=movie_ticket_reservation_db  \
-e POSTGRES_USER=assignment \
-e POSTGRES_PASSWORD=mysecretpassword \
-e PGDATA=/var/lib/postgresql/data/pgdata \
-v "$PWD/movie-ticket-reservation-db-data:/var/lib/postgresql/data" \
-p 5432:5432 \
postgres:13
```

##### Get in Docker database

```
psql -h 127.0.0.1 -U assignment movie_ticket_reservation_db
```

#### Run Spring Application

```
mvn clean spring-boot:run
```

#### Install Packages from Spring Initializer

```
mvn clean package -DskipTests
```

## TODO

- [ ] validations
- [ ] validation in request body
- [ ] handle custom exception
- [ ] handle http error
- [ ] implement softdelete
- [ ] create seeders
- [ ] http test
