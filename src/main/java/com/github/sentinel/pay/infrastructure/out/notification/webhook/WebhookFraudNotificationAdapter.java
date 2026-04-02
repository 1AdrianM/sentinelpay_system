package com.github.sentinel.pay.infrastructure.out.notification.webhook;

import org.springframework.stereotype.Component;

import com.github.sentinel.pay.application.port.FraudNotificationPort;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
@Component
public class WebhookFraudNotificationAdapter implements FraudNotificationPort {
    
    @Override
    public void notifyFraud(FraudDecision decision, FraudIncident incident) {
        // Implement webhook notification logic here
    }
    
}
