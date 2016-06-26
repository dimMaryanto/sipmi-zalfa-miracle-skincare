/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Barang;
import app.entity.Kategori;
import app.entity.Pemasok;
import app.entity.Pembelian;
import app.entity.PembelianDetail;
import app.repository.RepositoryBarang;
import app.repository.RepositoryKategori;
import app.repository.RepositoryPemasok;
import app.repository.RepositoryPembelian;
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
public class ServicePembelian implements RepositoryPembelian {

    private final DataSource ds;

    public ServicePembelian(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Pembelian findOne(String id) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("b.").append(COLUMN_PEMBELIAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMBELIAN_TGL).append(", ")
                .append("b.").append(COLUMN_PEMBELIAN_PEMASOK).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_NAMA).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_ALAMAT).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_TLP).append(" ");
        sb.append(" FROM ")
                .append(TABLE_PEMBELIAN).append(" b ")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" p ")
                .append(" ON ").append("(").append(" b.").append(COLUMN_PEMBELIAN_PEMASOK).append(" = ").append("p.").append(RepositoryPemasok.COLUMN_KODE).append(" )");
        sb.append(" WHERE ").append("b.").append(COLUMN_PEMBELIAN_KODE).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Pembelian b = null;
        if (rs.next()) {
            b = new Pembelian();
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
    public List<PembelianDetail> findByPembelianKode(String kode) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ")
                .append("b.").append(COLUMN_PEMBELIAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMBELIAN_TGL).append(", \n")
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
                .append("bd.").append(COLUMN_DETAIL_PEMBELIAN_HARGA).append(", ")
                .append("bd.").append(COLUMN_DETAIL_PEMBELIAN_JUMLAH).append(" \n");
        sb.append(" FROM ")
                .append(TABLE_DETAIL_PEMBELIAN).append(" bd ")
                .append(" JOIN ")
                .append(TABLE_PEMBELIAN).append(" b ")
                .append(" ON (").append("bd.").append(COLUMN_DETAIL_PEMBELIAN_PEMBELIAN).append(" = ").append("b.").append(COLUMN_PEMBELIAN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryBarang.TABLE_NAME).append(" brg ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KODE).append(" = ").append("bd.").append(COLUMN_DETAIL_PEMBELIAN_BARANG).append(") \n")
                .append(" JOIN ")
                .append(RepositoryKategori.TABLE_NAME).append(" kb ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KATEGORI).append(" = ").append("kb.").append(RepositoryKategori.COLUMN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" pm ")
                .append(" ON (").append("pm.").append(RepositoryPemasok.COLUMN_KODE).append(" = ").append("b.").append(COLUMN_PEMBELIAN_PEMASOK).append(") \n");
        sb.append(" WHERE b.").append(COLUMN_PEMBELIAN_KODE).append(" = ?");

        System.out.println(sb.toString());
        List<PembelianDetail> daftarDetailPembelian = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PembelianDetail bd = new PembelianDetail();

            Pembelian b = new Pembelian();
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

            bd.setPembelian(b);
            bd.setBarang(brg);
            bd.setHarga(rs.getDouble(13));
            bd.setJumlah(rs.getInt(14));

            daftarDetailPembelian.add(bd);
        }
        return daftarDetailPembelian;
    }

    @Override
    public void save(Pembelian p, List<PembelianDetail> pd) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Pembelian p, List<PembelianDetail> pd) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Pembelian p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pembelian> findAll() throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("b.").append(COLUMN_PEMBELIAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMBELIAN_TGL).append(", ")
                .append("b.").append(COLUMN_PEMBELIAN_PEMASOK).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_NAMA).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_ALAMAT).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_TLP).append(" ");
        sb.append(" FROM ")
                .append(TABLE_PEMBELIAN).append(" b ")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" p ")
                .append(" ON ").append("(").append(" b.").append(COLUMN_PEMBELIAN_PEMASOK).append(" = ").append("p.").append(RepositoryPemasok.COLUMN_KODE).append(" )");

        List<Pembelian> daftarPembelian = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Pembelian b = new Pembelian();
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
    public PembelianDetail findByPembelianDetailKode(Integer id) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ")
                .append("b.").append(COLUMN_PEMBELIAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMBELIAN_TGL).append(", \n")
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
                .append("bd.").append(COLUMN_DETAIL_PEMBELIAN_HARGA).append(", ")
                .append("bd.").append(COLUMN_DETAIL_PEMBELIAN_JUMLAH).append(" \n");
        sb.append(" FROM ")
                .append(TABLE_DETAIL_PEMBELIAN).append(" bd ")
                .append(" JOIN ")
                .append(TABLE_PEMBELIAN).append(" b ")
                .append(" ON (").append("bd.").append(COLUMN_DETAIL_PEMBELIAN_PEMBELIAN).append(" = ").append("b.").append(COLUMN_PEMBELIAN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryBarang.TABLE_NAME).append(" brg ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KODE).append(" = ").append("bd.").append(COLUMN_DETAIL_PEMBELIAN_BARANG).append(") \n")
                .append(" JOIN ")
                .append(RepositoryKategori.TABLE_NAME).append(" kb ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KATEGORI).append(" = ").append("kb.").append(RepositoryKategori.COLUMN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" pm ")
                .append(" ON (").append("pm.").append(RepositoryPemasok.COLUMN_KODE).append(" = ").append("b.").append(COLUMN_PEMBELIAN_PEMASOK).append(") \n");
        sb.append(" WHERE bd.").append(COLUMN_DETAIL_PEMBELIAN_KODE).append(" = ?");

        System.out.println(sb.toString());
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        PembelianDetail bd = null;
        if (rs.next()) {
            bd = new PembelianDetail();

            Pembelian b = new Pembelian();
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

            bd.setPembelian(b);
            bd.setBarang(brg);
            bd.setHarga(rs.getDouble(13));
            bd.setJumlah(rs.getInt(14));

        }
        return bd;
    }

}
