/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.Pemasok;
import app.entity.PemesananPembelian;
import app.entity.PemesananPembelianDetail;
import app.repository.RepositoryPemasok;
import app.repository.RepositoryPemesananPembelian;
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
public class ServicePemesananPembelian implements RepositoryPemesananPembelian {

    private DataSource ds;

    public ServicePemesananPembelian(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<PemesananPembelian> findAll() throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("b.").append(COLUMN_PEMESANAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_TGL).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_PEMASOK).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_NAMA).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_ALAMAT).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_TLP).append(" ");
        sb.append(" FROM ")
                .append(TABLE_PEMESANAN).append(" b ")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" p ")
                .append(" ON ").append("(").append(" b.").append(COLUMN_PEMESANAN_PEMASOK).append(" = ").append("p.").append(RepositoryPemasok.COLUMN_KODE).append(" )");

        List<PemesananPembelian> daftarPembelian = new ArrayList<>();
        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PemesananPembelian b = new PemesananPembelian();
            b.setKode(rs.getString(1));
            b.setTanggal(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setKode(rs.getInt(3));
            p.setNama(rs.getString(4));
            p.setAlamat(rs.getString(5));
            p.setTlp(rs.getString(6));
            b.setPemasok(p);
            daftarPembelian.add(b);
        }

        ps.close();
        rs.close();
        connect.close();
        return daftarPembelian;
    }

    @Override
    public PemesananPembelian findOne(String id) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("b.").append(COLUMN_PEMESANAN_KODE).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_TGL).append(", ")
                .append("b.").append(COLUMN_PEMESANAN_PEMASOK).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_NAMA).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_ALAMAT).append(", ")
                .append("p.").append(RepositoryPemasok.COLUMN_TLP).append(" ");
        sb.append(" FROM ")
                .append(TABLE_PEMESANAN).append(" b ")
                .append(" JOIN ")
                .append(RepositoryPemasok.TABLE_NAME).append(" p ")
                .append(" ON ").append("(").append(" b.").append(COLUMN_PEMESANAN_PEMASOK).append(" = ").append("p.").append(RepositoryPemasok.COLUMN_KODE).append(" )");
        sb.append(" WHERE ").append("b.").append(COLUMN_PEMESANAN_KODE).append(" = ? ");

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sb.toString());
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        PemesananPembelian b = null;
        if (rs.next()) {
            b = new PemesananPembelian();
            b.setKode(rs.getString(1));
            b.setTanggal(rs.getDate(2));

            Pemasok p = new Pemasok();
            p.setKode(rs.getInt(3));
            p.setNama(rs.getString(4));
            p.setAlamat(rs.getString(5));
            p.setTlp(rs.getString(6));
            b.setPemasok(p);
        }

        ps.close();
        rs.close();
        connect.close();
        return b;
    }

    @Override
    public PemesananPembelianDetail findByPemesananDetailKode(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PemesananPembelianDetail> findByPemesananKode(String kode) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(PemesananPembelian p, List<PemesananPembelianDetail> pd) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PemesananPembelian p, List<PemesananPembelianDetail> pd) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String kode) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
