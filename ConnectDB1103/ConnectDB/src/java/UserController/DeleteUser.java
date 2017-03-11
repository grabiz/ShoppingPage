/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ConnectionPool;

/**
 *
 * @author iviettech
 */
@WebServlet ("/delete-user")
public class DeleteUser extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        {
        
//        String dbUrl ="jdbc:mysql://localhost:3306/trainning";
//        String userName="root";
//        String password="";
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection = pool.getConnection();
        try {
            
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            
            String sqlCommand = "Delete from user where id=?";
            PreparedStatement pst = connection.prepareStatement(sqlCommand);
            HttpSession session = req.getSession();
            
            String url=null;
            ArrayList<Integer> idList = (ArrayList) session.getAttribute("idList");
            int isIDInputExist = 0;
            for (int i=0;i<idList.size();i++)
            {
                if ((Integer.parseInt(req.getParameter("id")))==idList.get(i)) 
                {
                    isIDInputExist +=1;
                    break;
                }
            }
            if(isIDInputExist ==0)
            {
                    req.setAttribute("message1", "New ID must be existent !");
                    url = "/action.jsp";
            }
            else 
            {
                pst.setInt(1, Integer.parseInt(req.getParameter("id")));
            
                if (pst.executeUpdate() != 0) {
                    req.setAttribute("message", "Update succesfully!");
                    url = "/show-user";
                } else {
                    req.setAttribute("message", "Failed to insert!");
                    url = "/action.jsp";
                }
            }
            getServletContext().getRequestDispatcher(url).forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
           pool.freeConnnection(connection);
        
        }        
    }
}
}