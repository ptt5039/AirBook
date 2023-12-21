package edu.snhu.airbook.services;

import edu.snhu.airbook.dto.*;
import edu.snhu.airbook.utils.DateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

/**
 * Flight service class
 *
 * @author phongtran
 */
@Service
public class FlightService {

    @Autowired
    AirportService airportService;

    @Autowired
    TravelClassService travelClassService;

    /**
     * Generate dummy flight data
     *
     * @param flightSearchCriteria search criteria
     * @return List of Flights
     */
    public List<FlightDto> searchFlights(FlightSearchCriteria flightSearchCriteria) {
        List<FlightDto> flightList = new ArrayList<>();
        List<TravelClassDto> travelClassDtos = travelClassService.getAllTravelClass();
        for (int i = 0; i < 10; i++) {
            final Random random = new Random();
            final int millisInDay = 24 * 60 * 60 * 1000;
            int stops = random.nextInt(3);
            List<FlightStopDto> flightStops = new ArrayList<>();
            if (stops > 0) {
                for (int j = 0; j < stops; j++) {
                    FlightStopDto stop = FlightStopDto
                            .builder()
                            .flight(new FlightDto())
                            .airport(airportService.getAirportById((j + 1) + (i * 5)))
                            .orderNumber(j + 1)
                            .build();
                    flightStops.add(stop);
                }
            }
            List<FlightOfferDto> flightOffers = new ArrayList<>();
            for (TravelClassDto travelClass : travelClassDtos) {
                FlightOfferDto offer = FlightOfferDto
                        .builder()
                        .flight(new FlightDto())
                        .travelClass(travelClass)
                        .cost(travelClass.getClassId() * 250.62 * random.nextInt(1, 3))
                        .availableSeat((travelClass.getClassId() * 10) - 5)
                        .build();
                flightOffers.add(offer);
            }
            FlightDto flightDto = FlightDto
                    .builder()
                    .flightId(i + 1)
                    .flightNumber("A00" + (i + 1) * random.nextInt(1, 50))
                    .departureAirport(airportService.getAirportByIata(flightSearchCriteria.getDepartureAirport()))
                    .arrivalAirport(airportService.getAirportByIata(flightSearchCriteria.getArrivalAirport()))
                    .departureDate(flightSearchCriteria.getDepartDate())
                    .departureTime(DateTimeConverter.fromTimeToString(new Time((long) random.nextInt(millisInDay))))
                    .arrivalDate(flightSearchCriteria.getDepartDate())
                    .arrivalTime(DateTimeConverter.fromTimeToString(new Time((long) random.nextInt(millisInDay))))
                    .flightOffers(flightOffers)
                    .flightStops(flightStops)
                    .build();

            flightList.add(flightDto);
        }
        return flightList;
    }
}
