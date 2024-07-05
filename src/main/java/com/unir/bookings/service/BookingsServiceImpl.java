package com.unir.bookings.service;

import com.unir.bookings.data.BookingRepository;
import com.unir.bookings.facade.HotelsFacade;
import com.unir.bookings.model.db.Booking;
import com.unir.bookings.model.dto.BookingDto;
import com.unir.bookings.model.dto.Hotel;
import com.unir.bookings.model.request.CreateBookingRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BookingsServiceImpl implements BookingsService {

	@Autowired
	private BookingRepository repository;

	@Autowired
	private HotelsFacade hotelsFacade;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Booking> getBookings(String userId, String rooms, String adults, String children, String contact,
									 String hotelId, Date startDate, Date endDate) {

		if (StringUtils.hasLength(rooms) || StringUtils.hasLength(adults) || StringUtils.hasLength(children)
				|| StringUtils.hasLength(contact) || StringUtils.hasLength(hotelId)
				|| (startDate != null && endDate != null) || StringUtils.hasLength(userId)){

			return repository.search(userId, rooms, adults, children, contact,
					hotelId, startDate, endDate);
		}

		List<Booking> bookings = repository.getBookings();
		return bookings.isEmpty() ? null : bookings;
	}

	@Override
	public Booking getBooking(String bookingId) {
		return repository.getById(Long.valueOf(bookingId));
	}

	@Override
	public Boolean removeBooking(String bookingId) {

		Booking booking = repository.getById(Long.valueOf(bookingId));

		if (booking != null) {
			repository.delete(booking);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Booking createBooking(CreateBookingRequest request) {

		if (request != null && StringUtils.hasLength(request.getRooms().toString().trim()) && request.getAdults() != null
				&&  request.getChildren() != null && request.getContact() != null && request.getHotel() != null
				&&  request.getStartDate() != null && request.getEndDate() != null && request.getUserId() != null) {

			Hotel hotel = hotelsFacade.getHotel(request.getHotel());

			if (hotel == null) {
				return null;
			}

			// Check availability:
			List<Booking> bookings = repository.search("", "", "", "", "",
					request.getHotel().toString(), request.getStartDate(), request.getEndDate());

			hotel = calculateAvailability(hotel, bookings);
			if (hotel.getAvailableRooms() < request.getRooms()){
				return null;
			}

			Booking booking = Booking.builder().rooms(request.getRooms()).adults(request.getAdults())
					.children(request.getChildren()).contact(request.getContact()).userId(request.getUserId())
					.hotel(hotel.getId()).startDate(request.getStartDate()).endDate(request.getEndDate()).build();

			return repository.save(booking);
		} else {
			return null;
		}
	}

	private Hotel calculateAvailability(Hotel hotel, List<Booking> bookings) {
		if(bookings!= null && !bookings.isEmpty()) {
			for (Booking booking : bookings) {
				hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getRooms());
			}
		}

		return hotel;
	}

	@Override
	public Booking updateBooking(String bookingId, String request) {

		Booking booking = repository.getById(Long.valueOf(bookingId));
		if (booking != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(booking)));
				Booking patched = objectMapper.treeToValue(target, Booking.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating booking {}", bookingId, e);
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public Booking updateBooking(String bookingId, BookingDto updateRequest) {
		Booking booking = repository.getById(Long.valueOf(bookingId));
		if (booking != null) {
			booking.update(updateRequest);
			repository.save(booking);
			return booking;
		} else {
			return null;
		}
	}

}
