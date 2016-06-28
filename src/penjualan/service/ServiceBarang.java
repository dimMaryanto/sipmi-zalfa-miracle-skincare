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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import penjualan.entity.Barang;
import penjualan.entity.KategoriBarang;
import penjualan.repository.RepositoryBarang;

/**
 *
 * @author dimmaryanto
 */
public class ServiceBarang implements RepositoryBarang {

    private DataSource ds;

    public ServiceBarang(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Barang save(Barang value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Barang update(Barang value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Barang> findAll() throws SQLException {
        String sql = "select \n"
                + "    b.kode_barang, \n"
                + "    b.nama_barang, \n"
                + "    b.harga, \n"
                + "    b.jumlah, \n"
                + "    b.paket, \n"
                + "    k.id_kategori, \n"
                + "    k.kategori\n"
                + "from barang b JOIN kategori_brg k ON (b.id_kategori = k.id_kategori)";

        Connection connect = ds.getConnection();
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<Barang> list = new ArrayList<>();
        while (rs.next()) {
            Barang brg = new Barang();
            brg.setKode(rs.getString(1));
            brg.setNama(rs.getString(2));
            brg.setHarga(rs.getDouble(3));
            brg.setJumlah(rs.getInt(4));
            brg.setPaket(rs.getBoolean(5));

            KategoriBarang k = new KategoriBarang();
            k.setKode(rs.getString(6));
            k.setNama(rs.getString(7));
            brg.setKategori(k);

            list.add(brg);
        }

        st.close();
        rs.close();
        connect.close();

        return list;
    }

    @Override
    public Barang findOne(String id) throws SQLException {

        String sql = "select \n"
                + "    b.kode_barang, \n"
                + "    b.nama_barang, \n"
                + "    b.harga, \n"
                + "    b.jumlah, \n"
                + "    b.paket, \n"
                + "    k.id_kategori, \n"
                + "    k.kategori\n"
                + "from barang b JOIN kategori_brg k ON (b.id_kategori = k.id_kategori)\n"
                + "WHERE b.kode_barang = ?";

        Connection connect = ds.getConnection();
        PreparedStatement st = connect.prepareStatement(sql);
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        Barang brg = null;
        if (rs.next()) {
            brg = new Barang();
            brg.setKode(rs.getString(1));
            brg.setNama(rs.getString(2));
            brg.setHarga(rs.getDouble(3));
            brg.setJumlah(rs.getInt(4));
            brg.setPaket(rs.getBoolean(5));

            KategoriBarang k = new KategoriBarang();
            k.setKode(rs.getString(6));
            k.setNama(rs.getString(7));
            brg.setKategori(k);

        }

        st.close();
        rs.close();
        connect.close();
        return brg;
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
