package org.spider.vbencek.spider_web_modul.beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.spider.vbencek.spider_ejb_modul.eb.Users;
import org.spider.vbencek.spider_ejb_modul.sb.UsersFacadeLocal;
import org.spider.vbencek.spider_web_modul.helpers.EmailSender;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Named(value = "forgotPassBean")
@ViewScoped
public class ForgotPassBean implements Serializable {

    @EJB(beanName = "UsersFacade")
    UsersFacadeLocal usersFacade;

    @Getter
    @Setter
    String username = "";

    @Getter
    @Setter
    String email = "";

    /**
     * Creates a new instance of ForgotPassBean
     */
    public ForgotPassBean() {
    }

    private Users findUserWithEmail(String username) {
        Users user = usersFacade.findUserByUsername(username);
        if (!user.getEmail().equals(email)) {
            return null;
        }
        return user;
    }
    
    private void updatePassword(Users user, String newPass){
        Users updatedUser=user;
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        String hashPassword = pbkdf2PasswordEncoder.encode(newPass);
        updatedUser.setPassword(hashPassword);
        usersFacade.edit(updatedUser);
    }

    public void sendPassword() {
        Users user = findUserWithEmail(username);
        if (user != null) {
            EmailSender emailSender = new EmailSender();
            String emailTo = email;
            String subject = "Forgoten password";
            String newPass=String.valueOf(emailSender.generateConfirmationCode());
            String text = "Your new password is: "+ newPass;
            emailSender.sendMessage(emailTo, subject, text);
            System.out.println("ForgotenPass: Sending: " + text);
            updatePassword(user, newPass);
            PrimeFaces.current().executeScript("alertSuccess()");
        } else {
            PrimeFaces.current().executeScript("alertFailure()");
        }
        username = "";
        email = "";
    }

}
