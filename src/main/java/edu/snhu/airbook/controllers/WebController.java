package edu.snhu.airbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Web Controller
 *
 * @author phongtran
 */
@Controller
public class WebController {

    /**
     * Home
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = {"", "/", "/home"})
    public String home(Model model) {
        model.addAttribute("welcomeMessage", "Welcome to AirBook! We will take you to places.");
        return "home";
    }
}
