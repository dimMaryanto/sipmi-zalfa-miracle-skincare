/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  dimmaryanto
 * Created: Jun 28, 2016
 */

SELECT
    j.no_faktur as kode_penjualan,
    j.tgl_penjualan as tanggal_jual,
    p.id_pelanggan as kode_pelanggan,
    p.nama as nama_pelanggan,
    p.jp as agen,
    p.alamat as alamat_pelanggan,
    p.notlp as tlp_pelanggan,
    jd.harga as harga_jual,
    jd.diskon as diskon_jual,
    jd.jumlah as jumlah_jual,
    brg.kode_barang as kode_barang,
    brg.nama_barang as nama_barang,
    brg.harga as harga_barang, 
    brg.jumlah as stok_barang,
    brg.paket as paket_barang,
    kbrg.id_kategori as kode_kategori_barang,
    kbrg.kategori as nama_kategori
FROM penjualan j 
    JOIN detail_jual jd ON (j.no_faktur = jd.no_faktur)
    JOIN pelanggan p ON (j.id_pelanggan = p.id_pelanggan)
    JOIN barang brg ON (brg.kode_barang = jd.kode_barang)
    JOIN kategori_brg kbrg ON (kbrg.id_kategori = brg.id_kategori)
WHERE j.tgl_penjualan between '2016-06-01' AND '2016-06-31'
