/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Pengguna;
import app.repository.RepositoryPengguna;
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
public class ServicePengguna implements RepositoryPengguna {

    private final DataSource ds;

    public ServicePengguna(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Pengguna> findAll() throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(COLUMN_ID).append(", ")
                .append(COLUMN_USERNAME).append(", ")
                .append(COLUMN_PASSWORD).append(", ")
                .append(COLUMN_NAME).append(", ")
                .append(COLUMN_JABATAN).append(", ")
                .append(COLUMN_STATUS);
        sb.append(" FROM ").append(TABLE_NAME);

        List<Pengguna> daftarPengguna = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Pengguna p = new Pengguna();
            p.setId(rs.getInt(1));
            p.setUsername(rs.getString(2));
            p.setPassword(rs.getString(3));
            p.setNama(rs.getString(4));
            p.setJabatan(rs.getString(5));
            p.setStatus(rs.getBoolean(6));
            daftarPengguna.add(p);
        }

        ps.close();
        rs.close();
        connect.close();
        return daftarPengguna;
    }

    @Override
    public Pengguna save(Pengguna user) throws SQLException {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME);
        sb.append(" (")
                .append(COLUMN_USERNAME).append(", ")
                .append(COLUMN_PASSWORD).append(", ")
                .append(COLUMN_JABATAN).append(", ")
                .append(COLUMN_NAME).append(", ")
                .append(COLUMN_STATUS).append(") ");
        sb.append(" VALUES (?, ?, ? , ? , ?)");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getJabatan());
        ps.setString(4, user.getNama());
        ps.setBoolean(5, false);
        ps.executeUpdate();

        ps.close();
        connect.close();
        return user;
    }

    @Override
    @Deprecated
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public Boolean exists(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public Pengguna findOne(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public Pengguna update(Integer id, Pengguna value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pengguna findByUsernameAndPasswordAndStatus(String username, String password, Boolean status) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(COLUMN_ID).append(", ")
                .append(COLUMN_USERNAME).append(", ")
                .append(COLUMN_PASSWORD).append(", ")
                .append(COLUMN_NAME).append(", ")
                .append(COLUMN_JABATAN).append(", ")
                .append(COLUMN_STATUS);
        sb.append(" FROM ").append(TABLE_NAME);
        sb.append(" WHERE ").append(COLUMN_USERNAME).append(" = ? ")
                .append(" AND ")
                .append(COLUMN_PASSWORD).append(" = ? ")
                .append(" AND ")
                .append(COLUMN_STATUS).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setBoolean(3, status);
        ResultSet rs = ps.executeQuery();
        Pengguna p = null;
        if (rs.next()) {
            p = new Pengguna();
            p.setId(rs.getInt(1));
            p.setUsername(rs.getString(2));
            p.setPassword(rs.getString(3));
            p.setNama(rs.getString(4));
            p.setJabatan(rs.getString(5));
            p.setStatus(rs.getBoolean(6));

        }

        ps.close();
        rs.close();
        connect.close();
        return p;
    }

    @Override
    public Boolean exists(String username) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(COLUMN_ID).append(", ")
                .append(COLUMN_USERNAME).append(", ")
                .append(COLUMN_PASSWORD).append(", ")
                .append(COLUMN_NAME).append(", ")
                .append(COLUMN_JABATAN).append(", ")
                .append(COLUMN_STATUS);
        sb.append(" FROM ").append(TABLE_NAME);
        sb.append(" WHERE ").append(COLUMN_USERNAME).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        
        Boolean p = false;
        if (rs.next()) {
            p = true;
        } else {
            p = false;
        }

        ps.close();
        rs.close();
        connect.close();
        return p;
    }

}
