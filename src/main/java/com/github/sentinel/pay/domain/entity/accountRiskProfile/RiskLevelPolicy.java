package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import org.springframework.stereotype.Component;

@Component
public class RiskLevelPolicy {

            public RiskLevel evaluate(AccountRiskProfile profile) {
                long confirmedFrauds=0;
                long underReview=0;

                      if(profile.getIncidents()!=null){
                          confirmedFrauds = profile.getIncidents().status()
                                  .stream()
                                  .filter(i -> i == FraudIncidentStatus.CONFIRMED_FRAUD)
                                  .count();

                          underReview = profile.getIncidents().status()
                                  .stream()
                                  .filter(i -> i == FraudIncidentStatus.UNDER_REVIEW)
                                  .count();
                      }

                double avgScore=0;
                if(profile.getAverageRiskScore()!=0) {
                    avgScore = profile.getAverageRiskScore();
                }


                if (confirmedFrauds >= 2) {
                    return RiskLevel.RESTRICTED;
                }

                if (confirmedFrauds == 1 || underReview >= 3 || avgScore > 75) {
                    return RiskLevel.HIGH;
                }

                if (avgScore > 40) {
                    return RiskLevel.MEDIUM;
                }

                return RiskLevel.LOW;
            }
}



