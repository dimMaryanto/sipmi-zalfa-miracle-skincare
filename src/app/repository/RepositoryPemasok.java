/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.entity.Pemasok;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPemasok {
    
    public String TABLE_NAME = "m_pemasok";
    public String COLUMN_KODE = "kode";
    public String COLUMN_NAMA = "nama";
    public String COLUMN_ALAMAT = "alamat";
    public String COLUMN_TLP = "tlp";

    public Pemasok fineOne(String kode) throws SQLException;

    public List<Pemasok> findAll() throws SQLException;

    public Pemasok save(Pemasok p) throws SQLException;

    public Pemasok update(String id, Pemasok p) throws SQLException;

    public void delete(String id) throws SQLException;
    
}
