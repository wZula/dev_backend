/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.controllers;

import com.nimbusds.jose.JOSEException;
import com.tourgoat.users.models.User;
import com.tourgoat.users.services.UserService;
import com.tourgoat.users.utils.AuthUtils;
import static com.tourgoat.users.utils.AuthenticationConstantMesg.CONFLICT_MSG;
import static com.tourgoat.users.utils.AuthenticationConstantMesg.NOT_FOUND_MSG;
import com.tourgoat.users.utils.DateService;
import com.tourgoat.users.utils.Token;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author fitsum
 */
@Component
public class UserProcessorImpl implements UserProcessor {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity processUser(final HttpServletRequest request, final User.Provider provider,
            final String id, final String fullName, final String email, final String picture,
            final String firstName, final String lastName, final Date dateOfBirth,final String gender)
            throws JOSEException, ParseException {

        User user = null;
        switch (provider) {
            case FACEBOOK:
                user = userService.findByFacebook(id);
                break;
            case GOOGLE:
                user = userService.findByGoogle(id);
                break;
            default:
                return new ResponseEntity<>("Unknown OAUTH2.0 Provider", HttpStatus.NOT_FOUND);
        }

        //If not found by provider try to find it by email
        if (user == null && StringUtils.isNotEmpty(email)) {
            user = userService.findByEmail(email);
        }

        // Step 3a. If user is already signed in then link accounts.
        User userToSave;
        final String authHeader = request.getHeader(AuthUtils.AUTH_HEADER_KEY);
        if (StringUtils.isNotBlank(authHeader)) {
            if (user == null) {
                return new ResponseEntity<>(String.format(CONFLICT_MSG, provider.capitalize()),
                        HttpStatus.CONFLICT);
            }
            final String subject = AuthUtils.getSubject(authHeader);
            final User foundUser = userService.findOne(subject);
            if (foundUser == null) {
                return new ResponseEntity<>(NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
            }

            userToSave = foundUser;
            boolean updated = setUserProvider(provider, userToSave, id);
            if (userToSave.getFullName() == null) {
                userToSave.setFullName(fullName);
                updated = true;
            }
            if (userToSave.getPicture() == null) {
                userToSave.setPicture(picture);
                updated = true;
            }

            if (updated) {
                userToSave = userService.save(userToSave);
            }
        } else {
            // Step 3b. Create a new user account or return an existing one.
            if (user != null) {
                userToSave = user;
                if (setUserProvider(provider, userToSave, id)) {
                    if (userToSave.getPicture() == null) {
                        userToSave.setPicture(picture);
                    }
                    userToSave = userService.save(userToSave);
                }
            } else {
                userToSave = new User();
                userToSave.setUserId(UUID.randomUUID().toString());
                userToSave.setFullName(fullName);
                userToSave.setEmail(email);
                userToSave.setFirstName(firstName);
                userToSave.setPicture(picture);
                userToSave.setLastName(lastName);
                userToSave.setDateOfBirth(dateOfBirth); 
                userToSave.setCreatedDate(DateService.getTodayDate());
                userToSave.setIsAccountActive(true);
                userToSave.setGender(gender);
                setUserProvider(provider, userToSave, id);
                if(provider.getName().equalsIgnoreCase("facebook") || provider.getName().equalsIgnoreCase("google")){
                   userToSave.setIsEmailVerification(true);
                   userToSave.setPassword("N/A");
                }else{
                  userToSave.setIsEmailVerification(false);  
                }
                userToSave = userService.save(userToSave);
            }
        }

        Token token = AuthUtils.createToken(request.getRemoteHost(), userToSave.getUserId());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    private boolean setUserProvider(User.Provider provider, User userToSave, String id) {
        boolean updated = false;
        switch (provider) {
            case FACEBOOK:
                if (userToSave.getFacebook() == null) {
                    userToSave.setFacebook(id);
                    updated = true;
                }
                break;
            case GOOGLE:
                if (userToSave.getGoogle() == null) {
                    userToSave.setGoogle(id);
                    updated = true;
                }
                break;
        }
        return updated;
    }
}
