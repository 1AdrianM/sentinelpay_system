package com.github.sentinel.pay.infrastructure.in.web.controller;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDetailsDto;
import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.services.GetDecisionDetailByDecisionIdUseCase;
import com.github.sentinel.pay.application.services.GetDecisionsByClientAccountIdUseCase;
import com.github.sentinel.pay.application.services.UpdateDecisionUseCase;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DecisionController {
   private final GetDecisionsByClientAccountIdUseCase getDecisionByClientAccountIdUseCase;
   private final GetDecisionDetailByDecisionIdUseCase getDecisionDetailByDecisionIdUseCase;
   private final UpdateDecisionUseCase updateDecisionUseCase;

    @GetMapping("/decisions")
    public String getDecisions(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
Model model){
      List<FraudDecisionDto> decisionDtoList= getDecisionByClientAccountIdUseCase.execute(PageRequest.of(page,size));
        model.addAttribute("decisions",decisionDtoList);
        model.addAttribute("currentPage", page);
        return "decisions";
    }
    @GetMapping("/decision/{id}")
    public String getDecisionDetails(@PathVariable("id")UUID decisionId, Model model){
        FraudDecisionDetailsDto decisionDto= getDecisionDetailByDecisionIdUseCase.execute(new FraudDecisionId(decisionId));
        model.addAttribute("decision",decisionDto);
        return "decision-details";
    }
    @PatchMapping("/decision/{id}")
    public String updateDecision(@PathVariable("id")UUID decisionId,
                                 @ModelAttribute("decision") FraudDecisionDto fraudDecisionDto,
                                 Model model){
       updateDecisionUseCase.execute(new FraudDecisionId(decisionId), fraudDecisionDto);
      return "";
    }
}

