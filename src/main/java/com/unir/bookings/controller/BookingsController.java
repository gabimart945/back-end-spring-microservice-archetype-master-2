package com.unir.bookings.controller;

import com.unir.bookings.model.db.Booking;
import com.unir.bookings.model.dto.BookingDto;
import com.unir.bookings.model.request.CreateBookingRequest;
import com.unir.bookings.service.BookingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookingsController {

    private final BookingsService service;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String rooms,
            @RequestParam(required = false) String adults,
            @RequestParam(required = false) String children,
            @RequestParam(required = false) String contact,
            @RequestParam(required = false) String hotelId,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ) {

        List<Booking> bookings = service.getBookings(userId, rooms, adults, children, contact, hotelId, startDate, endDate);

        if (bookings != null) {
            return ResponseEntity.ok(bookings);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable String bookingId) {

        Booking booking = service.getBooking(bookingId);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String bookingId) {

        Boolean removed = service.removeBooking(bookingId);
        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> addBooking(@RequestBody CreateBookingRequest request) {

        Booking createdBooking = service.createBooking(request);
        if (createdBooking != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> patchBooking(@PathVariable String bookingId, @RequestBody String patchBody) {

        Booking patched = service.updateBooking(bookingId, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String bookingId, @RequestBody BookingDto body) {

        Booking updated = service.updateBooking(bookingId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
