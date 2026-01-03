# Domain Layer

This package is the heart of the SentinelPay application. It contains all the core business logic, rules, and models, completely decoupled from any infrastructure or delivery mechanism concerns. It is a pure representation of the fraud detection domain.

The key patterns and building blocks used here are derived from **Domain-Driven Design (DDD)**:

-   **Entities & Aggregates**: Rich domain objects that encapsulate state and behavior (e.g., `Transaction`, `FraudIncident`, `AccountRiskProfile`).
-   **Value Objects**: Immutable objects that represent descriptive aspects of the domain (e.g., `Money`, `RiskScore`, `Location`).
-   **Repository Interfaces**: Contracts defined in the domain layer that specify how aggregate roots are persisted and retrieved. The implementation details are handled by adapters in the infrastructure layer.
-   **Domain Events**: Objects that represent something that has happened in the domain.
-   **Domain Services**: For logic that doesn't naturally fit within a single entity or aggregate.

---

### 🚧 Work in Progress: Domain Services

The current development effort is focused on implementing the **Domain Services**.

Our immediate goal is to build out the `FraudEvaluationService`. This service will be responsible for:

1.  Receiving a `Transaction` to be evaluated.
2.  Orchestrating the various `FraudRule` implementations to calculate a total `RiskScore`.
3.  Making a `FraudDecision` based on the calculated risk.
4.  Potentially raising a `FraudIncident` if the transaction is deemed fraudulent.

This service represents a critical piece of the core business logic, and its development is the main focus at this stage.
