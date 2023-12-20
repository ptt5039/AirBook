package edu.snhu.airbook.controllers;

import edu.snhu.airbook.dto.AirportDto;
import edu.snhu.airbook.dto.Credential;
import edu.snhu.airbook.dto.UserDto;
import edu.snhu.airbook.services.AirportService;
import edu.snhu.airbook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    UserService userService;

    @Autowired
    AirportService airportService;

    @RequestMapping(value = {"", "/", "/home"})
    public String home(Model model) {
        UserDto user = userService.authentication(Credential.builder().email("phong.tran1@snhu.edu").password("123456").build());
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping("/bookFlight")
    public String bookFlight(Model model) {
        List<AirportDto> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        return "bookflight";
    }

    @RequestMapping("/login")
    public String login(@RequestBody Credential credential, Model model) {
        UserDto user = userService.authentication(credential);
        model.addAttribute("user", user);
        return "home";
    }
}
