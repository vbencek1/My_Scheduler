/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_web_modul.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import lombok.Getter;
import lombok.Setter;
import org.spider.vbencek.spider_ejb_modul.eb.Users;
import org.spider.vbencek.spider_ejb_modul.sb.UsersFacadeLocal;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.spider.vbencek.spider_web_modul.helpers.EmailSender;

/**
 *
 * @author NWTiS_2
 */
@Named(value = "registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {

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
    String email = "";

    @Getter
    @Setter
    String confirmationCode;

    @Getter
    @Setter
    String codeMessage="";
    
    private int generatedCode;

    @Getter
    public static Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();

    public RegistrationBean() {
    }

    public void sendRegistrationCode() {
        EmailSender emailSender = new EmailSender();
        String emailTo = email;
        String subject = "Registration Code";
        generatedCode = emailSender.generateConfirmationCode();
        String text = "Confirmation code: " + generatedCode;
        emailSender.sendMessage(emailTo, subject, text);
        System.out.println("Registration: Sending: "+text);
        codeMessage="";
    }

    public void confirmCode() {
        System.out.println(username + " " + generatedCode + " " + confirmationCode);
        int integerCode=convertToInt(confirmationCode);
        if (generatedCode == integerCode && integerCode != 0) {
            RegisterUser();
            System.out.println("Registration: User registered!");
            codeMessage= "Registration successful!";
        } else {
            System.out.println("Registration: Registration failed; Wrong confirmation code");
            codeMessage= "Code you entered is Wrong. please try again.";
        }
    }

    private void RegisterUser() {
        Users user = new Users();
        String hashPassword = pbkdf2PasswordEncoder.encode(password);
        user.setPassword(hashPassword);
        user.setUsername(username);
        user.setEmail(email);
        usersFacade.create(user);
    }

    private int convertToInt(String broj) {
        try {
            return Integer.parseInt(broj);
        } catch (Exception e) {
            return 0;
        }
    }

}
