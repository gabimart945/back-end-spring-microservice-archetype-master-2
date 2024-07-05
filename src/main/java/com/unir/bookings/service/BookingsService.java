package com.unir.bookings.service;


import com.unir.bookings.model.db.Booking;


import com.unir.bookings.model.dto.BookingDto;
import com.unir.bookings.model.request.CreateBookingRequest;

import java.util.Date;
import java.util.List;

public interface BookingsService {
	
	List<Booking> getBookings(String userId, String rooms, String adults, String children, String contact,
							  String hotelId, Date startDate, Date endDate);

	Booking getBooking(String bookingId);
	
	Boolean removeBooking(String bookingId);

	Booking createBooking(CreateBookingRequest request);

	Booking updateBooking(String bookingId, String updateRequest);

	Booking updateBooking(String bookingId, BookingDto updateRequest);

}
