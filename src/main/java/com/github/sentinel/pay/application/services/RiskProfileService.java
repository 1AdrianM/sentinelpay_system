package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.shared.AccountId;

public interface  RiskProfileService {
    AccountRiskProfile findOrCreate(AccountId accountId);
    AccountRiskProfile  update(AccountRiskProfile accountRiskProfile);
}
