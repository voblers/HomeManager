/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate.user.dao;

import com.smarthome.hibernate.exceptions.HibernateDeleteException;
import com.smarthome.hibernate.exceptions.HibernateSaveException;
import com.smarthome.hibernate.exceptions.HibernateUpdateException;
import com.smarthome.hibernate.user.model.User;
import com.smarthome.hibernate.user.model.UserNotFoundException;

/**
 *
 * @author Kaspa
 */
public interface UserDao {
    void save(User user) throws HibernateSaveException;
    void update(User user) throws HibernateUpdateException;
    void delete(User user) throws HibernateDeleteException;
    User findByUserName(String userName) throws UserNotFoundException;
}
