package edu.snhu.airbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Airport DTO
 *
 * @author phongtran
 */
@Data
@Builder
@AllArgsConstructor
public class AirportDto {
    private int airportId;

    private String iata;

    private String name;

    private String state;

    private String country;
}
