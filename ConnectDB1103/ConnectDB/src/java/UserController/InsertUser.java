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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ConnectionPool;
import util.DBUtil;

/**
 *
 * @author NGUYEN
 */
@WebServlet("/insert-user")
public class InsertUser extends HttpServlet{
     
    protected void doInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
//        String dbUrl ="jdbc:mysql://localhost:3306/trainning";
//        String userName="root";
//        String password="";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            
//          Class.forName("com.mysql.jdbc.Driver");
//          Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            String sqlCommand = "Insert into user (first_name,last_name,email) values (?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sqlCommand);
            String url=null;
            
            if (((String)req.getParameter("firstname"))==null )
            { 
                req.setAttribute("message2", "First name must be filled!!!"); 
                url="/action.jsp";
            }
            else if (((String) req.getParameter("lastname")).equals(""))
            {
                req.setAttribute("message3", "Last name must be filled!!!"); 
                url="/action.jsp";
            }
            else 
            {
                pst.setString(1, req.getParameter("firstname"));
                pst.setString(2, req.getParameter("lastname"));
                pst.setString(3, req.getParameter("email"));
            
            if(pst.executeUpdate()!=0)
            {
               req.setAttribute("message", "Inserted succesfully!");
               url="/show-user";
            }
            else
            {
              req.setAttribute("message", "Failed to insert!");
              url="/action.jsp";
            }
            DBUtil.closePreparedStatement(pst);
            getServletContext().getRequestDispatcher(url).forward(req, resp);
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
           pool.freeConnnection(connection);
        }
                
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doInsert(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         doInsert(req,resp);
    }
}
