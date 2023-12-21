package edu.snhu.airbook.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Flight Offer PK class
 *
 * @author phongtran
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightOfferPK implements Serializable {
    private TravelClassEntity travelClass;
    private FlightEntity flight;
}
