/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_ejb_modul.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.spider.vbencek.spider_ejb_modul.eb.Notes;

/**
 *
 * @author NWTiS_2
 */
@Stateless
public class NotesFacade extends AbstractFacade<Notes> implements NotesFacadeLocal {

    @PersistenceContext(unitName = "spider_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotesFacade() {
        super(Notes.class);
    }
    
}
