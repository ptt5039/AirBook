package edu.snhu.airbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Flight DTO class
 *
 * @author phongtran
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDto {
    private int flightId;

    private AirportDto departureAirport;

    private AirportDto arrivalAirport;

    private String flightNumber;

    private Date departureDate;

    private String departureTime;

    private Date arrivalDate;

    private String arrivalTime;

    private List<FlightOfferDto> flightOffers;

    private List<FlightStopDto> flightStops;

}
