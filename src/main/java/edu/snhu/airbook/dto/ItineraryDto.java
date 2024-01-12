package edu.snhu.airbook.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Itinerary DTO.
 *
 * @author phongtran
 */
@Data
@Builder
public class ItineraryDto {
    private String itineraryId;
    private LocalDateTime departDateTime;
    private String duration;
    private List<FlightDto> flights;
    private List<FlightStopDto> flightStops;
    private FlightOfferDto flightOffer;
}
