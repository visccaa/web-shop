package com.webshop.shop.servlets;


import com.webshop.shop.classes.Buyer;
import com.webshop.shop.services.BuyerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/buyers")
public class BuyerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        List<Buyer> buyers = null;
        try {
            buyers = BuyerService.getAllBuyers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("buyers", buyers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/buyers.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Buyer newBuyer = new Buyer(name, email);
        try {
            BuyerService.addBuyer(newBuyer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/buyers");
    }
}
