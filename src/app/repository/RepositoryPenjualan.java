/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.Penjualan;
import app.entity.PenjualanDetail;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPenjualan {

    public String TABLE_PENJUALAN = "t_penjualan";
    public String TABLE_DETAIL_PENJUALAN = "t_penjualan_detail";

    public String COLUMN_PENJUALAN_KODE = "kode";
    public String COLUMN_PENJUALAN_TANGGAL = "tgl";
    public String COLUMN_PENJUALAN_PELANGGAN = "kode_pelanggan";

    public String COLUMN_DETAIL_PENJUALAN_ID = "id";
    public String COLUMN_DETAIL_PENJUALAN_PENJUALAN = "kode_penjualan";
    public String COLUMN_DETAIL_PENJUALAN_BARANG = "kode_barang";
    public String COLUMN_DETAIL_PENJUALAN_HARGA = "harga";
    public String COLUMN_DETAIL_PENJUALAN_JUMLAH = "jumlah";
    public String COLUMN_DETAIL_PENJUALAN_DISKON = "diskon";

    public List<Penjualan> findAll() throws SQLException;

    public List<PenjualanDetail> findByPenjualanKode(String kode) throws SQLException;

}
