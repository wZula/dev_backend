/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.repositories;

import com.tourgoat.users.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fitsum
 */
public interface UserRepository extends CrudRepository<User, String> {

    public User findByFacebook(String id);

    public User findByGoogle(String id);

    public User findByEmail(String email);
    
    public User findByUserId(String userId);
    
    public User findBySessionId(String sessionId);
}
