package com.unir.bookings.facade;

import com.unir.bookings.model.dto.Hotel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class HotelsFacade {

    @Value("${getHotel.url}")
    private String getHotelUrl;

    private final RestTemplate restTemplate;

    public Hotel getHotel(Long id) {

        try {
            String url = String.format(getHotelUrl, id);
            log.info("Getting product with ID {}. Request to {}", id, url);
            return restTemplate.getForObject(url, Hotel.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        } catch (Exception e) {
            log.error("Error: {}, Product with ID {}", e.getMessage(), id);
            return null;
        }
    }

}