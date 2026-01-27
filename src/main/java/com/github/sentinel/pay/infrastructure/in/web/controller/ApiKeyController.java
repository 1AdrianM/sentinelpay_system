package com.github.sentinel.pay.infrastructure.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
public class ApiKeyController {
    @GetMapping("/setting/keys/")
    public String apiKeyPage(Model model){

    return "key-page";
    }

    @PostMapping("/api/client-accounts/{id}/api-keys")
    public String newKey(@PathVariable("id")UUID clientAccountId){
       return "k";}
}
