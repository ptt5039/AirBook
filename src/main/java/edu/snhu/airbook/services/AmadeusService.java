package edu.snhu.airbook.services;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOfferSearch.Itinerary;
import com.amadeus.resources.FlightOfferSearch.SearchSegment;
import edu.snhu.airbook.config.AmadeusApiProperties;
import edu.snhu.airbook.dto.*;
import edu.snhu.airbook.utils.DateComparator;
import edu.snhu.airbook.utils.DateTimeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Amadeus Service.
 * <a href="https://developers.amadeus.com/get-started/get-started-with-self-service-apis-335">Learn more</a>
 *
 * @author phongtran
 */
@Service
@Slf4j
public class AmadeusService {
    @Autowired
    private AmadeusApiProperties amadeusApiProperties;
    @Autowired
    private AirportService airportService;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Get flight offers from Amadeus API service.
     *
     * @param flightSearchCriteria flight search criteria
     * @return Map of Itinerary DTO list with depart and return flight data
     */
    public Map<String, List<ItineraryDto>> getFlightOffers(FlightSearchCriteria flightSearchCriteria) {
        //Amadeus object build using api key and secret code
        Amadeus amadeus = Amadeus.builder(amadeusApiProperties.getApiKey(), amadeusApiProperties.getApiSecret()).build();

        Map<String, List<ItineraryDto>> map = new HashMap<>();
        List<ItineraryDto> departItineraries;
        List<ItineraryDto> returnItineraries;
        FlightOfferSearch[] flightOfferSearches;

        try {
            //round trip
            if (flightSearchCriteria.isRoundTrip()) {
                //get flights from amadeus api
                flightOfferSearches = amadeus.shopping.flightOffersSearch.get(Params
                        .with("originLocationCode", flightSearchCriteria.getDepartureAirport())
                        .and("destinationLocationCode", flightSearchCriteria.getArrivalAirport())
                        .and("departureDate", dateFormat.format(flightSearchCriteria.getDepartDate()))
                        .and("returnDate", dateFormat.format(flightSearchCriteria.getReturnDate()))
                        .and("adults", flightSearchCriteria.getPassengerNumber())
                        .and("currencyCode", "USD")
                        .and("max", 20));

                // map depart flights from api call result
                departItineraries = new ArrayList<>(Arrays.stream(flightOfferSearches).map(offer -> {
                    ItineraryDto data = mapFlightOfferSearchToItinerary(offer.getItineraries()[0]);
                    data.setFlightOffer(mapFlightOfferSearchToFlightOffer(offer));
                    data.setItineraryId("departure" + offer.getId());
                    data.setDepartDateTime(LocalDateTime.parse(offer.getItineraries()[0].getSegments()[0].getDeparture().getAt()));
                    return data;
                }).toList());

                // map return flights from api call result
                returnItineraries = new ArrayList<>(Arrays.stream(flightOfferSearches).map(offer -> {
                    ItineraryDto data = mapFlightOfferSearchToItinerary(offer.getItineraries()[1]);
                    data.setFlightOffer(mapFlightOfferSearchToFlightOffer(offer));
                    data.setItineraryId("return" + offer.getId());
                    data.setDepartDateTime(LocalDateTime.parse(offer.getItineraries()[1].getSegments()[0].getDeparture().getAt()));
                    return data;
                }).toList());

                //sort the lists based on depart date time
                departItineraries.sort(new DateComparator());
                returnItineraries.sort(new DateComparator());

                map.put("departItineraries", departItineraries);
                map.put("returnItineraries", returnItineraries);
            } else { //one way
                //get one way flights from amadeus api
                flightOfferSearches = amadeus.shopping.flightOffersSearch.get(Params
                        .with("originLocationCode", flightSearchCriteria.getDepartureAirport())
                        .and("destinationLocationCode", flightSearchCriteria.getArrivalAirport())
                        .and("departureDate", dateFormat.format(flightSearchCriteria.getDepartDate()))
                        .and("adults", flightSearchCriteria.getPassengerNumber())
                        .and("currencyCode", "USD")
                        .and("max", 20));

                // map depart flights from api call result
                departItineraries = new ArrayList<>(Arrays.stream(flightOfferSearches).map(offer -> {
                    ItineraryDto data = mapFlightOfferSearchToItinerary(offer.getItineraries()[0]);
                    data.setFlightOffer(mapFlightOfferSearchToFlightOffer(offer));
                    data.setItineraryId("departure" + offer.getId());
                    data.setDepartDateTime(LocalDateTime.parse(offer.getItineraries()[0].getSegments()[0].getDeparture().getAt()));
                    return data;
                }).toList());

                //sort the depart flight list based on depart date time
                departItineraries.sort(new DateComparator());
                map.put("departItineraries", departItineraries);
            }
            return map;
        } catch (ResponseException e) {
            log.error("Unable to retrieve flight offers");
        }
        return null;
    }

    /**
     * map Amadeus flight offer search itinerary to Itinerary DTO.
     *
     * @param itinerary Amadeus Itinerary
     * @return Itinerary DTO
     */
    public ItineraryDto mapFlightOfferSearchToItinerary(Itinerary itinerary) {
        List<SearchSegment> flightSegments = Arrays.asList(itinerary.getSegments());
        List<FlightDto> flights = flightSegments.stream().map(this::mapSearchSegmentToFlight).toList();
        List<FlightStopDto> flightStops = new ArrayList<>();
        for (int i = 0; i < flightSegments.size(); i++) {
            if (i != flightSegments.size() - 1) {
                flightStops.add(mapSearchSegmentToFlightStop(flightSegments.get(i), i + 1));
            }
        }
        return ItineraryDto
                .builder()
                .duration(DateTimeConverter.converterAmadeusDuration(itinerary.getDuration()))
                .flights(flights)
                .flightStops(flightStops)
                .build();
    }

    /**
     * map Amadeus search segment to FlightStop DTO.
     *
     * @param segment   Amadeus SearchSegment
     * @param stopOrder stop order number
     * @return FlightStop DTO
     */
    public FlightStopDto mapSearchSegmentToFlightStop(SearchSegment segment, int stopOrder) {
        return FlightStopDto
                .builder()
                .orderNumber(stopOrder)
                .airport(airportService.getAirportByIata(segment.getArrival().getIataCode()))
                .build();
    }

    /**
     * Map Amadeus search segment to Flight DTO.
     *
     * @param segment Amadeus SearchSegment
     * @return Flight DTO
     */
    public FlightDto mapSearchSegmentToFlight(SearchSegment segment) {
        return FlightDto
                .builder()
                .flightId(Integer.parseInt(segment.getId()))
                .flightNumber(segment.getCarrierCode() + "-" + segment.getNumber())
                .flightDuration(DateTimeConverter.converterAmadeusDuration(segment.getDuration()))
                .departureAirport(airportService.getAirportByIata(segment.getDeparture().getIataCode()))
                .departureDate(DateTimeConverter.convertDateTimeStringToDate(segment.getDeparture().getAt()))
                .departureTime(DateTimeConverter.convertDateTimeStringToTimeString(segment.getDeparture().getAt()))
                .arrivalAirport(airportService.getAirportByIata(segment.getArrival().getIataCode()))
                .arrivalDate(DateTimeConverter.convertDateTimeStringToDate(segment.getArrival().getAt()))
                .arrivalTime(DateTimeConverter.convertDateTimeStringToTimeString(segment.getArrival().getAt()))
                .build();
    }

    /**
     * Map Amadeus Flight Offer Search to FlightOffer DTO.
     *
     * @param offer Amadeus FlightOfferSearch
     * @return FlightOffer DTO
     */
    public FlightOfferDto mapFlightOfferSearchToFlightOffer(FlightOfferSearch offer) {
        return FlightOfferDto
                .builder()
                .availableSeat(offer.getNumberOfBookableSeats())
                .cost(offer.getPrice().getGrandTotal())
                .build();
    }
}
