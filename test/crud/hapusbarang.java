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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import penjualan.koneksi.koneksi;

public class hapusbarang {

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Masukan Kode Barang Yang akan di hapus\t : ");
            String kode = input.nextLine();

            Connection c = koneksi.getConnection();
            String sql = "delete from barang where kode_barang = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, kode);

            int rs = ps.executeUpdate();
            if (rs > 0) {
                System.out.println("Data Berhasil Di Hapus");
            }
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
