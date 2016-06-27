/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import penjualan.entity.KategoriBarang;
import penjualan.config.Koneksi;
import penjualan.repository.JKategoriInterfc;
//import penjualan.view.barangview;

/**
 *
 * @author Lani
 */
public class JKategoriImplement implements JKategoriInterfc {

    //------untuk select atau view dataBarang dari DB ke form-------//
    @Override
    public List<KategoriBarang> getAll() throws SQLException {
        Statement st = Koneksi.getConnection().createStatement();
        ResultSet rs1 = st.executeQuery("select * from kategori_brg");
        List<KategoriBarang> list = new ArrayList<>();
        while (rs1.next()) {
            KategoriBarang tKateg = new KategoriBarang();
            tKateg.setid_kategori(rs1.getString("id_kategori"));
            tKateg.setkategori(rs1.getString("kategori"));
            list.add(tKateg);
        }
        return list;

    }

    //-- UNTUK INSERT KE DATABASE --//
    @Override
    public KategoriBarang insert(KategoriBarang o) throws SQLException {
        PreparedStatement st = Koneksi.getConnection().prepareStatement("insert into kategori_brg values(?,?)");
        st.setString(1, o.getid_kategori());
        st.setString(2, o.getkategori());
        st.executeUpdate();
        return o;
    }

    //-- UNTUK UPDATE DATA KE DATABASE --//
    @Override
    public void update(KategoriBarang o) throws SQLException {
        PreparedStatement st = Koneksi.getConnection().prepareStatement("update kategori_brg set kategori=? where id_kategori=?");
        st.setString(1, o.getid_kategori());
        st.setString(2, o.getkategori());
        st.executeUpdate();
    }

//-- UNTUK MENGHAPUS DATA DARI DATABASE --//
    @Override
    public void delete(String JKategori) throws SQLException {
        PreparedStatement st = Koneksi.getConnection().prepareStatement("delete from kategori_brg where id_kategori=?");
        st.setString(1, JKategori);
        st.executeUpdate();
    }

    public ArrayList<String> viewKategori() throws SQLException {
        ArrayList<String> viewJenisKnd = new ArrayList();
        try {
            Statement kt = Koneksi.getConnection().createStatement();
            ResultSet rsKb = kt.executeQuery("select id_kategori, kategori from kategori_brg");
            while (rsKb.next()) {
                viewJenisKnd.add(rsKb.getString("id_kategori") + " - " + (rsKb.getString("kategori")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewJenisKnd;
    }
}
