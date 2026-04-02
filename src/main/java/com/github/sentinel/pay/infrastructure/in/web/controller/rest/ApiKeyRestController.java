package com.github.sentinel.pay.infrastructure.in.web.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class ApiKeyRestController {
    @GetMapping("/setting/keys/")
    public String apiKeyPage(Model model){

    return "key-page";
    }

    @PostMapping("/client-accounts/{id}/api-keys")
    public String newKey(@PathVariable("id")UUID clientAccountId){
       return "k";}
}
