package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.*;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;

import java.util.List;

public interface RiskProfileRepository {
     List<AccountRiskProfile> findAllOrderByRiskScoreDesc();

    AccountRiskProfile update(AccountRiskProfile accountRiskProfile);

    int findByHighAndRestrictedAccountCount(ClientAccountId clientAccountId);

    List<AccountRiskProfile> findLastFiveRiskProfileAccounts(ClientAccountId clientAccountId);

    java.util.Optional<AccountRiskProfile> findByAccountId(AccountId accountId);

    AccountRiskProfile save(AccountRiskProfile riskProfile);
}