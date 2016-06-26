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

    public Kategori findOne(Integer kode) throws SQLException;

    public List<Kategori> findAll() throws SQLException;    

    public Kategori save(Kategori b) throws SQLException;

    public Kategori update(String id, Kategori b) throws SQLException;

    public void delete(String id) throws SQLException;

}
