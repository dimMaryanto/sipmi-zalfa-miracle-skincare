/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  dimmaryanto
 * Created: Jun 28, 2016
 */

select
    b.kode as kode_pesan,
    b.tgl as tgl_pesan,
    bd.id as kode_pesan_barang, 
    bd.harga as harga_pesan_barang, 
    bd.jumlah as jumlah_pesan_barang,
    brg.kode_barang as kode_barang,
    brg.nama_barang as nama_barang, 
    brg.harga as harga_barang, 
    brg.jumlah as jumlah_barang,
    brg.paket as paket, 
    k.id_kategori as kode_kategori_barang,
    k.kategori as nama_kategori_barang
from
    pesanan_pembelian b JOIN pesanan_pembelian_detail bd ON (b.kode = bd.kode_pesanan)
    JOIN barang brg ON (brg.kode_barang = bd.kode_barang)
    JOIN kategori_brg k ON (brg.id_kategori = k.id_kategori)
WHERE b.kode = 'PO-160628-001'

