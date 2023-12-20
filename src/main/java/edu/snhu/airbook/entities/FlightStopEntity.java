package edu.snhu.airbook.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Flight Stop Entity
 *
 * @author phongtran
 */
@Entity
@Table(name="FLIGHT_STOP")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(FlightStopPK.class)
public class FlightStopEntity {
    @Id
    @Column(name="FLIGHT_ID")
    private int flightId;

    @Id
    @Column(name="AIRPORT_ID")
    private int airportId;

    @Column(name="ORDER_NUMBER")
    private int orderNumber;
}
