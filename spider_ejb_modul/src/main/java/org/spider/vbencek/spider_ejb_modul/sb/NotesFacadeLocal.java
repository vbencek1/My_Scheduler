/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_ejb_modul.sb;

import java.util.List;
import javax.ejb.Local;
import org.spider.vbencek.spider_ejb_modul.eb.Notes;
import org.spider.vbencek.spider_ejb_modul.eb.Users;

/**
 *
 * @author NWTiS_2
 */
@Local
public interface NotesFacadeLocal {

    void create(Notes notes);

    void edit(Notes notes);

    void remove(Notes notes);

    Notes find(Object id);

    List<Notes> findAll();

    List<Notes> findRange(int[] range);

    int count();
    
    List<Notes> findNotebyUser(Users user);
    
}
