/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.repository;

import java.sql.SQLException;
import java.util.List;
import penjualan.entity.Pembelian;
import penjualan.entity.PembelianDetail;

/**
 *
 * @author dimmaryanto
 */
public interface RepositoryPembelian extends BaseRepository<Pembelian, String> {

    public void save(Pembelian beli, List<PembelianDetail> list) throws SQLException;

}
