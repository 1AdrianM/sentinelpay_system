package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.IncidentStatistics;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.CurrencyProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.IncidentStatisticsEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.LocationProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.MonetaryProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.VelocityProfileEntity;

@Entity
@Table(name = "risk_profile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRiskProfileEntity {

    @Id private UUID id;
    private UUID accountId; //extermal id for account that made the transaction

    private UUID clientAccountId;
    // domain record transactionActivity equivalent;
      //domain record usualTransactionLocation;
      private String riskLevel;
      private Instant lastUpdated;
      private int averageRiskScore;
      private int riskScoreSamples;


      @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
      @JoinColumn(name = "incident_statistics_id")
      private IncidentStatisticsEntity IncidentStatistics;

      @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
      @JoinColumn(name = "currency_profile_id")
      private CurrencyProfileEntity currencyProfileEntity;
      @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
      @JoinColumn(name = "location_profile_id")
      private LocationProfileEntity locationProfileEntity;
      @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
      @JoinColumn(name = "velocity_profile_id")
      private VelocityProfileEntity velocityProfileEntity;
      @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
      @JoinColumn(name = "monetary_profile_id")
      private MonetaryProfileEntity monetaryProfileEntity;

}
