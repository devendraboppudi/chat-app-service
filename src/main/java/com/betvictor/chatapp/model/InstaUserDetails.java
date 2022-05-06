package com.betvictor.chatapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


public class InstaUserDetails extends User implements UserDetails {

    public InstaUserDetails(final User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return java.util.Collections.singleton(new SimpleGrantedAuthority("ROLE_" +getRole()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isActive();
    }

    @Override
    public boolean isEnabled() {
        return this.isActive();
    }
}
