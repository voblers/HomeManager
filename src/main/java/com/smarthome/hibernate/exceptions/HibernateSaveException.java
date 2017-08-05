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
public class HibernateSaveException extends Exception {

    public HibernateSaveException() {
    }

    public HibernateSaveException(String message) {
        super(message);
    }

    public HibernateSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public HibernateSaveException(Throwable cause) {
        super(cause);
    }
}
