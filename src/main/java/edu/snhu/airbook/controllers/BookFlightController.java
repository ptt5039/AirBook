package edu.snhu.airbook.controllers;

import com.amadeus.resources.FlightOfferSearch;
import edu.snhu.airbook.dto.AirportDto;
import edu.snhu.airbook.dto.FlightSearchCriteria;
import edu.snhu.airbook.dto.ItineraryDto;
import edu.snhu.airbook.services.AirportService;
import edu.snhu.airbook.services.AmadeusService;
import edu.snhu.airbook.utils.DateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("bookFlight")
public class BookFlightController {
    @Autowired
    AirportService airportService;
    @Autowired
    AmadeusService amadeusService;

    /**
     * Book Flight
     *
     * @param model Spring Model
     * @return String
     */
    @GetMapping
    public String bookFlight(Model model) {
        List<AirportDto> airports = airportService.getAllAirports();
        FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
        model.addAttribute("airports", airports);
        model.addAttribute("flightSearch", flightSearchCriteria);
        return "book-flight";
    }

    /**
     * Search for flights
     *
     * @param flightSearchCriteria flight search criteria
     * @param model                Spring model
     * @return String
     */
    @GetMapping("/search")
    public String flightList(@ModelAttribute("flightSearch") FlightSearchCriteria flightSearchCriteria, Model model) {
        model.addAttribute("departDate", DateTimeConverter.convertDateToFormattedString(flightSearchCriteria.getDepartDate()));
        Map<String, List<ItineraryDto>> flightMap = amadeusService.getFlightOffers(flightSearchCriteria);
        if (flightSearchCriteria.isRoundTrip()) {
            model.addAttribute("returnDate", DateTimeConverter.convertDateToFormattedString(flightSearchCriteria.getReturnDate()));
        }
        model.addAttribute("departureAirport", airportService.getAirportByIata(flightSearchCriteria.getDepartureAirport()));
        model.addAttribute("arrivalAirport", airportService.getAirportByIata(flightSearchCriteria.getArrivalAirport()));
        model.addAttribute("flightSearchCriteria", flightSearchCriteria);
        model.addAttribute("flightMap", flightMap);
        return "search-result";
    }
}
