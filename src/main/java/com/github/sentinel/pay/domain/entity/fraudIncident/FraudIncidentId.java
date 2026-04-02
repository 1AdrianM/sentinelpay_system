package com.github.sentinel.pay.domain.entity.fraudIncident;

import java.util.List;
import java.util.UUID;

public record FraudIncidentId(
        UUID id
) {

    public static FraudIncidentId of(UUID id) {
       return new FraudIncidentId(id);
    }

    public static List<FraudIncidentId> of(List<UUID> list) {
   return  list.stream().map(i-> new FraudIncidentId(i)).toList();
    }
}
