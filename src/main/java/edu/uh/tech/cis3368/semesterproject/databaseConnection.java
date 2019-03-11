package edu.uh.tech.cis3368.semesterproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    public String NAME = "project";
    public String URL = "jdbc:h2:~/project";
    public String USER = "sa";
    public String PASS = "1234";

    public Connection getConnection(){

        try {
            Class.forName("org.h2.Driver");
            Connection connect = DriverManager.getConnection(URL, USER,PASS);
            return connect;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
