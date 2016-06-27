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
    b.kode_barang, 
    b.nama_barang, 
    b.harga, 
    b.jumlah, 
    b.paket, 
    k.id_kategori, 
    k.kategori
from barang b JOIN kategori_brg k ON (b.id_kategori = k.id_kategori)
WHERE b.kode_barang = 'PB01'