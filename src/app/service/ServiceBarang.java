/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Barang;
import app.entity.Kategori;
import app.repository.BarangRepository;
import app.repository.KategoriRepository;
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
public class ServiceBarang implements BarangRepository {

    private final DataSource ds;

    public ServiceBarang(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Barang findOne(String kode) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("b." + COLUMN_KODE + ", b." + COLUMN_NAMA + ", b." + COLUMN_HARGA + ", b."
                + COLUMN_JUMLAH + ", k." + KategoriRepository.COLUMN_KODE + ", k." + KategoriRepository.COLUMN_NAME + " ");
        sb.append("FROM " + TABLE_NAME + " b JOIN " + KategoriRepository.TABLE_NAME + " k ON (b.kode_kategori = k.kode) ");
        sb.append("WHERE b.kode = ?");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, kode);
        ResultSet hasil = ps.executeQuery();

        Barang b = new Barang();
        if (hasil.next()) {
            b.setKode(hasil.getString(1));
            b.setName(hasil.getString(2));
            b.setHarga(hasil.getDouble(3));
            b.setJumlah(hasil.getInt(4));

            Kategori k = new Kategori();
            k.setKode(hasil.getInt(5));
            k.setNama(hasil.getString(6));
            b.setKategori(k);
        }
        ps.close();
        connect.close();
        return b;
    }

    @Override
    public List<Barang> findByKategori(Kategori k) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * ");
        sb.append("FROM m_barang ");
        sb.append("WHERE b.kode_kategori = ?");

        List<Barang> daftarBarang = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setInt(1, k.getKode());
        ResultSet hasil = ps.executeQuery();
        while (hasil.next()) {
            Barang b = new Barang();
            b.setKode(hasil.getString(1));
            b.setName(hasil.getString(2));
            b.setHarga(hasil.getDouble(3));
            b.setJumlah(hasil.getInt(4));
            b.setKategori(k);
            daftarBarang.add(b);
        }

        ps.close();
        connect.close();
        return daftarBarang;
    }

    @Override
    public List<Barang> findAll() throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * ");
        sb.append("FROM m_barang ");

        List<Barang> daftarBarang = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ResultSet hasil = ps.executeQuery();
        while (hasil.next()) {
            Barang b = new Barang();
            b.setKode(hasil.getString(1));
            b.setName(hasil.getString(2));
            b.setHarga(hasil.getDouble(3));
            b.setJumlah(hasil.getInt(4));
            daftarBarang.add(b);
        }

        ps.close();
        connect.close();
        return daftarBarang;
    }

    @Override
    public Barang save(Barang b) throws SQLException {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME);
        sb.append("(")
                .append(COLUMN_KODE).append(", ")
                .append(COLUMN_NAMA).append(", ")
                .append(COLUMN_KATEGORI).append(", ")
                .append(COLUMN_HARGA).append(", ")
                .append(COLUMN_JUMLAH)
                .append(")");
        sb.append(" VALUES (?, ?, ?, ?, ?)");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, b.getKode());
        ps.setString(2, b.getName());
        ps.setInt(3, b.getKategori().getKode());
        ps.setDouble(4, b.getHarga());
        ps.setInt(5, b.getJumlah());
        ps.executeUpdate();
        ps.close();
        connect.close();

        return b;
    }

    @Override
    public Barang update(String id, Barang b) throws SQLException {
        StringBuilder sb = new StringBuilder("UPDATE " + TABLE_NAME + " SET ");
        sb.append(COLUMN_NAMA).append(" = ?, ")
                .append(COLUMN_KATEGORI).append(" = ?, ")
                .append(COLUMN_HARGA).append(" = ?, ")
                .append(COLUMN_JUMLAH).append(" = ? ");
        sb.append("WHERE " + COLUMN_KODE + " = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, b.getName());
        ps.setInt(2, b.getKategori().getKode());
        ps.setDouble(3, b.getHarga());
        ps.setInt(4, b.getJumlah());
        ps.setString(5, id);
        ps.executeUpdate();

        ps.close();
        connect.close();
        return b;
    }

    @Override
    public void delete(String id) throws SQLException {
        StringBuilder sb = new StringBuilder("DELETE FROM " + TABLE_NAME + " ");
        sb.append("WHERE " + COLUMN_KODE + " = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, id);
        ps.executeUpdate();

        ps.close();
        connect.close();
    }

}
