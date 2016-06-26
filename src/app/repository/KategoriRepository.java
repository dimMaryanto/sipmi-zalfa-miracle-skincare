/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.Kategori;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dimmaryanto
 */
public interface KategoriRepository {
    
    public String TABLE_NAME = "m_kategori";
    public String COLUMN_KODE = "kode";
    public String COLUMN_NAME = "nama";

    public Kategori findOne(Integer kode) throws SQLException;

    public List<Kategori> findAll() throws SQLException;    

    public Kategori save(Kategori b) throws SQLException;

    public Kategori update(Integer id, Kategori b) throws SQLException;

    public void delete(Integer id) throws SQLException;

}
