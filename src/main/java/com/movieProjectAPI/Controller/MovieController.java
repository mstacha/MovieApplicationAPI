package com.movieProjectAPI.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MovieController {

    @GetMapping("/")
    public String getAllTutorials() {
        return "test";
    }
}
