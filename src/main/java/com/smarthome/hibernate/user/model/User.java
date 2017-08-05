/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate.user.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kaspa
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int USER_ID;

    @Column(unique = true)
    private String USER_NAME;
    
    private String USER_PASSWORD;
    private String USER_SALT;
    private String USER_EMAIL;

    @Temporal(TemporalType.TIMESTAMP)
    private Date USER_CREATED;

    //1-ACTIVE; 0-NON-ACTIVE
    private int USER_STATUS;

    public User() {
    }

    public User(int USER_ID, String USER_NAME, String USER_PASSWORD, String USER_SALT, String USER_EMAIL, Date USER_CREATED, int USER_STATUS) {
        this.USER_ID = USER_ID;
        this.USER_NAME = USER_NAME;
        this.USER_PASSWORD = USER_PASSWORD;
        this.USER_SALT = USER_SALT;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_CREATED = USER_CREATED;
        this.USER_STATUS = USER_STATUS;
    }
    
    @PrePersist
    protected void onCreate(){
        if(USER_CREATED == null){
            USER_CREATED = new Date();
        }
    }

    public Date getUSER_CREATED() {
        return USER_CREATED;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public String getUSER_SALT() {
        return USER_SALT;
    }

    public int getUSER_STATUS() {
        return USER_STATUS;
    }

    public void setUSER_CREATED(Date USER_CREATED) {
        this.USER_CREATED = USER_CREATED;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public void setUSER_SALT(String USER_SALT) {
        this.USER_SALT = USER_SALT;
    }

    public void setUSER_STATUS(int USER_STATUS) {
        this.USER_STATUS = USER_STATUS;
    }

}
