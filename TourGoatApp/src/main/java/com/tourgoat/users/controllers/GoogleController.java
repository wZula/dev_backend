/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nimbusds.jose.JOSEException;
import com.tourgoat.users.controller.helper.JsonHelper;
import static com.tourgoat.users.controllers.OAuthConstants.AUTH_CODE;
import static com.tourgoat.users.controllers.OAuthConstants.CLIENT_ID_KEY;
import static com.tourgoat.users.controllers.OAuthConstants.CLIENT_SECRET;
import static com.tourgoat.users.controllers.OAuthConstants.CODE_KEY;
import static com.tourgoat.users.controllers.OAuthConstants.GRANT_TYPE_KEY;
import static com.tourgoat.users.controllers.OAuthConstants.REDIRECT_URI_KEY;
import com.tourgoat.users.models.User;
import com.tourgoat.users.utils.AuthUtils;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fitsum
 */
@RestController
public class GoogleController {

    private static final Logger LOG = Logger.getLogger(GoogleController.class);

    @Value("${google.secret}")
    private String googleSecret;
    
     @Value("${google.accessTokenUrl}")
    private String accessTokenUrl;
     
      @Value("${google.peopleApiUrl}")
    private String peopleApiUrl;

    private final Client restClient;

    @Autowired
    private UserProcessor userProcessor;

    private final JsonHelper jsonHelper = new JsonHelper();

    public GoogleController() {
        restClient = ClientBuilder.newClient();
    }

    @RequestMapping("/auth/google")
    public ResponseEntity loginGoogle(@RequestBody final Payload payload,
            @Context final HttpServletRequest request) throws JOSEException, ParseException,
            JsonParseException, JsonMappingException, IOException {
        Response response;

        // Step 1. Exchange authorization code for access token.
        final MultivaluedMap<String, String> accessData = new MultivaluedHashMap<>();
        accessData.add(CLIENT_ID_KEY, payload.getClientId());
        accessData.add(REDIRECT_URI_KEY, payload.getRedirectUri());
        accessData.add(CLIENT_SECRET, googleSecret);
        accessData.add(CODE_KEY, payload.getCode());
        accessData.add(GRANT_TYPE_KEY, AUTH_CODE);
        response = restClient.target(accessTokenUrl).request().post(Entity.form(accessData));
        accessData.clear();

        // Step 2. Retrieve profile information about the current user.
        final String accessToken = (String) jsonHelper.getResponseEntity(response).get("access_token");
        response
                = restClient.target(peopleApiUrl).request("text/plain")
                .header(AuthUtils.AUTH_HEADER_KEY, String.format("Bearer %s", accessToken)).get();
        final Map<String, Object> userInfo = jsonHelper.getResponseEntity(response);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Google UserInfo: '" + userInfo + "'");
        }
        // Step 3. Process the authenticated the user.
        return userProcessor.processUser(request, User.Provider.GOOGLE, userInfo.get("sub").toString(),
                userInfo.get("name").toString(), userInfo.get("email").toString(),
                userInfo.get("picture").toString(),
                userInfo.get("name").toString(),
                userInfo.get("given_name").toString(),
                userInfo.get("family_name").toString());
    }

}
