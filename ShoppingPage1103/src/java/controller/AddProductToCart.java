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
             ArrayList<Product> prodAddedList = new ArrayList<>();
             prodAddedList.add(prod);
             session.setAttribute("prodAddedList",prodAddedList);
          }
       else{
           ArrayList<Product> prodAddedList= (ArrayList) session.getAttribute("prodAddedList"); 
           int check=0;
           for(int i=0;i<prodAddedList.size();i++)
           {  if (prod.getProdID()==prodAddedList.get(i).getProdID())
                {  check+=1; }
            }
//            if(((ArrayList)session.getAttribute("prodAddedList")).contains(prod)==false)
           if (check ==0)
           {
            prodAddedList.add(prod);
            session.removeAttribute("prodAddedList");
            session.setAttribute("prodAddedList",prodAddedList);
            }
        
          }
        getServletContext().getRequestDispatcher("/jsp/order.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
    
}
