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
import penjualan.repository.RepositoryPemasok;

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
    public Pemasok save(Pemasok value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pemasok update(Pemasok value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pemasok> findAll() throws SQLException {
        String sql = "select * from supplier";

        List<Pemasok> daftarPemasok = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Pemasok p = new Pemasok();
            p.setKode(rs.getString(1));
            p.setNama(rs.getString(2));
            p.setAlamat(rs.getString(3));
            p.setTlp(rs.getString(4));
            daftarPemasok.add(p);
        }

        ps.close();
        rs.close();
        connect.close();
        return daftarPemasok;
    }

    @Override
    public Pemasok findOne(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean exists(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
