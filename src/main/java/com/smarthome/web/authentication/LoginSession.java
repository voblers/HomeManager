/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.web.authentication;

import com.smarthome.crypto.PBKDF2WithHmacSHA1Validator;
import com.smarthome.hibernate.user.dao.impl.UserDaoImpl;
import com.smarthome.hibernate.user.model.User;
import com.smarthome.hibernate.user.model.UserNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 *
 * @author Kaspa
 */
public class LoginSession extends AuthenticatedWebSession {

    private String user;

    public LoginSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String username, String password) {
        UserDaoImpl userDao = new UserDaoImpl();
        User newUser;
        try {
            newUser = userDao.findByUserName(username);

            PBKDF2WithHmacSHA1Validator validator = new PBKDF2WithHmacSHA1Validator(password, newUser.getUSER_PASSWORD(), newUser.getUSER_SALT());

            if (validator.validate()) {
                return true;
            } else {
                Logger.getLogger(UserAuthenticator.class.getName()).log(Level.INFO, "Failed login attempt");
                return false;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | UserNotFoundException ex) {
            Logger.getLogger(UserAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public Roles getRoles() {
        return null;
    }

}
