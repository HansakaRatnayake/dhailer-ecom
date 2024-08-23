package com.eCom.dhailer.auth;


import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
public class ApplicationUserDetail implements UserDetails {

    private final Collection<? extends GrantedAuthority> authorities;
    private final String username;
    private final String password;
    private final boolean isEnabled;
    private final boolean isAccountNonExpired;
    private final boolean isCredentialsNonExpired;
    private final boolean isAccountNonLocked;

    public ApplicationUserDetail(Collection<? extends GrantedAuthority> authorities, String username, String password,
                                 boolean isEnabled, boolean isAccountNonExpired, boolean isCredentialsNonExpired, boolean isAccountNonLocked) {
        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
