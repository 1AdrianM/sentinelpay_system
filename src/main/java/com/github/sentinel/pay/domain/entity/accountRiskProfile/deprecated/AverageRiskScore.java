package com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated;

import java.util.List;

public record AverageRiskScore(
        int avgScore,
        List<Integer> scoreList
) {
    public double averageLastDays(int i) {
      return this.scoreList
              .stream()
              .skip(Math.max(0, this.scoreList.size()-i))
              .mapToInt(Integer::intValue)
              .average()
              .orElse(0.0);
    }
}
