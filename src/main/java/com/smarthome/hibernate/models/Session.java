/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BB3605
 */
@Entity
@Table(name = "SESSIONS")
public class Session implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String sessionID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionRqStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionRqEnd;

    private String sessionIP;
    private int sessionPort;
    private String sessionReqURL;
    private String requestParamaters;

    public Session() {
    }

    public Session(int ID, String sessionID, Date sessionRqStart, Date sessionRqEnd, String sessionIP, int sessionPort, String sessionReqURL, String requestParamaters) {
        this.ID = ID;
        this.sessionID = sessionID;
        this.sessionRqStart = sessionRqStart;
        this.sessionRqEnd = sessionRqEnd;
        this.sessionIP = sessionIP;
        this.sessionPort = sessionPort;
        this.sessionReqURL = sessionReqURL;
        this.requestParamaters = requestParamaters;
    }

    public int getID() {
        return ID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSessionIP() {
        return sessionIP;
    }

    public int getSessionPort() {
        return sessionPort;
    }

    public String getSessionReqURL() {
        return sessionReqURL;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public void setSessionIP(String sessionIP) {
        this.sessionIP = sessionIP;
    }

    public void setSessionPort(int sessionPort) {
        this.sessionPort = sessionPort;
    }

    public void setSessionReqURL(String sessionReqURL) {
        this.sessionReqURL = sessionReqURL;
    }

    public Date getSessionRqEnd() {
        return sessionRqEnd;
    }

    public Date getSessionRqStart() {
        return sessionRqStart;
    }

    public void setSessionRqEnd(Date sessionRqEnd) {
        this.sessionRqEnd = sessionRqEnd;
    }

    public void setSessionRqStart(Date sessionRqStart) {
        this.sessionRqStart = sessionRqStart;
    }

    public String getRequestParamaters() {
        return requestParamaters;
    }

    public void setRequestParamaters(String requestParamaters) {
        this.requestParamaters = requestParamaters;
    }

}
