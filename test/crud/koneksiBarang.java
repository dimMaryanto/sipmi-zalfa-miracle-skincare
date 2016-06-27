/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

/**
 *
 * @author Lani
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import penjualan.config.Koneksi;
import penjualan.config.Koneksi;

public class koneksiBarang {

    public static void main(String[] args) {
        try {
            Connection c = Koneksi.getConnection();
            Statement s = (Statement) c.createStatement();
            String sql = "Select * From barang";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                String kode_barang = r.getString("kode_barang");
                String nama_barang = r.getString("nama_barang");

                System.out.println("====================");
                System.out.println("Kode \t = " + kode_barang);
                System.out.println("Nama \t = " + nama_barang);
            }
            s.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
