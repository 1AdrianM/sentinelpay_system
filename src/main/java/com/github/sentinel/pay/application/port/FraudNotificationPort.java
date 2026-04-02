package com.github.sentinel.pay.application.port;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;

public interface FraudNotificationPort {
        void notifyFraud(FraudDecision decision, FraudIncident incident);

}
