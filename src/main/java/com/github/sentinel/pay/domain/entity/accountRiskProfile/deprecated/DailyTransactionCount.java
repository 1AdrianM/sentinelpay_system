package com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

public record DailyTransactionCount(
        Map<LocalDate,Integer> counts
) {
    public void register(Instant timestamp) {
        LocalDate day = timestamp.atZone(ZoneId.of("UTC")).toLocalDate();
        counts.merge(day, 1, Integer::sum);
    }

    public int countFor(LocalDate day) {
        return counts.getOrDefault(day, 0);
    }
}
