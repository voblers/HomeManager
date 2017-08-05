/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SESSIONFACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSIONFACTORY;
    }

    public static void saveAndCommit(Object obj) throws Exception {
        try (Session transactionSession = getSessionFactory().openSession()) {
            transactionSession.beginTransaction();
            transactionSession.save(obj);
            transactionSession.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public static void saveOrUpdateAndCommit(Object obj) throws Exception {
        try (Session transactionSession = getSessionFactory().openSession()) {
            transactionSession.beginTransaction();
            transactionSession.saveOrUpdate(obj);
            transactionSession.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public static void updateAndCommit(Object obj) throws Exception {
        try (Session transactionSession = getSessionFactory().openSession()) {
            transactionSession.beginTransaction();
            transactionSession.update(obj);
            transactionSession.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public static void deleteAndCommit(Object obj) throws Exception {
        try (Session transactionSession = getSessionFactory().openSession()) {
            transactionSession.beginTransaction();
            transactionSession.delete(obj);
            transactionSession.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public static void mergeAndCommit(Object obj) throws Exception {
        try (Session transactionSession = getSessionFactory().openSession()) {
            transactionSession.beginTransaction();
            transactionSession.merge(obj);
            transactionSession.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    public static Session openNewSession(){
        return getSessionFactory().openSession();
    }
    
    public static Session getCurrentSession(){
        return getSessionFactory().getCurrentSession();
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
