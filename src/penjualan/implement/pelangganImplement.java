/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.implement;

import penjualan.entity.pelanggan;
import penjualan.koneksi.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import penjualan.entity.pelanggan;
import penjualan.interfc.pelangganInterfc;

/**
 *
 * @author Lani
 */
public class pelangganImplement implements pelangganInterfc {
//---untuk select atau view datapelanggan dari DB ke form----//  

    public List<pelanggan> getAll() throws SQLException {
        Statement st = koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from pelanggan");
        List<pelanggan> list = new ArrayList<pelanggan>();
        while (rs.next()) {
            pelanggan pel = new pelanggan();
            pel.setid_pelanggan(rs.getString("id_pelanggan"));
            pel.setNama(rs.getString("nama"));
            pel.setjp(rs.getString("jp"));
            pel.setalamat(rs.getString("alamat"));
            pel.setnope(rs.getString("notlp"));
            list.add(pel);
        }
        return list;
    }

    //---untuk insert ke database---//
    public pelanggan insert(pelanggan o) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("insert into pelanggan values (?,?,?,?,?)");
        st.setString(1, o.getid_pelanggan());
        st.setString(2, o.getNama());
        st.setString(3, o.getjp());
        st.setString(4, o.getalamat());
        st.setString(5, o.getnope());
        st.executeUpdate();
        return o;
    }
//--untuk update ke database--//

    public void update(pelanggan o) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("update pelanggan set"
                + " nama=?,jp=?,alamat=?,notlp=? where id_pelanggan=?");
        st.setString(1, o.getNama());
        st.setString(2, o.getjp());
        st.setString(3, o.getalamat());
        st.setString(4, o.getnope());
        st.setString(5, o.getid_pelanggan());
        st.executeUpdate();
    }
//--untuk delete data berdasarkan kode_barang--//

    public void delete(String idPelanggan) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("delete from pelanggan where id_pelanggan=?");
        st.setString(1, idPelanggan);
        st.executeUpdate();
    }

}
