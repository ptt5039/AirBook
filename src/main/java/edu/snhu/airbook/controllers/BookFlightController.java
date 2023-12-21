package edu.snhu.airbook.controllers;

import edu.snhu.airbook.dto.AirportDto;
import edu.snhu.airbook.dto.FlightDto;
import edu.snhu.airbook.dto.FlightSearchCriteria;
import edu.snhu.airbook.services.AirportService;
import edu.snhu.airbook.services.FlightService;
import edu.snhu.airbook.utils.DateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
import java.util.List;

@Controller
public class BookFlightController {
    @Autowired
    AirportService airportService;
    @Autowired
    FlightService flightService;

    /**
     * Book Flight
     * @param model Spring Model
     * @return String
     */
    @GetMapping("/bookFlight")
    public String bookFlight(Model model) {
        List<AirportDto> airports = airportService.getAllAirports();
        FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
        model.addAttribute("airports", airports);
        model.addAttribute("flightSearch", flightSearchCriteria);
        return "book-flight";
    }

    /**
     * Search for flights
     * @param flightSearchCriteria flight search criteria
     * @param model Spring model
     * @return String
     */
    @GetMapping("/bookFlight/search")
    public String flightList(@ModelAttribute("flightSearch") FlightSearchCriteria flightSearchCriteria, Model model) {
        model.addAttribute("departDate", DateTimeConverter.convertDateToFormattedString(flightSearchCriteria.getDepartDate()));
        List<FlightDto> departFlights = flightService.searchFlights(flightSearchCriteria);
        List<FlightDto> returnFlights = null;
        if (flightSearchCriteria.isRoundTrip()) {
            model.addAttribute("returnDate", DateTimeConverter.convertDateToFormattedString(flightSearchCriteria.getReturnDate()));
            String departureAirport = flightSearchCriteria.getArrivalAirport();
            String arrivalAirport =  flightSearchCriteria.getDepartureAirport();
            Date departDate = flightSearchCriteria.getReturnDate();
            flightSearchCriteria.setDepartureAirport(departureAirport);
            flightSearchCriteria.setArrivalAirport(arrivalAirport);
            flightSearchCriteria.setDepartDate(departDate);
            returnFlights = flightService.searchFlights(flightSearchCriteria);;
        }
        FlightDto flight = departFlights.get(0);
        model.addAttribute("departureAirport", flight.getDepartureAirport());
        model.addAttribute("arrivalAirport", flight.getArrivalAirport());
        model.addAttribute("departFlights", departFlights);
        model.addAttribute("returnFlights", returnFlights);
        return "search-result";
    }
}
