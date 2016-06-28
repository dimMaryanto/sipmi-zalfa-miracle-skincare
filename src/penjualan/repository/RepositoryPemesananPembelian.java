/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import java.sql.SQLException;
import java.util.List;
import penjualan.entity.PemesananPembelian;
import penjualan.entity.PemesananPembelianDetail;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPemesananPembelian extends BaseRepository<PemesananPembelian, String> {

    public String TABLE_NAME = "pesanan_pembelian";
    public String COLUMN_KODE = "kode";
    public String COLUMN_TGL = "tgl";
    public String COLUMN_PEMASOK = "kode_pemasok";

    public String TABLE_PEMESANAN = "pesanan_pembelian";
    public String TABLE_DETAIL_PEMESANAN = "pesanan_pembelian_detail";
    public String COLUMN_PEMESANAN_KODE = "kode";
    public String COLUMN_PEMESANAN_TGL = "tgl";
    public String COLUMN_PEMESANAN_PEMASOK = "kode_pemasok";
    public String COLUMN_DETAIL_PEMESANAN_ID = "id";
    public String COLUMN_DETAIL_PEMESANAN_PEMBELIAN = "kode_pesanan";
    public String COLUMN_DETAIL_PEMESANAN_BARANG = "kode_barang";
    public String COLUMN_DETAIL_PEMESANAN_JUMLAH = "jumlah";
    public String COLUMN_DETAIL_PEMESANAN_HARGA = "harga";

    public void save(PemesananPembelian pesan, List<PemesananPembelianDetail> listBarang) throws SQLException;

    public List<PemesananPembelianDetail> findByPemesananPembelianKode(String kode) throws SQLException;

}
