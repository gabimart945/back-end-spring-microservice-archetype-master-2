package com.unir.bookings.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unir.bookings.model.dto.BookingDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "rooms")
	private Integer rooms;

	@Column(name = "adults")
	private Integer adults;

	@Column(name = "children")
	private Integer children;

	@Column(name = "contact")
	private String contact;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "hotel")
	private Long hotel;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	private Date endDate;

	public void update(BookingDto bookingDto) {
		this.rooms = bookingDto.getRooms();
		this.adults = bookingDto.getAdults();
		this.children = bookingDto.getChildren();
		this.contact = bookingDto.getContact();
		this.userId = bookingDto.getUserId();
		this.hotel = bookingDto.getHotel();
		this.startDate = bookingDto.getStartDate();
		this.endDate = bookingDto.getEndDate();
	}
}
