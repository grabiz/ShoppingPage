/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.User;
import Model.User;
import util.ConnectionPool;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import util.DBUtil;
/**
 *
 * @author iviettech
 */
@WebServlet ("/show-user")
public class UserServlet extends HttpServlet {
    
    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
//        String dbUrl ="jdbc:mysql://localhost:3306/trainning";
//        String userName="root";
//        String password="";
         ConnectionPool pool = ConnectionPool.getInstance();
         Connection connection = pool.getConnection();
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            Statement statement = connection.createStatement();
            String sqlCommand = "Select * from user";
            ResultSet userObject = statement.executeQuery(sqlCommand);
            ArrayList<User> userList = new ArrayList<>();
            ArrayList<Integer> idList = new ArrayList<>(); //Kiem tra id nhap khi Update/Delete vao co nam trong database ko
            HttpSession session = req.getSession();
            while(userObject.next())
            {
                User user = new User(); 
                user.setUserID(userObject.getInt("id"));
                user.setFn(userObject.getString("first_name"));
                user.setLn(userObject.getString("last_name"));
                user.setEmail(userObject.getString("email"));
                idList.add(userObject.getInt("id"));
                userList.add(user);
            }
            DBUtil.closeResultSet(userObject);
            DBUtil.closeStatement(statement);
//            statement.close();  //Giai phong bo nho !!!! Tuong tu cho PreparedStatement
//            connection.close(); //Giai phong bo nho !!!! 
            session.setAttribute("idList",idList);
            session.setAttribute("userList",userList);
            getServletContext().getRequestDispatcher("/showListUser.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
           pool.freeConnnection(connection);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doProcess(req,resp);
    }
    
}
