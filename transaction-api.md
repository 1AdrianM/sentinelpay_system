Prompt para Agente Frontend (Gemini)

Actúa como un Senior Frontend Engineer especializado en UI para herramientas internas (internal tooling).
El proyecto es un sistema antifraude llamado SentinelPay, orientado a analistas técnicos y desarrolladores, no a usuarios finales. 
🧪 4️⃣ Transaction Console (NUEVO – tipo Swagger)
🧠 Concepto

Esta pantalla NO es para usuarios finales.
Es una herramienta técnica interna para enviar transacciones al motor antifraude.

Inspiración:

Swagger UI

Postman

Stripe API Playground

📄 Layout de Transaction Console
Sección izquierda – Request

HTTP Method: POST

Endpoint:

/api/transactions/evaluate


JSON Editor (textarea grande, monoespaciado):

{
"transactionId": "TX-001",
"accountId": "ACC-123",
"amount": 12500,
"currency": "USD",
"location": "DO-SD",
"timestamp": "2026-01-14T15:00:00Z",
"channel": "CRYPTO",
"merchantType": "EXCHANGE"
}


Botón:

Send Transaction

Sección derecha – Response

Renderizar respuesta del backend:

Fraud Decision:

APPROVED / REVIEW / BLOCKED

Global Risk Score (badge)

Breakdown por profiles:

MonetaryProfile

LocationProfile

VelocityProfile

CurrencyProfile

Incident ID (si fue creado)

Formato tipo:

JSON prettified

O tarjetas expandibles (accordion)

🔐 5️⃣ Auth (mínimo, conceptual)
Reglas de autenticación (simulada)

No login real

Usuario mockeado:

Role: INTERNAL_ANALYST

Auth solo visual:

Sidebar visible

Acciones habilitadas

Sign out deshabilitado (placeholder)

Objetivo

Mostrar que la UI está pensada para entornos controlados, no para producción abierta.

🎨 Estilo visual

Paleta neutra:

Gris oscuro

Blanco

Azul primario

Badges de riesgo:

Verde (low)

Amarillo (medium)

Rojo (high)

Tipografía limpia

Espaciado generoso

Nada “estudiante”

Nada recargado

🚫 Restricciones importantes

No SPA

No frameworks JS modernos

No lógica de negocio en frontend

No edición directa de entidades

Transaction Console solo envía JSON

🏁 Resultado esperado

UI lista para:

Demos

Testing manual

Portfolio técnico

Claramente separada entre:

Operación (Dashboard / Incidents)

Ingreso técnico (Transaction Console)