/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NGUYEN
 */
@WebServlet ("/orderProcessing")
public class OrderProcessing extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action[] = req.getParameterValues("action");
        if (action[0].equals("Update cart")){
        
        }else if (action[0].equals("Remove product")){
        
        
        }else if (action[0].equals("Continue shopping")){
        
        
        }else{
        }
        
            
    }
    
}
