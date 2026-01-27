package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.shared.Location;

 import java.util.HashMap;
import java.util.Map;
public record LocationProfile(
        Map<Location, Integer> locationCount,
        long samples
) {

    public static LocationProfile empty() {
        return new LocationProfile(new HashMap<>(), 0);
    }



    public LocationProfile observe(Location location) {
        var newMap = new HashMap<>(locationCount);
        newMap.merge(location, 1, Integer::sum);
        return new LocationProfile(newMap, samples + 1);
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