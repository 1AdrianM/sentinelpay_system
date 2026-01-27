package com.github.sentinel.pay.infrastructure.config.security.tenant;

import lombok.Data;

@Data
public class TenantContextHolder {

    private static final ThreadLocal<TenantContext> CONTEXT = new ThreadLocal<>();


    public static TenantContext get() { return CONTEXT.get();

    }
    public static void set(TenantContext tenantContext) {
        CONTEXT.set(tenantContext);

    }
    public static void clear() {
        CONTEXT.remove(); // IMPORTANTÍSIMO
    }

}
