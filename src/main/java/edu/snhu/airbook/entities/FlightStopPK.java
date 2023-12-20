package edu.snhu.airbook.entities;

import lombok.*;

import java.io.Serializable;

/**
 * Flight Stop PK Class
 *
 * @author phongtran
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class FlightStopPK implements Serializable {
    private int flightId;
    private int airportId;
}
