<%@ page import="com.webshop.shop.classes.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webshop.shop.services.ProductService" %>
<%@ page import="com.webshop.shop.classes.Buyer" %>
<%@ page import="com.webshop.shop.services.BuyerService" %>
<%@ page import="com.webshop.shop.classes.Sale" %>
<%@ page import="com.webshop.shop.services.SaleServcie" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sales</title>
</head>
<body>
<h1>Sales</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Product</th>
        <th>Buyer</th>
        <th>Quantity</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        SaleServcie SaleService = null;
        List<Sale> sales = SaleService.getAllSales();

        for (Sale sale : sales) {
    %>
    <tr>
        <td><%= sale.getId() %></td>
        <td><%= sale.getProduct().getName() %></td>
        <td><%= sale.getBuyer().getName() %></td>
        <td><%= sale.getQuantity() %></td>
        <td>

        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<h2>Add New Sale</h2>
<form action="addSale.jsp" method="post">
    <label for="product">Product:</label>
    <select name="product" id="product">
        <%
            List<Product> products = ProductService.getAllProducts();

            for (Product product : products) {
        %>
        <option value="<%= product.getId() %>"><%= product.getName() %></option>
        <% } %>
    </select><br>
    <label for="buyer">Buyer:</label>
    <select name="buyer" id="buyer">
        <
        <%
            List<Buyer> buyers = BuyerService.getAllBuyers();

            for (Buyer buyer : buyers) {
        %>
        <option value="<%= buyer.getId() %>"><%= buyer.getName() %></option>
        <% } %>
    </select><br>
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required><br>
    <input type="submit" value="Add Sale">
</form>
</body>
</html>