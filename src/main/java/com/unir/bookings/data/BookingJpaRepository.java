package com.unir.bookings.data;

import com.unir.bookings.model.db.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


interface BookingJpaRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

}
