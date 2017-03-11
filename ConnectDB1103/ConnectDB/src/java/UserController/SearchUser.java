/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserController;

import Model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iviettech
 */
@WebServlet("/search-user")
public class SearchUser extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbUrl ="jdbc:mysql://localhost:3306/trainning";
        String userName="root";
        String password="";
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            String name =(String) req.getParameter("search");
            String sqlCommand = "Select * from user where first_name like '%"+name+"%'"+" OR"+" last_name like '%"+name+"%'";
            Statement st = connection.createStatement();
            
            //pst.setString(1, req.getParameter("search"));
            //pst.setString(2, req.getParameter("search"));
            ResultSet result = st.executeQuery(sqlCommand);
            
            HttpSession session = req.getSession();
            ArrayList<User> list = new ArrayList<>();
            
            while(result.next())
            {  
                User us = new User();
                us.setUserID(result.getInt("id"));
                us.setFn(result.getString("first_name"));
                us.setLn(result.getString("last_name"));
                us.setEmail(result.getString("email"));
                list.add(us);
            }
            
            session.setAttribute("userListSearch",list);
            getServletContext().getRequestDispatcher("/showSearch.jsp").forward(req, resp);
           
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchUser.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
                
    }
    
