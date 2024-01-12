package edu.snhu.airbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Flight Offer DTO
 *
 * @author phongtran
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightOfferDto {
    private int availableSeat;
    private String cost;
}
