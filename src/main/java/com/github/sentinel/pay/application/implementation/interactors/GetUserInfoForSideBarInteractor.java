package com.github.sentinel.pay.application.implementation.interactors;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.dto.user.MinimalUserInfoDto;
import com.github.sentinel.pay.application.usecases.GetUserInfoForSideBarUseCase;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.UserRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserInfoForSideBarInteractor implements GetUserInfoForSideBarUseCase{
     private final UserRepository userRepository;
     private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GetDashBoardDataInteractor.class);
    @Override
    public MinimalUserInfoDto execute() {
        logger.atInfo().log("retreving user info for sidebar");
       var context= TenantContextHolder.get();
       logger.atInfo().log("ClientAccountId from context tenat");
      var user= userRepository.findByClientAccountId(ClientAccountId.of(context.getClientAccountId()));
       logger.atInfo().log("User found with ID : {} ",user.getId());

        return new MinimalUserInfoDto(user.getName(),user.getRole().name()); 

    }
    
}
