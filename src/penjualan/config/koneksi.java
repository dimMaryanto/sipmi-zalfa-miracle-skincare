/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.config;

/**
 *
 * @author Lani
 */
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class koneksi {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sipmi", "root", "admin");
            } catch (SQLException ex) {
                Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }
    
    public static DataSource getDataSource(){
        MysqlDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUser("root");
        ds.setPassword("admin");
        ds.setDatabaseName("sipmi");
        return ds;
    }

}
