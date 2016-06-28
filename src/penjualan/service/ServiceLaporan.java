/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import penjualan.entity.Barang;
import penjualan.entity.KategoriBarang;
import penjualan.entity.Pelanggan;
import penjualan.entity.Pemasok;
import penjualan.entity.Pembelian;
import penjualan.entity.PembelianDetail;
import penjualan.entity.Penjualan;
import penjualan.entity.PenjualanDetail;

/**
 *
 * @author dimmaryanto
 */
public class ServiceLaporan {

    private final DataSource ds;

    public ServiceLaporan(DataSource ds) {
        this.ds = ds;
    }

    public List<PenjualanDetail> findPenjualanDetailBetweenTanggal(Date awal, Date Akhir) throws SQLException {
        List<PenjualanDetail> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    j.no_faktur as kode_penjualan,\n"
                + "    j.tgl_penjualan as tanggal_jual,\n"
                + "    p.id_pelanggan as kode_pelanggan,\n"
                + "    p.nama as nama_pelanggan,\n"
                + "    p.jp as agen,\n"
                + "    p.alamat as alamat_pelanggan,\n"
                + "    p.notlp as tlp_pelanggan,\n"
                + "    jd.harga as harga_jual,\n"
                + "    jd.diskon as diskon_jual,\n"
                + "    jd.jumlah as jumlah_jual,\n"
                + "    brg.kode_barang as kode_barang,\n"
                + "    brg.nama_barang as nama_barang,\n"
                + "    brg.harga as harga_barang, \n"
                + "    brg.jumlah as stok_barang,\n"
                + "    brg.paket as paket_barang,\n"
                + "    kbrg.id_kategori as kode_kategori_barang,\n"
                + "    kbrg.kategori as nama_kategori\n"
                + "FROM penjualan j \n"
                + "    JOIN detail_jual jd ON (j.no_faktur = jd.no_faktur)\n"
                + "    JOIN pelanggan p ON (j.id_pelanggan = p.id_pelanggan)\n"
                + "    JOIN barang brg ON (brg.kode_barang = jd.kode_barang)\n"
                + "    JOIN kategori_brg kbrg ON (kbrg.id_kategori = brg.id_kategori)\n"
                + "WHERE j.tgl_penjualan between ? AND ?";

        Connection connect = ds.getConnection();
        System.out.println("Tanggal " + awal + ":" + Akhir);
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setDate(1, awal);
        ps.setDate(2, Akhir);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Penjualan j = new Penjualan();
            j.setKode(rs.getString("kode_penjualan"));
            j.setTgl(rs.getDate("tanggal_jual"));

            Pelanggan p = new Pelanggan();
            p.setKode(rs.getString("kode_pelanggan"));
            p.setNama(rs.getString("nama_pelanggan"));
            p.setJp(rs.getString("agen"));
            p.setAlamat(rs.getString("alamat_pelanggan"));
            p.setTlp(rs.getString("tlp_pelanggan"));
            j.setPelanggan(p);

            PenjualanDetail jd = new PenjualanDetail();
            jd.setPenjualan(j);

            Barang b = new Barang();
            b.setKode(rs.getString("kode_barang"));
            b.setNama(rs.getString("nama_barang"));
            b.setHarga(rs.getDouble("harga_barang"));
            b.setJumlah(rs.getInt("stok_barang"));
            b.setPaket(rs.getBoolean("paket_barang"));

            KategoriBarang kbrg = new KategoriBarang();
            kbrg.setKode(rs.getString("kode_kategori_barang"));
            kbrg.setNama(rs.getString("nama_kategori"));
            b.setKategori(kbrg);

            jd.setBarang(b);
            jd.setDiskon(rs.getDouble("diskon_jual"));
            jd.setHarga(rs.getDouble("harga_jual"));
            jd.setJumlah(rs.getInt("jumlah_jual"));

            list.add(jd);
        }

        ps.close();
        rs.close();
        connect.close();
        return list;
    }

    public List<PembelianDetail> findPembelianDetailBetweenTanggal(Date awal, Date akhir) throws SQLException {
        List<PembelianDetail> daftarBeli = new ArrayList<>();
        String sql = "SELECT\n"
                + "    b.no_struk as kode_pembelian,\n"
                + "    b.tgl_pembelian as tanggal_pembelian,\n"
                + "    p.kode_supplier as kode_pemasok,\n"
                + "    p.nama_supplier as nama_pemasok,\n"
                + "    p.alamat as alamat_pemasok,\n"
                + "    p.notlp as tlp_pemasok,\n"
                + "    bd.harga as harga_beli,\n"
                + "    bd.jumlah as jumlah_beli,\n"
                + "    brg.kode_barang as kode_barang,\n"
                + "    brg.nama_barang as nama_barang,\n"
                + "    brg.harga as harga_barang,\n"
                + "    brg.jumlah as stok_barang,\n"
                + "    brg.paket as paket_barang,\n"
                + "    kbrg.id_kategori as kode_kategori,\n"
                + "    kbrg.kategori as nama_kategori\n"
                + "FROM\n"
                + "    pembelian b JOIN detail_beli bd ON (b.no_struk = bd.no_struk)\n"
                + "    JOIN barang brg ON (bd.kode_barang = brg.kode_barang)\n"
                + "    JOIN kategori_brg kbrg ON (brg.id_kategori = kbrg.id_kategori)\n"
                + "    JOIN supplier p ON (p.kode_supplier = b.kode_supplier)\n"
                + "WHERE b.tgl_pembelian BETWEEN ? AND ?";

        Connection connect = ds.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setDate(1, awal);
        ps.setDate(2, akhir);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Pembelian b = new Pembelian();
            b.setKode(rs.getString("kode_pembelian"));
            b.setTgl(rs.getDate("tanggal_pembelian"));

            Pemasok p = new Pemasok();
            p.setKode(rs.getString("kode_pemasok"));
            p.setNama(rs.getString("nama_pemasok"));
            p.setAlamat(rs.getString("alamat_pemasok"));
            p.setTlp(rs.getString("tlp_pemasok"));
            b.setPemasok(p);

            PembelianDetail bd = new PembelianDetail();
            bd.setHarga(rs.getDouble("harga_beli"));
            bd.setJumlah(rs.getInt("jumlah_beli"));

            Barang brg = new Barang();
            brg.setKode(rs.getString("kode_barang"));
            brg.setNama(rs.getString("nama_barang"));
            brg.setHarga(rs.getDouble("harga_barang"));
            brg.setJumlah(rs.getInt("stok_barang"));
            brg.setPaket(rs.getBoolean("paket_barang"));

            KategoriBarang kbrg = new KategoriBarang();
            kbrg.setKode(rs.getString("kode_kategori"));
            kbrg.setNama(rs.getString("nama_kategori"));
            brg.setKategori(kbrg);
            bd.setBarang(brg);
            bd.setPembelian(b);
            
            daftarBeli.add(bd);
        }

        ps.close();
        rs.close();
        connect.close();
        return daftarBeli;
    }

}
