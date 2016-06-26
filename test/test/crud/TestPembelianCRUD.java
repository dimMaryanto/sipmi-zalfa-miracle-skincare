/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.crud;

import app.configs.Koneksi;
import app.entity.Barang;
import app.entity.Pemasok;
import app.entity.Pembelian;
import app.entity.PembelianDetail;
import app.repository.RepositoryPembelian;
import app.service.ServicePembelian;
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
public class TestPembelianCRUD extends TestCase {

    private RepositoryPembelian repo;

    @Test
    public void testFindOne() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        Pembelian p = repo.findOne("PB-20160601-2");
        assertNotNull(p);
        assertEquals(p.getPemasok().getKode(), Integer.valueOf(2));

    }

    @Test
    public void testFindByPembelianDetail() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        List<PembelianDetail> pd = repo.findByPembelianKode("PB-20160601-2");
        assertEquals(pd.size(), 4);
    }

    @Test
    public void testFindOneByPembelianDetail() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        PembelianDetail pd = repo.findByPembelianDetailKode(2);
        assertNotNull(pd);
        assertEquals(pd.getBarang().getKode(), "PB01");
    }

    @Test
    public void testFindAllPembelian() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        List<Pembelian> daftarBeli = repo.findAll();
        assertNotNull(daftarBeli);
        assertEquals(daftarBeli.size(), 6);
    }

    @Test
    public void testInsertPembelian() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        Pembelian p = new Pembelian();
        p.setKode("PB-20160625-7");
        p.setTanggal(Date.valueOf(LocalDate.now()));
        Pemasok pasok = new Pemasok();
        pasok.setKode(2);
        p.setPemasok(pasok);

        PembelianDetail pd = new PembelianDetail();
        Barang b = new Barang();
        b.setKode("PW04");
        pd.setBarang(b);
        pd.setHarga(139000D);
        pd.setJumlah(2);
        pd.setPembelian(p);

        PembelianDetail pd2 = new PembelianDetail();
        Barang b2 = new Barang();
        b2.setKode("PW03");
        pd2.setBarang(b);
        pd2.setHarga(195000D);
        pd2.setJumlah(1);
        pd2.setPembelian(p);

        List<PembelianDetail> daftarBeli = new ArrayList<>();
        daftarBeli.add(pd);
        daftarBeli.add(pd2);

        repo.save(p, daftarBeli);
    }

    @Test
    public void testDeletePembelian() throws SQLException {
        repo = new ServicePembelian(Koneksi.getDataSource());
        repo.delete("PB-20160625-7");
    }

}
