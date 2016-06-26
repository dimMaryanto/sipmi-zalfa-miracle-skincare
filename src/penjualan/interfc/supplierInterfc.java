/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.interfc;

import penjualan.entity.supplier;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lani
 */
public interface supplierInterfc {

    List getAll() throws SQLException;

    supplier insert(supplier o) throws SQLException;

    void update(supplier o) throws SQLException;

    void delete(String kode_supplier) throws SQLException;

}
