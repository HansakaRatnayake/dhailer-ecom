package com.eCom.dhailer.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {

    USER(Sets.newHashSet(
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.CUSTOMER_WRITE)
    ),

    ADMIN(Sets.newHashSet(
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.CUSTOMER_WRITE,
            ApplicationUserPermission.ORDER_WRITE,
            ApplicationUserPermission.ORDER_READ,
            ApplicationUserPermission.PRODUCT_READ,
            ApplicationUserPermission.PRODUCT_WRITE)
    ),

    CUSTOMER(Sets.newHashSet(
            ApplicationUserPermission.PRODUCT_READ,
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.ORDER_READ)
    ),

    MANAGER(Sets.newHashSet(
            ApplicationUserPermission.ORDER_WRITE,
            ApplicationUserPermission.ORDER_READ,
            ApplicationUserPermission.PRODUCT_READ)
    ),

    CASHIER(Sets.newHashSet(
            ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.ORDER_WRITE,
            ApplicationUserPermission.ORDER_READ)
    );


    private final Set<ApplicationUserPermission> applicationUserPermissions;

    ApplicationUserRole(Set<ApplicationUserPermission> applicationUserPermissions) {
        this.applicationUserPermissions = applicationUserPermissions;
    }

    public Set<ApplicationUserPermission> getApplicationUserPermissions() {
        return applicationUserPermissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getApplicationUserPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;

    }
}
