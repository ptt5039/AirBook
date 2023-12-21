package edu.snhu.airbook.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Flight Offer Entity class
 *
 * @author phongtran
 */
@Entity
@Table(name = "FLIGHT_OFFER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(FlightOfferPK.class)
public class FlightOfferEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "TRAVEL_CLASS_ID", nullable = false)
    private TravelClassEntity travelClass;

    @Id
    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private FlightEntity flight;

    @Column(name="AVAILABLE_SEAT")
    private int availableSeat;

    @Column(name="COST")
    private double cost;
}
