/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.crud;

import app.configs.Koneksi;
import app.entity.Barang;
import app.entity.Pemasok;
import app.entity.PemesananPembelian;
import app.entity.PemesananPembelianDetail;
import app.repository.RepositoryPemesananPembelian;
import app.service.ServicePemesananPembelian;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author dimmaryanto
 */
public class TestPemesananPembelianCRUD extends TestCase {

    private RepositoryPemesananPembelian repo;

    @Test
    public void testFindOne() throws SQLException {
        repo = new ServicePemesananPembelian(Koneksi.getDataSource());
        PemesananPembelian pp = repo.findOne("PO-20160625-5");
        assertEquals(pp.getPemasok().getKode(), Integer.valueOf(1));
    }

    @Test
    public void testFindAll() throws SQLException {
        repo = new ServicePemesananPembelian(Koneksi.getDataSource());
        List<PemesananPembelian> daftarPesanan = repo.findAll();
        assertNotNull(daftarPesanan);
        assertEquals(daftarPesanan.size(), 7);
    }

    @Test
    public void testFindPemesananPembelian() throws SQLException {
        repo = new ServicePemesananPembelian(Koneksi.getDataSource());
        PemesananPembelianDetail p = repo.findByPemesananDetailKode(1);
        assertNotNull(p);
        assertEquals(p.getBarang().getKode(), "PW01");
    }

    @Test
    public void testFindByPesananKode() throws SQLException {
        repo = new ServicePemesananPembelian(Koneksi.getDataSource());
        List<PemesananPembelianDetail> daftarDetailPesanan = repo.findByPemesananKode("PO-20160601-2");
        assertNotNull(daftarDetailPesanan);
        assertEquals(daftarDetailPesanan.size(), 2);
    }

    @Test
    public void testDeletePemesanPembelian() throws SQLException {
        repo = new ServicePemesananPembelian(Koneksi.getDataSource());
        repo.delete("PO-20160625-6");
    }

    @Test
    public void testInsertPemesanan() throws SQLException {
        repo = new ServicePemesananPembelian(Koneksi.getDataSource());
        PemesananPembelian p = new PemesananPembelian();
        p.setKode("P0-20160625-11");
        p.setTanggal(Date.valueOf(LocalDate.now()));
        Pemasok pasok = new Pemasok();
        pasok.setKode(2);
        p.setPemasok(pasok);

        PemesananPembelianDetail pd = new PemesananPembelianDetail();
        Barang b = new Barang();
        b.setKode("PW04");
        pd.setBarang(b);
        pd.setJumlah(2);
        pd.setPesanPembelian(p);

        PemesananPembelianDetail pd2 = new PemesananPembelianDetail();
        Barang b2 = new Barang();
        b2.setKode("PW03");
        pd2.setBarang(b);
        pd2.setJumlah(1);
        pd2.setPesanPembelian(p);

        List<PemesananPembelianDetail> daftarBeli = new ArrayList<>();
        daftarBeli.add(pd);
        daftarBeli.add(pd2);

        repo.save(p, daftarBeli);
    }

}
