/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import penjualan.entity.Barang;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lani
 */
public interface barangInterfc {

    List getAll() throws SQLException;

    Barang insert(Barang o) throws SQLException;

    void update(Barang o) throws SQLException;

    void delete(String kodeBarang) throws SQLException;

}
