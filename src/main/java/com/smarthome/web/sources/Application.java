/*
 * Application.java
 *
 * Created on February 14, 2017, 5:28 PM
 */
package com.smarthome.web.sources;

import com.smarthome.hibernate.HibernateUtil;
import com.smarthome.utilities.ApplicationUtilities;
import com.smarthome.web.authentication.AuthenticatedWebPage;
import com.smarthome.web.authentication.LoginSession;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.settings.SecuritySettings;
import org.hibernate.Session;

/**
 *
 * @author BB3605
 * @version
 */
public class Application extends WebApplication {

    @Override
    protected void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        SecuritySettings secSet = getSecuritySettings();
        secSet.setEnforceMounts(true);

        mountPage("Login", Login.class);
        mountPage("MainInterface", MainInterface.class);

        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy.AllowAllAuthorizationStrategy() {
            @Override
            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
                    Class<T> componentClass) {
                if (AuthenticatedWebPage.class.isAssignableFrom(componentClass)) {
                    // Is user signed in?
                    if (((LoginSession) org.apache.wicket.Session.get()).isSignedIn()) {
                        // okay to proceed
                        return true;
                    }

                    // Intercept the request, but remember the target for later.
                    // Invoke Component.continueToOriginalDestination() after successful logon to
                    // continue with the target remembered.
                    throw new RestartResponseAtInterceptPageException(Login.class);
                }

                // okay to proceed
                return true;
            }
        });
    }

    public Application() {
        getRequestCycleListeners().add(new IRequestCycleListener() {
            private com.smarthome.hibernate.models.Session session;

            @Override
            public void onBeginRequest(RequestCycle cycle) {
                WebRequest req = (WebRequest) cycle.get().getRequest();
                HttpServletRequest httpReq = (HttpServletRequest) req.getContainerRequest();

                session = new com.smarthome.hibernate.models.Session();

                session.setSessionID(getSessionStore().getSessionId(req, true));
                session.setSessionIP(httpReq.getRemoteHost());
                session.setSessionPort(httpReq.getRemotePort());
                session.setSessionReqURL(cycle.getRequest().getUrl().getPath());
                session.setSessionRqStart(new Date(new Timestamp(cycle.getStartTime()).getTime()));
                session.setRequestParamaters(ApplicationUtilities.parseRequestParams(cycle.getRequest().getRequestParameters()));
            }

            @Override
            public void onEndRequest(RequestCycle cycle) {
                session.setSessionRqEnd(new Date(new Timestamp(cycle.getStartTime()).getTime()));
            }

            @Override
            public void onDetach(RequestCycle cycle) {
                try (Session transactionSession = HibernateUtil.openNewSession()) {
                    transactionSession.beginTransaction();
                    transactionSession.save(session);
                    transactionSession.getTransaction().commit();
                }
            }
        });
    }

    ;

    @Override
    public Class getHomePage() {
        return MainInterface.class;
    }

    @Override
    public org.apache.wicket.Session newSession(Request request, Response response) {
        return new LoginSession(request);
    }

}
