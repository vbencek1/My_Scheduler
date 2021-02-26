package org.spider.vbencek.spider_web_modul.helpers;

import java.io.Serializable;


public class FormatedNote implements Serializable{
    private int id;
    private String notename;
    private String description;
    private String datefrom;
    private String dateto;
    private String color;

    public FormatedNote(int id, String notename, String datefrom, String dateto) {
        this.id = id;
        this.notename = notename;
        this.datefrom = datefrom;
        this.dateto = dateto;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotename() {
        return notename;
    }

    public void setNotename(String notename) {
        this.notename = notename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String dateFrom) {
        this.datefrom = dateFrom;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateTo) {
        this.dateto = dateTo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
}
