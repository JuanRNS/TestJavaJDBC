package app;


import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement ps = null;

        try{
            con = DB.getConnection();


            ps = con.prepareStatement(
                    "UPDATE seller "
                    + "SET BaseSalary = BaseSalary + ?"
                    + "WHERE "
                    + "(DepartmentId = ?)"
            );
            ps.setDouble(1,300);
            ps.setInt(2,2);

            int row = ps.executeUpdate();
            System.out.println(row
            + " rows affected");
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(ps);
            DB.closeConnection();
        }

    }
}