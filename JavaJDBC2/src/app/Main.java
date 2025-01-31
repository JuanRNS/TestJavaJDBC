package app;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM department");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}