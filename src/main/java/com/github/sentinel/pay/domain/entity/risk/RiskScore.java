package com.github.sentinel.pay.domain.entity.risk;

import java.util.List;

/**
 * @param value
  * Fraud Incident
 * Parameterized Score Based on Fraud Decision and Risk Contribution
 */
public record RiskScore(
        int value
     //   String description
) {

    //5 reglas Confirmadas
    //1 LOW  10
    //2/ 1/2 HIGH  120 + 60 potencial
    //1 1/2 MEDIUM  30
    //TOTAL 220 en total

    private static final int RESTRICTED_SCORE=200;
    private static final int HIGH_RISK_SCORE=180;
    private static final int MEDIUM_RISK_SCORE = 90;
    private static final int LOW_RISK_SCORE= 40;

    public static RiskScore from(List<RiskContribution> contributionScore) {
        return new RiskScore(contributionScore.stream().mapToInt(RiskContribution::getScore).sum());
    }
    public boolean isRestrictedScore(){
        return this.value > RESTRICTED_SCORE ;
    }
    public boolean isHighRisk(){
    return this.value >= HIGH_RISK_SCORE  &&  this.value < RESTRICTED_SCORE ;
}
public boolean isMediumRisk(){
    return this.value >= MEDIUM_RISK_SCORE && this.value < HIGH_RISK_SCORE;

}
public boolean isLowRisk(){
    return this.value >= LOW_RISK_SCORE && this.value < MEDIUM_RISK_SCORE;

}
}
