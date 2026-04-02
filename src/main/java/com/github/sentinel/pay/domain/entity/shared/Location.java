package com.github.sentinel.pay.domain.entity.shared;

public record Location(
        String city,
        String country
) {
    public static Location of(String city, String country) {
        if(city.isEmpty() || city.isBlank()|| country.isEmpty()|| country.isEmpty()){
            throw new RuntimeException("can not create Location Value Object with empty or blank input values");
        }
        return new Location(city, country);
    }

    public boolean isUnusualComparedTo(Location otherLocation) {
        return !this.country.equals(otherLocation.country) || !this.city.equals(otherLocation.city);
    }

    @Override
    public String toString() {
        return String.format("%s,%s",this.city,this.country);
    }
}
