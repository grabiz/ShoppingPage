/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
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
        String url =null;
        
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
          url="/jsp/order.jsp";
        }
        else if (action[0].equals("Remove product")){
            
          String removeList[]=req.getParameterValues("checkbox");
//          if(removeList.length==0)
//          {
//              url="/jsp/order.jsp";
//          }
//          else{
          ArrayList<Product> prodAddedList=(ArrayList)session.getAttribute("prodAddedList");
          ArrayList<Product> prodlist = (ArrayList) session.getAttribute("prodList");
          for (int i=0;i<removeList.length;i++)
          {
              int intStr = Integer.parseInt(removeList[i]);
              for(int j=0;j<prodAddedList.size();j++)
              {
                if(prodAddedList.get(j).getProdID()==intStr)
                {  
                    prodAddedList.remove(prodlist.get(intStr)) ;
                 }
               }
             }
              //session.removeAttribute("prodAddedList");
              session.setAttribute("prodAddedList",prodAddedList);
              url="/jsp/order.jsp";
        }
        
        else if (action[0].equals("Continue shopping")){
            //url="/gotoList"; work as the same as statement below but return by contains method of ArrayList isn't exact anymore 
            url="/jsp/product.jsp";
            
        }
        else{
            
            //ServletContext sc = getServletContext();
            //String path =sc.getRealPath("/order/order.txt");
            //FileWriter writer = null;
            //BufferedWriter buff = null;
            File file = new File("D://order.txt");
            try{
            FileWriter writer = new FileWriter(file);
            BufferedWriter buff = new BufferedWriter(writer);
            
            ArrayList<Product> prod = (ArrayList<Product>) session.getAttribute("prodAddedList");
            ArrayList<String> nbSold = (ArrayList) session.getAttribute("quantity");
            
            if(prod==null)
            {
               url="/jsp/sorry.jsp";
            }
            else{
                for (int i = 0; i < prod.size(); i++) 
                {   
                    double total =Integer.parseInt(nbSold.get(prod.get(i).getProdID()))*prod.get(i).getProdPrice();
                    String orderText= prod.get(i).getProdID()+ "," + prod.get(i).getProdName() + "," + prod.get(i).getProdPrice()+
                            ","+prod.get(i).getProdDescription()+","+nbSold.get(prod.get(i).getProdID())+","+ total;
                    buff.write(orderText);
                    buff.newLine();
                    
                }
                buff.close();
                url = "/jsp/thanks.jsp";
            }
            session.invalidate();
            }
            catch (IOException e){
                System.err.println("Error: " + e.getMessage());
              }
        }
        
        getServletContext().getRequestDispatcher(url).forward(req, resp);
            
    }
    
}
