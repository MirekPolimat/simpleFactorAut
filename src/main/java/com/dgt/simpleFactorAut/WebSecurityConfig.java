/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dgt.simpleFactorAut;

import com.dgt.simpleFactorAut.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author miroslawk
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
private UserDetailsServiceImp userDetailsServiceImp;
    @Bean
    public PasswordEncoder getpasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public WebSecurityConfig(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/h").permitAll()
                .antMatchers(EndPoint.HELLO_API).hasAnyRole("ADMIN","USER")
                .antMatchers(EndPoint.HELLO_API_FUNCTION).hasAnyRole("ADMIN","USER")
                .and()
                .formLogin().defaultSuccessUrl(EndPoint.HELLO_API_FUNCTION);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp);
    }
    
}
