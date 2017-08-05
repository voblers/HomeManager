/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.crypto.beans;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Kaspa
 */
public class HashBean implements Serializable {

    private byte[] salt;
    private String hash;

    public HashBean() {
    }

    public HashBean(byte[] salt, String hash) {
        this.salt = salt;
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }
    
    public String getStringSalt(){
        BigInteger bi = new BigInteger(1, getSalt());
        String hex = bi.toString(16);
        int paddingLength = (getSalt().length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

}
