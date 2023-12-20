package edu.snhu.airbook.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Airport Entity
 *
 * @author phongtran
 */
@Entity
@Table(name = "AIRPORT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AIRPORT_ID")
    private int airportId;

    private String icao;

    private String iata;

    private String name;

    private String state;

    private String country;
}
