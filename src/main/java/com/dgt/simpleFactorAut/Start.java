/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dgt.simpleFactorAut;

import com.dgt.simpleFactorAut.model.ApiUser;
import com.dgt.simpleFactorAut.repo.ApiUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author miroslawk
 */
//@Configuration
public class Start {
    private ApiUserRepo apiUserRepo;
    private PasswordEncoder passwordEncoder;
//@Autowired
    public Start(ApiUserRepo apiUserRepo, PasswordEncoder passwordEncoder) {
        this.apiUserRepo = apiUserRepo;
        this.passwordEncoder = passwordEncoder;
        ApiUser apiUser=new ApiUser("Polimat", "ROLE_ADMIN");
        apiUser.setPassword(passwordEncoder.encode("Mirek"));
        apiUser.setIsEnable(true);
        apiUserRepo.save(apiUser);
        
        apiUser=new ApiUser("dgt", "ROLE_USER");
        apiUser.setIsEnable(true);
        apiUser.setPassword(passwordEncoder.encode("Mirek"));
        apiUserRepo.save(apiUser);
    }

}
