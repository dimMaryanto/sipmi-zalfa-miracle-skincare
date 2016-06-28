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
import penjualan.entity.Pembelian;
import penjualan.entity.PembelianDetail;
import penjualan.repository.RepositoryBarang;
import penjualan.repository.RepositoryKategoriBarang;
import penjualan.repository.RepositoryPemasok;
import penjualan.repository.RepositoryPembelian;

/**
 *
 * @author dimmaryanto
 */
public class ServicePembelian implements RepositoryPembelian {

    private DataSource ds;

    public ServicePembelian(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Pembelian save(Pembelian value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pembelian update(Pembelian value) throws SQLException {
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
            b.setTgl(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setKode(rs.getString(3));
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
            b.setTgl(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setKode(rs.getString(3));
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
    public Boolean exists(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Pembelian beli, List<PembelianDetail> list) throws SQLException {
        StringBuilder sqlBeli = new StringBuilder("INSERT INTO ").append(TABLE_PEMBELIAN);
        sqlBeli.append("(")
                .append(COLUMN_PEMBELIAN_KODE).append(", ")
                .append(COLUMN_PEMBELIAN_TGL).append(", ")
                .append(COLUMN_PEMBELIAN_PEMASOK).append(") \n");
        sqlBeli.append("VALUES (?,?,?)");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sqlBeli.toString());
        ps.setString(1, beli.getKode());
        ps.setDate(2, beli.getTgl());
        ps.setString(3, beli.getPemasok().getKode());
        ps.executeUpdate();
        ps.close();

        StringBuilder sqlDetailBeli = new StringBuilder("INSERT INTO ").append(TABLE_DETAIL_PEMBELIAN);
        sqlDetailBeli.append("(")
                .append(COLUMN_DETAIL_PEMBELIAN_BARANG).append(", ")
                .append(COLUMN_DETAIL_PEMBELIAN_PEMBELIAN).append(", ")
                .append(COLUMN_DETAIL_PEMBELIAN_HARGA).append(", ")
                .append(COLUMN_DETAIL_PEMBELIAN_JUMLAH)
                .append(") ");
        sqlDetailBeli.append(" VALUES (?,?,?,?)");
        ps = connect.prepareStatement(sqlDetailBeli.toString());
        for (PembelianDetail d : list) {
            ps.setString(1, d.getBarang().getKode());
            ps.setString(2, d.getPembelian().getKode());
            ps.setDouble(3, d.getHarga());
            ps.setInt(4, d.getJumlah());
            ps.addBatch();
        }

        ps.executeBatch();
        ps.close();
        connect.close();
    }

    @Override
    public void delete(String id) throws SQLException {
        StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_PEMBELIAN);
        sb.append(" WHERE ").append(COLUMN_PEMBELIAN_KODE).append(" = ?");
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, id);
        ps.executeUpdate();

        ps.close();
        connect.close();
    }

    @Override
    public List<PembelianDetail> findPembelianDetailByPembelian(Pembelian beli) throws SQLException {
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
                .append("kb.").append(RepositoryKategoriBarang.COLUMN_KODE).append(", ")
                .append("kb.").append(RepositoryKategoriBarang.COLUMN_NAME).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_HARGA).append(", ")
                .append("brg.").append(RepositoryBarang.COLUMN_JUMLAH).append(", \n")
                .append("brg.").append(RepositoryBarang.COLUMN_PAKET).append(", \n")
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
                .append(RepositoryKategoriBarang.TABLE_NAME).append(" kb ")
                .append(" ON (").append("brg.").append(RepositoryBarang.COLUMN_KATEGORI).append(" = ").append("kb.").append(RepositoryKategoriBarang.COLUMN_KODE).append(") \n")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" pm ")
                .append(" ON (").append("pm.").append(RepositoryPemasok.COLUMN_KODE).append(" = ").append("b.").append(COLUMN_PEMBELIAN_PEMASOK).append(") \n");
        sb.append(" WHERE b.").append(COLUMN_PEMBELIAN_KODE).append(" = ?");

        System.out.println(sb.toString());
        List<PembelianDetail> daftarDetailPembelian = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, beli
                .getKode());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PembelianDetail bd = new PembelianDetail();

            Pembelian b = new Pembelian();
            b.setKode(rs.getString(1));
            b.setTgl(rs.getDate(2));

            Pemasok pm = new Pemasok();
            pm.setKode(rs.getString(3));
            pm.setNama(rs.getString(4));
            pm.setAlamat(rs.getString(5));
            pm.setTlp(rs.getString(6));
            b.setPemasok(pm);

            Barang brg = new Barang();

            KategoriBarang kb = new KategoriBarang();
            kb.setKode(rs.getString(9));
            kb.setNama(rs.getString(10));

            brg.setKategori(kb);
            brg.setKode(rs.getString(7));
            brg.setNama(rs.getString(8));
            brg.setHarga(rs.getDouble(11));
            brg.setJumlah(rs.getInt(12));
            brg.setPaket(rs.getBoolean(13));

            bd.setPembelian(b);
            bd.setBarang(brg);
            bd.setHarga(rs.getDouble(14));
            bd.setJumlah(rs.getInt(15));

            daftarDetailPembelian.add(bd);
        }

        rs.close();
        ps.close();
        connect.close();
        return daftarDetailPembelian;
    }

}
