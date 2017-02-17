/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.web.sources;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 *
 * @author BB3605
 */
public class Login extends WebPage {

    public Login() {
        final TextField<String> username = new TextField<>("username", Model.of(""));
        username.setRequired(true);
        
        final PasswordTextField pass = new PasswordTextField("pass", Model.of(""));
        
        
        Form<?> form = new Form<Void>("loginForm"){
            @Override
            protected void onSubmit() {
                
            }
        };
        
        add(form);
        form.add(username);
        form.add(pass);
    }
}
