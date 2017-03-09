/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
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
@WebServlet ("/orderProcessing")
public class OrderProcessing extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action[] = req.getParameterValues("action");
        HttpSession session = req.getSession();
        
        if (action[0].equals("Update cart"))
        {
           ArrayList<String> quan = (ArrayList) session.getAttribute("quantity");
           String quantityHidden[] = req.getParameterValues("Quan");
           String quantity[] = req.getParameterValues("Quantity");
           for (int i=0;i<quan.size();i++)
           {
             for(int j=0;j<quantityHidden.length;j++)
             { 
                 if(Integer.parseInt(quantityHidden[j])==i)
                 {
                    quan.set(i, quantity[j]);
                 }
             }
           }
          session.removeAttribute("quantity");
          session.setAttribute("quantity", quan);
          getServletContext().getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
         
         
        }
        else if (action[0].equals("Remove product")){
          String removeList[]=req.getParameterValues("checkbox");
          for (String s:removeList)
          {
              int intStr = Integer.parseInt(s);
              ArrayList<Product> prodAddedListTemp=(ArrayList)session.getAttribute("prodAddedList");
              prodAddedListTemp.remove(intStr);
              session.removeAttribute("prodAddedList");
              session.setAttribute("prodAddedList",prodAddedListTemp);
          }
          getServletContext().getRequestDispatcher("/jsp/order.jsp").forward(req,resp);
        }
        else if (action[0].equals("Continue shopping")){
           getServletContext().getRequestDispatcher("/gotoList").forward(req, resp);
        
        }
        else{
            
            ServletContext sc = getServletContext();
            String path =sc.getRealPath("/order/order.txt");
            FileWriter writer = null;
            BufferedWriter buff = null;
            
            writer = new FileWriter(path);
            buff = new BufferedWriter(writer);
            
            ArrayList<Product> prod = (ArrayList<Product>) session.getAttribute("prodAddedList");
            
            String numberSold[] = req.getParameterValues("Quantity");
            
            for(int i=0;i<prod.size();i++)
            {
                buff.write(prod.get(i).getProdID()+","+prod.get(i).getProdName()+","+prod.get(i).getProdName());
            }
            
            
        }
        
            
    }
    
}
