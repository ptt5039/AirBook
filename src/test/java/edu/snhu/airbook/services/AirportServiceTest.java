package edu.snhu.airbook.services;


import edu.snhu.airbook.dto.AirportDto;
import edu.snhu.airbook.entities.AirportEntity;
import edu.snhu.airbook.repo.AirportRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AirportServiceTest {
    @Mock
    private AirportRepo airportRepo;

    @InjectMocks
    private AirportService airportService;

    private static final int AIRPORT_ID_1 = 1;

    private static final String IATA_1 = "ACC";
    private static final String NAME_1 = "Kotoka International Airport";

    private static final int AIRPORT_ID_2 = 2;
    private static final String IATA_2 = "WAC";
    private static final String NAME_2 = "Wacca Airport";

    private static final AirportEntity ENTITY_1 = AirportEntity
            .builder()
            .airportId(AIRPORT_ID_1)
            .iata(IATA_1)
            .name(NAME_1)
            .build();

    private static final AirportEntity ENTITY_2 = AirportEntity
            .builder()
            .airportId(AIRPORT_ID_2)
            .iata(IATA_2)
            .name(NAME_2)
            .build();
    private static final AirportDto DTO_1 = AirportDto
            .builder()
            .airportId(AIRPORT_ID_1)
            .iata(IATA_1)
            .name(NAME_1)
            .build();

    private static final AirportDto DTO_2 = AirportDto
            .builder()
            .airportId(AIRPORT_ID_2)
            .iata(IATA_2)
            .name(NAME_2)
            .build();

    /**
     * Test getAllAirports method
     */
    @Test
    public void getAllAirportTest() {
        List<AirportEntity> entityList = new ArrayList<>();
        entityList.add(ENTITY_1);
        entityList.add(ENTITY_2);
        doReturn(entityList).when(airportRepo).findAll();

        List<AirportDto> dtoList = new ArrayList<>();
        dtoList.add(DTO_1);
        dtoList.add(DTO_2);
        assertEquals(dtoList, airportService.getAllAirports());
    }

    /**
     * Test getAirportById method
     */
    @Test
    public void getAirportByIdTest() {
        doReturn(Optional.of(ENTITY_1)).when(airportRepo).findById(AIRPORT_ID_1);
        assertEquals(DTO_1, airportService.getAirportById(AIRPORT_ID_1));
    }

    /**
     * Test getAirportByIata method
     */
    @Test
    public void getAirportByIataTest() {
        doReturn(Collections.singletonList(ENTITY_1)).when(airportRepo).findByIataContainingIgnoreCase(IATA_1);
        assertEquals(DTO_1, airportService.getAirportByIata(IATA_1));
    }

    /**
     * Test getAirportByIata method
     */
    @Test
    public void getAirportsByIataOrName() {
        doReturn(Collections.singletonList(ENTITY_1)).when(airportRepo).findByIataContainingIgnoreCase(IATA_1);
        doReturn(Collections.singletonList(ENTITY_2)).when(airportRepo).findByNameContainingIgnoreCase(IATA_1);

        List<AirportDto> dtoList = new ArrayList<>();
        dtoList.add(DTO_1);
        dtoList.add(DTO_2);
        assertEquals(dtoList, airportService.getAirportsByIataOrName(IATA_1));
    }

}
