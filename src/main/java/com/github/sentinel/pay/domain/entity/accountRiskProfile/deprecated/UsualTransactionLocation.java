package com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated;

import com.github.sentinel.pay.domain.entity.shared.Location;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record UsualTransactionLocation(
      Location usualLocation,
      List<Location> locationList
) {
    public boolean isUnusualComparedTo(Location otherLocation) {
        return !this.usualLocation.equals( otherLocation);
    }
    public Location getMostUsualLocation(){
        return  this.locationList.stream()
                .collect(Collectors.groupingBy(p->p,Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        }
    }
