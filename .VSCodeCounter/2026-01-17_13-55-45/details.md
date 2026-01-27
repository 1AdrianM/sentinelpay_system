# Details

Date : 2026-01-17 13:55:45

Directory c:\\Users\\Administrator\\Desktop\\Projects\\JAVA\\sentinel.pay\\sentinel.pay

Total : 136 files,  3489 codes, 133 comments, 989 blanks, all 4611 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [Frontend.md](/Frontend.md) | Markdown | 134 | 0 | 126 | 260 |
| [README.md](/README.md) | Markdown | 44 | 0 | 28 | 72 |
| [build.gradle](/build.gradle) | Gradle | 39 | 1 | 7 | 47 |
| [frontend-improvements.md](/frontend-improvements.md) | Markdown | 71 | 0 | 68 | 139 |
| [gradle/wrapper/gradle-wrapper.properties](/gradle/wrapper/gradle-wrapper.properties) | Java Properties | 7 | 0 | 1 | 8 |
| [gradlew.bat](/gradlew.bat) | Batch | 41 | 32 | 22 | 95 |
| [settings.gradle](/settings.gradle) | Gradle | 1 | 0 | 1 | 2 |
| [src/main/java/com/github/sentinel/pay/Application.java](/src/main/java/com/github/sentinel/pay/Application.java) | Java | 9 | 0 | 5 | 14 |
| [src/main/java/com/github/sentinel/pay/application/dto/dashboard/DashBoardDto.java](/src/main/java/com/github/sentinel/pay/application/dto/dashboard/DashBoardDto.java) | Java | 15 | 0 | 4 | 19 |
| [src/main/java/com/github/sentinel/pay/application/dto/decision/FraudDecisionDto.java](/src/main/java/com/github/sentinel/pay/application/dto/decision/FraudDecisionDto.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/dto/incidents/IncidentResponseDto.java](/src/main/java/com/github/sentinel/pay/application/dto/incidents/IncidentResponseDto.java) | Java | 19 | 0 | 3 | 22 |
| [src/main/java/com/github/sentinel/pay/application/dto/incidents/incidentRequestDto.java](/src/main/java/com/github/sentinel/pay/application/dto/incidents/incidentRequestDto.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/dto/riskProfile/RiskProfileDto.java](/src/main/java/com/github/sentinel/pay/application/dto/riskProfile/RiskProfileDto.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/dto/transaction/TransactionRequestDto.java](/src/main/java/com/github/sentinel/pay/application/dto/transaction/TransactionRequestDto.java) | Java | 19 | 0 | 6 | 25 |
| [src/main/java/com/github/sentinel/pay/application/exceptions/TransactionAmountBelowZeroException.java](/src/main/java/com/github/sentinel/pay/application/exceptions/TransactionAmountBelowZeroException.java) | Java | 6 | 0 | 3 | 9 |
| [src/main/java/com/github/sentinel/pay/application/implementation/EvaluateTransactionForFraudInteractor.java](/src/main/java/com/github/sentinel/pay/application/implementation/EvaluateTransactionForFraudInteractor.java) | Java | 65 | 13 | 11 | 89 |
| [src/main/java/com/github/sentinel/pay/application/implementation/ListFraudIncidentsByAccountInteractor.java](/src/main/java/com/github/sentinel/pay/application/implementation/ListFraudIncidentsByAccountInteractor.java) | Java | 13 | 0 | 3 | 16 |
| [src/main/java/com/github/sentinel/pay/application/implementation/OpenFraudIncidentInteractor.java](/src/main/java/com/github/sentinel/pay/application/implementation/OpenFraudIncidentInteractor.java) | Java | 30 | 0 | 8 | 38 |
| [src/main/java/com/github/sentinel/pay/application/implementation/ResolveFraudIncidentInteractor.java](/src/main/java/com/github/sentinel/pay/application/implementation/ResolveFraudIncidentInteractor.java) | Java | 40 | 2 | 10 | 52 |
| [src/main/java/com/github/sentinel/pay/application/implementation/UpdateAccountRiskProfileInteractor.java](/src/main/java/com/github/sentinel/pay/application/implementation/UpdateAccountRiskProfileInteractor.java) | Java | 24 | 3 | 6 | 33 |
| [src/main/java/com/github/sentinel/pay/application/services/EvaluateTransactionForFraudUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/EvaluateTransactionForFraudUseCase.java) | Java | 8 | 0 | 4 | 12 |
| [src/main/java/com/github/sentinel/pay/application/services/GetDashBoardDataUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/GetDashBoardDataUseCase.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/services/GetFraudIncidentByIdUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/GetFraudIncidentByIdUseCase.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/services/GetIncidentDetailsByIncidentIdUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/GetIncidentDetailsByIncidentIdUseCase.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/services/GetRiskProfileByAccountIdUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/GetRiskProfileByAccountIdUseCase.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/services/ListAllFraudIncidentsUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/ListAllFraudIncidentsUseCase.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/services/ListFraudIncidentsByAccountUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/ListFraudIncidentsByAccountUseCase.java) | Java | 7 | 0 | 4 | 11 |
| [src/main/java/com/github/sentinel/pay/application/services/ListFraudIncidentsByIncidentIdUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/ListFraudIncidentsByIncidentIdUseCase.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/application/services/OpenFraudIncidentUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/OpenFraudIncidentUseCase.java) | Java | 6 | 0 | 3 | 9 |
| [src/main/java/com/github/sentinel/pay/application/services/ResolveFraudIncidentUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/ResolveFraudIncidentUseCase.java) | Java | 9 | 0 | 4 | 13 |
| [src/main/java/com/github/sentinel/pay/application/services/UpdateAccountRiskProfileUseCase.java](/src/main/java/com/github/sentinel/pay/application/services/UpdateAccountRiskProfileUseCase.java) | Java | 7 | 0 | 4 | 11 |
| [src/main/java/com/github/sentinel/pay/domain/README.md](/src/main/java/com/github/sentinel/pay/domain/README.md) | Markdown | 17 | 0 | 10 | 27 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/AccountRiskProfile.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/AccountRiskProfile.java) | Java | 55 | 1 | 13 | 69 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/CurrencyProfile.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/CurrencyProfile.java) | Java | 50 | 0 | 9 | 59 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/IncidentStatistics.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/IncidentStatistics.java) | Java | 9 | 0 | 4 | 13 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/LocationProfile.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/LocationProfile.java) | Java | 42 | 0 | 12 | 54 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/MonetaryProfile.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/MonetaryProfile.java) | Java | 53 | 1 | 9 | 63 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/RiskLevelPolicy.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/RiskLevelPolicy.java) | Java | 28 | 0 | 14 | 42 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/RiskProfileId.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/RiskProfileId.java) | Java | 6 | 0 | 3 | 9 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/VelocityProfile.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/VelocityProfile.java) | Java | 43 | 0 | 10 | 53 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/deprecated/AverageAmount.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/deprecated/AverageAmount.java) | Java | 19 | 0 | 5 | 24 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/deprecated/AverageCurrencyTransaction.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/deprecated/AverageCurrencyTransaction.java) | Java | 19 | 0 | 7 | 26 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/deprecated/AverageRiskScore.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/deprecated/AverageRiskScore.java) | Java | 15 | 0 | 3 | 18 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/deprecated/DailyTransactionCount.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/deprecated/DailyTransactionCount.java) | Java | 16 | 0 | 4 | 20 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/deprecated/TransactionActivity.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/deprecated/TransactionActivity.java) | Java | 34 | 0 | 9 | 43 |
| [src/main/java/com/github/sentinel/pay/domain/entity/AccountRiskProfileAggregate/deprecated/UsualTransactionLocation.java](/src/main/java/com/github/sentinel/pay/domain/entity/accountRiskProfile/deprecated/UsualTransactionLocation.java) | Java | 22 | 0 | 4 | 26 |
| [src/main/java/com/github/sentinel/pay/domain/entity/FraudIncidentAggregate/FraudIncident.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudIncident/FraudIncident.java) | Java | 36 | 0 | 7 | 43 |
| [src/main/java/com/github/sentinel/pay/domain/entity/FraudIncidentAggregate/FraudIncidentId.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudIncident/FraudIncidentId.java) | Java | 6 | 0 | 3 | 9 |
| [src/main/java/com/github/sentinel/pay/domain/entity/FraudIncidentAggregate/FraudIncidentStatus.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudIncident/FraudIncidentStatus.java) | Java | 8 | 0 | 2 | 10 |
| [src/main/java/com/github/sentinel/pay/domain/entity/FraudIncidentAggregate/FraudIncidentStatusPolicy.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudIncident/FraudIncidentStatusPolicy.java) | Java | 12 | 0 | 3 | 15 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudDecision/FraudDecision.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudDecision/FraudDecision.java) | Java | 25 | 0 | 6 | 31 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudDecision/FraudDecisionId.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudDecision/FraudDecisionId.java) | Java | 6 | 0 | 3 | 9 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudDecision/FraudDecisionPolicy.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudDecision/FraudDecisionPolicy.java) | Java | 13 | 0 | 4 | 17 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudDecision/FraudDecisionType.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudDecision/FraudDecisionType.java) | Java | 6 | 0 | 2 | 8 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/AccessOutsideOfOpeningHoursFraudRule.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/AccessOutsideOfOpeningHoursFraudRule.java) | Java | 10 | 4 | 7 | 21 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/FraudRule.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/FraudRule.java) | Java | 7 | 0 | 3 | 10 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/HighRiskAndRemoteTransactionFraudRule.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/HighRiskAndRemoteTransactionFraudRule.java) | Java | 13 | 0 | 3 | 16 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/TransactionAmountFraudRule.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/TransactionAmountFraudRule.java) | Java | 14 | 9 | 6 | 29 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/TransactionVelocityFraudRule.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/TransactionVelocityFraudRule.java) | Java | 17 | 5 | 6 | 28 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/UnusualCurrencyFraudRule.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/UnusualCurrencyFraudRule.java) | Java | 13 | 0 | 3 | 16 |
| [src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/UnusualLocationFraudRule.java](/src/main/java/com/github/sentinel/pay/domain/entity/fraudRules/UnusualLocationFraudRule.java) | Java | 14 | 0 | 3 | 17 |
| [src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskContribution.java](/src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskContribution.java) | Java | 13 | 3 | 5 | 21 |
| [src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskLevel.java](/src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskLevel.java) | Java | 7 | 5 | 3 | 15 |
| [src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskScore.java](/src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskScore.java) | Java | 25 | 11 | 8 | 44 |
| [src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskSignal.java](/src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskSignal.java) | Java | 6 | 0 | 2 | 8 |
| [src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskType.java](/src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskType.java) | Java | 7 | 0 | 2 | 9 |
| [src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskWeightPolicy.java](/src/main/java/com/github/sentinel/pay/domain/entity/risk/RiskWeightPolicy.java) | Java | 13 | 0 | 5 | 18 |
| [src/main/java/com/github/sentinel/pay/domain/entity/shared/AccountId.java](/src/main/java/com/github/sentinel/pay/domain/entity/shared/AccountId.java) | Java | 6 | 0 | 3 | 9 |
| [src/main/java/com/github/sentinel/pay/domain/entity/shared/Currency.java](/src/main/java/com/github/sentinel/pay/domain/entity/shared/Currency.java) | Java | 8 | 0 | 4 | 12 |
| [src/main/java/com/github/sentinel/pay/domain/entity/shared/Location.java](/src/main/java/com/github/sentinel/pay/domain/entity/shared/Location.java) | Java | 9 | 0 | 2 | 11 |
| [src/main/java/com/github/sentinel/pay/domain/entity/transactionAggregate/Channel.java](/src/main/java/com/github/sentinel/pay/domain/entity/transaction/Channel.java) | Java | 3 | 0 | 2 | 5 |
| [src/main/java/com/github/sentinel/pay/domain/entity/transactionAggregate/Money.java](/src/main/java/com/github/sentinel/pay/domain/entity/transaction/Money.java) | Java | 18 | 0 | 7 | 25 |
| [src/main/java/com/github/sentinel/pay/domain/entity/transactionAggregate/Transaction.java](/src/main/java/com/github/sentinel/pay/domain/entity/transaction/Transaction.java) | Java | 25 | 0 | 9 | 34 |
| [src/main/java/com/github/sentinel/pay/domain/entity/transactionAggregate/TransactionId.java](/src/main/java/com/github/sentinel/pay/domain/entity/transaction/TransactionId.java) | Java | 6 | 0 | 3 | 9 |
| [src/main/java/com/github/sentinel/pay/domain/entity/transactionAggregate/TransactionType.java](/src/main/java/com/github/sentinel/pay/domain/entity/transaction/TransactionType.java) | Java | 31 | 8 | 11 | 50 |
| [src/main/java/com/github/sentinel/pay/domain/repository/FraudDecisionRepository.java](/src/main/java/com/github/sentinel/pay/domain/repository/FraudDecisionRepository.java) | Java | 10 | 0 | 5 | 15 |
| [src/main/java/com/github/sentinel/pay/domain/repository/FraudIncidentRepository.java](/src/main/java/com/github/sentinel/pay/domain/repository/FraudIncidentRepository.java) | Java | 12 | 0 | 8 | 20 |
| [src/main/java/com/github/sentinel/pay/domain/repository/RiskProfileRepository.java](/src/main/java/com/github/sentinel/pay/domain/repository/RiskProfileRepository.java) | Java | 14 | 0 | 7 | 21 |
| [src/main/java/com/github/sentinel/pay/domain/repository/TransactionRepository.java](/src/main/java/com/github/sentinel/pay/domain/repository/TransactionRepository.java) | Java | 10 | 0 | 5 | 15 |
| [src/main/java/com/github/sentinel/pay/infrastructure/in/web/controller/AccountController.java](/src/main/java/com/github/sentinel/pay/infrastructure/in/web/controller/AccountController.java) | Java | 24 | 2 | 3 | 29 |
| [src/main/java/com/github/sentinel/pay/infrastructure/in/web/controller/DashboardController.java](/src/main/java/com/github/sentinel/pay/infrastructure/in/web/controller/DashboardController.java) | Java | 15 | 1 | 3 | 19 |
| [src/main/java/com/github/sentinel/pay/infrastructure/in/web/controller/IncidentController.java](/src/main/java/com/github/sentinel/pay/infrastructure/in/web/controller/IncidentController.java) | Java | 24 | 5 | 4 | 33 |
| [src/main/java/com/github/sentinel/pay/infrastructure/in/web/controller/TransactionController.java](/src/main/java/com/github/sentinel/pay/infrastructure/in/web/controller/TransactionController.java) | Java | 58 | 8 | 11 | 77 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/AccountRiskProfileEntity.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/AccountRiskProfileEntity.java) | Java | 27 | 2 | 5 | 34 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/CurrencyProfileEntity.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/CurrencyProfileEntity.java) | Java | 19 | 0 | 6 | 25 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/FraudDecisionEntity.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/FraudDecisionEntity.java) | Java | 27 | 0 | 3 | 30 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/FraudIncidentEntity.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/FraudIncidentEntity.java) | Java | 28 | 0 | 4 | 32 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/LocationProfileEntity.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/LocationProfileEntity.java) | Java | 19 | 0 | 5 | 24 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/MonetaryProfileEntity.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/MonetaryProfileEntity.java) | Java | 18 | 0 | 5 | 23 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/TransactionEntity.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/TransactionEntity.java) | Java | 32 | 0 | 4 | 36 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/VelocityProfileEntity.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/EntityModels/VelocityProfileEntity.java) | Java | 20 | 0 | 4 | 24 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/adapter/FraudDecisionAdapter.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/adapter/FraudDecisionAdapter.java) | Java | 35 | 0 | 6 | 41 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/adapter/FraudIncidentAdapter.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/adapter/FraudIncidentAdapter.java) | Java | 46 | 0 | 10 | 56 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/adapter/RiskProfileAdapter.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/adapter/RiskProfileAdapter.java) | Java | 110 | 1 | 13 | 124 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/adapter/TransactionAdapter.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/adapter/TransactionAdapter.java) | Java | 40 | 0 | 7 | 47 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/base/BasePersistenceAdapter.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/base/BasePersistenceAdapter.java) | Java | 53 | 3 | 14 | 70 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/base/GenericPersistenceRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/base/GenericPersistenceRepository.java) | Java | 14 | 6 | 3 | 23 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/AccountRiskProfileEntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/AccountRiskProfileEntityRepository.java) | Java | 14 | 0 | 6 | 20 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/CurrencyEntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/CurrencyEntityRepository.java) | Java | 5 | 0 | 4 | 9 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/FraudDecisionEntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/FraudDecisionEntityRepository.java) | Java | 14 | 0 | 5 | 19 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/FraudIncidentEntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/FraudIncidentEntityRepository.java) | Java | 19 | 0 | 6 | 25 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/LocationEntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/LocationEntityRepository.java) | Java | 5 | 0 | 4 | 9 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/MonetaryEntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/MonetaryEntityRepository.java) | Java | 5 | 0 | 4 | 9 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/TransactionEntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/TransactionEntityRepository.java) | Java | 17 | 0 | 6 | 23 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/VelocityEntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/VelocityEntityRepository.java) | Java | 6 | 0 | 5 | 11 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/generic/EntityRepository.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/jpa/generic/EntityRepository.java) | Java | 6 | 0 | 3 | 9 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/EntityMapper.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/EntityMapper.java) | Java | 5 | 0 | 3 | 8 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/FraudDecisionMapper.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/FraudDecisionMapper.java) | Java | 5 | 0 | 3 | 8 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/FraudIncidentMapper.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/FraudIncidentMapper.java) | Java | 5 | 0 | 3 | 8 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/RiskProfileMapper.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/RiskProfileMapper.java) | Java | 5 | 0 | 3 | 8 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/TransactionMapper.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/TransactionMapper.java) | Java | 5 | 0 | 3 | 8 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/adapters/FraudDecisionMapperImpl.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/adapters/FraudDecisionMapperImpl.java) | Java | 34 | 0 | 5 | 39 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/adapters/FraudIncidentMapperImpl.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/adapters/FraudIncidentMapperImpl.java) | Java | 38 | 0 | 4 | 42 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/adapters/RiskProfileMapperImpl.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/adapters/RiskProfileMapperImpl.java) | Java | 28 | 0 | 5 | 33 |
| [src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/adapters/TransactionMapperImpl.java](/src/main/java/com/github/sentinel/pay/infrastructure/out/persistence/mapper/adapters/TransactionMapperImpl.java) | Java | 36 | 0 | 4 | 40 |
| [src/main/resources/application.properties](/src/main/resources/application.properties) | Java Properties | 7 | 0 | 0 | 7 |
| [src/main/resources/static/css/custom.css](/src/main/resources/static/css/custom.css) | PostCSS | 69 | 5 | 16 | 90 |
| [src/main/resources/templates/account-profile.html](/src/main/resources/templates/account-profile.html) | Django HTML | 87 | 0 | 5 | 92 |
| [src/main/resources/templates/console.html](/src/main/resources/templates/console.html) | Django HTML | 71 | 0 | 5 | 76 |
| [src/main/resources/templates/dashboard.html](/src/main/resources/templates/dashboard.html) | Django HTML | 171 | 0 | 7 | 178 |
| [src/main/resources/templates/fragments/transaction-response.html](/src/main/resources/templates/fragments/transaction-response.html) | Django HTML | 70 | 0 | 9 | 79 |
| [src/main/resources/templates/incident-detail.html](/src/main/resources/templates/incident-detail.html) | Django HTML | 105 | 0 | 7 | 112 |
| [src/main/resources/templates/incidents.html](/src/main/resources/templates/incidents.html) | Django HTML | 113 | 0 | 5 | 118 |
| [src/main/resources/templates/layout.html](/src/main/resources/templates/layout.html) | Django HTML | 52 | 0 | 9 | 61 |
| [src/main/resources/templates/sidebar.html](/src/main/resources/templates/sidebar.html) | Django HTML | 53 | 0 | 4 | 57 |
| [src/test/java/com/github/sentinel/pay/ApplicationTests.java](/src/test/java/com/github/sentinel/pay/ApplicationTests.java) | Java | 9 | 0 | 5 | 14 |
| [src/test/java/com/github/sentinel/pay/domain/fraudrRules/HighRiskAndRemoteTransactionFraudRuleTest.java](/src/test/java/com/github/sentinel/pay/domain/fraudrRules/HighRiskAndRemoteTransactionFraudRuleTest.java) | Java | 41 | 0 | 5 | 46 |
| [src/test/java/com/github/sentinel/pay/domain/fraudrRules/TransactionAmountFraudRuleTest.java](/src/test/java/com/github/sentinel/pay/domain/fraudrRules/TransactionAmountFraudRuleTest.java) | Java | 41 | 0 | 5 | 46 |
| [src/test/java/com/github/sentinel/pay/domain/fraudrRules/UnusualLocationFraudRuleTest.java](/src/test/java/com/github/sentinel/pay/domain/fraudrRules/UnusualLocationFraudRuleTest.java) | Java | 41 | 0 | 5 | 46 |
| [src/test/java/com/github/sentinel/pay/domain/fraudrRules/VelocityFraudRuleTest.java](/src/test/java/com/github/sentinel/pay/domain/fraudrRules/VelocityFraudRuleTest.java) | Java | 49 | 0 | 9 | 58 |
| [src/test/java/com/github/sentinel/pay/domain/riskProfile/TransactionActivityTest.java](/src/test/java/com/github/sentinel/pay/domain/riskProfile/TransactionActivityTest.java) | Java | 36 | 2 | 8 | 46 |
| [src/test/java/com/github/sentinel/pay/domain/riskScore/FraudDecisionPolicyTest.java](/src/test/java/com/github/sentinel/pay/domain/riskScore/FraudDecisionPolicyTest.java) | Java | 3 | 0 | 2 | 5 |
| [src/test/java/com/github/sentinel/pay/domain/riskScore/RiskScoreTest.java](/src/test/java/com/github/sentinel/pay/domain/riskScore/RiskScoreTest.java) | Java | 3 | 0 | 2 | 5 |
| [src/test/java/com/github/sentinel/pay/domain/transaction/LocationTest.java](/src/test/java/com/github/sentinel/pay/domain/transaction/LocationTest.java) | Java | 12 | 0 | 5 | 17 |
| [src/test/java/com/github/sentinel/pay/domain/transaction/MoneyTest.java](/src/test/java/com/github/sentinel/pay/domain/transaction/MoneyTest.java) | Java | 22 | 0 | 6 | 28 |
| [transaction-api.md](/transaction-api.md) | Markdown | 81 | 0 | 67 | 148 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)