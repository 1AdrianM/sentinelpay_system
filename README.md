# SentinelPay 🛡️

SentinelPay is a backend system designed to evaluate banking transactions in real time, detect potentially fraudulent activity, and manage fraud incidents through a rule-based risk engine.

The system is built using **Domain-Driven Design (DDD)** and **Hexagonal Architecture** principles, focusing on creating a clean, scalable, and maintainable codebase where the business logic is decoupled from infrastructure concerns.

## ✨ Core Principles

- **Domain-Centric**: The core of the application is the domain model, which represents the business processes and rules in a pure, technology-agnostic way.
- **Ports & Adapters (Hexagonal Architecture)**: The application communicates with the outside world (like web controllers, databases, message queues) through ports (interfaces) and adapters (implementations), keeping the core domain isolated.
- **Scalability & Maintainability**: By separating concerns, the system is easier to test, maintain, and evolve over time.

## 🏛️ Architectural Overview

The project is structured to reflect the Hexagonal Architecture and DDD principles:

- `src/main/java/com/github/sentinel/pay/domain`: **The Domain Layer**
  - This is the heart of the application. It contains the business logic, entities, aggregates, value objects, domain events, and repository interfaces. It has no dependencies on any other layer.

- `src/main/java/com/github/sentinel/pay/application`: **The Application Layer**
  - This layer orchestrates the domain logic. It contains use cases (Application Services) that are called by external clients (like web controllers). It acts as a bridge between the outside world and the domain.

- `src/main/java/com/github/sentinel/pay/infrastructure`: **The Infrastructure Layer**
  - This layer contains the concrete implementations of the interfaces defined in the domain layer (e.g., repository implementations using a specific database). It also holds all external-facing components like REST controllers, message listeners, and configuration.

## 🚀 Getting Started

This project is built using Java and Gradle.

### Prerequisites

- Java 17 or higher
- Gradle

### Build

To build the project and run all tests, execute the following command:

```bash
./gradlew build
```

### Run the Application

To run the application locally, use the Spring Boot Gradle plugin:

```bash
./gradlew bootRun
```

### Run Tests

To run the unit and integration tests, execute:

```bash
./gradlew test
```

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1.  Fork the repository.
2.  Create a new branch (`git checkout -b feature/your-feature-name`).
3.  Make your changes and commit them (`git commit -m 'Add some feature'`).
4.  Push to the branch (`git push origin feature/your-feature-name`).
5.  Create a new Pull Request.

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.
