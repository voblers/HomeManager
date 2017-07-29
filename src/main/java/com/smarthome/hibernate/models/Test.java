/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Kaspa
 */
@Entity
@Table
public class Test implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String val;

    public Test() {
    }

    public Test(Integer id, String value) {
        this.id = id;
        this.val = value;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.val = value;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return val;
    }

}
