package edu.snhu.airbook.entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "DEPARTURE_AIRPORT", nullable = false)
    private AirportEntity departureAirport;

    @ManyToOne
    @JoinColumn(name = "ARRIVAL_AIRPORT", nullable = false)
    private AirportEntity arrivalAirport;

    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;

    @Column(name = "DEPARTURE_DATE")
    private Date departureDate;

    @Column(name = "DEPARTURE_TIME")
    private Time departureTime;

    @Column(name = "ARRIVAL_DATE")
    private Date arrivalDateTime;

    @Column(name = "ARRIVAL_TIME")
    private Time arrivalTime;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private List<FlightOfferEntity> flightOffers;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private List<FlightStopEntity> flightStops;
}
