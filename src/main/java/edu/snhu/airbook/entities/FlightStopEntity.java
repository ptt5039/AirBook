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
    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private FlightEntity flight;

    @Id
    @ManyToOne
    @JoinColumn(name = "AIRPORT_ID", nullable = false)
    private AirportEntity airport;

    @Column(name="ORDER_NUMBER")
    private int orderNumber;
}
