package edu.snhu.airbook.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

/**
 * Flight Entity
 *
 * @author phongtran
 */
@Entity
@Table(name = "FLIGHT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ID")
    private int flightId;

    @Column(name = "DEPARTURE_AIRPORT")
    private int departureAirport;

    @Column(name = "ARRIVAL_AIRPORT")
    private int arrivalAirport;

    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;

    @Column(name = "DEPARTURE_DATETIME")
    private LocalDateTime departureDateTime;

    @Column(name = "ARRIVAL_DATETIME")
    private LocalDateTime arrivalDateTime;
}
