/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Barang;
import app.entity.Kategori;
import app.entity.Pemasok;
import app.entity.PemesananPembelian;
import app.entity.PemesananPembelianDetail;
import app.repository.RepositoryBarang;
import app.repository.RepositoryKategori;
import app.repository.RepositoryPemasok;
import app.repository.RepositoryPemesananPembelian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

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
    public List<PemesananPembelian> findAll() throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("b.").append(COLUMN_PEMESANAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_TGL).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_PEMASOK).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_NAMA).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_ALAMAT).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_TLP).append(" ");
        sb.append(" FROM ")
                .append(TABLE_PEMESANAN).append(" b ")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" p ")
                .append(" ON ").append("(").append(" b.").append(COLUMN_PEMESANAN_PEMASOK).append(" = ").append("p.").append(RepositoryPemasok.COLUMN_KODE).append(" )");

        List<PemesananPembelian> daftarPembelian = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PemesananPembelian b = new PemesananPembelian();
            b.setKode(rs.getString(1));
            b.setTanggal(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setKode(rs.getInt(3));
            p.setNama(rs.getString(4));
            p.setAlamat(rs.getString(5));
            p.setTlp(rs.getString(6));
            b.setPemasok(p);
            daftarPembelian.add(b);
        }

        ps.close();
        rs.close();
        connect.close();
        return daftarPembelian;
    }

    @Override
    public PemesananPembelian findOne(String id) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("b.").append(COLUMN_PEMESANAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_TGL).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_PEMASOK).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_NAMA).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_ALAMAT).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_TLP).append(" ");
        sb.append(" FROM ")
                .append(TABLE_PEMESANAN).append(" b ")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" p ")
                .append(" ON ").append("(").append(" b.").append(COLUMN_PEMESANAN_PEMASOK).append(" = ").append("p.").append(RepositoryPemasok.COLUMN_KODE).append(" )");
        sb.append(" WHERE ").append("b.").append(COLUMN_PEMESANAN_KODE).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        PemesananPembelian b = null;
        if (rs.next()) {
            b = new PemesananPembelian();
            b.setKode(rs.getString(1));
            b.setTanggal(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setKode(rs.getInt(3));
            p.setNama(rs.getString(4));
            p.setAlamat(rs.getString(5));
            p.setTlp(rs.getString(6));
            b.setPemasok(p);
        }

        ps.close();
        rs.close();
        connect.close();
        return b;
    }

    @Override
    public PemesananPembelianDetail findByPemesananDetailKode(Integer id) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ")
                .append("b.").append(COLUMN_PEMESANAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_TGL).append(", \n")
                .append("pm.").append(RepositoryPemasok.COLUMN_KODE).append(", ")
                .append("pm.").append(RepositoryPemasok.COLUMN_NAMA).append(", ")
                .append("pm.").append(RepositoryPemasok.COLUMN_ALAMAT).append(", ")
                .append("pm.").append(RepositoryPemasok.COLUMN_TLP).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_KODE).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_NAMA).append(", \n")
                .append("kb.").append(RepositoryKategori.COLUMN_KODE).append(", ")
                .append("kb.").append(RepositoryKategori.COLUMN_NAME).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_HARGA).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_JUMLAH).append(", \n")
                .append("bd.").append(COLUMN_DETAIL_PEMESANAN_JUMLAH).append(" \n");
        sb.append(" FROM ")
                .append(TABLE_DETAIL_PEMESANAN).append(" bd ")
                .append(" JOIN ")
                .append(TABLE_PEMESANAN).append(" b ")
                .append(" ON (").append("bd.").append(COLUMN_DETAIL_PEMESANAN_PEMBELIAN).append(" = ").append("b.").append(COLUMN_PEMESANAN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryBarang.TABLE_NAME).append(" brg ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KODE).append(" = ").append("bd.").append(COLUMN_DETAIL_PEMESANAN_BARANG).append(") \n")
                .append(" JOIN ")
                .append(RepositoryKategori.TABLE_NAME).append(" kb ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KATEGORI).append(" = ").append("kb.").append(RepositoryKategori.COLUMN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" pm ")
                .append(" ON (").append("pm.").append(RepositoryPemasok.COLUMN_KODE).append(" = ").append("b.").append(COLUMN_PEMESANAN_PEMASOK).append(") \n");
        sb.append(" WHERE bd.").append(COLUMN_DETAIL_PEMESANAN_KODE).append(" = ?");

        System.out.println(sb.toString());
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        PemesananPembelianDetail bd = null;
        if (rs.next()) {
            bd = new PemesananPembelianDetail();

            PemesananPembelian b = new PemesananPembelian();
            b.setKode(rs.getString(1));
            b.setTanggal(rs.getDate(2));

            Pemasok pm = new Pemasok();
            pm.setKode(rs.getInt(3));
            pm.setNama(rs.getString(4));
            pm.setAlamat(rs.getString(5));
            pm.setTlp(rs.getString(6));
            b.setPemasok(pm);

            Barang brg = new Barang();

            Kategori kb = new Kategori();
            kb.setKode(rs.getInt(9));
            kb.setNama(rs.getString(10));

            brg.setKategori(kb);
            brg.setKode(rs.getString(7));
            brg.setName(rs.getString(8));
            brg.setHarga(rs.getDouble(11));
            brg.setJumlah(rs.getInt(12));

            bd.setPesanPembelian(b);
            bd.setBarang(brg);
            bd.setJumlah(rs.getInt(13));
        }

        return bd;
    }

    @Override
    public List<PemesananPembelianDetail> findByPemesananKode(String kode) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ")
                .append("b.").append(COLUMN_PEMESANAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_TGL).append(", \n")
                .append("pm.").append(RepositoryPemasok.COLUMN_KODE).append(", ")
                .append("pm.").append(RepositoryPemasok.COLUMN_NAMA).append(", ")
                .append("pm.").append(RepositoryPemasok.COLUMN_ALAMAT).append(", ")
                .append("pm.").append(RepositoryPemasok.COLUMN_TLP).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_KODE).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_NAMA).append(", \n")
                .append("kb.").append(RepositoryKategori.COLUMN_KODE).append(", ")
                .append("kb.").append(RepositoryKategori.COLUMN_NAME).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_HARGA).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_JUMLAH).append(", \n")
                .append("bd.").append(COLUMN_DETAIL_PEMESANAN_JUMLAH).append(" \n");
        sb.append(" FROM ")
                .append(TABLE_DETAIL_PEMESANAN).append(" bd ")
                .append(" JOIN ")
                .append(TABLE_PEMESANAN).append(" b ")
                .append(" ON (").append("bd.").append(COLUMN_DETAIL_PEMESANAN_PEMBELIAN).append(" = ").append("b.").append(COLUMN_DETAIL_PEMESANAN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryBarang.TABLE_NAME).append(" brg ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KODE).append(" = ").append("bd.").append(COLUMN_DETAIL_PEMESANAN_BARANG).append(") \n")
                .append(" JOIN ")
                .append(RepositoryKategori.TABLE_NAME).append(" kb ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KATEGORI).append(" = ").append("kb.").append(RepositoryKategori.COLUMN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" pm ")
                .append(" ON (").append("pm.").append(RepositoryPemasok.COLUMN_KODE).append(" = ").append("b.").append(COLUMN_PEMESANAN_PEMASOK).append(") \n");
        sb.append(" WHERE b.").append(COLUMN_PEMESANAN_KODE).append(" = ?");

        System.out.println(sb.toString());
        List<PemesananPembelianDetail> daftarDetailPesanan = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PemesananPembelianDetail bd = new PemesananPembelianDetail();

            PemesananPembelian b = new PemesananPembelian();
            b.setKode(rs.getString(1));
            b.setTanggal(rs.getDate(2));

            Pemasok pm = new Pemasok();
            pm.setKode(rs.getInt(3));
            pm.setNama(rs.getString(4));
            pm.setAlamat(rs.getString(5));
            pm.setTlp(rs.getString(6));
            b.setPemasok(pm);

            Barang brg = new Barang();

            Kategori kb = new Kategori();
            kb.setKode(rs.getInt(9));
            kb.setNama(rs.getString(10));

            brg.setKategori(kb);
            brg.setKode(rs.getString(7));
            brg.setName(rs.getString(8));
            brg.setHarga(rs.getDouble(11));
            brg.setJumlah(rs.getInt(12));

            bd.setPesanPembelian(b);
            bd.setBarang(brg);
            bd.setJumlah(rs.getInt(13));

            daftarDetailPesanan.add(bd);
        }

        rs.close();
        ps.close();
        connect.close();
        return daftarDetailPesanan;
    }

    @Override
    public void save(PemesananPembelian p, List<PemesananPembelianDetail> pd) throws SQLException {
        StringBuilder sqlBeli = new StringBuilder("INSERT INTO ").append(TABLE_PEMESANAN);
        sqlBeli.append("(")
                .append(COLUMN_PEMESANAN_KODE).append(", ")
                .append(COLUMN_PEMESANAN_TGL).append(", ")
                .append(COLUMN_PEMESANAN_PEMASOK).append(") \n");
        sqlBeli.append("VALUES (?,?,?)");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sqlBeli.toString());
        ps.setString(1, p.getKode());
        ps.setDate(2, p.getTanggal());
        ps.setInt(3, p.getPemasok().getKode());
        ps.executeUpdate();
        ps.close();

        StringBuilder sqlDetailBeli = new StringBuilder("INSERT INTO ").append(TABLE_DETAIL_PEMESANAN);
        sqlDetailBeli.append("(")
                .append(COLUMN_DETAIL_PEMESANAN_BARANG).append(", ")
                .append(COLUMN_DETAIL_PEMESANAN_PEMBELIAN).append(", ")
                .append(COLUMN_DETAIL_PEMESANAN_JUMLAH)
                .append(") ");
        sqlDetailBeli.append(" VALUES (?,?,?)");
        ps = connect.prepareStatement(sqlDetailBeli.toString());
        for (PemesananPembelianDetail d : pd) {
            ps.setString(1, d.getBarang().getKode());
            ps.setString(2, d.getPesanPembelian().getKode());
            ps.setInt(3, d.getJumlah());
            ps.addBatch();
        }

        ps.executeBatch();
        ps.close();
        connect.close();
    }

    @Override
    @Deprecated
    public void update(PemesananPembelian p, List<PemesananPembelianDetail> pd) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String kode) throws SQLException {
        StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_PEMESANAN);
        sb.append(" WHERE ").append(COLUMN_PEMESANAN_KODE).append(" = ?");
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, kode);
        ps.executeUpdate();

        ps.close();
        connect.close();
    }

}
