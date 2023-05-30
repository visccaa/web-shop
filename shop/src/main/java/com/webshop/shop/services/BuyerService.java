package com.webshop.shop.services;

import com.webshop.shop.classes.Buyer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerService {
    private static Connection connection;

    public BuyerService(Connection connection){
        this.connection = connection;
    }

    public static List<Buyer> getAllBuyers() throws SQLException{
        List<Buyer> buyers = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM buyers")){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                Buyer buyer = new Buyer(id, name, email);
                buyers.add(buyer);
            }
        }
        return buyers;
    }

    public static void addBuyer(Buyer buyer) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO buyers (name, email) VALUES (?, ?)")){

            statement.setString(1, buyer.getName());
            statement.setString(2, buyer.getEmail());
            statement.executeUpdate();
        }
    }

    public void deleteBuyer(int buyerId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM buyers WHERE id = ?")){

            statement.setInt(1, buyerId);
            statement.executeUpdate();
        }
    }

    public void updateBuyer(Buyer buyer) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE buyers SET name = ?, email = ?, WHERE id = ?")){

            statement.setString(1, buyer.getName());
            statement.setString(2, buyer.getEmail());
            statement.setInt(3, buyer.getId());
            statement.executeUpdate();
        }
    }
}
