/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;

/**
 *
 * @author NGUYEN
 */
@WebServlet ("/add")
public class AddProductToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String prodIDStr = req.getParameter("getValue");
        int index = Integer.parseInt(prodIDStr);
        HttpSession session = req.getSession();
        ArrayList<Product> prodList = (ArrayList)session.getAttribute("prodList");
        Product prod = prodList.get(index);        
        
       if(session.getAttribute("prodAddedList")==null)
         {
             prodList.add(prod);
          }
       else if(((ArrayList)session.getAttribute("prodAddedList")).contains(prod)==false){
            prodList.add(prod);
        }
        else{
       }
        session.setAttribute("prodAddedList",prodList);
        getServletContext().getRequestDispatcher("/jsp/order.jsp").forward(req,resp);
    }
    
}
