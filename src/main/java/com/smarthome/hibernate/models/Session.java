/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.hibernate.models;

/**
 *
 * @author BB3605
 */
public class Session implements java.io.Serializable {
    private String sessionID;
    private long sessionRqStart;
    private long sessionRqEnd;
    private String sessionIP;
    private int sessionPort;
    private String sessionReqURL;

    public Session() {
    }

    public Session(String sessionID, long sessionRqStart, long sessionRqEnd, String sessionIP, int sessionPort, String sessionReqURL) {
        this.sessionID = sessionID;
        this.sessionRqStart = sessionRqStart;
        this.sessionRqEnd = sessionRqEnd;
        this.sessionIP = sessionIP;
        this.sessionPort = sessionPort;
        this.sessionReqURL = sessionReqURL;
    }

    public String getSessionID() {
        return sessionID;
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

    public long getSessionRqEnd() {
        return sessionRqEnd;
    }

    public long getSessionRqStart() {
        return sessionRqStart;
    }

    public void setSessionRqEnd(long sessionRqEnd) {
        this.sessionRqEnd = sessionRqEnd;
    }

    public void setSessionRqStart(long sessionRqStart) {
        this.sessionRqStart = sessionRqStart;
    }
}
