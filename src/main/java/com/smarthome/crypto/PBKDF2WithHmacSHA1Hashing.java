/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.crypto;

import com.smarthome.crypto.beans.HashBean;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Kaspa
 */
public class PBKDF2WithHmacSHA1Hashing {

    private final int iterations;
    private final String algorithm;
    private final int length;

    private String password;

    public PBKDF2WithHmacSHA1Hashing() {
        this.iterations = 10000;
        this.algorithm = "PBKDF2WithHmacSHA1";
        this.length = 64;
    }

    public PBKDF2WithHmacSHA1Hashing(int iterations, String algorithm, int length) {
        this.iterations = iterations;
        this.algorithm = algorithm;
        this.length = length;
    }

    public PBKDF2WithHmacSHA1Hashing(int iterations, String algorithm, int length, String password) {
        this.iterations = iterations;
        this.algorithm = algorithm;
        this.length = length;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoSaltHash() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (this.password.length() == 0) {
            throw new IllegalArgumentException("Password parameter is not initialized");
        }

        PBEKeySpec spec = new PBEKeySpec(this.password.toCharArray());
        SecretKeyFactory skf = SecretKeyFactory.getInstance(this.algorithm);
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return toHex(hash);
    }

    public HashBean getHash() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (this.password.length() == 0) {
            throw new IllegalArgumentException("Password parameter is not initialized");
        }

        HashBean hashObj = new HashBean();
        hashObj.setSalt(getSalt());

        PBEKeySpec spec = new PBEKeySpec(this.password.toCharArray(), hashObj.getSalt(), this.iterations, this.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        hashObj.setHash(this.iterations + ":" + toHex(skf.generateSecret(spec).getEncoded()));
        
        return hashObj;
    }

    private String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
