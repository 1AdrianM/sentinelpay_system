package com.github.sentinel.pay.domain.entity.auth.apiKey;

public enum ApiScope {

    TRANSACTION_EVALUATE,      // Enviar transacciones
    INCIDENT_READ,             // Leer incidentes
    INCIDENT_CREATE,           // Crear incidentes
    DASHBOARD_READ,            // Métricas
    RISK_PROFILE_READ,         // Leer perfiles
    AUDIT_LOG_READ             // Logs
}