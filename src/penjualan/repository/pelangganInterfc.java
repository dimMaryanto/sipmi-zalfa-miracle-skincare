/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import penjualan.entity.Pelanggan;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lani
 */
public interface pelangganInterfc {

    List getAll() throws SQLException;

    Pelanggan insert(Pelanggan o) throws SQLException;

    void update(Pelanggan o) throws SQLException;

    void delete(String idPelanggan) throws SQLException;

}
