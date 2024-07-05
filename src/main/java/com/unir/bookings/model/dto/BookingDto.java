package com.unir.bookings.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookingDto {
	
	private Integer rooms;
	private Integer adults;
	private Integer children;
	private String contact;
	private Long userId;
	private Long hotel;
	private Date startDate;
	private Date endDate;

}
