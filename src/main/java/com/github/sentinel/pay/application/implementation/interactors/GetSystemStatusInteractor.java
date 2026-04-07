package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.dashboard.SystemStatusDto;
import com.github.sentinel.pay.application.usecases.GetSystemStatusUseCase;
import com.github.sentinel.pay.domain.entity.fraudRules.FraudRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSystemStatusInteractor implements GetSystemStatusUseCase {
    private final List<FraudRule> fraudRules;

    @Override
    public SystemStatusDto execute() {
        return SystemStatusDto.builder()
                .status("ONLINE")
                .version("1.2.0")
                .uptimeSeconds(ManagementFactory.getRuntimeMXBean().getUptime() / 1000)
                .activeRules(fraudRules.size())
                .build();
    }
}
