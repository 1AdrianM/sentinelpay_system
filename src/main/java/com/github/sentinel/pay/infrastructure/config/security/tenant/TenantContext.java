package com.github.sentinel.pay.infrastructure.config.security.tenant;

import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TenantContext {

private final String CLIENT_ACCOUNT_ID = "clientAccountId";
    private final Map<String, UUID> attributes = new HashMap<>();

    public void put( UUID value) {
            attributes.put(CLIENT_ACCOUNT_ID, value);
        }

        public Object get(String key) {
            return attributes.get(key);
        }

        public UUID getClientAccountId() {
            return  attributes.get(CLIENT_ACCOUNT_ID);
        }


     }

