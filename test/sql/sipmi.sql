CREATE TABLE IF NOT EXISTS barang (
    kode_barang varchar(30) NOT NULL,
    id_kategori int(4) NOT NULL,
    nama_barang varchar(50) NOT NULL,
    harga double NOT NULL default 0,
    jumlah int(8) NOT NULL default 0,
    paket boolean NOT NULL default 0,
    PRIMARY KEY (kode_barang)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table barang
--

INSERT INTO barang (kode_barang, id_kategori, nama_barang, harga, jumlah, paket) VALUES
    ('PB01', 2, 'Handbody', 75000, 90, 0),
    ('PW01', 1, 'Paket Whitening', 189000, 56, 1),
    ('PW02', 1, 'Paket Acne', 189000, 56, 1),
    ('PW03', 1, 'Paket Antispot', 195000, 56, 1),
    ('PW04', 1, 'Serum', 139000, 41, 0),
    ('PW05', 1, 'Toner', 32000, 46, 0),
    ('PW06', 1, 'Cleansing Milk', 32000, 46, 0),
    ('PW07', 1, 'Enzyme Peeling', 139000, 41, 0);


-- --------------------------------------------------------

--
-- Table structure for table kategori_brg
--

CREATE TABLE IF NOT EXISTS kategori_brg (
    id_kategori int(4) NOT NULL AUTO_INCREMENT,
    kategori varchar(30) NOT NULL,
    PRIMARY KEY (id_kategori)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table kategori_brg
--

INSERT INTO kategori_brg (id_kategori, kategori) VALUES
    (1, 'perawatan wajah'),
    (2, 'perawatan tubuh');

-- --------------------------------------------------------

--
-- Table structure for table pelanggan
--

CREATE TABLE IF NOT EXISTS pelanggan (
    id_pelanggan varchar(10) NOT NULL,
    nama varchar(100) NOT NULL,
    jp varchar(15) NOT NULL,
    alamat text NOT NULL,
    notlp varchar(100) NOT NULL,
    PRIMARY KEY (id_pelanggan)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table pelanggan
--

INSERT INTO pelanggan (id_pelanggan, nama, jp, alamat, notlp) VALUES
    ('N003', 'kiki', 'NoN Agen', ' bandung', ' 0987876765545'),
    ('N001', 'Ulvia', 'NoN Agen', 'Gerlong Bandung', ' 085221101909'),
    ('N002', 'Febry', 'NoN Agen', 'dago, bandung', ' 087678987123'),
    ('P001', 'Meri', 'Agen', 'surabaya', '087987676565'),
    ('P002', 'Mila', 'NoN Agen', ' sekeloa, bandung', ' 089987867542'),
    ('P003', 'Ica', 'Agen', ' Bandung', '089878765453'),
    ('P004', 'Diana', 'Agen', ' sadang serang bandung', ' 081656543234');

-- --------------------------------------------------------

--
-- Table structure for table pembelian
--

CREATE TABLE IF NOT EXISTS pembelian (
    no_struk varchar(15) NOT NULL,
    tgl_pembelian date NOT NULL,
    kode_supplier int(11) NOT NULL,
    KEY no_struk (no_struk),
    KEY kode_supplier (kode_supplier)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table pembelian
--

INSERT INTO pembelian (no_struk, tgl_pembelian, kode_supplier) VALUES
    ('PB-20160601-2', '2016-06-01', 2),
    ('PB-20160624-4', '2016-06-24', 2),
    ('PB-20160625-3', '2016-06-25', 1),
    ('PB-20160625-4', '2016-06-25', 1),
    ('PB-20160625-5', '2016-06-25', 1),
    ('PB-20160625-6', '2016-06-25', 1);

--
-- Table structure for table detail_beli
--

CREATE TABLE IF NOT EXISTS detail_beli (
    no_struk varchar(15) NOT NULL,
    kode_barang varchar(5) NOT NULL,
    harga double NOT NULL default 0,
    jumlah int NOT NULL default 0,
    KEY kode_barang (kode_barang),
    KEY no_struk (no_struk)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table detail_beli
--

INSERT INTO detail_beli (no_struk, kode_barang, harga, jumlah) VALUES
    ('PB-20160625-3', 'PW01', 189000, 50),
    ('PB-20160625-4', 'PB01', 75000, 10),
    ('PB-20160625-5', 'PB01', 75000, 5),
    ('PB-20160625-6', 'PB01', 75000, 29);

-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS pesanan_pembelian (
    kode varchar(15) NOT NULL,
    tgl date NOT NULL,
    kode_pemasok int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO pesanan_pembelian (kode, tgl, kode_pemasok) VALUES
    ('PO-20160601-2', '2016-06-01', 2);


ALTER TABLE pesanan_pembelian
    ADD CONSTRAINT fk_pesan_beli_pemasok FOREIGN KEY (kode_pemasok)
    REFERENCES supplier (kode_supplier)
    ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS pesanan_pembelian_detail (
    id int primary key not null auto_increment,
    kode_pesanan varchar(15) NOT NULL,
    kode_barang varchar(5) NOT NULL,
    harga double NOT NULL default 0,
    jumlah int NOT NULL default 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO pesanan_pembelian_detail (kode_pesanan, kode_barang, harga, jumlah) VALUES
    ('PO-20160601-2', 'PW01', 189000, 50),
    ('PO-20160601-2', 'PB02', 75000, 10),
    ('PO-20160601-2', 'PB03', 75000, 5),
    ('PO-20160601-2', 'PB04', 75000, 29);

ALTER TABLE pesanan_pembelian_detail
    ADD CONSTRAINT fk_pesan_beli_detail_pesanan FOREIGN KEY (kode_pesanan)
    REFERENCES pesanan_pembelian(kode) ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_pesan_beli_detail_barang FOREIGN KEY (kode_barang)
    REFERENCES barang (kode_barang) ON UPDATE CASCADE ON DELETE CASCADE;

-- --------------------------------------------------------

--
-- Table structure for table penjualan
--

CREATE TABLE IF NOT EXISTS penjualan (
    no_faktur varchar(25) NOT NULL,
    tgl_penjualan date NOT NULL,
    id_pelanggan varchar(10) NOT NULL,
    PRIMARY KEY (no_faktur),
    KEY id_pelanggan (id_pelanggan)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table penjualan
--

INSERT INTO penjualan (no_faktur, tgl_penjualan, id_pelanggan) VALUES
    ('PJ-20160624-1', '2016-06-24', 'P001'),
    ('PJ-20160625-2', '2016-06-25', 'P001'),
    ('PJ-20160625-3', '2016-06-25', 'P001'),
    ('PJ-20160625-4', '2016-06-25', 'N001'),
    ('PJ-20160625-5', '2016-06-25', 'P001');

--
-- Table structure for table detail_jual
--

CREATE TABLE IF NOT EXISTS detail_jual (
    no_faktur varchar(25) NOT NULL,
    kode_barang varchar(5) NOT NULL,
    harga double NOT NULL default 0,
    diskon double NOT NULL default 0,
    jumlah int NOT NULL default 0,
    KEY kode_barang (kode_barang),
    KEY no_faktur (no_faktur)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table detail_jual
--

INSERT INTO detail_jual (no_faktur, kode_barang, harga, diskon, jumlah) VALUES
    ('PJ-20160625-2', 'PB01', 75000, 11250, 9),
    ('PJ-20160625-3', 'PW03', 195000, 29250, 1),
    ('PJ-20160625-4', 'PW01', 189000, 0, 2),
    ('PJ-20160625-4', 'PW04', 139000, 0, 1),
    ('PJ-20160625-5', 'PW02', 189000, 28350, 1);

--------------------------------------------------------------------------------------
--
-- Table structure for table supplier
--

CREATE TABLE IF NOT EXISTS supplier (
    kode_supplier int(5) NOT NULL AUTO_INCREMENT,
    nama_supplier varchar(30) NOT NULL,
    alamat text NOT NULL,
    notlp int(15) NOT NULL,
    PRIMARY KEY (kode_supplier)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table supplier
--

INSERT INTO supplier (kode_supplier, nama_supplier, alamat, notlp) VALUES
    (1, 'Zalfa Pusat', 'Bogor', 2288910),
    (2, 'nurul Hayyii', 'Distributor Surabaya', 229876778);

-- --------------------------------------------------------

--
-- Table structure for table user
--

CREATE TABLE IF NOT EXISTS user (
    id int(11) NOT NULL AUTO_INCREMENT,
    nama varchar(50) NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    jabatan varchar(20) NOT NULL,
    status varchar(3) NOT NULL DEFAULT 'OFF',
    PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table user
--

INSERT INTO user (id, nama, username, password, jabatan, status) VALUES
    (1, 'admin', 'admin', 'admin', 'Admin', 'ON'),
    (2, 'lani ', 'lani', 'gudang', 'gudang', 'OFF'),
    (3, 'wulan', 'wulan', 'kasir', 'kasir', 'OFF');

-- --------------------------------------------------------

--
-- Constraints for table detail_beli
--
ALTER TABLE detail_beli
  ADD CONSTRAINT fk_detail_beli_barang FOREIGN KEY (kode_barang) REFERENCES barang (kode_barang) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table detail_jual
--
ALTER TABLE detail_jual
  ADD CONSTRAINT fk_detail_jual_barang FOREIGN KEY (kode_barang) REFERENCES barang (kode_barang) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT fk_detail_jual_jual FOREIGN KEY (no_faktur) REFERENCES penjualan (no_faktur) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table pembelian
--
ALTER TABLE pembelian
  ADD CONSTRAINT fk_beli_supplier FOREIGN KEY (kode_supplier) REFERENCES supplier (kode_supplier) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table penjualan
--
ALTER TABLE penjualan
  ADD CONSTRAINT fk_beli_pelanggan FOREIGN KEY (id_pelanggan) REFERENCES pelanggan (id_pelanggan) ON DELETE CASCADE ON UPDATE CASCADE;
