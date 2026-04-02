package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetDecisionsByClientAccountIdUseCase {
    List<FraudDecisionDto> execute(Pageable pageable);
}
