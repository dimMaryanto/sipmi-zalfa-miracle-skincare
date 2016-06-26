/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.Pelanggan;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dimmaryanto
 */
public interface PelangganRepository {

    public Pelanggan fineOne(String kode) throws SQLException;

    public List<Pelanggan> findAll() throws SQLException;

    public Pelanggan save(Pelanggan p) throws SQLException;

    public Pelanggan update(String id, Pelanggan p) throws SQLException;

    public void delete(String id) throws SQLException;
}
