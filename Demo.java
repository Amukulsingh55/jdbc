package org.example;
import java.sql.*;
import java.util.Scanner;

//1.import---> java.sql
//2.load and register the driver---->com.mysql.jdbc.Driver
//3.create connection--->connection
//4.create a statement
//5.execute the query
//6.process the result
//7.close
public class Demo {
    public static void main(String[] args) throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//class.forname call static without creating object
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "root");
            Statement st = con.createStatement();
            insertRecord(con);
            ResultSet rs = st.executeQuery("select * from sakila.employee");

            //accessing next values by rs.next()
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            rs.close();
            st.close();
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    static void insertRecord(Connection con) throws SQLException{
        Scanner sc=new Scanner(System.in);
        int id=sc.nextInt();
        String name=sc.next();
        int managerId=sc.nextInt();
        //prepare the statement to insert the query
        PreparedStatement smt=con.prepareStatement("insert into sakila.employee values(?,?,?)");
        //set the query at proper index
        smt.setInt(1,id);
        smt.setString(2,name);
        smt.setInt(3,managerId);
        //execute the record
        int record=smt.executeUpdate();
        System.out.println(record +" record is inserted ");
        smt.close();



    }
}
