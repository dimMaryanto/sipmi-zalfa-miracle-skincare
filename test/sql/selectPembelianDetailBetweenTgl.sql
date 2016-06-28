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
    b.no_struk as kode_pembelian,
    b.tgl_pembelian as tanggal_pembelian,
    p.kode_supplier as kode_pemasok,
    p.nama_supplier as nama_pemasok,
    p.alamat as alamat_pemasok,
    p.notlp as tlp_pemasok,
    bd.harga as harga_beli,
    bd.jumlah as jumlah_beli,
    brg.kode_barang as kode_barang,
    brg.nama_barang as nama_barang,
    brg.harga as harga_barang,
    brg.jumlah as stok_barang,
    brg.paket as paket_barang,
    kbrg.id_kategori as kode_kategori,
    kbrg.kategori as nama_kategori
FROM
    pembelian b JOIN detail_beli bd ON (b.no_struk = bd.no_struk)
    JOIN barang brg ON (bd.kode_barang = brg.kode_barang)
    JOIN kategori_brg kbrg ON (brg.id_kategori = kbrg.id_kategori)
    JOIN supplier p ON (p.kode_supplier = b.kode_supplier)
WHERE b.tgl_pembelian BETWEEN '2016-06-01' AND '2016-06-30'