/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.crud;

import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.Test;
import app.configs.Koneksi;
import app.entity.Kategori;
import app.repository.KategoriRepository;
import app.service.ServiceKategori;

public class TestKategoriCRUD extends TestCase {

    @Test
    public void testConnection() throws SQLException {
        KategoriRepository repo = new ServiceKategori(Koneksi.getDataSource());
        Kategori k = repo.findOne(1);
        assertEquals(k.getNama(), "Perawatan wajah");
    }

}
