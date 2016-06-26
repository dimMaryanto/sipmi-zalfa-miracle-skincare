/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.crud;

import app.configs.Koneksi;
import app.entity.Pembelian;
import app.entity.PembelianDetail;
import app.repository.RepositoryPembelian;
import app.service.ServicePembelian;
import java.sql.SQLException;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author dimmaryanto
 */
public class TestPembelianCRUD extends TestCase {

    private RepositoryPembelian repo;

    @Test
    public void testFindOne() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        Pembelian p = repo.findOne("PB-20160601-2");
        assertNotNull(p);
        assertEquals(p.getPemasok().getKode(), Integer.valueOf(2));

    }

    public void testFindByPembelianDetail() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        List<PembelianDetail> pd = repo.findByPembelianKode("PB-20160601-2");
        assertEquals(pd.size(), 4);
    }

    public void testFindOneByPembelianDetail() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        PembelianDetail pd = repo.findByPembelianDetailKode(2);
        assertNotNull(pd);
        assertEquals(pd.getBarang().getKode(), "PB01");
    }

}
