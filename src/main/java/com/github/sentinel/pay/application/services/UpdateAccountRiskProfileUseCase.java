package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;

public interface UpdateAccountRiskProfileUseCase {

  AccountRiskProfile  updateRiskProfile(AccountRiskProfile accountRiskProfile);
}
