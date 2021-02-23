/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spider.vbencek.spider_ejb_modul.sb;

import java.util.List;
import javax.ejb.Local;
import org.spider.vbencek.spider_ejb_modul.eb.Users;

/**
 *
 * @author NWTiS_2
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();
    
    Users findUserByUsername(String username);
    
}
