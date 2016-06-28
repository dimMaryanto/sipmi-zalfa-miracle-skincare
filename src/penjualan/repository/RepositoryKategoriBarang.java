/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import penjualan.entity.KategoriBarang;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryKategoriBarang extends BaseRepository<KategoriBarang, String> {

    public String TABLE_NAME = "kategori_brg";
    public String COLUMN_KODE = "id_kategori";
    public String COLUMN_NAME = "kategori";
}
