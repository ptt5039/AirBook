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
    @Column(name = "TRAVEL_CLASS_ID")
    private int travelClassId;

    @Id
    @Column(name="FLIGHT_ID")
    private int flightId;

    @Column(name="AVAILABLE_SEAT")
    private int availableSeat;

    private double cost;
}
