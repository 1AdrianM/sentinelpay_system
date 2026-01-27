package com.github.sentinel.pay.domain.entity.shared;

public record Location(
        String city,
        String country
) {
    public boolean isUnusualComparedTo(Location otherLocation) {
        return this.country.equals(otherLocation.country) || this.city.equals(otherLocation.city);
    }

    @Override
    public String toString() {
        return String.format("%s,%s",this.city,this.country);
    }
}
