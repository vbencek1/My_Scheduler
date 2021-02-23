/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_web_modul.beans;

import com.google.gson.Gson;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.primefaces.PrimeFaces;
import org.spider.vbencek.spider_ejb_modul.eb.Notes;
import org.spider.vbencek.spider_ejb_modul.eb.Users;

/**
 *
 * @author NWTiS_2
 */
@Named(value = "scheduleBean")
@ViewScoped
public class ScheduleBean implements Serializable {

    @Inject
    Authenticationbean authenticationbean;

    @Getter
    @Setter
    List<Notes> schedule = new ArrayList<>();

    public ScheduleBean() {
    }

    @PostConstruct
    public void init() {
        getActiveUserNotes();
    }

    public void getActiveUserNotes() {
        Users user = authenticationbean.getActiveUser();
        if (user != null) {
            schedule = user.getNotesList();
        }
    }

    public void addEvent(int id, long start, long end, String name, String description, String color) {
        // String id="2";
        // String text="Hello";
        // String start="2021-02-20T12:00:00";
        //String end="2021-02-20T15:00:00";
        PrimeFaces.current().executeScript("addEvent('" + id + "'," + start + "," + end + ",'" + name + "','" + description + "','" + color + "')");
    }

    public void loadScheduleNotes() {
        List<Notes> jsonNotes = new ArrayList<>();
        if (!schedule.isEmpty()) {
            for (Notes note : schedule) {
                long start = note.getDatefrom().getTime();
                long end = note.getDateto().getTime();
                addEvent(note.getId(), start, end, note.getNotename(), note.getDescription(), note.getColor());
                Notes n=new Notes(note.getId(), note.getNotename(), note.getDatefrom(), note.getDateto());
                n.setDescription(note.getDescription());
                n.setColor(note.getColor());
                jsonNotes.add(n);
            }
            JSONObject jsonList = new JSONObject();
            jsonList.put("json", jsonNotes);
            System.out.println("ScheduleBean: Creating Json -> "+jsonList);
            String stringJson=jsonList.toString();
            PrimeFaces.current().executeScript("appendSchedule("+stringJson+")");
        }

    }

    private String cnvDateToStr(Long dateTocnv) {
        Date date = new Date(dateTocnv);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

}
