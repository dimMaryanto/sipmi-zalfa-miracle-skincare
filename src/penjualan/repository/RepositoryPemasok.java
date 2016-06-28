/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import penjualan.entity.Pemasok;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPemasok extends BaseRepository<Pemasok, String> {

    public String TABLE_NAME = "supplier";
    public String COLUMN_KODE = "kode_supplier";
    public String COLUMN_NAMA = "nama_supplier";
    public String COLUMN_ALAMAT = "alamat";
    public String COLUMN_TLP = "notlp";
}
