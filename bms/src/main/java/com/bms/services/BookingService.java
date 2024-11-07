package com.bms.services;

import com.bms.dtos.BookMovieRequestDto;
import com.bms.models.*;
import com.bms.repositories.BookingRepository;
import com.bms.repositories.ShowRepository;
import com.bms.repositories.ShowSeatRepository;
import com.bms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;


    public Booking bookMovie(BookMovieRequestDto request) {

        //Validate if user is present or not
        //Validate if show is present or not
        //reserveShowSeat
        //createBooking object
        validateUser(request);

        validateShow(request);

        List<ShowSeat> reservedShowSeats = reserveShowSeats(request.getShowSeatIds());

        Booking booking = createBooking(reservedShowSeats);
        return bookingRepository.save(booking);
    }

    private static Booking createBooking(List<ShowSeat> reservedShowSeats) {
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(reservedShowSeats);
        //Need to iterate through all the showSeats -> find the corresponding price for all of them & sum them up
        booking.setBillingAmount(100);

        booking.setPayments(new ArrayList<>());
        return booking;
    }

    private void validateShow(BookMovieRequestDto request) {
        Optional<Show> show = showRepository.findById(request.getShowId());
        if (!show.isPresent()) {
            throw new RuntimeException();
        }
    }

    private void validateUser(BookMovieRequestDto request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if (!user.isPresent()) {
            throw new RuntimeException();
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> reserveShowSeats(List<Long> showSeatIds) {

        //First fetch all showSeats from ids
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        boolean allAvailable = areAllSeatsAvailableForBooking(showSeats, true);

        if(allAvailable) {
            markShowSeatAsLocked(showSeats);
        }

        if(!allAvailable) {
            throw new RuntimeException();
        }
        return showSeats;
    }

    private void markShowSeatAsLocked(List<ShowSeat> showSeats) {
        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatRepository.save(showSeat);
        }
    }

    private static boolean areAllSeatsAvailableForBooking(List<ShowSeat> showSeats, boolean allAvailable) {

        for(ShowSeat showSeat : showSeats) {

            if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.LOCKED)) {
                long lockedSince = Duration.between(showSeat.getLockedAt(), new Date().toInstant()).toMinutes();
                if(lockedSince<5){
                    allAvailable = false;
                    break;
                }
            }

            else if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED)) {
                allAvailable = false;
                break;
            }


        }
        return allAvailable;
    }
}
