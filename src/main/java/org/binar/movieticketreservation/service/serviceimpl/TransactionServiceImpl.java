package org.binar.movieticketreservation.service.serviceimpl;

import javax.transaction.Transactional;

import org.binar.movieticketreservation.dto.TransactionServiceInput;
import org.binar.movieticketreservation.entity.Film;
import org.binar.movieticketreservation.entity.Schedule;
import org.binar.movieticketreservation.entity.Studio;
import org.binar.movieticketreservation.entity.TransactionHistory;
import org.binar.movieticketreservation.entity.TransactionStatus;
import org.binar.movieticketreservation.entity.Users;
import org.binar.movieticketreservation.repository.FilmRepository;
import org.binar.movieticketreservation.repository.ScheduleRepository;
import org.binar.movieticketreservation.repository.StudioRepository;
import org.binar.movieticketreservation.repository.TransactionHistoryRepository;
import org.binar.movieticketreservation.repository.UserRepository;
import org.binar.movieticketreservation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

        private TransactionHistoryRepository transactionRepository;
        private UserRepository userRepository;
        private FilmRepository filmRepository;
        private StudioRepository studioRepository;
        private ScheduleRepository scheduleRepository;

        @Autowired
        public TransactionServiceImpl(
                        TransactionHistoryRepository transactionHistoryRepository,
                        UserRepository userRepository,
                        FilmRepository filmRepository,
                        StudioRepository studioRepository,
                        ScheduleRepository scheduleRepository) {
                this.transactionRepository = transactionHistoryRepository;
                this.userRepository = userRepository;
                this.filmRepository = filmRepository;
                this.studioRepository = studioRepository;
                this.scheduleRepository = scheduleRepository;
        }

        @Override
        public void saveTransaction(TransactionServiceInput transactionServiceInput) throws Exception {

                TransactionHistory transaction = new TransactionHistory();

                Users user = userRepository.findById(transactionServiceInput.getUserId())
                                .orElseThrow(() -> new Exception("user not found"));

                transaction.setUsers(user);

                Film film = filmRepository.findById(transactionServiceInput.getFilmId())
                                .orElseThrow(() -> new Exception("film not found"));

                transaction.setFilm(film);

                Studio studio = studioRepository.findById(transactionServiceInput.getStudioId())
                                .orElseThrow(() -> new Exception("studio not found"));

                transaction.setStudio(studio);

                Schedule schedule = scheduleRepository.findById(transactionServiceInput.getScheduleId())
                                .orElseThrow(() -> new Exception("schedule not found"));

                transaction.setSchedule(schedule);

                transactionRepository.save(transaction);
        }

        @Override
        public void updateStatusTransaction(TransactionStatus tStatus, String transactionId) throws Exception {

                TransactionHistory transaction = transactionRepository.findById(transactionId)
                                .orElseThrow(() -> new Exception("transactiond not found"));

                transaction.setTransactionStatus(tStatus);
        }

}
