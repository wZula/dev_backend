/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.controllers;

import com.nimbusds.jose.JOSEException;
import com.tourgoat.users.models.User;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author fitsum
 */
public interface UserProcessor {

     ResponseEntity processUser(final HttpServletRequest request, final User.Provider provider,
            final String id, final String displayName, final String email, final String picture,
             final String givenName, final String familyName, final Date dateOfBirth,final String gender)
            throws JOSEException, ParseException;
     
}
