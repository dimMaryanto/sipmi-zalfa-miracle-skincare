/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import penjualan.entity.Barang;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryBarang extends BaseRepository<Barang, String> {

    public String TABLE_NAME = "barang";
    public String COLUMN_KODE = "kode_barang";
    public String COLUMN_NAMA = "nama_barang";
    public String COLUMN_KATEGORI = "id_kategori";
    public String COLUMN_HARGA = "harga";
    public String COLUMN_JUMLAH = "jumlah";
    public String COLUMN_PAKET = "paket";

}
