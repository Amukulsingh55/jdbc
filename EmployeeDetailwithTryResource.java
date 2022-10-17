package org.example;

import java.sql.*;

class EmployeeDetailwithTryResource {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
    // Provide your own database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        String sql = "select * from Persons";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); //code to create a new connection
             Statement statement = connection.prepareStatement(sql);) {
            // Registering the JDBC driver
            //Class.forName(JDBC_DRIVER);
            // Opening a connection

            // Executing the query

            ResultSet resultset = statement.executeQuery(sql);
            // Extracting the result
            // The next() method moves the cursor forward by one row from it current
            // position in the resultset
            while (resultset.next()) {
                // getInt() gets the value of a column as integer
                // getString() gets the value of a column as string
                // Retrieve by column id
                int id = resultset.getInt(1);
                String lastname = resultset.getString(2);
                String firstname = resultset.getString(3);
                int age = resultset.getInt(4);
                // Displaying the values
                System.out.print("Id: " + id + ", LastName: " + lastname + ", FirstName = " + firstname + ", Age: " + age);
                System.out.println();
            }
            // Closing the connection
            resultset.close();
            statement.close();
            connection.close();
        } catch (SQLException se) {
            // This handles errors for JDBC
            System.out.println(se);
        } catch (Exception e) {
            // This handles errors for Class.forName
            e.printStackTrace();
        }
    }
}