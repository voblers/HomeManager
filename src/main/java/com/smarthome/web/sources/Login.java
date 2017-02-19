/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.web.sources;

import com.myapp.wicket.HeaderPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author BB3605
 */
public class Login extends WebPage {

    public Login() {
        add(new login_header("login_header"));
        
        final TextField<String> username = new TextField<>("username", Model.of(""));
        username.setRequired(true);
        
        final PasswordTextField pass = new PasswordTextField("pass", Model.of(""));
        
        
        Form<?> form = new Form<Void>("loginForm"){
            @Override
            protected void onSubmit() {
                PageParameters params = new PageParameters();
                params.add("user", username.getValue());
                params.add("pass", pass.getValue());
                setResponsePage(MainInterface.class, params);
            }
        };
        
        add(form);
        form.add(username);
        form.add(pass);
    }
}
