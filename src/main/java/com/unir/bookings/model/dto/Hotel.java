package com.unir.bookings.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Hotel {

	private Long id;
	private String name;
	private String address;
	private String description;
	private Double latitude;
	private Double longitude;
	private Double price;
	private Integer stars;
	private Double opinion;
	private Integer maxRooms;
	private String contactMail;
	private List<String> facilities;
	private String contactNumber;
	private Integer availableRooms;
	private List<String> images;

}
