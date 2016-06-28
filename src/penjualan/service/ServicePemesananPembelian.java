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
import penjualan.entity.Barang;
import penjualan.entity.KategoriBarang;
import penjualan.entity.Pemasok;
import penjualan.entity.PemesananPembelian;
import penjualan.entity.PemesananPembelianDetail;
import penjualan.repository.RepositoryPemesananPembelian;

/**
 *
 * @author dimmaryanto
 */
public class ServicePemesananPembelian implements RepositoryPemesananPembelian {

    private final DataSource ds;

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
    public void save(PemesananPembelian pesan, List<PemesananPembelianDetail> listBarang) throws SQLException {
        save(pesan);

        String sql = "INSERT INTO pesanan_pembelian_detail (kode_pesanan, kode_barang, harga, jumlah) VALUES (?,?,?,?)";
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        for (PemesananPembelianDetail d : listBarang) {
            ps.setString(1, d.getPesan().getKode());
            ps.setString(2, d.getBarang().getKode());
            ps.setDouble(3, d.getHarga());
            ps.setInt(4, d.getJumlah());
            ps.addBatch();
        }

        ps.executeBatch();
        ps.close();
        connect.close();
    }

    @Override
    public List<PemesananPembelianDetail> findByPemesananPembelianKode(String kode) throws SQLException {
        String sql = "select\n"
                + "    b.kode as kode_pesan,\n"
                + "    b.tgl as tgl_pesan,\n"
                + "    bd.id as kode_pesan_barang, \n"
                + "    bd.harga as harga_pesan_barang, \n"
                + "    bd.jumlah as jumlah_pesan_barang,\n"
                + "    brg.kode_barang as kode_barang,\n"
                + "    brg.nama_barang as nama_barang, \n"
                + "    brg.harga as harga_barang, \n"
                + "    brg.jumlah as jumlah_barang,\n"
                + "    brg.paket as paket, \n"
                + "    k.id_kategori as kode_kategori_barang,\n"
                + "    k.kategori as nama_kategori_barang\n"
                + "from\n"
                + "    pesanan_pembelian b JOIN pesanan_pembelian_detail bd ON (b.kode = bd.kode_pesanan)\n"
                + "    JOIN barang brg ON (brg.kode_barang = bd.kode_barang)\n"
                + "    JOIN kategori_brg k ON (brg.id_kategori = k.id_kategori)\n"
                + "WHERE b.kode = ?";

        List<PemesananPembelianDetail> list = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PemesananPembelian b = new PemesananPembelian();
            b.setKode(rs.getString("kode_pesan"));
            b.setTgl(rs.getDate("tgl_pesan"));

            PemesananPembelianDetail bd = new PemesananPembelianDetail();
            bd.setId(rs.getInt("kode_pesan_barang"));
            bd.setHarga(rs.getDouble("harga_pesan_barang"));
            bd.setJumlah(rs.getInt("jumlah_pesan_barang"));

            Barang brg = new Barang();
            brg.setKode(rs.getString("kode_barang"));
            brg.setNama(rs.getString("nama_barang"));
            brg.setHarga(rs.getDouble("harga_barang"));
            brg.setJumlah(rs.getInt("jumlah_barang"));
            brg.setPaket(rs.getBoolean("paket"));

            KategoriBarang k = new KategoriBarang();
            k.setKode(rs.getString("kode_kategori_barang"));
            k.setNama(rs.getString("nama_kategori_barang"));
            brg.setKategori(k);
            bd.setBarang(brg);
            bd.setPesan(b);

            list.add(bd);
        }

        ps.close();
        rs.close();
        connect.close();
        return list;
    }

    @Override
    public void delete(String id) throws SQLException {
        Connection connect = ds.getConnection();
        StringBuilder sqlDetail = new StringBuilder("DELETE FROM ").append(TABLE_DETAIL_PEMESANAN);
        sqlDetail.append(" WHERE ").append(COLUMN_DETAIL_PEMESANAN_PEMBELIAN).append(" = ?");
        PreparedStatement ps2 = connect.prepareStatement(sqlDetail.toString());
        ps2.setString(1, id);
        ps2.executeUpdate();

        StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_PEMESANAN);
        sb.append(" WHERE ").append(COLUMN_PEMESANAN_KODE).append(" = ?");
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, id);
        ps.executeUpdate();

        ps.close();
        ps2.close();
        connect.close();
    }

}
