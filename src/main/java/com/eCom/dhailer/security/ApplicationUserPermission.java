package com.eCom.dhailer.security;

public enum ApplicationUserPermission {

    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),
    PRODUCT_READ("product:write"),
    PRODUCT_WRITE("product:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
