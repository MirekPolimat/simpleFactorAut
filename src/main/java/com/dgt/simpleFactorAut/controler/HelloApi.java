/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dgt.simpleFactorAut.controler;

import com.dgt.simpleFactorAut.EndPoint;
import com.dgt.simpleFactorAut.model.ApiUser;
import com.dgt.simpleFactorAut.service.UserManager;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author miroslawk
 */
//@RestController
@Controller
public class HelloApi {
    UserManager userManager;

    @Autowired
    public HelloApi(UserManager userManager) {
        this.userManager = userManager;
    }
    
    @GetMapping("/h")
    @ResponseBody
    public String hello1(){
        return "czesc";
    }
    @GetMapping(EndPoint.HELLO_API)
    public String hello(Model m){
        Authentication details= SecurityContextHolder.getContext().getAuthentication();
        WebAuthenticationDetails webAuthenticationDetails=(WebAuthenticationDetails) details.getDetails();
        ApiUser apiUser=(ApiUser)details.getPrincipal();
        String message="Hello "+apiUser.getAuthorities();
        m.addAttribute("message", message);
        return "hello";
    }
    @GetMapping(EndPoint.HELLO_API_FUNCTION)
    public String helloF(Principal p,Model m){
        m.addAttribute("name",p.getName());
        return "helloFunction";
    }
    @GetMapping("/sign-up")
    public String signup(Model m){
        m.addAttribute("user", new ApiUser());
        return "sign-up";
    }
     @PostMapping("/register")
    public String register(ApiUser apiUser){
        userManager.addUser(apiUser);
        return "sign-up";
    }
    
   @GetMapping("/token")
    public String confirm(@RequestParam String value,Model m){
        ApiUser apiUser=userManager.checkToken(value);
        if (apiUser!=null){
             m.addAttribute("message","Witam : "+apiUser.getUsername());
        }else{
             m.addAttribute("message"," Nie witam Błędny Rycerzu");
        }
        return "hello";
    }
}
