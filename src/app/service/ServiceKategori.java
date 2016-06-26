/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Barang;
import app.entity.Kategori;
import app.repository.KategoriRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement("SELECT * FROM m_kategori WHERE kode = ?");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Kategori save(Kategori b) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Kategori update(String id, Kategori b) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
