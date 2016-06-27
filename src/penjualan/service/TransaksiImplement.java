package penjualan.service;

import penjualan.config.Koneksi;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TransaksiImplement {

    public int UrutanDb() throws SQLException {
        Statement St = Koneksi.getConnection().createStatement();
        int Jml = 0;
        ResultSet Rs = St.executeQuery("SELECT COUNT(*) AS Urutan FROM penjualan");
        while (Rs.next()) {
            Jml = Rs.getInt("Urutan");
        }
        St.close();
        Rs.close();
        return ++Jml;
    }

    public ArrayList<String> ViewNamaPlg() throws SQLException {
        ArrayList<String> ViewNamaPlg = new ArrayList();
        Statement Kt = Koneksi.getConnection().createStatement();
        ResultSet RsKb = Kt.executeQuery("SELECT * FROM pelanggan");
        while (RsKb.next()) {
            ViewNamaPlg.add(RsKb.getString(1) + "-" + (RsKb.getString(2)));
        }
        return ViewNamaPlg;

    }

    public ArrayList<String> ViewKdBrg() throws SQLException {
        ArrayList<String> ViewNamaBrg = new ArrayList();
        Statement Kt = Koneksi.getConnection().createStatement();
        ResultSet RsKb = Kt.executeQuery("SELECT * FROM barang");
        while (RsKb.next()) {
            ViewNamaBrg.add(RsKb.getString(1) + "-" + (RsKb.getString(2)));
        }
        RsKb.close();
        Kt.close();
        return ViewNamaBrg;

    }

}
