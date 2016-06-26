/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.crud;

import app.configs.Koneksi;
import app.entity.Pengguna;
import app.repository.RepositoryPengguna;
import app.service.ServicePengguna;
import java.sql.SQLException;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author dimmaryanto
 */
public class TestPenggunaCRUD extends TestCase {

    private RepositoryPengguna repo;

    @Test
    public void testFindAll() throws SQLException {
        repo = new ServicePengguna(Koneksi.getDataSource());
        List<Pengguna> daftarPengguna = repo.findAll();
        assertEquals(daftarPengguna.size(), 3);
    }

    @Test
    public void testLoginAsAdmin() throws SQLException {
        repo = new ServicePengguna(Koneksi.getDataSource());
        Pengguna user = repo.findByUsernameAndPasswordAndStatus("admin", "admin", true);
        assertNotNull(user);
        assertEquals(user.getNama(), "admin");
    }

}
