package edu.snhu.airbook.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Airport Entity.
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

    @Column(name = "ICAO")
    private String icao;

    @Column(name = "IATA")
    private String iata;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;
}
