package com.github.sentinel.pay.domain.entity.auth.apiKey;

import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
 import com.github.sentinel.pay.domain.utils.ApiKeySecurity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter
@AllArgsConstructor
@Builder
public class ApiKey {
    private ApiKeyId id;
    private String name;
    private ClientAccountId clientAccountId;
    private Set<ApiScope> scope;
    private String hashedKey;
    private ApiKeyStatus status;
    private Instant createdAt;
    private Instant lastUsedAt;

    public boolean hasScope(ApiScope scopes) {
        return scope.contains(scopes);
    }
    public boolean isValid() {
        return status == ApiKeyStatus.ACTIVE;
    }

    public void markUsed() {
        this.lastUsedAt = Instant.now();
    }

    public static ApiKey generate(ClientAccountId clientAccountId, String nameKey, String raw) {

        var hashedApiKey = ApiKeySecurity.hash(raw);

        return new ApiKey(
                new ApiKeyId(UUID.randomUUID()),
                nameKey,
                clientAccountId,
                 new HashSet<>(),
                 hashedApiKey,
                ApiKeyStatus.ACTIVE,
                Instant.now(),
                null
        );
    }
}
