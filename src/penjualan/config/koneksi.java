/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.config;

/**
 *
 * @author Lani
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class koneksi {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/si_si9_penjualan", "root", "admin");
            } catch (SQLException ex) {
                Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

}
