package app;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = DB.getConnection();

            ps = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1,"Juan Ramalho");
            ps.setString(2,"juan.ramalho@gmail.com");
            ps.setDate(3,new java.sql.Date(sdf.parse("01/06/2002").getTime()));
            ps.setDouble(4,1500);
            ps.setInt(5,4);

           int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("ID: " + id  );
                }
            }
            else{
                System.out.println("Insert failed");
            }
        }
        catch (SQLException | ParseException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(ps);
            DB.closeConnection();
        }

    }
}