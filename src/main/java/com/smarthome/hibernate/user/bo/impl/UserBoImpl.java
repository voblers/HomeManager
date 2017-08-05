/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate.user.bo.impl;

import com.smarthome.hibernate.exceptions.HibernateDeleteException;
import com.smarthome.hibernate.exceptions.HibernateSaveException;
import com.smarthome.hibernate.exceptions.HibernateUpdateException;
import com.smarthome.hibernate.user.bo.UserBo;
import com.smarthome.hibernate.user.dao.UserDao;
import com.smarthome.hibernate.user.model.User;
import com.smarthome.hibernate.user.model.UserNotFoundException;

/**
 *
 * @author Kaspa
 */
public class UserBoImpl implements UserBo {

    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) throws HibernateSaveException {
        this.userDao.save(user);
    }

    @Override
    public void update(User user) throws HibernateUpdateException {
        this.userDao.update(user);
    }

    @Override
    public void delete(User user) throws HibernateDeleteException {
        this.userDao.delete(user);
    }

    @Override
    public User findByUserName(String userName) throws UserNotFoundException {
        return userDao.findByUserName(userName);
    }

}
