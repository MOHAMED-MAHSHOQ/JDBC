package JDBC;

import java.sql.*;

public class JDBC_Logics {
    public static void main(String[] args) {
        try {

//            insertRecords();
//            readRecords();
//            insertNew();
//            deleteRecord();
//            noParamStoPro();
//            inParamStoPro();
//            inOutParamStoPro();
//            commitFalse();
            batchProcessAndRollBack();
        } catch (SQLException e) {
            System.out.println("DB ERROR!!");
            e.printStackTrace();

        }

    }

    private static void batchProcessAndRollBack() throws SQLException {
        String url="jdbc:mysql://localhost:3306/java";
        String userName = "root";
        String passWord="50345034";

        String query1="update employee set department='Pro Dev 1' where id=1 ";
        String query2="update employee set department='Pro Dev 2' where id=2 ";
        String query3="update employee set department='Pro Dev 3' where id=3 ";
        String query4="update employee set department='Pro Dev 4' where id=4 ";

        Connection con = DriverManager.getConnection(url,userName,passWord);
        con.setAutoCommit(false);
        Statement st1 = con.createStatement();
        st1.addBatch(query1);
        st1.addBatch(query2);
        st1.addBatch(query3);
        st1.addBatch(query4);
        int[] arr =st1.executeBatch();
        for(int i:arr){
            if(i>0)
                continue;
            else
                con.rollback();
        }
        
    }

    private static void commitFalse() throws SQLException {
        String url="jdbc:mysql://localhost:3306/java";
        String userName = "root";
        String passWord="50345034";

        String query1="update employee set department='java Pro Dev!' where id=1 ";
        String query2="update employee set department='java Pro Dev!' where id=2 ";

        Connection con = DriverManager.getConnection(url,userName,passWord);
        con.setAutoCommit(false);
        Statement st1 = con.createStatement();
        int row1 = st1.executeUpdate(query1);
        System.out.println("Rows Affected "+row1);
        int row2 = st1.executeUpdate(query2);
        System.out.println("Rows Affected "+row2);

        if(row1>0 && row2>0){
            con.commit();
        }
    }

    private static void inOutParamStoPro() throws SQLException {
        String url="jdbc:mysql://localhost:3306/java";
        String userName = "root";
        String passWord="50345034";
//        String query="call getEmployee()";


        Connection con = DriverManager.getConnection(url,userName,passWord);
        CallableStatement st=con.prepareCall("{call getNameByEmployeeById(?,?)}");
        st.setInt(1,4);
        st.registerOutParameter(2,Types.VARCHAR);
        st.executeUpdate();

        System.out.println(st.getString(2));


        con.close();

    }

    private static void inParamStoPro() throws SQLException {
        String url="jdbc:mysql://localhost:3306/java";
        String userName = "root";
        String passWord="50345034";
//        String query="call getEmployee()";


        Connection con = DriverManager.getConnection(url,userName,passWord);
        CallableStatement st=con.prepareCall("{call getEmployeeById(?)}");
        st.setInt(1,4);
        ResultSet rs= st.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
        }
        con.close();
    }

    private static void noParamStoPro() throws SQLException {
        String url="jdbc:mysql://localhost:3306/java";
        String userName = "root";
        String passWord="50345034";
//        String query="call getEmployee()";


        Connection con = DriverManager.getConnection(url,userName,passWord);
        CallableStatement st=con.prepareCall("{call getEmployee()}");
        ResultSet rs= st.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
        }
        con.close();




    }

    private static void deleteRecord() throws SQLException {
        String url="jdbc:mysql://localhost:3306/java";
        String userName = "root";
        String passWord="50345034";
        String query="delete from employee where id= 5";

        Connection con = DriverManager.getConnection(url,userName,passWord);
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();


    }

    public static void readRecords() throws SQLException{
        String url="jdbc:mysql://localhost:3306/java";
        String userName = "root";
        String passWord="50345034";
        String query="select * from employee";


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
        // Example: insert into employee values(5,'Ansar','CSE');
        String query="insert into employee values (" + id + ",'" + name + "','" + department + "')";
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
        // Example: insert into employee values(5,'Ansar','CSE');
        String query="insert into employee values (?,?,?);";
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
