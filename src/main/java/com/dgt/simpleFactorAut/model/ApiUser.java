/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dgt.simpleFactorAut.model;

import java.util.Collection;
import java.util.Collections;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author miroslawk
 */
@Entity
public class ApiUser implements UserDetails{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String userrole;
    private boolean isEnable;


    public ApiUser() {
    }

    public ApiUser(String username, String userrole) {
        this.username = username;
        this.userrole = userrole;
        password="";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userrole));
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
        return isEnable;
    }
    
    
}
