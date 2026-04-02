package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IncidentStatistics{

private UUID id;
private   List<FraudIncidentStatus> status;
 private   Instant createdAt;
     
    public static IncidentStatistics initial(){

    return new IncidentStatistics(UUID.randomUUID(),
     new ArrayList<FraudIncidentStatus>(),
      Instant.now());

}

     public void AddIncidentStatus(FraudIncidentStatus status, Instant createdAt){
 
        if (!(this.status instanceof ArrayList)) {
        this.status = new ArrayList<>(this.status);
    }

    this.status.add(status);
    this.createdAt = createdAt;
     }

     
    public int length(){
        if (this.getStatus().isEmpty()) return 0;
        return this.getStatus().size();
    }
}
