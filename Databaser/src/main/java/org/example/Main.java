package org.example;


import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args){
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection connection;
        try {
            connection  = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quizdb?allowPublicKeyRetrieval=true&useSSL=false",
                    "root",
                    "pissonger"

             );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("tilkoblingen fungerer");

        try(Statement statement = connection.createStatement()){


            String query = "CREATE TABLE IF NOT EXISTS users"
                    + "("
                        +"id INT NOT NULL AUTO_INCREMENT, " +
                        "username VARCHAR(64)," +
                        "password VARCHAR(256)," +
                        "PRIMARY KEY(id)" +
                        ")";

            statement.executeUpdate(query);

            if (statement.execute("SELECT * FROM users WHERE username = 'admin'")) {
                System.out.println("admin brukerern eskistrer");
                query = "INSERT INTO users(username, password) VALUES ('admin', 'admin');";
                statement.execute(query);
            }
            else{
                System.out.println("admin brukeren eksitrer ikke!");
                query = "INSERT INTO users(username, password) VALUES ('admin', 'admin');";
                statement.execute(query);
            }

            String createAdminUserQuery;

            System.out.println("Table created successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}