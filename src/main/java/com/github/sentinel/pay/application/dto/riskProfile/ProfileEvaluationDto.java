package com.github.sentinel.pay.application.dto.riskProfile;

import lombok.Builder;

@Builder
public class ProfileEvaluationDto{
        public String profileName;
        public int riskScore;
       public String decision;
       public String rulesFired; }