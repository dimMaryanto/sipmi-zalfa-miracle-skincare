/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  dimmaryanto
 * Created: Jun 27, 2016
 */

select 
    p.kode as kode_pesanan, 
    p.tgl as tanggal_transaksi, 
    s.kode_supplier as kode_pemasok, 
    s.nama_supplier as nama_pemasok, 
    s.alamat as alamat_pemasok, 
    s.notlp as tlp_pemasok
from pesanan_pembelian p JOIN supplier s ON (p.kode_pemasok = s.kode_supplier)