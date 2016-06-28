/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import java.sql.SQLException;
import java.util.List;
import penjualan.entity.Pembelian;
import penjualan.entity.PembelianDetail;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPembelian extends BaseRepository<Pembelian, String> {

    public String TABLE_PEMBELIAN = "pembelian";
    public String TABLE_DETAIL_PEMBELIAN = "detail_beli";
    public String COLUMN_PEMBELIAN_KODE = "no_struk";
    public String COLUMN_PEMBELIAN_TGL = "tgl_pembelian";
    public String COLUMN_PEMBELIAN_PEMASOK = "kode_supplier";
    public String COLUMN_DETAIL_PEMBELIAN_PEMBELIAN = "no_struk";
    public String COLUMN_DETAIL_PEMBELIAN_BARANG = "kode_barang";
    public String COLUMN_DETAIL_PEMBELIAN_HARGA = "harga";
    public String COLUMN_DETAIL_PEMBELIAN_JUMLAH = "jumlah";

    public void save(Pembelian beli, List<PembelianDetail> list) throws SQLException;
    
    public List<PembelianDetail> findPembelianDetailByPembelian(Pembelian beli)throws SQLException;

}
