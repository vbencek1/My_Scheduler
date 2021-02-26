/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_ejb_modul.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import org.spider.vbencek.spider_ejb_modul.eb.Notes;
import org.spider.vbencek.spider_ejb_modul.eb.Users;

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
    
        @Override
    public List<Notes> findNotebyUser(Users user) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Notes> cq = cb.createQuery(Notes.class); 
        Root<Notes> rt = cq.from(Notes.class); 
        ParameterExpression<Users> parametar = cb.parameter(Users.class);
        cq.select(rt).where(cb.equal(rt.get("userid"), parametar)); 
        Query q = getEntityManager().createQuery(cq);
        q.setParameter(parametar, user);
        
        List<Notes> notes = q.getResultList();
        return notes;
    }
    
}
