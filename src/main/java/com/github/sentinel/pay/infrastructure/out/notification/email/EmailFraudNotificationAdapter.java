package com.github.sentinel.pay.infrastructure.out.notification.email;

import org.springframework.stereotype.Component;

import com.github.sentinel.pay.application.port.FraudNotificationPort;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
@Component
public class EmailFraudNotificationAdapter implements FraudNotificationPort {
    
    @Override
    public void notifyFraud(FraudDecision decision, FraudIncident incident) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyFraud'");
    }
    
}
