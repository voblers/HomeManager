/*
 * Application.java
 *
 * Created on February 14, 2017, 5:28 PM
 */
 
package com.myapp.wicket;           

import org.apache.wicket.protocol.http.WebApplication;
/** 
 *
 * @author BB3605
 * @version 
 */

public class Application extends WebApplication {

    public Application() {
    }

    public Class getHomePage() {
        return HomePage.class;
    }

}
