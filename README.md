# 🛡️ SentinelPay | Real-Time Fraud Detection System

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![HTMX](https://img.shields.io/badge/HTMX-2.0.0-3D72D7?style=for-the-badge&logo=htmx&logoColor=white)](https://htmx.org/)
[![Architecture](https://img.shields.io/badge/Architecture-Hexagonal%20%2B%20DDD-blueviolet?style=for-the-badge)](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))

**SentinelPay** is a high-performance backend engine designed to evaluate banking transactions in real-time, detect potentially fraudulent activity, and manage fraud incidents using a rule-based risk scoring system.

---

## 🚀 Key Value Proposition

In the modern financial landscape, speed and security are paramount. SentinelPay solves the "trust gap" by providing:
- **Real-Time Evaluation**: Instant risk scoring for every transaction.
- **Adaptive Risk Engine**: Dynamic rules that catch everything from "Impossible Travel" to "Velocity Spikes".
- **Incident Lifecycle Management**: A full workflow for analysts to review, escalate, and resolve suspicious activities.
- **Developer-First API**: Seamless integration for any banking or e-commerce platform.

---

## 🏛️ Architectural Excellence

This project isn't just about *what* it does, but *how* it's built. It serves as a reference implementation for **Clean Architecture**:

### 1. Domain-Driven Design (DDD)
The core business logic is isolated in a pure `domain` layer, free from framework dependencies.
- **Aggregates**: `Transaction`, `FraudIncident`, `AccountRiskProfile`.
- **Value Objects**: `Money`, `RiskScore`, `Location`.
- **Domain Services**: Orchestrating complex fraud rules across multiple entities.

### 2. Hexagonal Architecture (Ports & Adapters)
Complete decoupling of business rules from infrastructure:
- **Inbound Ports**: Web Controllers, REST APIs.
- **Outbound Ports**: JPA Repositories, Notification Services.
- **Result**: The system is 100% testable without a database or web server.

---

## 🛠️ Tech Stack & Patterns

- **Backend**: Java 17, Spring Boot 3, Spring Security (OAuth2/JWT ready).
- **Frontend**: Thymeleaf + **HTMX** (for a reactive, SPA-like experience without complex JS frameworks).
- **Persistence**: Spring Data JPA with H2/PostgreSQL compatibility.
- **Testing**: JUnit 5, Mockito, AssertJ (focus on Domain-first testing).
- **UI/UX**: Bootstrap 5 + Custom CSS (Modern "Cyber-Security" Aesthetic).

---

## 🎮 Interactive Demo Scenarios

Once you run the application, navigate to the **Transaction Console** to test these built-in scenarios:

| Scenario | Description | Target Detection |
| :--- | :--- | :--- |
| **Normal** | A standard $125 purchase in the home city. | ✅ Approved |
| **High Amount** | A sudden $9,500 transfer. | ⚠️ Flags for Review |
| **Impossible Travel** | A TX in Paris followed by one in NY 5 mins later. | 🚫 Auto-Rejected |
| **Velocity Spike** | 10 small transactions in under 2 minutes. | 🛡️ Account Restricted |

---

## ⚙️ Getting Started

### Prerequisites
- **JDK 17** or higher
- **Gradle 8.x**

### Run Locally
```bash
./gradlew bootRun
```
Access the dashboard at: `http://localhost:8080` (Default credentials: `admin@sentinelpay.com` / `admin123`)

### Run Tests
```bash
./gradlew test
```

---

## 📈 Future Roadmap
- [ ] **ML Integration**: Hybrid rule-based + Machine Learning scoring.
- [ ] **Kafka Streams**: Processing transactions from a real-time message bus.
- [ ] **Dashboard Analytics**: Real-time charts using Chart.js for incident trends.

---

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
*Built with ❤️ for the Fintech community.*
