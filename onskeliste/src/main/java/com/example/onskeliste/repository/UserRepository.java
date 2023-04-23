package com.example.onskeliste.repository;

import com.example.onskeliste.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String UID;
    @Value("${spring.datasource.password}")
    private String PWD;

    public User findByUsername(String username) {
        final String FIND_QUERY = "SELECT * FROM users WHERE username = ?";
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String password = resultSet.getString(3);
                return new User(username, password);
            }
        } catch (SQLException e) {
            System.out.println("Could not find user");
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) {
        final String CREATE_QUERY = "INSERT INTO users(username, password) VALUES (?, ?)";
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not create user");
            e.printStackTrace();
        }
    }
}