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
public interface BarangRepository {

    public Barang findOne(String kode) throws SQLException;
    
    public List<Barang> findByKategori(Kategori k) throws SQLException;

    public List<Barang> findAll() throws SQLException;

    public Barang save(Barang b) throws SQLException;

    public Barang update(String id, Barang b) throws SQLException;

    public void delete(String id) throws SQLException;

}
