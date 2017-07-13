/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.web.sources;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author BB3605
 */
public class Login extends WebPage {

    public Login() {
        add(new login_header("login_header"));
        
        final TextField<String> username = new TextField<>("username", Model.of(""));
        final PasswordTextField pass = new PasswordTextField("pass", Model.of(""));
        
        StatelessForm form = new StatelessForm("loginForm"){

            @Override
            protected void onSubmit() {
                super.onSubmit(); //To change body of generated methods, choose Tools | Templates.
                System.out.println("Checking credentials");
                if(username.getValue().equals("admin") && pass.getValue().equals("admin")){
                    setResponsePage(MainInterface.class);
                }
                else{
                    System.out.println("Returning to HomePage");
                    setResponsePage(Application.get().getHomePage(), new PageParameters());
                }
            }
        };
        
        add(form);
        form.add(username);
        form.add(pass);
    }
}
