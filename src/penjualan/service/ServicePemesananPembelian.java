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
import penjualan.entity.PesananPembelian;
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
    public PesananPembelian save(PesananPembelian value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public PesananPembelian update(PesananPembelian value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PesananPembelian> findAll() throws SQLException {
        String sql = "select \n"
                + "    p.kode as kode_pesanan, \n"
                + "    p.tgl as tanggal_transaksi, \n"
                + "    s.kode_supplier as kode_pemasok, \n"
                + "    s.nama_supplier as nama_pemasok, \n"
                + "    s.alamat as alamat_pemasok, \n"
                + "    s.notlp as tlp_pemasok\n"
                + "from pesanan_pembelian p JOIN supplier s ON (p.kode_pemasok = s.kode_supplier)";

        List<PesananPembelian> daftarPesanan = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PesananPembelian pesan = new PesananPembelian();
            pesan.setKode(rs.getString(1));
            pesan.setTgl(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setkode_supplier(rs.getString(3));
            p.setnama(rs.getString(4));
            p.setalamat(rs.getString(5));
            p.setnope(rs.getString(6));
            pesan.setPemasok(p);

            daftarPesanan.add(pesan);
        }

        ps.close();
        rs.close();
        connect.close();
        return daftarPesanan;
    }

    @Override
    public PesananPembelian findOne(String id) throws SQLException {
        String sql = "select \n"
                + "    p.kode as kode_pesanan, \n"
                + "    p.tgl as tanggal_transaksi, \n"
                + "    s.kode_supplier as kode_pemasok, \n"
                + "    s.nama_supplier as nama_pemasok, \n"
                + "    s.alamat as alamat_pemasok, \n"
                + "    s.notlp as tlp_pemasok\n"
                + "from pesanan_pembelian p JOIN supplier s ON (p.kode_pemasok = s.kode_supplier)\n"
                + "WHERE p.kode = ?";
        List<PesananPembelian> daftarPesanan = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        PesananPembelian pesan = null;
        if (rs.next()) {
            pesan = new PesananPembelian();
            pesan.setKode(rs.getString(1));
            pesan.setTgl(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setkode_supplier(rs.getString(3));
            p.setnama(rs.getString(4));
            p.setalamat(rs.getString(5));
            p.setnope(rs.getString(6));
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
    public void save(PesananPembelian pesan, List<PesananPembelian> listBarang) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
