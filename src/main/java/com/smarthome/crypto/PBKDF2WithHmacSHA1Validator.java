/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Kaspa
 */
public class PBKDF2WithHmacSHA1Validator {

    private final String originalPassw, storedPassw;
    private String storedSalt = null;

    public PBKDF2WithHmacSHA1Validator(String originalPassw, String storedPassw) {
        this.originalPassw = originalPassw;
        this.storedPassw = storedPassw;
    }

    public PBKDF2WithHmacSHA1Validator(String originalPassw, String storedPassw, String storedSalt) {
        this.originalPassw = originalPassw;
        this.storedPassw = storedPassw;

        this.storedSalt = storedSalt;
    }

    public boolean validate() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassw.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] hash = fromHex(parts[1]);

        PBEKeySpec spec;
        if (this.storedSalt == null) {
            spec = new PBEKeySpec(originalPassw.toCharArray());
        } else {
            spec = new PBEKeySpec(originalPassw.toCharArray(), fromHex(this.storedSalt), iterations, hash.length * 8);
        }
        
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    private byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
