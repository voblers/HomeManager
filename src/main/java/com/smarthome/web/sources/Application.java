/*
 * Application.java
 *
 * Created on February 14, 2017, 5:28 PM
 */
 
package com.smarthome.web.sources;           

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.def.RequestCycleSettings;
/** 
 *
 * @author BB3605
 * @version 
 */

public class Application extends WebApplication {

    public Application() {
    }

    @Override
    public Class getHomePage() {
        getRequestCycleSettings().setRenderStrategy(RequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
        return Login.class;
    }

}
