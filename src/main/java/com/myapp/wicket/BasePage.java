/*
 * WicketExamplePage.java
 *
 * Created on February 14, 2017, 5:28 PM
 */
 
package com.myapp.wicket;           

import org.apache.wicket.markup.html.WebPage;

/** 
 *
 * @author BB3605
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        add(new HeaderPanel("headerpanel", "Welcome To Wicket")); 
        add(new FooterPanel("footerpanel", "Powered by Wicket and the NetBeans Wicket Plugin"));
    } 

}
