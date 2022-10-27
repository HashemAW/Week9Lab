/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.*;
import services.*;

/**
 *
 * @author Hashem
 */
public class UserServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserService userService = new UserService();
            List<User> users = userService.getAll();
            RoleService roleService = new RoleService();
            List<Role> roles = roleService.getAll();           
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);           
            this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setAttribute("message", "");
        if (action != null && action.equals("add")) {  
            try {

                String email = request.getParameter("email");
                boolean active = request.getParameter("active") != null;
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                String roleName = request.getParameter("role");
                int roleId = 0;

                RoleService roleService = new RoleService();
                List<Role> roleList;
                roleList = roleService.getAll();

                for (Role role : roleList) {
                    if (role.getRoleName().equals(roleName)) {
                        roleId = role.getRoleId();
                    }
                }

                if (roleId == 0) {
                    throw new Exception("Invalid role");
                }

                Role role = new Role(roleId, roleName);
                UserService userService = new UserService();
                userService.insert(email, active, firstName, lastName, password, role);
                request.setAttribute("message", "User added successfully!");

            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Error! Please double check all fields are valid!");
            }
        
        } else if (action != null && action.contains("edit?")) {
            try {
                String email = action.split("\\?", 2)[1];
                UserService userService = new UserService();
                User user = userService.get(email);
                request.setAttribute("user", user);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
        } else if (action != null && action.equals("edit")) {
            try {
                String email = request.getParameter("email");
                boolean active = request.getParameter("active") != null;
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                String roleName = request.getParameter("role");
                int roleId = 0;

                RoleService roleService = new RoleService();
                List<Role> roleList;
                roleList = roleService.getAll();

                for (Role role : roleList) {
                    if (role.getRoleName().equals(roleName)) {
                        roleId = role.getRoleId();
                    }
                }

                if (roleId == 0) {
                    throw new Exception("Invalid role");
                }

                Role role = new Role(roleId, roleName);
                UserService userService = new UserService();
                userService.update(email, active, firstName, lastName, password, role);
                request.setAttribute("message", "User updated successfully!");

            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Error! Please double check all fields are valid!");
            }
            
        } else if (action != null && action.contains("delete?")) {
            //Delete user
            try {
                String email = action.split("\\?", 2)[1];
                UserService userService = new UserService();
                userService.delete(email);
                request.setAttribute("message", "User deleted successfully!");
                
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        try {
            UserService userService = new UserService();
            List<User> users = userService.getAll();
            RoleService roleService = new RoleService();
            List<Role> roles = roleService.getAll();
            
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}