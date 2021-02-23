/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_web_modul.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.spider.vbencek.spider_ejb_modul.eb.Users;
import org.spider.vbencek.spider_ejb_modul.sb.UsersFacadeLocal;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 *
 * @author NWTiS_2
 */
@Named(value = "authenticationbean")
@SessionScoped
public class Authenticationbean implements Serializable {

    @EJB(beanName = "UsersFacade")
    UsersFacadeLocal usersFacade;

    @Getter
    @Setter
    String username = "";

    @Getter
    @Setter
    String password = "";
    @Getter
    @Setter
    boolean showBtnLogin = true;
    @Getter
    @Setter
    boolean showBtnLogout = false;
    @Getter
    @Setter
    boolean showInputUsername = true;
    @Getter
    @Setter
    boolean showInputPass = true;
    @Getter
    @Setter
    boolean showLblInfo = false;
    @Getter
    @Setter
    Users activeUser = null;

    public Authenticationbean() {
    }
    @PostConstruct
    private void init(){
        username="";
        password="";
    }

    private void renderLogin() {
        showBtnLogin = false;
        showBtnLogout = true;
        showInputUsername = false;
        showInputPass = false;
        showLblInfo = true;
    }

    private void renderLogout() {

        activeUser = null;
        showBtnLogin = true;
        showBtnLogout = false;
        showInputUsername = true;
        showInputPass = true;
        showLblInfo = false;
    }

    public void authenticate() {
        Users tempLogin = usersFacade.findUserByUsername(username);
        if (tempLogin != null) {
            Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
            boolean passwordIsValid = pbkdf2PasswordEncoder.matches(password, tempLogin.getPassword());
            if (passwordIsValid) {
                activeUser = tempLogin;
                System.out.println("Authentication: User: " + activeUser.getUsername() + " logged in");
                renderLogin();
            } else {
                System.out.println("Authentication: User: " + username + " -> wrong password");
                FacesContext.getCurrentInstance().validationFailed();
            }

        } else {
            System.out.println("Authentication: Login failed");
            FacesContext.getCurrentInstance().validationFailed();
        }
        username = "";
        password = "";
    }

    public void logout() {
                username = "";
        password = "";
        renderLogout();
    }

}
