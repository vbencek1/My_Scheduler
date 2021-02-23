/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_web_modul.listeners;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author NWTiS_1
 */
@WebListener
public class SlusacAplikacije implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        //to do
        System.out.println("Aplication started!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Aplication stopped!");
    }

}
