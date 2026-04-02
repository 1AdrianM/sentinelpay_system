package com.github.sentinel.pay.application.implementation.interactors;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.dto.incidents.OpenIncidentRequestDto;
import com.github.sentinel.pay.application.services.FraudIncidentService;
import com.github.sentinel.pay.application.usecases.OpenFraudIncidentUseCase;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import com.github.sentinel.pay.domain.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OpenFraudIncidentInteractor implements OpenFraudIncidentUseCase{
     private final FraudIncidentService fraudIncidentService;
    private final TransactionRepository transactionRepository;
    private final FraudDecisionRepository fraudDecisionRepository;

    @Override
    public FraudIncident execute(OpenIncidentRequestDto incidentRequestDto) {
    
        if(incidentRequestDto.transactionId == null || incidentRequestDto.transactionId.toString().isBlank()){
            throw new IllegalArgumentException("transaction id is required");
        }
     if(incidentRequestDto.decisionId == null || incidentRequestDto.decisionId.toString().isBlank()){
            throw new IllegalArgumentException("decision id is required");
        }
     if(incidentRequestDto.riskScore < 0){
            throw new IllegalArgumentException("risk score must be non-negative");
        }
    TransactionId id=  TransactionId.of(incidentRequestDto.transactionId);
     
      Transaction tx= transactionRepository.findById(id.id()).orElseThrow(() -> new EntityNotFoundException("tx is null"));
         
       FraudDecision decision= fraudDecisionRepository.findById(incidentRequestDto.decisionId).orElseThrow(() -> new EntityNotFoundException("decision not found")) ;
      
      return  fraudIncidentService.open(decision, tx);
    }
    
}
