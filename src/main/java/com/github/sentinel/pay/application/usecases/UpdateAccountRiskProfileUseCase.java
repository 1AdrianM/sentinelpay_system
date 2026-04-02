package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;

public interface UpdateAccountRiskProfileUseCase {
  void  execute(AccountRiskProfile accountRiskProfile);
}
