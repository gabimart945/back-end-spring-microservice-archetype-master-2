package com.unir.bookings.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {

	@NotNull(message = "`rooms` cannot be null")
	@NotEmpty(message = "`rooms` cannot be empty")
	private Integer rooms;
	@NotNull(message = "`adults` cannot be null")
	@NotEmpty(message = "`adults` cannot be empty")
	private Integer adults;
	@NotNull(message = "`children` cannot be null")
	@NotEmpty(message = "`children` cannot be empty")
	private Integer children;
	@NotNull(message = "`contact` cannot be null")
	@NotEmpty(message = "`contact` cannot be empty")
	private String contact;
	@NotNull(message = "`userId` cannot be null")
	@NotEmpty(message = "`userId` cannot be empty")
	private Long userId;
	@NotNull(message = "`hotel` cannot be null")
	@NotEmpty(message = "`hotel` cannot be empty")
	private Long hotel;
	@NotNull(message = "`startDate` cannot be null")
	@NotEmpty(message = "`startDate` cannot be empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@NotNull(message = "`endDate` cannot be null")
	@NotEmpty(message = "`endDate` cannot be empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
}
