/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  dimmaryanto (Dimas Maryanto)
 * Created: Jun 26, 2016
 */


# TABEL SECURITY

CREATE TABLE IF NOT EXISTS m_user(
    id int not null primary key auto_increment,
    nama varchar(50) not null, 
    username varchar(50) not null unique,
    password varchar(50) not null, 
    jabatan varchar(20) not null,
    status boolean not null
)ENGINE = InnoDB;

INSERT INTO m_user (id, nama, username, password, jabatan, status) VALUES
(1, 'admin', 'admin', 'admin', 'Admin', 1),
(2, 'lani ', 'lani', 'gudang', 'gudang', 0),
(3, 'wulan', 'wulan', 'kasir', 'kasir', 0);

# TABLE KATEGORI

CREATE TABLE IF NOT EXISTS m_kategori(
    kode int(4) not null primary key auto_increment,
    nama varchar(30) not null
) engine = InnoDB;

INSERT INTO m_kategori (kode, nama) VALUES
(1, 'Perawatan wajah'), (2, 'Perawatan tubuh');

# TABEL BARANG

CREATE TABLE IF NOT EXISTS m_barang(
    kode varchar(50) not null primary key,
    kode_kategori int(4) not null,
    nama varchar(50) not null,
    harga double not null default 0,
    jumlah int not null default 0
) engine = InnoDB;

ALTER TABLE m_barang 
ADD CONSTRAINT fk_kategori_barang FOREIGN KEY (kode_kategori)
REFERENCES m_kategori (kode) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO m_barang (`kode`, `kode_kategori`, `nama`, `harga`, `jumlah`) VALUES
('PB01', 2, 'Handbody', 75000, 90),
('PW01', 1, 'Paket Whitening', 189000, 56),
('PW02', 1, 'Paket Acne', 189000, 56),
('PW03', 1, 'Paket Antispot', 195000, 56),
('PW04', 1, 'Serum', 139000, 41),
('PW05', 1, 'Toner', 32000, 46),
('PW06', 1, 'Cleansing Milk', 32000, 46),
('PW07', 1, 'Enzyme Peeling', 139000, 41);

# TABEL PEMASOK

CREATE TABLE IF NOT EXISTS m_pemasok(
    kode int not null primary key auto_increment,
    nama varchar(30) not null,
    alamat text,
    tlp varchar(25) not null
)ENGINE = InnoDD;

INSERT INTO m_pemasok (kode, nama, alamat, tlp) VALUES
(1, 'Zalfa Pusat', 'Bogor', '2288910'),
(2, 'nurul Hayyii', 'Distributor Surabaya', '229876778');

# TABEL PELANGGAN

CREATE TABLE IF NOT EXISTS m_pelanggan(
    kode varchar(10) not null primary key,
    nama varchar(30) not null,
    alamat text,
    tlp varchar(25) not null,
    agen boolean not null
) ENGINE = InnoDB;

INSERT INTO m_pelanggan (kode, nama, alamat, tlp, agen) VALUES
('N003', ' kiki', ' bandung', '0987876765545', 0),
('N001', ' Ulvia', 'Gerlong Bandung', '085221101909', 0),
('N002', ' Febry', 'dago, bandung', '087678987123', 0),
('P001', 'Meri', 'surabaya', '087987676565', 1),
('P002', 'Mila', ' sekeloa, bandung', '089987867542', 0),
('P003', 'Ica', ' Bandung', '089878765453', 1),
('P004', 'Diana', ' sadang serang bandung', '081656543234', 1);
