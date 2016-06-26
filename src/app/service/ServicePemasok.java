/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Pemasok;
import app.repository.RepositoryPemasok;
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
public class ServicePemasok implements RepositoryPemasok {

    private DataSource ds;

    public ServicePemasok(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Pemasok fineOne(String kode) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(COLUMN_KODE).append(", ")
                .append(COLUMN_NAMA).append(", ")
                .append(COLUMN_ALAMAT).append(", ")
                .append(COLUMN_TLP);
        sb.append(" FROM ").append(TABLE_NAME);
        sb.append(" WHERE ").append(COLUMN_KODE).append(" = ? ");

        Connection connent = ds.getConnection();
        PreparedStatement ps = connent.prepareStatement(sb.toString());
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        Pemasok p = null;
        if (rs.next()) {
            p = new Pemasok();
            p.setKode(rs.getInt(COLUMN_KODE));
            p.setNama(rs.getString(COLUMN_NAMA));
            p.setAlamat(rs.getString(COLUMN_ALAMAT));
            p.setTlp(rs.getString(COLUMN_TLP));
        }

        ps.close();
        rs.close();
        connent.close();
        return p;
    }

    @Override
    public List<Pemasok> findAll() throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(COLUMN_KODE).append(", ")
                .append(COLUMN_NAMA).append(", ")
                .append(COLUMN_ALAMAT).append(", ")
                .append(COLUMN_TLP);
        sb.append(" FROM ").append(TABLE_NAME);

        List<Pemasok> daftarPemasok = new ArrayList<>();
        Connection connent = ds.getConnection();
        PreparedStatement ps = connent.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        Pemasok p = null;
        while (rs.next()) {
            p = new Pemasok();
            p.setKode(rs.getInt(COLUMN_KODE));
            p.setNama(rs.getString(COLUMN_NAMA));
            p.setAlamat(rs.getString(COLUMN_ALAMAT));
            p.setTlp(rs.getString(COLUMN_TLP));
            daftarPemasok.add(p);
        }

        ps.close();
        rs.close();
        connent.close();
        return daftarPemasok;
    }

    @Override
    public Pemasok save(Pemasok p) throws SQLException {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME);
        sb.append(" (");
        sb.append(COLUMN_KODE).append(", ")
                .append(COLUMN_NAMA).append(", ")
                .append(COLUMN_ALAMAT).append(", ")
                .append(COLUMN_TLP);
        sb.append(") ");
        sb.append(" VALUES (?,?,?,?)");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareCall(sb.toString());
        ps.setInt(1, p.getKode());
        ps.setString(2, p.getNama());
        ps.setString(3, p.getAlamat());
        ps.setString(4, p.getTlp());
        ps.executeUpdate();

        ps.close();
        connect.close();
        return p;
    }

    @Override
    public Pemasok update(String id, Pemasok p) throws SQLException {
        StringBuilder sb = new StringBuilder("UPDATE ").append(TABLE_NAME).append(" SET ");
        sb.append(COLUMN_NAMA).append(" = ?, ")
                .append(COLUMN_ALAMAT).append(" = ?, ")
                .append(COLUMN_TLP).append(" = ? ");
        sb.append(" WHERE ").append(COLUMN_KODE).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareCall(sb.toString());
        ps.setString(1, p.getNama());
        ps.setString(2, p.getAlamat());
        ps.setString(3, p.getTlp());
        ps.setInt(4, p.getKode());
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
