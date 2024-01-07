package edu.snhu.airbook.controllers;

import edu.snhu.airbook.AirBookApplication;
import edu.snhu.airbook.config.SecurityConfig;
import edu.snhu.airbook.config.WebFluxConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
@ContextConfiguration(classes={SecurityConfig.class, WebFluxConfig.class, AirBookApplication.class})
public class WebControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void showHomeTest1() throws Exception {
        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("welcomeMessage", "Welcome to AirBook! We will take you to places."));
    }

    @Test
    public void showHomeTest2() throws Exception {
        mockMvc
                .perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void showHomeTest3() throws Exception {
        mockMvc
                .perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
