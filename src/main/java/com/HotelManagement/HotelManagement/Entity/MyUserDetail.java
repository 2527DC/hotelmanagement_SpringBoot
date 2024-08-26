package com.HotelManagement.HotelManagement.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import java.util.stream.Collectors;

public class MyUserDetail implements UserDetails {

    private final User user;

    public MyUserDetail(User user){
            this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getRolename()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }


}
