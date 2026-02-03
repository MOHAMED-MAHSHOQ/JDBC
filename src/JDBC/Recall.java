package JDBC;

import java.sql.*;

public class Recall {
    public static void main(String[] args) {
        try {
            readRecords();
//            insertRecords();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void readRecords() throws SQLException {

        String url ="jdbc:mysql://localhost:3306/student";
        String userName="root";
        String password="50345034";
        String query="select * from student";

        Connection con = DriverManager.getConnection(url,userName,password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getInt(3));

        }
        con.close();

    }

    private static void insertRecords() throws SQLException {
        String url ="jdbc:mysql://localhost:3306/student";
        String userName="root";
        String password="50345034";
        String query="insert into student values (?,?,?)";
        int id=1;
        String name = "Mahshoq";
        int age=25;
        Connection con = DriverManager.getConnection(url,userName,password);
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1,id);
        ps.setString(2,name);
        ps.setInt(3,age);
        int row =ps.executeUpdate();
        System.out.println("Row affected : " + row);

    }
}
