/*
 * Application.java
 *
 * Created on February 14, 2017, 5:28 PM
 */
package com.smarthome.web.sources;

import javax.servlet.http.HttpServletRequest;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
/**
 *
 * @author BB3605
 * @version
 */
public class Application extends WebApplication {
    private final SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Application() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(ssrb.build());

        getRequestCycleListeners().add(new IRequestCycleListener() {

            @Override
            public void onBeginRequest(RequestCycle cycle) {
                //IRequestCycleListener.super.onBeginRequest(cycle); //To change body of generated methods, choose Tools | Templates.
                WebRequest req = (WebRequest) cycle.get().getRequest();
                HttpServletRequest httpReq = (HttpServletRequest) req.getContainerRequest();
                //System.out.println("Starting: " + httpReq.getRemoteHost() + "; time: " + cycle.getStartTime());
                System.out.println("Starting: " + cycle.getStartTime());
                
                com.smarthome.hibernate.models.Session session = new com.smarthome.hibernate.models.Session();
                session.setSessionID(WebSession.get().getId());
                session.setSessionIP(httpReq.getRemoteHost());
                session.setSessionPort(httpReq.getRemotePort());
                session.setSessionReqURL(cycle.getRequest().getUrl().getPath());
                session.setSessionRqStart(cycle.getStartTime());
                session.setSessionRqEnd(cycle.getStartTime());
                
                Session transactionSession =  getSessionFactory().openSession();
                transactionSession.save(session);
                transactionSession.getTransaction().commit();
                transactionSession.close();
            }

            @Override
            public void onEndRequest(RequestCycle cycle) {
                System.out.println("Ending: " + cycle.getStartTime());
            }

            @Override
            public void onDetach(RequestCycle cycle) {
                System.out.println("Detaching");
            }

            @Override
            public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {
                System.out.println("Request executed");
            }

        });
    }

    ;

    @Override
    public Class getHomePage() {
        mountPackage("/", MainInterface.class);
        mountPackage("/", Login.class);

        return Login.class;
    }
}
