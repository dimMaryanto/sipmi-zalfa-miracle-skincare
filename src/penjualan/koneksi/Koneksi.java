package penjualan.koneksi;

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

public class Koneksi {

    @Deprecated
    public static Connection getConnection() {
        Connection conn = null;
        if (conn == null) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/si_si9_penjualan", "root", "admin");
            } catch (SQLException ex) {
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public static DataSource getDataSource() {
        MysqlDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUser("sipmi");
        ds.setPassword("admin");
        ds.setDatabaseName("sipmi");
        return ds;
    }

}
