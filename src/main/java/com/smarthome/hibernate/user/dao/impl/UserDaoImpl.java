/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate.user.dao.impl;

import com.smarthome.hibernate.HibernateUtil;
import com.smarthome.hibernate.exceptions.HibernateDeleteException;
import com.smarthome.hibernate.exceptions.HibernateSaveException;
import com.smarthome.hibernate.exceptions.HibernateUpdateException;
import com.smarthome.hibernate.user.dao.UserDao;
import com.smarthome.hibernate.user.model.User;
import com.smarthome.hibernate.user.model.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Kaspa
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) throws HibernateSaveException {
        try {
            HibernateUtil.saveAndCommit(user);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new HibernateSaveException();
        }
    }

    @Override
    public void update(User user) throws HibernateUpdateException {
        try {
            HibernateUtil.updateAndCommit(user);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new HibernateUpdateException();
        }
    }

    @Override
    public void delete(User user) throws HibernateDeleteException {
        try {
            HibernateUtil.deleteAndCommit(user);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new HibernateDeleteException();
        }
    }

    @Override
    public User findByUserName(String userName) throws UserNotFoundException {
        Session session = HibernateUtil.openNewSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> from = criteria.from(User.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get("USER_NAME"), userName));

        List<User> resultList = session.createQuery(criteria).getResultList();

        if (resultList == null || resultList.size() < 1) {
            throw new UserNotFoundException();
        } else {
            return session.createQuery(criteria).getResultList().get(0);
        }
    }

}
