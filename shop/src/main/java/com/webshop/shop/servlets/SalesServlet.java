package com.webshop.shop.servlets;

import com.webshop.shop.classes.Sale;
import com.webshop.shop.services.SaleServcie;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/sales")
public class SalesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        List<Sale> sales = null;
        try {
            sales = SaleServcie.getAllSales();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("sales", sales);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/sales.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        int productId = Integer.parseInt(req.getParameter("product_id"));
        int buyerId = Integer.parseInt(req.getParameter("buyer_id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        try {
            SaleServcie.sellProduct(productId, buyerId, quantity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/sales");
    }
}
