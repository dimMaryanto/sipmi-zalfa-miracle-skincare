/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Pelanggan;
import java.sql.SQLException;
import java.util.List;
import app.repository.RepositoryPelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author dimmaryanto
 */
public class ServicePelanggan implements RepositoryPelanggan {

    private final DataSource ds;

    public ServicePelanggan(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Pelanggan fineOne(String kode) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(COLUMN_KODE).append(", ")
                .append(COLUMN_NAMA).append(", ")
                .append(COLUMN_ALAMAT).append(", ")
                .append(COLUMN_TLP).append(", ")
                .append(COLUMN_AGEN);
        sb.append(" FROM ").append(TABLE_NAME);
        sb.append(" WHERE ").append(COLUMN_KODE).append(" = ? ");

        Connection connent = ds.getConnection();
        PreparedStatement ps = connent.prepareStatement(sb.toString());
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        Pelanggan p = null;
        if (rs.next()) {
            p = new Pelanggan();
            p.setKode(rs.getString(COLUMN_KODE));
            p.setNama(rs.getString(COLUMN_NAMA));
            p.setAlamat(rs.getString(COLUMN_ALAMAT));
            p.setTlp(rs.getString(COLUMN_TLP));
            p.setAgen(rs.getBoolean(COLUMN_AGEN));
        }

        ps.close();
        rs.close();
        connent.close();
        return p;

    }

    @Override
    public List<Pelanggan> findAll() throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(COLUMN_KODE).append(", ")
                .append(COLUMN_NAMA).append(", ")
                .append(COLUMN_ALAMAT).append(", ")
                .append(COLUMN_TLP).append(", ")
                .append(COLUMN_AGEN);
        sb.append(" FROM ").append(TABLE_NAME);

        List<Pelanggan> daftarPelanggan = new ArrayList<>();
        Connection connent = ds.getConnection();
        PreparedStatement ps = connent.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        Pelanggan p = null;
        while (rs.next()) {
            p = new Pelanggan();
            p.setKode(rs.getString(COLUMN_KODE));
            p.setNama(rs.getString(COLUMN_NAMA));
            p.setAlamat(rs.getString(COLUMN_ALAMAT));
            p.setTlp(rs.getString(COLUMN_TLP));
            p.setAgen(rs.getBoolean(COLUMN_AGEN));
            daftarPelanggan.add(p);
        }

        ps.close();
        rs.close();
        connent.close();
        return daftarPelanggan;
    }

    @Override
    public Pelanggan save(Pelanggan p) throws SQLException {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME);
        sb.append(" (");
        sb.append(COLUMN_KODE).append(", ")
                .append(COLUMN_NAMA).append(", ")
                .append(COLUMN_ALAMAT).append(", ")
                .append(COLUMN_TLP).append(", ")
                .append(COLUMN_AGEN);
        sb.append(") ");
        sb.append(" VALUES (?,?,?,?,?)");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareCall(sb.toString());
        ps.setString(1, p.getKode());
        ps.setString(2, p.getNama());
        ps.setString(3, p.getAlamat());
        ps.setString(4, p.getTlp());
        ps.setBoolean(5, p.getAgen());
        ps.executeUpdate();

        ps.close();
        connect.close();
        return p;
    }

    @Override
    public Pelanggan update(String id, Pelanggan p) throws SQLException {
        StringBuilder sb = new StringBuilder("UPDATE ").append(TABLE_NAME).append(" SET ");
        sb.append(COLUMN_NAMA).append(" = ?, ")
                .append(COLUMN_ALAMAT).append(" = ?, ")
                .append(COLUMN_TLP).append(" = ?, ")
                .append(COLUMN_AGEN).append(" = ? ");
        sb.append(" WHERE ").append(COLUMN_KODE).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareCall(sb.toString());
        ps.setString(1, p.getNama());
        ps.setString(2, p.getAlamat());
        ps.setString(3, p.getTlp());
        ps.setBoolean(4, p.getAgen());
        ps.setString(5, p.getKode());
        ps.executeUpdate();

        ps.close();
        connect.close();
        return p;
    }

    @Override
    public void delete(String id) throws SQLException {
        StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME);
        sb.append(" WHERE ").append(COLUMN_KODE).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareCall(sb.toString());
        ps.setString(1, id);
        ps.executeUpdate();

        ps.close();
        connect.close();
    }

}
