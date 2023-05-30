package com.webshop.shop.services;

import com.webshop.shop.classes.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static Connection connection;

    public ProductService(Connection connection){
        this.connection = connection;
    }

    public static List<Product> getAllProducts() throws SQLException{
        List<Product> products = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products")){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");

                Product product = new Product(id, name, price);
                products.add(product);
            }
        }
        return products;
    }

    public static void addProduct(Product product) throws  SQLException{
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, price) VALUES (?, ?)")){

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();

        }
    }

    public void deleteProduct(int productId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id = ?")){

            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws  SQLException{
        try (PreparedStatement statement = connection.prepareStatement("UPDATE products SET name = ?, price = ? WHERE id = ?")){

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getId());
            statement.executeUpdate();
        }
    }
}
