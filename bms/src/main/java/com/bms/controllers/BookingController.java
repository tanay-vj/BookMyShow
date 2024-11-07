package com.bms.controllers;

import com.bms.dtos.BookMovieRequestDto;
import com.bms.dtos.BookMovieResponseDto;
import com.bms.models.Booking;
import com.bms.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    public BookMovieResponseDto bookMovie(BookMovieRequestDto request) {

        Booking booking = bookingService.bookMovie(request);
        return new BookMovieResponseDto(HttpStatus.ACCEPTED,booking.getBillingAmount(),
                booking.getId());


    }
}
