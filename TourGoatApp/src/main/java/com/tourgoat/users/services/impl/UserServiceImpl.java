/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.services.impl;

import com.tourgoat.users.models.User;
import com.tourgoat.users.repositories.UserRepository;
import com.tourgoat.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fitsum
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByFacebook(String id) {
        return this.userRepository.findByFacebook(id);
    }

    @Override
    public User findByGoogle(String id) {
        return this.userRepository.findByGoogle(id);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User findOne(String subject) {
        return this.userRepository.findOne(subject);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findByUserId(String userId) {
        return this.userRepository.findByUserId(userId);
    }

}
