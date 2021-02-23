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
import javax.persistence.criteria.*;
import org.spider.vbencek.spider_ejb_modul.eb.Users;

/**
 *
 * @author NWTiS_2
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "spider_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public Users findUserByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> cq = cb.createQuery(Users.class); 
        Root<Users> rt = cq.from(Users.class); 
        ParameterExpression<String> parametar = cb.parameter(String.class);
        cq.select(rt).where(cb.equal(rt.get("username"), parametar)); 
        Query q = getEntityManager().createQuery(cq);
        q.setParameter(parametar, username);
        
        List<Users> temp = q.getResultList();
        if(temp.isEmpty()){
            return null;
        }
        Users user =temp.get(0);
        return user;
    }
    
}
