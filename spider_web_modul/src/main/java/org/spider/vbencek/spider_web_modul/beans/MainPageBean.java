/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_web_modul.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import lombok.Getter;
import lombok.Setter;
import org.spider.vbencek.spider_ejb_modul.eb.Persons;
import org.spider.vbencek.spider_ejb_modul.sb.PersonsFacadeLocal;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 *
 * @author NWTiS_2
 */
@Named(value = "mainPageBean")
@SessionScoped
public class MainPageBean implements Serializable {
    
    @EJB(beanName = "PersonsFacade")
    PersonsFacadeLocal personsFacade;
    
    @Getter
    List<Persons> persons = new ArrayList<>();
    
    public MainPageBean() {
    }
    
    @PostConstruct
    public void init(){
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        String pbkdf2CryptedPassword = pbkdf2PasswordEncoder.encode("password");
        boolean passwordIsValid = pbkdf2PasswordEncoder.matches("passwordd", pbkdf2CryptedPassword);
        System.out.println(pbkdf2CryptedPassword);
        System.out.println(passwordIsValid);
        persons=personsFacade.findAll();
    }
    
}
