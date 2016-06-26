/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.PemesananPembelian;
import app.entity.PemesananPembelianDetail;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPemesananPembelian extends BaseRepository<PemesananPembelian, String> {

    public String TABLE_PEMESANAN = "t_pemesanan_pembelian";
    public String TABLE_DETAIL_PEMESANAN = "t_pemesanan_pembelian_detail";
    public String COLUMN_PEMESANAN_KODE = "kode";
    public String COLUMN_PEMESANAN_TGL = "tgl";
    public String COLUMN_PEMESANAN_PEMASOK = "kode_pemasok";
    public String COLUMN_DETAIL_PEMESANAN_KODE = "kode";
    public String COLUMN_DETAIL_PEMESANAN_PEMBELIAN = "kode_pemesanan";
    public String COLUMN_DETAIL_PEMESANAN_BARANG = "kode_barang";
    public String COLUMN_DETAIL_PEMESANAN_JUMLAH = "jumlah";

    public PemesananPembelianDetail findByPemesananDetailKode(Integer id) throws SQLException;

    public List<PemesananPembelianDetail> findByPemesananKode(String kode) throws SQLException;

    public void save(PemesananPembelian p, List<PemesananPembelianDetail> pd) throws SQLException;

    public void update(PemesananPembelian p, List<PemesananPembelianDetail> pd) throws SQLException;

}
