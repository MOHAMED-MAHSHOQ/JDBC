package JDBC;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws SQLException {
      try{

//          insertRecords();
          readrecords();
//          insertNew();
      }
      catch (SQLException e) {
          System.out.println("DB ERROR!!");
          e.printStackTrace();

      }

    }
    public static void readrecords() throws SQLException{
        String url="jdbc:mysql://localhost:3306/java";
        String userName = "root";
        String passWord="50345034";
        String query="select * from Employee";


        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st=con.createStatement();
        ResultSet rs= st.executeQuery(query);

        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
        }
        con.close();
    }
    public static void insertRecords() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/java";
        String userName="root";
        String passWord="50345034";
        int id=5;
        String name="Irfan";
        String department="AI Engineer";
        // String query="insert into Employee values(5,"Ansar","CSE");
        String query="insert into Employee values (" + id + ",'" + name + "','" + department + "')";
        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int row = st.executeUpdate(query);
        System.out.println("Row Affected"+row);
        con.close();
    }
    public static void insertNew() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/java";
        String userName="root";
        String passWord="50345034";
        int id=6;
        String name="Faizal";
        String department="DevOps Engineer";
        // String query="insert into Employee values(5,"Ansar","CSE");
        String query="insert into Employee values (?,?,?);";
        Connection con = DriverManager.getConnection(url,userName,passWord);
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,id);
        st.setString(2,name);
        st.setString(3,department);
        int row = st.executeUpdate();
        System.out.println("Row Affected"+row);
        con.close();
    }
}
