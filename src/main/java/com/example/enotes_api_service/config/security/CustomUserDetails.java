package com.example.enotes_api_service.config.security;

import com.example.enotes_api_service.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private User user;

    //param cons
    public CustomUserDetails(User user){
        super();
        this.user=user;

    }


    public User getUser(){
        return this.user;
    }



    //get the roles from entity class
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authority = new ArrayList<>();
        //get role from user
        //for each role
        user.getRoles().forEach(r->{
            //add role r.getname() to the arraylist named authority
            //which is of the type simpleGrantedAuthority()
           authority.add(new SimpleGrantedAuthority("ROLE_"+r.getName().toUpperCase()));
        });
        return authority;
    }


    //get the password from entity
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //get username i.e. email from the entity
    @Override
    public String getUsername() {
        return user.getEmail() ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}