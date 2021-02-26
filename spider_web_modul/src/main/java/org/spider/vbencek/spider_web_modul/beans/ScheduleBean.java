/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_web_modul.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.primefaces.PrimeFaces;
import org.spider.vbencek.spider_ejb_modul.eb.Notes;
import org.spider.vbencek.spider_ejb_modul.eb.Users;
import org.spider.vbencek.spider_ejb_modul.sb.NotesFacadeLocal;
import org.spider.vbencek.spider_web_modul.helpers.FormatedNote;

/**
 *
 * @author NWTiS_2
 */
@Named(value = "scheduleBean")
@ViewScoped
public class ScheduleBean implements Serializable {

    @EJB(beanName = "NotesFacade")
    NotesFacadeLocal notesFacade;

    @Inject
    Authenticationbean authenticationbean;

    @Getter
    @Setter
    List<Notes> schedule = new ArrayList<>();

    @Getter
    @Setter
    String noteName = "note_name";
    @Getter
    @Setter
    String noteDescription = "note_description";
    @Getter
    @Setter
    String noteColor = "#FFFFFF";
    @Getter
    @Setter
    String noteDateFrom = "0";
    @Getter
    @Setter
    String noteDateTo = "0";
    @Getter
    @Setter
    String noteID="0";

    public ScheduleBean() {
    }

    @PostConstruct
    public void init() {
        getActiveUserNotes();
    }

    public void getActiveUserNotes() {
        Users user = authenticationbean.getActiveUser();
        if (user != null) {
            schedule = notesFacade.findNotebyUser(user);
        }else{
            schedule=new ArrayList<>();
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
        getActiveUserNotes();
        System.out.println("ScheduleBean: loading schedule...");
        List<FormatedNote> jsonNotes = new ArrayList<>();
        if (!schedule.isEmpty()) {
            for (Notes note : schedule) {
                long start = note.getDatefrom().getTime();
                long end = note.getDateto().getTime();
                addEvent(note.getId(), start, end, note.getNotename(), note.getDescription(), note.getColor());
                FormatedNote n = new FormatedNote(note.getId(), note.getNotename(), String.valueOf(note.getDatefrom().getTime()), String.valueOf(note.getDateto().getTime()));
                n.setDescription(note.getDescription());
                n.setColor(note.getColor());
                jsonNotes.add(n);
            }
            JSONObject jsonList = new JSONObject();
            jsonList.put("json", jsonNotes);
            System.out.println("ScheduleBean: Creating Json -> " + jsonList);
            String stringJson = jsonList.toString();
            PrimeFaces.current().executeScript("appendSchedule(" + stringJson + ")");
        }

    }
    
    public void createNote(){
        Users user = authenticationbean.getActiveUser();
        if (user != null) {
           Notes note=notesFacade.find(Integer.parseInt(noteID));
           Notes newNote= new Notes();
           newNote.setNotename(noteName);
           newNote.setUserid(user);
           newNote.setDescription(noteDescription);
           newNote.setColor(noteColor);
           newNote.setDatefrom(new Date(Long.parseLong(noteDateFrom)));
           newNote.setDateto(new Date(Long.parseLong(noteDateTo)));
            if(note!=null){
               newNote.setId(note.getId());
               notesFacade.edit(newNote);
               System.out.println("ScheduleBean: Updated note: "+newNote.getId()+" of user: "+user.getUsername());
            }
            else{
               newNote.setId(Integer.parseInt(noteID));
               notesFacade.create(newNote);
               System.out.println("ScheduleBean: Created note: "+newNote.getId()+" of user: "+user.getUsername());
            }
        }      
        System.out.println(noteName+" "+noteDateFrom+" "+noteDateTo+" "+noteColor+" "+noteDescription+" "+noteID);
}
    public void deleteNode(){
        Notes node=notesFacade.find(Integer.parseInt(noteID));
        Users user = authenticationbean.getActiveUser();
        if(node!=null){
            notesFacade.remove(node);
            System.out.println("ScheduleBean: Removed note: "+node.getId()+" of user: "+user.getUsername());
        }else{
            System.out.println("ScheduleBean: Error while removing note!");
        }
    }
    
    private String cnvDateToStr(Long dateTocnv) {
        Date date = new Date(dateTocnv);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
    

}
