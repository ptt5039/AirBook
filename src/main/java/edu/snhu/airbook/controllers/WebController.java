package edu.snhu.airbook.controllers;

import edu.snhu.airbook.dto.*;
import edu.snhu.airbook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    @Autowired
    UserService userService;

    /**
     * Home
     * @param model model
     * @return
     */
    @GetMapping(value = {"", "/", "/home"})
    public String home(Model model) {
        model.addAttribute("welcomeMessage", "Welcome to AirBook! We will take you to places.");
        return "home";
    }

    /**
     * Login
     * @param credential
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestBody Credential credential, Model model) {
        UserDto user = userService.authentication(credential);
        model.addAttribute("user", user);
        return "home";
    }
}
