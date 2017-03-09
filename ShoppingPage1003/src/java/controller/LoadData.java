/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
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
@WebServlet ("/gotoList")
public class LoadData extends HttpServlet {

    
    protected void doLoad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        ServletContext sc=getServletContext();
        String path = sc.getRealPath("/data/productList.txt");
        FileReader reader = null;
        BufferedReader buff =null;
        ArrayList<Product> prodList = new ArrayList<>();
        try{
            reader = new FileReader(path);
            buff = new BufferedReader(reader);
            String textLine;
            while((textLine=buff.readLine())!=null)
            {
               String s[] = textLine.split(",");
               int prodID =Integer.parseInt(s[0]);
               String prodName =s[1];
               double prodPrice =Double.parseDouble(s[2]);
               String prodDescription =s[3];
               Product prod = new Product(prodID,prodName,prodPrice,prodDescription);
               prodList.add(prod);
            }
            HttpSession session = req.getSession();
            session.setAttribute("prodList",prodList);
            session.setAttribute("prodAddedList",null);
            ArrayList<String>  numberSold = new ArrayList<>();
            for(int i=0;i<prodList.size();i++)
            {
                numberSold.add("1");
            }
            session.setAttribute("quantity",numberSold);
        }
        catch(IOException ex)
        {
             System.out.println("-------------");
        }
        finally{
            try{
                 if(reader !=null){
          
                     reader.close();
                 }
                 if(buff !=null)
                {
                    buff.close();
                }
            }
            catch(IOException ex)
            {
                System.out.println("-------------");
            }
        }
        getServletContext().getRequestDispatcher("/jsp/product.jsp").forward(req,resp);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doLoad(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doLoad(req,resp);
    }
    
}
