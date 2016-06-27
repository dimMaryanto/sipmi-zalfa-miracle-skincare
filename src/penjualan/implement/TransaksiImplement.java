package penjualan.implement;

import penjualan.koneksi.koneksi;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import penjualan.entity.Transaksi;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import penjualan.view.transaksi.PenjualanView;
//import penjualan.interfc.TransaksiInterfc;
public class TransaksiImplement {

    public int UrutanDb() throws SQLException {

        Statement St = koneksi.getConnection().createStatement();

        int Jml = 0;

        try {

            ResultSet Rs = St.executeQuery("SELECT COUNT(*) AS Urutan FROM penjualan");

            while (Rs.next()) {

                Jml = Rs.getInt("Urutan");

            }

            St.close();
            Rs.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ++Jml;

    }

    public ArrayList<String> ViewNamaPlg() throws SQLException {

        ArrayList<String> ViewNamaPlg = new ArrayList();

        try {

            Statement Kt = koneksi.getConnection().createStatement();

            ResultSet RsKb = Kt.executeQuery("SELECT Id_Pelanggan, Nama FROM Pelanggan");

            while (RsKb.next()) {

                ViewNamaPlg.add(RsKb.getString("Id_Pelanggan") + "-" + (RsKb.getString("Nama")));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ViewNamaPlg;

    }

    public ArrayList<String> ViewKdBrg() throws SQLException {

        ArrayList<String> ViewNamaBrg = new ArrayList();

        try {

            Statement Kt = koneksi.getConnection().createStatement();

            ResultSet RsKb = Kt.executeQuery("SELECT Kode_Barang, Nama_Barang FROM Barang");

            while (RsKb.next()) {

                ViewNamaBrg.add(RsKb.getString("Kode_Barang") + "-" + (RsKb.getString("Nama_Barang")));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ViewNamaBrg;

    }

}
