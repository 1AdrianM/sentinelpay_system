Prompt para Agente Frontend (Gemini)

You are a senior frontend engineer building an enterprise-grade antifraud dashboard for a fintech system called SentinelPay.

The backend is already defined using DDD, with aggregates such as:

FraudIncident

FraudDecision

Transaction

AccountRiskProfile

The frontend stack MUST be:

Thymeleaf

Bootstrap 5

HTMX

No SPA frameworks (no React, no Vue, no Next.js)

🎨 Design goals

Clean, modern enterprise fintech UI

Minimalist, readable, analyst-focused

No flashy animations, no neon colors

Should look like an internal banking or antifraud tool

📄 Required Screens
1️⃣ Main Dashboard

At the top, display metric cards:

Total Open Incidents

Incidents Under Review

High Risk Incidents (Risk Score ≥ 80)

Resolved Today

Below the metrics:

A Fraud Incidents table with:

Incident ID

Account ID

Transaction ID

Risk Score (colored badge: green → yellow → red)

Status (OPEN, UNDER_REVIEW, RESOLVED, CONFIRMED_FRAUD)

Created At

Action button: View

2️⃣ Filters Section

Above the table:

Filter by Status

Filter by Risk Level

Optional Date Range

Filters should be applied using HTMX without full page reload.

3️⃣ Incident Detail View

When clicking View:

Open a modal or side panel (offcanvas) using Bootstrap

Load content dynamically with HTMX

The detail view must show:

Incident summary

FraudDecisionType

Risk Score breakdown

Fraud rules triggered

Timeline of status changes

Related Transaction data

Account Risk Profile summary (RiskLevel, avg score, velocity indicators)

⚙️ Technical Constraints

Use Bootstrap cards and badges

Use subtle shadows (shadow-sm)

No client-side state management

All data comes from server-rendered Thymeleaf templates

HTMX is used only for partial updates and modals

📌 UX Principles

Prioritize readability over decoration

Analysts should understand risk at a glance

Consistent color semantics:

Green = Low Risk / Resolved

Yellow = Review

Red = High Risk / Open Fraud

Produce:

HTML structure examples

Component layout suggestions

Bootstrap class usage

HTMX usage examples

Do NOT generate backend code.
Focus only on frontend structure, UX, and integration patterns.