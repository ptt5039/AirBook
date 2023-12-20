package edu.snhu.airbook.services;

import edu.snhu.airbook.dto.AirportDto;
import edu.snhu.airbook.entities.AirportEntity;
import edu.snhu.airbook.repo.AirportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {
    @Autowired
    private AirportRepo airportRepo;

    /**
     * Get all airports.
     *
     * @return List of all airports
     */
    public List<AirportDto> getAllAirports() {
        List<AirportEntity> airports = airportRepo.findAll();
        return airports.stream().map(this::mapAirportDto).collect(Collectors.toList());
    }

    /**
     * Get Airport by Airport ID.
     *
     * @param id airport Id
     * @return Airport if present
     */
    public AirportDto getAirportById(Integer id) {
        AirportEntity airport = airportRepo.getReferenceById(id);
        return mapAirportDto(airport);

    }

    /**
     * Get Airports by IATA or Name
     *
     * @param value value of IATA or Name
     * @return List of Airports
     */
    public List<AirportDto> getAirportsByIataOrName(String value) {
        List<AirportEntity> firstList = airportRepo.findByIataContainingIgnoreCase(value);
        List<AirportEntity> secondList = airportRepo.findByNameContainingIgnoreCase(value);

        LinkedHashSet<AirportEntity> set = new LinkedHashSet<>(firstList);
        set.addAll(secondList);
        return set.stream().map(this::mapAirportDto).collect(Collectors.toList());
    }

    /**
     * Map Airport Entity to Airport DTO.
     *
     * @param airport airport entity
     * @return AirportDto
     */
    private AirportDto mapAirportDto(AirportEntity airport) {
        return AirportDto.builder()
                .airportId(airport.getAirportId())
                .iata(airport.getIata())
                .name(airport.getName())
                .state(airport.getState())
                .country(airport.getCountry())
                .build();
    }
}
