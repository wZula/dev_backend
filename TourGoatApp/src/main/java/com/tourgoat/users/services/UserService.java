/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.services;

import com.tourgoat.users.models.User;

/**
 *
 * @author fitsum
 */
public interface UserService {

    User findByFacebook(String id);

    User findByGoogle(String id);

    User findByEmail(String email);

    User findOne(String subject);

    User save(User user);
    
    User findByUserId(String userId);
    
    User findBySessionId(String userId);
}
