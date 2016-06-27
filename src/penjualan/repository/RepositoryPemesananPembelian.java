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

    public void save(PemesananPembelian pesan, List<PemesananPembelianDetail> listBarang) throws SQLException;

    public List<PemesananPembelianDetail> findByPemesananPembelianKode(String kode) throws SQLException;

}
