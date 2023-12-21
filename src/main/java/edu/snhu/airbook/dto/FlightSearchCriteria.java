package edu.snhu.airbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Flight Search Criteria class
 *
 * @author phongtran
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FlightSearchCriteria {
    @Builder.Default
    private boolean roundTrip = false;
    @Builder.Default
    private boolean oneWay = false;
    private String departureAirport;
    private String arrivalAirport;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private String passengerNumber;
}
