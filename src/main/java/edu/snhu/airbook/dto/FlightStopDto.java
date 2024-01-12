package edu.snhu.airbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Flight Stop DTO
 *
 * @author phongtran
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightStopDto {
    private AirportDto airport;
    private int orderNumber;
}
