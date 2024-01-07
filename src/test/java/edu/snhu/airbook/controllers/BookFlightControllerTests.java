package edu.snhu.airbook.controllers;

import edu.snhu.airbook.AirBookApplication;
import edu.snhu.airbook.config.SecurityConfig;
import edu.snhu.airbook.config.WebFluxConfig;
import edu.snhu.airbook.dto.AirportDto;
import edu.snhu.airbook.dto.FlightDto;
import edu.snhu.airbook.dto.FlightSearchCriteria;
import edu.snhu.airbook.dto.FlightStopDto;
import edu.snhu.airbook.services.AirportService;
import edu.snhu.airbook.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookFlightController.class)
@ContextConfiguration(classes = {SecurityConfig.class, WebFluxConfig.class, AirBookApplication.class})
public class BookFlightControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @MockBean
    private FlightService flightService;

    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    private final AirportDto AIRPORT_1 = AirportDto
            .builder()
            .airportId(1)
            .name("Test Airport")
            .iata("TST")
            .build();

    private final List<AirportDto> AIRPORT_LIST = Collections.singletonList(AIRPORT_1);

    private FlightSearchCriteria flightSearchCriteria;

    private final FlightDto FLIGHT = FlightDto
            .builder()
            .flightId(1)
            .departureAirport(AIRPORT_1)
            .arrivalAirport(AIRPORT_1)
            .departureDate(FORMATTER.parse("2024-01-13"))
            .arrivalDate(FORMATTER.parse("2023-01-15"))
            .flightStops(Collections.singletonList(FlightStopDto.builder().airport(AIRPORT_1).orderNumber(1).build()))
            .build();

    private final List<FlightDto> DEPART_FLIGHTS = Collections.singletonList(FLIGHT);

    private final List<FlightDto> RETURN_FLIGHTS = Collections.singletonList(FLIGHT);

    public BookFlightControllerTests() throws ParseException {
    }

    @BeforeEach
    public void beforeEach() {
        flightSearchCriteria = new FlightSearchCriteria();
        doReturn(AIRPORT_LIST).when(airportService).getAllAirports();
    }

    @Test
    public void showBookingFlightPageTest() throws Exception {
        mockMvc
                .perform(get("/bookFlight"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-flight"))
                .andExpect(model().attribute("airports", AIRPORT_LIST))
                .andExpect(model().attribute("flightSearch", flightSearchCriteria));
    }

    @Test
    public void showBookingFlightSearchPageWithRoundTripTest() throws Exception {
        doReturn(DEPART_FLIGHTS, RETURN_FLIGHTS).when(flightService).searchFlights(any());
        mockMvc
                .perform(get("/bookFlight/search")
                        .param("roundTrip", "on")
                        .param("departureAirport", "SHJ")
                        .param("arrivalAirport", "AAA")
                        .param("departDate", "2024-01-06")
                        .param("returnDate", "2024-01-13")
                        .param("passengerNumber", "8"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-result"))
                .andExpect(model().attribute("departureAirport", AIRPORT_1))
                .andExpect(model().attribute("arrivalAirport", AIRPORT_1))
                .andExpect(model().attribute("departFlights", DEPART_FLIGHTS))
                .andExpect(model().attribute("returnFlights", RETURN_FLIGHTS));
    }

    @Test
    public void showBookingFlightSearchPageWithOneWayTest() throws Exception {
        doReturn(DEPART_FLIGHTS).when(flightService).searchFlights(any());
        mockMvc
                .perform(get("/bookFlight/search")
                        .param("oneWay", "on")
                        .param("departureAirport", "SHJ")
                        .param("arrivalAirport", "AAA")
                        .param("departDate", "2024-01-06")
                        .param("returnDate", "2024-01-13")
                        .param("passengerNumber", "8"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-result"))
                .andExpect(model().attribute("departureAirport", AIRPORT_1))
                .andExpect(model().attribute("arrivalAirport", AIRPORT_1))
                .andExpect(model().attribute("departFlights", DEPART_FLIGHTS));
    }
}
