/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.web.sources;

import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author Kaspa
 */
public class main_base extends WebPage{
    public main_base(){
        super();
        add(new login_header("login_header"));
        System.out.println("Base page class loaded");
    }
}
