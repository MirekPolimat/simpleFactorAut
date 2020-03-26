/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dgt.simpleFactorAut.service;

import com.dgt.simpleFactorAut.model.ApiUser;
import com.dgt.simpleFactorAut.model.TokenApiUser;
import com.dgt.simpleFactorAut.repo.ApiUserRepo;
import com.dgt.simpleFactorAut.repo.TokenRepo;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author miroslawk
 */
@Service
public class UserManager {
    private ApiUserRepo apiUserRepo;
    private TokenRepo tokenRepo;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;
@Autowired
    public UserManager(ApiUserRepo apiUserRepo, TokenRepo tokenRepo, PasswordEncoder passwordEncoder, MailService mailService) {
        this.apiUserRepo = apiUserRepo;
        this.tokenRepo = tokenRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public void addUser(ApiUser apiUser){
        apiUser.setPassword(passwordEncoder.encode(apiUser.getPassword()));
        apiUser.setUserrole("ROLE_USER");
        apiUserRepo.save(apiUser);
        sendToken(apiUser);
    }
    public ApiUser checkToken(String value){
        Optional<TokenApiUser> token=tokenRepo.findByValueToken(value);
        if (token.isPresent()){
            TokenApiUser tokenApiUser=token.get();
            ApiUser apiUser=tokenApiUser.getApiUser();
            apiUser.setIsEnable(true);
            apiUserRepo.save(apiUser);
            return apiUser;
        }
        return null;
    }
    private void sendToken(ApiUser apiUser){
        String tokenValue=UUID.randomUUID().toString();
        TokenApiUser token=new TokenApiUser();
        token.setApiUser(apiUser);
        token.setValueToken(tokenValue);
        tokenRepo.save(token);
        String url="http://localhost:8080/token?value="+tokenValue;
        try {
            mailService.sendMail(apiUser.getUsername(), "Potwierdzenie logowania ", url, false);
        } catch (MessagingException ex) {
            System.out.println("@@@@"+ex.getMessage());
        }
        
    }
    
}
