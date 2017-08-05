/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate.exceptions;

/**
 *
 * @author Kaspa
 */
public class HibernateDeleteException extends Exception {

    public HibernateDeleteException() {
    }

    public HibernateDeleteException(String message) {
        super(message);
    }

    public HibernateDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public HibernateDeleteException(Throwable cause) {
        super(cause);
    }
}
