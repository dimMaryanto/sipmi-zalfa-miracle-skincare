/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

/**
 *
 * @author Lani
 */
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import app.configs.Koneksi;
import app.configs.Koneksi;

public class inputbarang {

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Masukan Kode Barang \t : ");
            String kode_barang = input.nextLine();
            System.out.print("Masukan Nama Barang \t : ");
            String nama_barang = input.nextLine();
            System.out.print("Masukan Harga Barang \t : ");
            String harga = input.nextLine();

            Connection c = Koneksi.getConnection();
            String sql = "Insert into barang values (?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, kode_barang);
            ps.setString(2, nama_barang);
            ps.setString(3, harga);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                System.out.println("Data Berhasil Di Simpan");
            }
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
