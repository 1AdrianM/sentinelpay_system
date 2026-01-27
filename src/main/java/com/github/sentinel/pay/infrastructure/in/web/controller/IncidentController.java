package com.github.sentinel.pay.infrastructure.in.web.controller;

import com.github.sentinel.pay.application.services.*;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class IncidentController {
  private final  GetIncidentDetailsByIncidentIdUseCase getIncidentDetailsByIncidentIdUseCase;
  private final  ListAllFraudIncidentsByTenantUseCase listAllFraudIncidentsByTenantUseCase;
    ListFraudIncidentsByIncidentIdUseCase listFraudIncidentsByIncidentIdUseCase;
    ListFraudIncidentsByAccountUseCase listFraudIncidentsByAccountUseCase;
    OpenFraudIncidentUseCase openFraudIncidentUseCase;
    ResolveFraudIncidentUseCase resolveFraudIncidentUseCase;

    public IncidentController(GetIncidentDetailsByIncidentIdUseCase getIncidentDetailsByIncidentIdUseCase, ListAllFraudIncidentsByTenantUseCase listAllFraudIncidentsByTenantUseCase) {
        this.getIncidentDetailsByIncidentIdUseCase = getIncidentDetailsByIncidentIdUseCase;
        this.listAllFraudIncidentsByTenantUseCase = listAllFraudIncidentsByTenantUseCase;
    }

    //second Easiest to make work, usecase Already built
    //just need to adapt DTO
    @GetMapping("/incidents")
    public String listIncidents(Model model) {
       var incidentsDto= listAllFraudIncidentsByTenantUseCase.execute();
        model.addAttribute("incidents", incidentsDto);
        return "incidents";
    }
//Incident Details needs a UseCase
    @GetMapping("/incidents/{id}")
    public String getIncidentDetails(@PathVariable UUID id, Model model) {
        // Mock data will be added here later
      var incidentDto=  getIncidentDetailsByIncidentIdUseCase.execute(new FraudIncidentId(id));
        model.addAttribute("incident", incidentDto);
        return "incident-detail";
    }
    @PatchMapping("incidents/transaction/{id}/open")
    public String openIncident(@PathVariable UUID id, Model model){
        return "incident-details";
    }
    @PatchMapping("incidents/transaction/{id}/resolve")
    public String resolveIncident(@PathVariable UUID id, Model model){
                resolveFraudIncidentUseCase.resolveFraudIncident(new TransactionId(id),
                FraudIncidentStatus.RESOLVED.name());
        return "incident-details";
    }
}
