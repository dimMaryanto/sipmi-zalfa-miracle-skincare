/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Kategori;
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
public class ServiceKategori implements KategoriRepository {

    private DataSource ds;

    public ServiceKategori(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Kategori findOne(Integer kode) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * ");
        sb.append(" FORM ").append(TABLE_NAME).append(" WHERE ").append(COLUMN_KODE).append(" = ?");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setInt(1, kode);
        ResultSet hasil = ps.executeQuery();
        Kategori k = new Kategori();
        if (hasil.next()) {
            k.setKode(hasil.getInt(1));
            k.setNama(hasil.getString(2));
        }

        ps.close();
        connect.close();
        return k;
    }

    @Override
    public List<Kategori> findAll() throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * ").append(" FORM ").append(TABLE_NAME);

        List<Kategori> daftarKategori = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ResultSet hasil = ps.executeQuery();
        while (hasil.next()) {
            Kategori k = new Kategori();
            k.setKode(hasil.getInt(1));
            k.setNama(hasil.getString(2));
            daftarKategori.add(k);
        }

        ps.close();
        connect.close();
        return daftarKategori;
    }

    @Override
    public Kategori save(Kategori b) throws SQLException {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME);
        sb.append(" (").append(COLUMN_NAME).append(") ");
        sb.append("VALUES (?)");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, b.getNama());
        ps.executeUpdate();

        ps.close();
        connect.close();
        return b;
    }

    @Override
    public Kategori update(Integer id, Kategori b) throws SQLException {
        StringBuilder sb = new StringBuilder("UPDATE ").append(TABLE_NAME);
        sb.append(" SET ").append(COLUMN_NAME).append(" = ? ");
        sb.append(" WHERE ").append(COLUMN_KODE).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, b.getNama());
        ps.setInt(2, id);
        ps.executeUpdate();

        ps.close();
        connect.close();
        return b;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME);
        sb.append(" WHERE ").append(COLUMN_KODE).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setInt(1, id);
        ps.executeUpdate();

        ps.close();
        connect.close();
    }

}
