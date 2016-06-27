/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import penjualan.entity.Pemasok;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lani
 */
public interface supplierInterfc {

    List getAll() throws SQLException;

    Pemasok insert(Pemasok o) throws SQLException;

    void update(Pemasok o) throws SQLException;

    void delete(String kode_supplier) throws SQLException;

}
