package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.shared.Location;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationProfile{
     private UUID id;
       private Map<Location, Integer> locationCount;
       private long samples;
       private Instant lastUpdatedAt;

  
    public static LocationProfile initial() {
        return new LocationProfile(
            UUID.randomUUID(),
            new HashMap<>(),
             0,
            null);
    }



    public void observe(Location location) {
        
        this.locationCount.merge(location, 1, Integer::sum);
        this.samples++;
    }

    public Location mostFrequentLocation() {
        return locationCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public double confidence(Location location) {
        if (samples == 0) return 0.0;
        return locationCount.getOrDefault(location, 0) / (double) samples;
    }

    public double maxConfidence() {
        if (samples == 0) return 0.0;
        return locationCount.values()
                .stream()
                .mapToDouble(c -> c / (double) samples)
                .max()
                .orElse(0.0);
    }

    public double diversity() {
        return 1.0 - maxConfidence();
    }


    public boolean isUnusual(Location txLocation) {
        return !mostFrequentLocation().equals(txLocation) && confidence(txLocation) > 0.6;
    }
 
}