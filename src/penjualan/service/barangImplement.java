/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.service;

import penjualan.entity.Barang;
import penjualan.config.Koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import penjualan.repository.barangInterfc;

/**
 *
 * @author Lani
 */
public class barangImplement implements barangInterfc {

    public int urutanDb() throws SQLException {
        Statement st = Koneksi.getConnection().createStatement();
        int jml = 0;
        try {
            ResultSet rs = st.executeQuery("select count(*) as urutan from barang");
            while (rs.next()) {
                jml = rs.getInt("urutan");
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ++jml;
    }

    //--untuk select atau view databarang dari DB ke Form----//
    @Override
    public List<Barang> getAll() throws SQLException {
        Statement st = Koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from barang");
        List<Barang> list = new ArrayList<>();
        while (rs.next()) {
            Barang brg = new Barang();
            brg.setKodeBarang(rs.getString("kode_barang"));
            brg.setKodeKategori(rs.getString("id_kategori"));
            brg.setNamaBarang(rs.getString("nama_barang"));
            brg.setHarga(rs.getInt("harga"));
            brg.setJumlah(rs.getInt("jumlah"));
            brg.setPaket(rs.getBoolean("paket"));
            list.add(brg);
        }
        return list;

    }

//---untuk insert ke database---//
    @Override
    public Barang insert(Barang o) throws SQLException {
        PreparedStatement st = Koneksi.getConnection().prepareStatement("insert into barang values (?,?,?,?,?,?)");
        st.setString(1, o.getKodeBarang());
        st.setString(2, o.getKodeKategori());
        st.setString(3, o.getNamaBarang());
        st.setDouble(4, o.getHarga());
        st.setInt(5, o.getJumlah());
        st.setBoolean(6, o.getPaket());
        st.executeUpdate();
        return o;
    }
//--untuk update ke database--//

    @Override
    public void update(Barang o) throws SQLException {
        PreparedStatement st = Koneksi.getConnection().prepareStatement("update barang set"
                + "id_kategori=?, nama_barang=?, hargaBeli=?, hargaJual=?, jumlah=? where kode_barang=?");
        st.setString(1, o.getKodeKategori());
        st.setString(2, o.getNamaBarang());
        st.setString(3, o.getNamaBarang());
        st.setDouble(4, o.getHarga());
        st.setInt(5, o.getJumlah());
        st.executeUpdate();
    }
//--untuk delete data berdasarkan kode_barang--//

    @Override
    public void delete(String kode_barang) throws SQLException {
        PreparedStatement st = Koneksi.getConnection().prepareStatement("delete from barang where kode_barang=?");
        st.setString(1, kode_barang);
        st.executeUpdate();
    }

}
