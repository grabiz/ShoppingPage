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
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGUYEN
 */
@WebServlet ("/add")
public class AddProductToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prodIDStr = req.getParameter("getValue");
        HttpSession session = req.getSession();
        session.setAttribute("prodIDStr",prodIDStr);
        getServletContext().getRequestDispatcher("/jsp/order.jsp").forward(req,resp);
    }
    
}
