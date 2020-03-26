/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dgt.simpleFactorAut.repo;

import com.dgt.simpleFactorAut.model.ApiUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author miroslawk
 */
@Repository
public interface ApiUserRepo  extends JpaRepository<ApiUser, Long>{
    
    Optional<ApiUser> findByUsername(String username);
}
