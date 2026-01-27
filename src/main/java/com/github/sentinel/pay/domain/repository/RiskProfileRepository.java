package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.*;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;

import java.util.List;

public interface RiskProfileRepository {
     List<AccountRiskProfile> findAllOrderByRiskScoreDesc();

    AccountRiskProfile findOrCreateByAccountId(AccountId accountId);
    AccountRiskProfile update(AccountRiskProfile accountRiskProfile);
   CurrencyProfile findCurrencyProfileByRiskProfileId(RiskProfileId id);

  VelocityProfile findVelocityProfileByRiskProfileId(RiskProfileId id);
   MonetaryProfile findMonetaryProfileByRiskProfileId(RiskProfileId id);
   LocationProfile findLocationProfileByRiskProfileId(RiskProfileId id);


    int findByHighAndRestrictedAccountCount(ClientAccountId clientAccountId);

    List<AccountRiskProfile> findLastFiveRiskProfileAccounts(ClientAccountId clientAccountId);

    AccountRiskProfile findByAccountId(AccountId accountId);
}