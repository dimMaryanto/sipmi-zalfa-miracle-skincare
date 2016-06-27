/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.service;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import penjualan.entity.Pembelian;
import penjualan.entity.PembelianDetail;
import penjualan.repository.RepositoryPembelian;

/**
 *
 * @author dimmaryanto
 */
public class ServicePembelian implements RepositoryPembelian {

    private DataSource ds;

    public ServicePembelian(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Pembelian save(Pembelian value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pembelian update(Pembelian value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pembelian> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pembelian findOne(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean exists(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Pembelian beli, List<PembelianDetail> list) throws SQLException {
        save(beli);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
