/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.web.authentication;

import com.smarthome.crypto.PBKDF2WithHmacSHA1Hashing;
import com.smarthome.crypto.PBKDF2WithHmacSHA1Validator;
import com.smarthome.crypto.beans.HashBean;
import com.smarthome.hibernate.exceptions.HibernateSaveException;
import com.smarthome.hibernate.user.bo.impl.UserBoImpl;
import com.smarthome.hibernate.user.dao.impl.UserDaoImpl;
import com.smarthome.hibernate.user.model.User;
import com.smarthome.hibernate.user.model.UserNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kaspa
 */
public class UserAuthenticator {

    public UserAuthenticator() {
    }

    public boolean authenticate(String userName, String userPass) {
        return false;
        /*UserDaoImpl userDao = new UserDaoImpl();
        User user;
        try {
            user = userDao.findByUserName(userName);

            PBKDF2WithHmacSHA1Validator validator = new PBKDF2WithHmacSHA1Validator(userPass, user.getUSER_PASSWORD(), user.getUSER_SALT());

            if (validator.validate()) {
                return true;
            } else {
                Logger.getLogger(UserAuthenticator.class.getName()).log(Level.INFO, "Failed login attempt");
                return false;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | UserNotFoundException ex) {
            Logger.getLogger(UserAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;*/
    }

    public boolean registerUser(String userName, String userPass, String userEmail) {
        try {
            PBKDF2WithHmacSHA1Hashing passHasher = new PBKDF2WithHmacSHA1Hashing();
            passHasher.setPassword(userPass);
            HashBean hashBean = passHasher.getHash();

            UserBoImpl userBo = new UserBoImpl();
            userBo.setUserDao(new UserDaoImpl());

            User newUser = new User();

            newUser.setUSER_NAME(userName);
            newUser.setUSER_PASSWORD(hashBean.getHash());
            newUser.setUSER_SALT(hashBean.getStringSalt());
            newUser.setUSER_EMAIL(userEmail);
            newUser.setUSER_STATUS(1);

            PBKDF2WithHmacSHA1Validator validator = new PBKDF2WithHmacSHA1Validator(userPass, newUser.getUSER_PASSWORD(), newUser.getUSER_SALT());

            if (validator.validate()) {
                userBo.save(newUser);
                Logger.getLogger(UserAuthenticator.class.getName()).log(Level.INFO, "New user created");
                return true;
            } else {
                Logger.getLogger(UserAuthenticator.class.getName()).log(Level.WARNING, "Failed to validate password for the new user");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | HibernateSaveException ex) {
            Logger.getLogger(UserAuthenticator.class.getName()).log(Level.SEVERE, "User registration failed!");
            Logger.getLogger(UserAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
