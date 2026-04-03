package com.github.sentinel.pay.infrastructure.in.web.controller.rest;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/health") 
public class HeathRestController {
    private final Random random = new Random();

    public List<String> RANDOM_HEALTH_RESPONSES= List.of("WE'RE OK BRO","UP AND LIVING","ALIVE","HEALTHY, LOVING THE MACRO","GOOD TO GO","RUNNING SMOOTHLY","ALL SYSTEMS GO","FEELING FINE","WORKING PERFECTLY","IN GREAT SHAPE");

    @GetMapping()
    public String healthCheck() {
        
        return RANDOM_HEALTH_RESPONSES.get(random.nextInt(RANDOM_HEALTH_RESPONSES.size()));
    }



}
