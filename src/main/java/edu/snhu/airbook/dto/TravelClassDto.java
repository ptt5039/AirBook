package edu.snhu.airbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Travel Class DTO
 *
 * @author phongtran
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelClassDto {
    private int classId;
    private String className;
}
