package com.pick.nalsoom.jwt;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pick.nalsoom.Domain.User.User;

public class UserDetailsImpl implements UserDetails {

    private final String userId;
    private final String userPw;

    public UserDetailsImpl(String userId) {
        this.userId = userId;
        this.userPw = "";
    }

    public UserDetailsImpl(User user) {
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    
}
