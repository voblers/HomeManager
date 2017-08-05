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
public class HibernateUpdateException extends Exception{

    public HibernateUpdateException() {
    }

    public HibernateUpdateException(String message) {
        super(message);
    }

    public HibernateUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public HibernateUpdateException(Throwable cause) {
        super(cause);
    }
}
