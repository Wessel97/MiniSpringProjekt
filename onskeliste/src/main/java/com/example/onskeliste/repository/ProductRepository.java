package com.example.onskeliste.repository;

import com.example.onskeliste.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String UID;

    @Value("${spring.datasource.password}")
    private String PWD;


    public List<Product> getAll(){
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int amount = resultSet.getInt(4);
                String link = resultSet.getString(5);
                Product product = new Product(id, name, price, amount, link);
                productList.add(product);
                System.out.println(product);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Could not query database");
            e.printStackTrace();
        }
        return productList;
    }

    public void addProduct(Product product){
        try{
            //connect to db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            final String CREATE_QUERY = "INSERT INTO products(name, price, amount, link) VALUES  (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            //set attributer i prepared statement
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getAmount());
            preparedStatement.setString(4, product.getLink());

            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not create product");
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product){
        //SQL statement
        final String UPDATE_QUERY = "UPDATE products SET name = ?, price = ?, amount = ?, link = ? WHERE id = ?";

        try {
            //connect db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);

            //prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            //set parameters
            String name = product.getName();
            double price = product.getPrice();
            int amount  =  product.getAmount();
            String link = product.getLink();
            int id = product.getId();

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, amount);
            preparedStatement.setString(4, link);
            preparedStatement.setInt(5, id);

            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not update product");
            e.printStackTrace();
        }
    }

    public Product findProductById(int id){
        //SQL-statement
        final String FIND_QUERY = "SELECT * FROM products WHERE id = ?";
        Product product = new Product();
        product.setId(id);
        try {
            //db connection
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);

            //prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);

            //set parameters
            preparedStatement.setInt(1, id);

            //execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            //få product ud af resultset
            resultSet.next();
            String name = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            product.setName(name);
            product.setPrice(price);
        } catch (SQLException e){
            System.out.println("Could not find product");
            e.printStackTrace();
        }
        System.out.println(product);
        //return product
        return product;
    }

    public void deleteById(int id){
        //SQL-query
        final String DELETE_QUERY = "DELETE FROM products WHERE id=?";

        try {
            //connect til db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);

            //create statement
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            //set parameter
            preparedStatement.setInt(1, id);

            //execute statement
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println("Could not delete product");
            e.printStackTrace();
        }
    }

}