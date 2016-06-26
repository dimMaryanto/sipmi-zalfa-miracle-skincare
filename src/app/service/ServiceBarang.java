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
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author dimmaryanto
 */
public class ServiceBarang implements BarangRepository {

    private DataSource ds;

    public ServiceBarang(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Barang findOne(String kode) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("b.kode, b.nama, b.harga, b.jumlah, k.kode, k.nama ");
        sb.append("FROM m_barang b JOIN m_kategori k ON (b.kode_kategori = k.kode) ");
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
            Integer kodeKategori = hasil.getInt(5);
            Kategori k = null;
            if (kodeKategori != 0) {
                KategoriRepository repo = new ServiceKategori(ds);
                k = repo.findOne(kodeKategori);
            }
            b.setKategori(k);
        }
        ps.close();
        connect.close();
        return b;
    }

    @Override
    public List<Barang> findByKategori(Kategori k) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Barang> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Barang save(Barang b) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Barang update(String id, Barang b) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
