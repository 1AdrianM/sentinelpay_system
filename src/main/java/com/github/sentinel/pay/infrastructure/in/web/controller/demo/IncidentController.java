package com.github.sentinel.pay.infrastructure.in.web.controller.demo;

import com.github.sentinel.pay.application.dto.incidents.IncidentDTO;
import com.github.sentinel.pay.application.dto.incidents.OpenIncidentRequestDto;
import com.github.sentinel.pay.application.usecases.EscalateFraudIncidentUseCase;
import com.github.sentinel.pay.application.usecases.GetIncidentDetailsByIncidentIdUseCase;
import com.github.sentinel.pay.application.usecases.ListAllFraudIncidentsByTenantUseCase;
import com.github.sentinel.pay.application.usecases.OpenFraudIncidentUseCase;
import com.github.sentinel.pay.application.usecases.ResolveFraudIncidentUseCase;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class IncidentController {
    // ListFraudIncidentsByIncidentIdUseCase listFraudIncidentsByIncidentIdUseCase;
   // ListFraudIncidentsByAccountUseCase listFraudIncidentsByAccountUseCase;
  private final  GetIncidentDetailsByIncidentIdUseCase getIncidentDetailsByIncidentIdUseCase;
  private final  ListAllFraudIncidentsByTenantUseCase listAllFraudIncidentsByTenantUseCase;
   private final OpenFraudIncidentUseCase openFraudIncidentUseCase;
    private final ResolveFraudIncidentUseCase resolveFraudIncidentUseCase;
    private final EscalateFraudIncidentUseCase escalateFraudIncidentUseCase;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OpenIncidentRequestDto.class);
    public IncidentController(GetIncidentDetailsByIncidentIdUseCase getIncidentDetailsByIncidentIdUseCase, ListAllFraudIncidentsByTenantUseCase listAllFraudIncidentsByTenantUseCase, EscalateFraudIncidentUseCase escalateFraudIncidentUseCase, OpenFraudIncidentUseCase openFraudIncidentUseCase, ResolveFraudIncidentUseCase resolveFraudIncidentUseCase) {
        this.getIncidentDetailsByIncidentIdUseCase = getIncidentDetailsByIncidentIdUseCase;
        this.listAllFraudIncidentsByTenantUseCase = listAllFraudIncidentsByTenantUseCase;
        this.openFraudIncidentUseCase = openFraudIncidentUseCase;
        this.resolveFraudIncidentUseCase = resolveFraudIncidentUseCase;
        this.escalateFraudIncidentUseCase = escalateFraudIncidentUseCase;
    }

    //second Easiest to make work, usecase Already built
    //just need to adapt DTO
    @GetMapping("/incidents")
    public String listIncidents(Model model) {
       var incidentsDto= listAllFraudIncidentsByTenantUseCase.execute();
        model.addAttribute("incidents", incidentsDto);
        return "incidents";
    }
     @PostMapping("/incidents/open")
@ResponseBody
public String openIncident(@RequestParam("decisionId") UUID decisionId,
                          @RequestParam("transactionId") UUID transactionId,
                          @RequestParam("riskScore") int riskScore) {
    try {
        // Validación básica
        if (riskScore < 0 || riskScore > 100) {
            return """
                <div class="alert alert-danger border-danger mb-0" role="alert">
                    <div class="d-flex align-items-center">
                        <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
                        <div>
                            <strong>Validation Error!</strong> Risk score must be between 0 and 100.
                        </div>
                    </div>
                </div>
                """;
        }

         
       
        // El status se determina automáticamente en el servicio
        
        // Llamar al use case
        FraudIncident incident = openFraudIncidentUseCase.execute(
        OpenIncidentRequestDto.builder().decisionId(decisionId)
        .transactionId(transactionId)
        .riskScore(riskScore).build());
        
        return """
            <div class="alert alert-success border-success mb-0" role="alert">
                <div class="d-flex align-items-center">
                    <i class="bi bi-check-circle-fill me-2 fs-5"></i>
                    <div>
                        <strong>Success!</strong> Incident created successfully.
                        <div class="mt-2">
                            <a href="/incidents/%s" class="btn btn-sm btn-success">
                                <i class="bi bi-eye me-1"></i>View Incident
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                setTimeout(function() {
                    location.reload();
                }, 2000);
            </script>
            """.formatted(incident.getIncidentId().id());
            
    } catch (Exception e) {
        return """
            <div class="alert alert-danger border-danger mb-0" role="alert">
                <div class="d-flex align-items-center">
                    <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
                    <div>
                        <strong>Error!</strong> Failed to create incident: %s
                    </div>
                </div>
            </div>
            """.formatted(e.getMessage());
    }
}
    
//Incident Details needs a UseCase
    @GetMapping("/incidents/{id}")
    public String getIncidentDetails(@PathVariable UUID id, Model model) {
        logger.atInfo().log("getting incident by id");
      var incidentDto=  getIncidentDetailsByIncidentIdUseCase.execute(new FraudIncidentId(id));
      logger.atInfo().log("populating view with incident data, incident id: {}", incidentDto.incidentId);
        model.addAttribute("incidentDetails", incidentDto);
        return "incident-detail";
    }
    @PatchMapping("/incidents/{id}/escalate")
@ResponseBody
public String escalateIncident(@PathVariable("id") UUID id, 
                               @RequestParam("status") String status,
                               @RequestParam(value="riskScore", required=false) Integer riskScore,
                               Model model) {
    try {
        logger.atInfo().log("Escalating incident with id: {} to status: {}", id, status);
        
        escalateFraudIncidentUseCase.execute(FraudIncidentId.of(id), status);
        
        logger.atInfo().log("Incident escalated successfully");
        
        return """
            <div class="alert alert-success border-success mb-0" role="alert">
                <div class="d-flex align-items-center">
                    <i class="bi bi-check-circle-fill me-2 fs-5"></i>
                    <div>
                        <strong>Success!</strong> Incident escalated successfully to <strong>%s</strong>.
                    </div>
                </div>
            </div>
            <script>
                setTimeout(function() {
                    location.reload();
                }, 2000);
            </script>
            """.formatted(status);
            
    } catch (Exception e) {
        logger.atError().log("Error escalating incident: {}", e.getMessage());
        return """
            <div class="alert alert-danger border-danger mb-0" role="alert">
                <div class="d-flex align-items-center">
                    <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
                    <div>
                        <strong>Error!</strong> Failed to escalate incident: %s
                    </div>
                </div>
            </div>
            """.formatted(e.getMessage());
    }
}

    @PatchMapping("incidents/transaction/{id}/resolve")
    public String resolveIncident(@PathVariable UUID id, Model model){
                resolveFraudIncidentUseCase.execute(TransactionId.of(id),
                FraudIncidentStatus.RESOLVED.name());
        return "incident-detail";
    }
}
