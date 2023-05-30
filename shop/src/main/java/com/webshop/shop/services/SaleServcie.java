package com.webshop.shop.services;

import com.webshop.shop.classes.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleServcie {
    private static Connection connection;

    public SaleServcie(Connection connection){
        this.connection = connection;
    }


    public static void sellProduct(int productId, int buyerId, int quantity) throws SQLException {
        try {
            connection.setAutoCommit(false);
            PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM products WHERE id = ? AND quantity >= ?");
            checkStatement.setInt(1, productId);
            checkStatement.setInt(2, quantity);
            ResultSet checkResult = checkStatement.executeQuery();

            if (!checkResult.next()){
                throw new SQLException("Proizvod nije dostupan u trazenoj kolicini.");
            }

            PreparedStatement updateStatement = connection.prepareStatement("UPDATE products SET quantity = quantity - ? WHERE id = ?");
            updateStatement.setInt(1, quantity);
            updateStatement.setInt(2, productId);
            updateStatement.executeUpdate();

            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO sales (product_id, buyer_id, quantity) VALUES (?, ?, ?)");
            insertStatement.setInt(1, productId);
            insertStatement.setInt(2, buyerId);
            insertStatement.setInt(3, quantity);
            insertStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex){
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    public static List<Sale> getAllSales() throws SQLException {
        List<Sale> sales = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sales");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int saleId = resultSet.getInt("id");
            int productId = resultSet.getInt("product_id");
            int buyerId = resultSet.getInt("buyer_id");
            int quantity = resultSet.getInt("quantity");

            Sale sale = new Sale(saleId, productId, buyerId, quantity);
            sales.add(sale);
        }

        return sales;
    }
}
