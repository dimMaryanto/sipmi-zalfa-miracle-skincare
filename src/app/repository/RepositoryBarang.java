/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.Barang;
import app.entity.Kategori;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryBarang {
    
    public String TABLE_NAME = "m_barang";
    public String COLUMN_KODE = "kode";
    public String COLUMN_NAMA = "nama";
    public String COLUMN_KATEGORI = "kode_kategori";
    public String COLUMN_HARGA = "harga";
    public String COLUMN_JUMLAH = "jumlah";

    public Barang findOne(String kode) throws SQLException;
    
    public List<Barang> findByKategori(Kategori k) throws SQLException;

    public List<Barang> findAll() throws SQLException;

    public Barang save(Barang b) throws SQLException;

    public Barang update(String id, Barang b) throws SQLException;

    public void delete(String id) throws SQLException;

}