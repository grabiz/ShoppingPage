/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iviettech
 */
@WebServlet ("/processing")
public class Processing extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=null;
        String action[]=req.getParameterValues("action");
        
        if(action[0].equals("Insert")){
           url="/insert-user";
        }
        else if (action[0].equals("Update"))
        {
           url="/update-user";
        }
        else if (action[0].equals("Delete")){
           url="/delete-user";
        }
        else{
           url="/search-user";
        }
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }
    
}
