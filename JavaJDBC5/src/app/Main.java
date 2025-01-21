package app;


import db.DB;
import db.DbIntegrityEx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = DB.getConnection();

            ps = conn.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE "
                    + "Id = ?"
            );
            ps.setInt(1,2);

            int row = ps.executeUpdate();

            System.out.println("Rows affected: " + row);
        }
        catch (SQLException e){
            throw new DbIntegrityEx(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeConnection();
        }



    }
}