/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.web.sources;

import com.smarthome.web.authentication.LoginSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 *
 * @author BB3605
 */
public class Login extends WebPage {

    public Login() {
        add(new login_header("login_header"));

        final TextField<String> username = new TextField<>("username", Model.of(""));
        final PasswordTextField pass = new PasswordTextField("pass", Model.of(""));

        StatelessForm form = new StatelessForm("loginForm") {

            @Override
            protected void onSubmit() {
                super.onSubmit(); //To change body of generated methods, choose Tools | Templates.
                
                LoginSession session = getMySession();
                if (session.signIn(username.getValue(), pass.getValue())) {
                    continueToOriginalDestination();
                    setResponsePage(MainInterface.class);
                } else {
                    setResponsePage(Application.get().getHomePage());
                }
            }
        };

        add(form);
        form.add(username);
        form.add(pass);
    }

    private LoginSession getMySession() {
        return (LoginSession) getSession();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure(); //To change body of generated methods, choose Tools | Templates.
        
        if(getMySession().isSignedIn()){
            setResponsePage(MainInterface.class);
        }
    }
    
    
}
