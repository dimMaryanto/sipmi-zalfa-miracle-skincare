/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import penjualan.entity.Pemasok;
import penjualan.entity.PemesananPembelian;
import penjualan.repository.RepositoryPemesananPembelian;

/**
 *
 * @author dimmaryanto
 */
public class ServicePemesananPembelian implements RepositoryPemesananPembelian {

    private DataSource ds;

    public ServicePemesananPembelian(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public PemesananPembelian save(PemesananPembelian pesan) throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(TABLE_NAME);
        sql.append(" (")
                .append(COLUMN_KODE).append(", ")
                .append(COLUMN_TGL).append(", ")
                .append(COLUMN_PEMASOK).append(" )");
        sql.append(" VALUES (?,?,?)");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql.toString());
        ps.setString(1, pesan.getKode());
        ps.setDate(2, pesan.getTgl());
        ps.setString(3, pesan.getPemasok().getKode());
        ps.executeUpdate();

        ps.close();
        connect.close();
        return pesan;
    }

    @Override
    @Deprecated
    public PemesananPembelian update(PemesananPembelian value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PemesananPembelian> findAll() throws SQLException {
        String sql = "select \n"
                + "    p.kode as kode_pesanan, \n"
                + "    p.tgl as tanggal_transaksi, \n"
                + "    s.kode_supplier as kode_pemasok, \n"
                + "    s.nama_supplier as nama_pemasok, \n"
                + "    s.alamat as alamat_pemasok, \n"
                + "    s.notlp as tlp_pemasok\n"
                + "from pesanan_pembelian p JOIN supplier s ON (p.kode_pemasok = s.kode_supplier)";

        List<PemesananPembelian> daftarPesanan = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PemesananPembelian pesan = new PemesananPembelian();
            pesan.setKode(rs.getString(1));
            pesan.setTgl(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setKode(rs.getString(3));
            p.setNama(rs.getString(4));
            p.setAlamat(rs.getString(5));
            p.setTlp(rs.getString(6));
            pesan.setPemasok(p);

            daftarPesanan.add(pesan);
        }

        ps.close();
        rs.close();
        connect.close();
        return daftarPesanan;
    }

    @Override
    public PemesananPembelian findOne(String id) throws SQLException {
        String sql = "select \n"
                + "    p.kode as kode_pesanan, \n"
                + "    p.tgl as tanggal_transaksi, \n"
                + "    s.kode_supplier as kode_pemasok, \n"
                + "    s.nama_supplier as nama_pemasok, \n"
                + "    s.alamat as alamat_pemasok, \n"
                + "    s.notlp as tlp_pemasok\n"
                + "from pesanan_pembelian p JOIN supplier s ON (p.kode_pemasok = s.kode_supplier)\n"
                + "WHERE p.kode = ?";
        List<PemesananPembelian> daftarPesanan = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        PemesananPembelian pesan = null;
        if (rs.next()) {
            pesan = new PemesananPembelian();
            pesan.setKode(rs.getString(1));
            pesan.setTgl(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setKode(rs.getString(3));
            p.setNama(rs.getString(4));
            p.setAlamat(rs.getString(5));
            p.setTlp(rs.getString(6));
            pesan.setPemasok(p);

            daftarPesanan.add(pesan);
        }

        ps.close();
        rs.close();
        connect.close();
        return pesan;
    }

    @Override
    public Boolean exists(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(PemesananPembelian pesan, List<PemesananPembelian> listBarang) throws SQLException {
        save(pesan);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
