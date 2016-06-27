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
import penjualan.config.koneksi;

public class editbarang {

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Masukan Kode Barang Yang akan di edit\t : ");
            String kode = input.nextLine();
            System.out.print("Masukan Kode Barang Baru\t : ");
            String kode_barang = input.nextLine();
            System.out.print("Masukan Nama Barang Baru\t : ");
            String nama_barang = input.nextLine();
            System.out.print("Masukan Harga Barang Baru\t : ");
            String harga = input.nextLine();

            Connection c = koneksi.getConnection();
            String sql = "update barang set nama_barang = ?, harga = ?, kode_barang = ? where kode_barang = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nama_barang);
            ps.setString(3, harga);
            ps.setString(4, kode_barang);
            ps.setString(5, kode);
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
