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
    private FlightEntity flight;
    private AirportEntity airport;
}
