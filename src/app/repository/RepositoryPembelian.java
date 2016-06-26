/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.Pembelian;
import app.entity.PembelianDetail;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPembelian {

    public String TABLE_PEMBELIAN = "t_pembelian";
    public String TABLE_DETAIL_PEMBELIAN = "t_pembelian_detail";
    public String COLUMN_PEMBELIAN_KODE = "kode";
    public String COLUMN_PEMBELIAN_TGL = "tgl";
    public String COLUMN_PEMBELIAN_PEMASOK = "kode_pemasok";
    public String COLUMN_DETAIL_PEMBELIAN_KODE = "kode";
    public String COLUMN_DETAIL_PEMBELIAN_PEMBELIAN = "kode_pembelian";
    public String COLUMN_DETAIL_PEMBELIAN_BARANG = "kode_barang";
    public String COLUMN_DETAIL_PEMBELIAN_HARGA = "harga";
    public String COLUMN_DETAIL_PEMBELIAN_JUMLAH = "jumlah";

    public List<Pembelian> findAll() throws SQLException;

    public Pembelian findOne(String id) throws SQLException;

    public PembelianDetail findByPembelianDetailKode(Integer id) throws SQLException;

    public List<PembelianDetail> findByPembelianKode(String kode) throws SQLException;

    public void save(Pembelian p, List<PembelianDetail> pd) throws SQLException;

    public void update(Pembelian p, List<PembelianDetail> pd) throws SQLException;

    public void delete(Pembelian p) throws SQLException;

}
