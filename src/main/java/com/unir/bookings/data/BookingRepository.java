package com.unir.bookings.data;


import com.unir.bookings.data.utils.SearchCriteria;
import com.unir.bookings.data.utils.SearchOperation;
import com.unir.bookings.data.utils.SearchStatement;
import com.unir.bookings.model.db.Booking;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRepository {

    private final BookingJpaRepository repository;

    public List<Booking> getBookings() {
        return repository.findAll();
    }

    public Booking getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Booking save(Booking booking) {
        return repository.save(booking);
    }

    public void delete(Booking booking) {
        repository.delete(booking);
    }

    public List<Booking> search(String userId, String rooms, String adults, String children, String contact,
                                String hotelId, Date startDate, Date endDate) {
        SearchCriteria<Booking> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(userId)) {
            spec.add(new SearchStatement("userId", userId, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(rooms)) {
            spec.add(new SearchStatement("rooms", rooms, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(adults)) {
            spec.add(new SearchStatement("adults", adults, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(children)) {
            spec.add(new SearchStatement("children", children, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(contact)) {
            spec.add(new SearchStatement("contact", contact, SearchOperation.MATCH));
        }

        if (endDate != null && startDate != null) {
            spec.add(new SearchStatement("startDate", endDate, SearchOperation.DATE_LESS_THAN_EQUAL));
            spec.add(new SearchStatement("endDate", startDate, SearchOperation.DATE_GREATER_THAN_EQUAL));
        }

        if (StringUtils.isNotBlank(hotelId)) {
            spec.add(new SearchStatement("hotel", hotelId, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }

}
