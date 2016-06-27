package penjualan.service;

import penjualan.config.Koneksi;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lani
 */
public class TransaksiPembelianImplement {

    public int UrutanDb() throws SQLException {

        Statement St = Koneksi.getConnection().createStatement();

        int Jml = 0;

        try {

            ResultSet Rs = St.executeQuery("SELECT COUNT(*) AS Urutan FROM pembelian");

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

    public ArrayList<String> ViewKdBrg() throws SQLException {

        ArrayList<String> ViewNamaBrg = new ArrayList();

        try {

            Statement Kt = Koneksi.getConnection().createStatement();

            ResultSet RsKb = Kt.executeQuery("SELECT kode_barang, nama_barang FROM barang");

            while (RsKb.next()) {

                ViewNamaBrg.add(RsKb.getString("kode_barang") + "-" + (RsKb.getString("nama_barang")));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ViewNamaBrg;

    }

    public ArrayList<String> ViewIdSp() throws SQLException {

        ArrayList<String> ViewIdSp = new ArrayList();

        try {

            Statement Kt = Koneksi.getConnection().createStatement();

            ResultSet RsKb = Kt.executeQuery("SELECT kode_supplier, nama_supplier FROM supplier");

            while (RsKb.next()) {

                ViewIdSp.add(RsKb.getString("kode_supplier") + "-" + (RsKb.getString("nama_supplier")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ViewIdSp;

    }
}
