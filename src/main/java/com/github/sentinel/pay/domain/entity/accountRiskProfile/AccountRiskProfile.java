package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class AccountRiskProfile {
    private RiskProfileId riskProfileId;
    private ClientAccountId clientAccountId;
     private AccountId accountId;
    private RiskLevel riskLevel;
    //soon to evolve into IncidentStats
    private IncidentStatistics incidents;
     private LocationProfile locationProfile;
    private MonetaryProfile monetaryProfile;
    private CurrencyProfile currencyProfile;
    private VelocityProfile velocityProfile;

    private int averageRiskScore;
    private int riskScoreSamples;
    private Instant lastUpdated;


    public static RiskProfileId generateRiskProfileId(){
        return new RiskProfileId(UUID.randomUUID());
    }

    public static AccountRiskProfile initial(ClientAccountId clientAccountId, RiskProfileId riskProfileId, AccountId accountId) {

        return new AccountRiskProfile(
                riskProfileId,
                clientAccountId,
                accountId,
                RiskLevel.LOW,
                IncidentStatistics.initial(),
                LocationProfile.initial(),
                MonetaryProfile.initial(BigDecimal.valueOf(0)),
                CurrencyProfile.initial(),
                VelocityProfile.initial(),
                0, 
                0,
                Instant.now());
    }

    public void registerTransactionData(Transaction tx){
      this.locationProfile.observe(tx.getLocation());
      this.monetaryProfile.observe(tx.getMoney().amount());
      this.currencyProfile.observe(tx.getMoney().currency());
      this.velocityProfile.observe(tx.getTimestamp());

    }

    public void calculateAverageRiskScore(RiskScore riskScore) {
    this.averageRiskScore = this.averageRiskScore + 
    (riskScore.value() - this.averageRiskScore) / (this.riskScoreSamples+1);
    this.riskScoreSamples += 1;
    this.lastUpdated=Instant.now();
  
    }

    public void updateRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
        this.lastUpdated=Instant.now();
    
    }

    public void registerIncident(FraudIncidentStatus status, Instant openedAt) {
     this.incidents.AddIncidentStatus(status, openedAt);
     this.lastUpdated=Instant.now();
    }

}
