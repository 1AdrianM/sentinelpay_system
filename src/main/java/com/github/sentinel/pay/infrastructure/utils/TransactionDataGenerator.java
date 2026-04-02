package com.github.sentinel.pay.infrastructure.utils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import com.github.sentinel.pay.domain.entity.shared.Currency;
import com.github.sentinel.pay.domain.entity.transaction.TransactionType;

/**
 * Utility class for generating realistic random transaction data for testing.
 * Provides methods to generate valid amounts, locations, channels, and complete transactions.
 */
public class TransactionDataGenerator {

    private static final Random random = new Random();


    // Realistic city-country pairs
    private static final List<LocationData> LOCATIONS = List.of(
            new LocationData("Santo Domingo", "DO"),
            new LocationData("New York", "US"),
            new LocationData("Miami", "US"),
            new LocationData("London", "GB"),
            new LocationData("Paris", "FR"),
            new LocationData("Madrid", "ES"),
            new LocationData("Berlin", "DE"),
            new LocationData("Tokyo", "JP"),
            new LocationData("Toronto", "CA"),
            new LocationData("Sydney", "AU"),
            new LocationData("Mexico City", "MX"),
            new LocationData("São Paulo", "BR"),
            new LocationData("Buenos Aires", "AR"),
            new LocationData("Lima", "PE"),
            new LocationData("Bogotá", "CO"),
            new LocationData("Panama City", "PA"),
            new LocationData("San Juan", "PR"),
            new LocationData("Punta Cana", "DO"),
            new LocationData("Santiago", "CL")
    );

    // Suspicious locations (for fraud testing)
    private static final List<LocationData> SUSPICIOUS_LOCATIONS = List.of(
            new LocationData("Unknown", "XX"),
            new LocationData("Lagos", "NG"),
            new LocationData("Kiev", "UA"),
            new LocationData("Manila", "PH"),
            new LocationData("Jakarta", "ID")
    );

    /**
     * Generates a random transaction amount based on risk profile
     */
    public static BigDecimal generateAmount(AmountProfile profile) {
        return switch (profile) {
            case LOW -> generateAmount(10.0, 250.0);
            case MEDIUM -> generateAmount(250.0, 2000.0);
            case HIGH -> generateAmount(2000.0, 5000.0);
            case VERY_HIGH -> generateAmount(5000.0, 15000.0);
            case RANDOM -> generateAmount(10.0, 10000.0);
        };
    }

    /**
     * Generates a random amount within a specific range
     */
    public static BigDecimal generateAmount(double min, double max) {
        double amount = min + (max - min) * random.nextDouble();
        return BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Generates a random transaction type
     */
    public static String generateTransactionType() {
        return TransactionType.values()[random.nextInt(TransactionType.values().length)].name();
    }

    /**
     * Generates a random channel
     */
    public static String generateChannel() {
     com.github.sentinel.pay.domain.entity.transaction.Channel[] channels=   com.github.sentinel.pay.domain.entity.transaction.Channel.values();
     return channels[random.nextInt(channels.length)].name();
    }

    /**
     * Generates a random currency
     */
    public static String generateCurrency() {
        return Currency.values()[random.nextInt(Currency.values().length)].name();
    }

    /**
     * Generates a random location based on profile
     */
    public static LocationData generateLocation(LocationProfile profile) {
        return switch (profile) {
            case DOMESTIC -> new LocationData("Santo Domingo", "DO");
            case INTERNATIONAL -> LOCATIONS.get(random.nextInt(LOCATIONS.size()));
            case SUSPICIOUS -> SUSPICIOUS_LOCATIONS.get(random.nextInt(SUSPICIOUS_LOCATIONS.size()));
            case RANDOM -> random.nextDouble() < 0.8 
                    ? LOCATIONS.get(random.nextInt(LOCATIONS.size()))
                    : SUSPICIOUS_LOCATIONS.get(random.nextInt(SUSPICIOUS_LOCATIONS.size()));
        };
    }

    /**
     * Generates a timestamp within a range
     */
    public static Instant generateTimestamp(TimestampProfile profile) {
        Instant now = Instant.now();
        return switch (profile) {
            case NOW -> now;
            case RECENT_PAST -> now.minus(random.nextInt(60), ChronoUnit.MINUTES);
            case TODAY -> now.minus(random.nextInt(24), ChronoUnit.HOURS);
            case THIS_WEEK -> now.minus(random.nextInt(7), ChronoUnit.DAYS);
            case THIS_MONTH -> now.minus(random.nextInt(30), ChronoUnit.DAYS);
            case RANDOM -> now.minus(random.nextInt(365), ChronoUnit.DAYS);
        };
    }

    /**
     * Generates a complete random transaction JSON
     */
    public static String generateTransactionJson(String accountId, TransactionProfile profile) {
        BigDecimal amount;
        LocationData location;
        String currency;
        Instant timestamp;

        switch (profile) {
            case NORMAL:
                amount = generateAmount(AmountProfile.LOW);
                location = new LocationData("Santo Domingo", "DO");
                currency = "USD";
                timestamp = generateTimestamp(TimestampProfile.NOW);
                break;

            case HIGH_RISK:
                amount = generateAmount(AmountProfile.VERY_HIGH);
                location = generateLocation(LocationProfile.SUSPICIOUS);
                currency = generateCurrency();
                timestamp = generateTimestamp(TimestampProfile.NOW);
                break;

            case INTERNATIONAL:
                amount = generateAmount(AmountProfile.MEDIUM);
                location = generateLocation(LocationProfile.INTERNATIONAL);
                currency = random.nextBoolean() ? "USD" : location.country.equals("DO") ? "DOP" : "EUR";
                timestamp = generateTimestamp(TimestampProfile.NOW);
                break;

            case RAPID_FIRE:
                amount = generateAmount(AmountProfile.RANDOM);
                location = generateLocation(LocationProfile.RANDOM);
                currency = random.nextBoolean() ? "USD" : generateCurrency();
                timestamp = generateTimestamp(TimestampProfile.RECENT_PAST);
                break;

            default: // RANDOM
                amount = generateAmount(AmountProfile.RANDOM);
                location = generateLocation(LocationProfile.RANDOM);
                currency = generateCurrency();
                timestamp = generateTimestamp(TimestampProfile.RANDOM);
        }

        String transactionType = generateTransactionType();
        String channel = generateChannel();

        return """
                {
                    "accountId": "%s",
                    "amount": %s,
                    "currency": "%s",
                    "city": "%s",
                    "country": "%s",
                    "timestamp": "%s",
                    "channel": "%s",
                    "transactionType": "%s"
                }""".formatted(
                accountId,
                amount,
                currency,
                location.city,
                location.country,
                timestamp.toString(),
                channel,
                transactionType
        );
    }

    /**
     * Generates multiple random transactions for bulk testing
     */
    public static List<String> generateBulkTransactions(String accountId, int count, TransactionProfile profile) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> generateTransactionJson(accountId, profile))
                .toList();
    }

    // Inner classes for structured data
    public record LocationData(String city, String country) {}

    public enum AmountProfile {
        LOW, MEDIUM, HIGH, VERY_HIGH, RANDOM
    }

    public enum LocationProfile {
        DOMESTIC, INTERNATIONAL, SUSPICIOUS, RANDOM
    }

    public enum TimestampProfile {
        NOW, RECENT_PAST, TODAY, THIS_WEEK, THIS_MONTH, RANDOM
    }

    public enum TransactionProfile {
        NORMAL,          // Low amount, domestic, standard channel
        HIGH_RISK,       // High amount, suspicious location
        INTERNATIONAL,   // International location, medium amount
        RAPID_FIRE,      // Recent timestamps, random data
        RANDOM           // Completely random
    }
}
