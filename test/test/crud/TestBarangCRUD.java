/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.crud;

import app.configs.Koneksi;
import app.entity.Barang;
import app.repository.BarangRepository;
import app.service.ServiceBarang;
import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author dimmaryanto
 */
public class TestBarangCRUD extends TestCase {

    @Test
    public void testFindBarangById() throws SQLException {
        BarangRepository repo = new ServiceBarang(Koneksi.getDataSource());
        Barang b = repo.findOne("PB01");
        assertNotNull(b);
        assertEquals(b.getName(), "Handbody");
        assertEquals(b.getKategori().getNama(), "Perawatan tubuh");
    }
}
