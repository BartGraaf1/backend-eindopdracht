package com.novi.easyboat.controllers;

import com.novi.easyboat.controllers.dto.BookingDto;
import com.novi.easyboat.controllers.dto.BookingInputDto;
import com.novi.easyboat.exceptions.BadRequestException;
import com.novi.easyboat.model.Booking;
import com.novi.easyboat.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingDto> getBookings(@RequestParam(value = "boatId", required = false) Long boatId,
                                        @RequestParam(value = "customerId", required = false) Long customerId,
                                        @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                        @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        var dtos = new ArrayList<BookingDto>();

        List<Booking> bookings;
        if (boatId != null && customerId == null && start == null && end == null) {
            bookings = bookingService.getBookingsForBoat(boatId);

        } else if (customerId != null && boatId == null && start == null && end == null) {
            bookings = bookingService.getBookingsForCustomer(customerId);

        } else if (start != null && end != null && customerId == null && boatId == null) {
            bookings = bookingService.getBookingsBetweenDates(start, end);

        } else {
            throw new BadRequestException();
        }

        for (Booking booking : bookings) {
            dtos.add(BookingDto.fromBooking(booking));
        }

        return dtos;
    }

    @PostMapping
    public void saveBooking(@RequestBody BookingInputDto dto) {
        bookingService.planBooking(dto.boatId, dto.customerId, dto.startTime, dto.endTime);
    }
}
