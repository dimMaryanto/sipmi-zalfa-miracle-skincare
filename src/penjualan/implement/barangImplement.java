/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.implement;

import penjualan.entity.barang;
import penjualan.koneksi.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import penjualan.interfc.barangInterfc;

/**
 *
 * @author Lani
 */
public class barangImplement implements barangInterfc {

    public int urutanDb() throws SQLException {
        Statement st = koneksi.getConnection().createStatement();
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
    public List<barang> getAll() throws SQLException {
        Statement st = koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from barang");
        List<barang> list = new ArrayList<barang>();
        while (rs.next()) {
            barang brg = new barang();
            brg.setkodeBarang(rs.getString("kode_barang"));
            brg.setid_kategori(rs.getString("id_kategori"));
            brg.setNamaBarang(rs.getString("nama_barang"));
            brg.setharga(rs.getString("harga"));
            brg.setjumlah(rs.getString("jumlah"));
            list.add(brg);
        }
        return list;

    }

//---untuk insert ke database---//
    public barang insert(barang o) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("insert into barang values (?,?,?,?,?)");
        st.setString(1, o.getkodeBarang());
        st.setString(2, o.getid_kategori());
        st.setString(3, o.getNamaBarang());
        st.setString(4, o.getharga());
        st.setString(5, o.getjumlah());
        st.executeUpdate();
        return o;
    }
//--untuk update ke database--//

    public void update(barang o) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("update barang set"
                + "id_kategori=?, nama_barang=?, hargaBeli=?, hargaJual=?, jumlah=? where kode_barang=?");
        st.setString(1, o.getkodeBarang());
        st.setString(2, o.getid_kategori());
        st.setString(3, o.getNamaBarang());
        st.setString(4, o.getharga());
        st.setString(5, o.getjumlah());
        st.executeUpdate();
    }
//--untuk delete data berdasarkan kode_barang--//

    public void delete(String kode_barang) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("delete from barang where kode_barang=?");
        st.setString(1, kode_barang);
        st.executeUpdate();
    }

}
