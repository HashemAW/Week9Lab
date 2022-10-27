/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.*;
import java.util.List;
import models.*;

/**
 *
 * @author Hashem
 */
public class RoleService {
    private RoleDB roleDB = new RoleDB();
    
    public List<Role> getAll() throws Exception {
        List<Role> roles = this.roleDB.getAll();
        return roles;
    }

    public int getRoleId(String roleName) throws Exception {
        int id = this.roleDB.lookupRoleId(roleName);
        return id;
    }
}
