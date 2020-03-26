/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dgt.simpleFactorAut.service;

import com.dgt.simpleFactorAut.model.ApiUser;
import com.dgt.simpleFactorAut.repo.ApiUserRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author miroslawk
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService{
private ApiUserRepo apiUserRepo;

    @Autowired
    public UserDetailsServiceImp(ApiUserRepo apiUserRepo) {
        this.apiUserRepo = apiUserRepo;
    }
        
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
         Optional<ApiUser> user=apiUserRepo.findByUsername(string);
         if (user.isPresent())
            return user.get();
         
         return new ApiUser("Anonim", "ROLE_USER");
    }
    
}
