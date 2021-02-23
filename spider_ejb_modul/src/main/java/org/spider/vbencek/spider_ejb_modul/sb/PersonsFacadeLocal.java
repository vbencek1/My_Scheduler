/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_ejb_modul.sb;

import java.util.List;
import javax.ejb.Local;
import org.spider.vbencek.spider_ejb_modul.eb.Persons;

/**
 *
 * @author NWTiS_2
 */
@Local
public interface PersonsFacadeLocal {

    void create(Persons persons);

    void edit(Persons persons);

    void remove(Persons persons);

    Persons find(Object id);

    List<Persons> findAll();

    List<Persons> findRange(int[] range);

    int count();
    
}
