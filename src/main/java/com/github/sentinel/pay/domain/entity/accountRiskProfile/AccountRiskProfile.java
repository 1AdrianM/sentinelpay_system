package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
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
                null,
                LocationProfile.empty(),
                MonetaryProfile.initial(BigDecimal.valueOf(0)),
                CurrencyProfile.empty(),
                VelocityProfile.initial(),
                0, Instant.now());
    }

    public void registerTransactionData(Transaction tx){


    }

    public void updateRiskLevel(RiskLevel riskLevel) {
        this.riskLevel=riskLevel;
    }

    public void registerIncidentSummary(IncidentStatistics summary) {
        this.incidents=summary;
    }

    public void hidrateProfile(LocationProfile locationProfile, MonetaryProfile monetaryProfile, VelocityProfile velocityProfile, CurrencyProfile currencyProfile) {
    this.locationProfile=locationProfile;
    this.monetaryProfile=monetaryProfile;
    this.velocityProfile=velocityProfile;
    this.currencyProfile=currencyProfile;
    }
}
