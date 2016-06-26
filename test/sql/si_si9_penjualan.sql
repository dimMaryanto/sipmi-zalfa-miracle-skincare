-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2016 at 06:10 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `si_si9_penjualan`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `a`
--
CREATE TABLE IF NOT EXISTS `a` (
`kode_barang` varchar(30)
,`jumlah` decimal(33,0)
,`nama_barang` varchar(50)
,`harga` int(8)
);
-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE IF NOT EXISTS `barang` (
  `kode_barang` varchar(30) NOT NULL,
  `id_kategori` int(4) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `harga` int(8) NOT NULL,
  `jumlah` int(4) NOT NULL,
  PRIMARY KEY (`kode_barang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_barang`, `id_kategori`, `nama_barang`, `harga`, `jumlah`) VALUES
('PB01', 2, 'Handbody', 75000, 90),
('PW01', 1, 'Paket Whitening', 189000, 56),
('PW02', 1, 'Paket Acne', 189000, 56),
('PW03', 1, 'Paket Antispot', 195000, 56),
('PW04', 1, 'Serum', 139000, 41),
('PW05', 1, 'Toner', 32000, 46),
('PW06', 1, 'Cleansing Milk', 32000, 46),
('PW07', 1, 'Enzyme Peeling', 139000, 41);

-- --------------------------------------------------------

--
-- Stand-in structure for view `beli`
--
CREATE TABLE IF NOT EXISTS `beli` (
`kode_barang` varchar(30)
,`id_kategori` int(4)
,`nama_barang` varchar(50)
,`harga` int(8)
,`jum` decimal(33,0)
);
-- --------------------------------------------------------

--
-- Table structure for table `detail_beli`
--

CREATE TABLE IF NOT EXISTS `detail_beli` (
  `no_struk` varchar(15) NOT NULL,
  `kode_barang` varchar(5) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  KEY `kode_barang` (`kode_barang`),
  KEY `no_struk` (`no_struk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_beli`
--

INSERT INTO `detail_beli` (`no_struk`, `kode_barang`, `harga`, `jumlah`) VALUES
('PB-20160625-3', 'PW01', 189000, 50),
('PB-20160625-4', 'PB01', 75000, 10),
('PB-20160625-5', 'PB01', 75000, 5),
('PB-20160625-6', 'PB01', 75000, 29);

-- --------------------------------------------------------

--
-- Table structure for table `detail_jual`
--

CREATE TABLE IF NOT EXISTS `detail_jual` (
  `no_faktur` varchar(25) NOT NULL,
  `kode_barang` varchar(5) NOT NULL,
  `harga` varchar(30) NOT NULL,
  `diskon` varchar(30) NOT NULL,
  `jumlah` int(11) NOT NULL,
  KEY `kode_barang` (`kode_barang`),
  KEY `no_faktur` (`no_faktur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_jual`
--

INSERT INTO `detail_jual` (`no_faktur`, `kode_barang`, `harga`, `diskon`, `jumlah`) VALUES
('PO-20160625-2', 'PB01', '75000', '11250', 9),
('PO-20160625-3', 'PW03', '195000', '29250', 1),
('PO-20160625-4', 'PW01', '189000', '0', 2),
('PO-20160625-4', 'PW04', '139000', '0', 1),
('PO-20160625-5', 'PW02', '189000', '28350', 1);

-- --------------------------------------------------------

--
-- Table structure for table `kategori_brg`
--

CREATE TABLE IF NOT EXISTS `kategori_brg` (
  `id_kategori` int(4) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(30) NOT NULL,
  PRIMARY KEY (`id_kategori`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `kategori_brg`
--

INSERT INTO `kategori_brg` (`id_kategori`, `kategori`) VALUES
(1, 'perawatan wajah'),
(2, 'perawatan tubuh');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE IF NOT EXISTS `pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jp` varchar(15) NOT NULL,
  `alamat` text NOT NULL,
  `notlp` varchar(100) NOT NULL,
  PRIMARY KEY (`id_pelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `jp`, `alamat`, `notlp`) VALUES
(' N003', ' kiki', 'NoN Agen', ' bandung', ' 0987876765545'),
('N001', ' Ulvia', 'NoN Agen', 'Gerlong Bandung', ' 085221101909'),
('N002', ' Febry', 'NoN Agen', 'dago, bandung', ' 087678987123'),
('P001', 'Meri', 'Agen', 'surabaya', '087987676565'),
('P002', 'Mila', 'NoN Agen', ' sekeloa, bandung', ' 089987867542'),
('P003', 'Ica', 'Agen', ' Bandung', '089878765453'),
('P004', 'Diana', 'Agen', ' sadang serang bandung', ' 081656543234');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE IF NOT EXISTS `pembelian` (
  `no_struk` varchar(15) NOT NULL,
  `tgl_pembelian` date NOT NULL,
  `kode_supplier` int(11) NOT NULL,
  KEY `no_struk` (`no_struk`),
  KEY `kode_supplier` (`kode_supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`no_struk`, `tgl_pembelian`, `kode_supplier`) VALUES
('PB-20160601-2', '2016-06-01', 2),
('PB-20160624-4', '2016-06-24', 2),
('PB-20160625-3', '2016-06-25', 1),
('PB-20160625-4', '2016-06-25', 1),
('PB-20160625-5', '2016-06-25', 1),
('PB-20160625-6', '2016-06-25', 1);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE IF NOT EXISTS `penjualan` (
  `no_faktur` varchar(25) NOT NULL,
  `tgl_penjualan` date NOT NULL,
  `id_pelanggan` varchar(10) NOT NULL,
  PRIMARY KEY (`no_faktur`),
  KEY `id_pelanggan` (`id_pelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`no_faktur`, `tgl_penjualan`, `id_pelanggan`) VALUES
('PO-20160624-1', '2016-06-24', 'P001'),
('PO-20160625-2', '2016-06-25', 'P001'),
('PO-20160625-3', '2016-06-25', 'P001'),
('PO-20160625-4', '2016-06-25', 'N001'),
('PO-20160625-5', '2016-06-25', 'P001');

-- --------------------------------------------------------

--
-- Stand-in structure for view `q`
--
CREATE TABLE IF NOT EXISTS `q` (
`kode_barang` varchar(30)
,`id_kategori` int(4)
,`nama_barang` varchar(50)
,`harga` int(8)
,`jum` decimal(33,0)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `st`
--
CREATE TABLE IF NOT EXISTS `st` (
`kode_barang` varchar(30)
,`jumlah` decimal(32,0)
,`nama_barang` varchar(50)
,`harga` int(8)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `stok`
--
CREATE TABLE IF NOT EXISTS `stok` (
`kode_barang` varchar(30)
,`id_kategori` int(4)
,`nama_barang` varchar(50)
,`harga` int(8)
,`jumlah` decimal(34,0)
);
-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `kode_supplier` int(5) NOT NULL AUTO_INCREMENT,
  `nama_supplier` varchar(30) NOT NULL,
  `alamat` text NOT NULL,
  `notlp` int(15) NOT NULL,
  PRIMARY KEY (`kode_supplier`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`kode_supplier`, `nama_supplier`, `alamat`, `notlp`) VALUES
(1, 'Zalfa Pusat', 'Bogor', 2288910),
(2, 'nurul Hayyii', 'Distributor Surabaya', 229876778);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `jabatan` varchar(20) NOT NULL,
  `status` varchar(3) NOT NULL DEFAULT 'OFF',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nama`, `username`, `password`, `jabatan`, `status`) VALUES
(1, 'admin', 'admin', 'admin', 'Admin', 'ON'),
(2, 'lani ', 'lani', 'gudang', 'gudang', 'OFF'),
(3, 'wulan', 'wulan', 'kasir', 'kasir', 'OFF');

-- --------------------------------------------------------

--
-- Structure for view `a`
--
DROP TABLE IF EXISTS `a`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `a` AS select `s`.`kode_barang` AS `kode_barang`,coalesce((`s`.`jumlah` - sum(`j`.`jumlah`)),0) AS `jumlah`,`s`.`nama_barang` AS `nama_barang`,`s`.`harga` AS `harga` from (`st` `s` left join `detail_jual` `j` on((`s`.`kode_barang` = `j`.`kode_barang`))) group by `s`.`kode_barang`;

-- --------------------------------------------------------

--
-- Structure for view `beli`
--
DROP TABLE IF EXISTS `beli`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `beli` AS select `b`.`kode_barang` AS `kode_barang`,`b`.`id_kategori` AS `id_kategori`,`b`.`nama_barang` AS `nama_barang`,`b`.`harga` AS `harga`,(`b`.`jumlah` + sum(`db`.`jumlah`)) AS `jum` from (`barang` `b` join `detail_beli` `db` on((`b`.`kode_barang` = `db`.`kode_barang`))) group by `b`.`kode_barang`;

-- --------------------------------------------------------

--
-- Structure for view `q`
--
DROP TABLE IF EXISTS `q`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `q` AS select `b`.`kode_barang` AS `kode_barang`,`b`.`id_kategori` AS `id_kategori`,`b`.`nama_barang` AS `nama_barang`,`b`.`harga` AS `harga`,(`b`.`jumlah` + sum(`db`.`jumlah`)) AS `jum` from (`barang` `b` join `detail_beli` `db` on((`b`.`kode_barang` = `db`.`kode_barang`))) group by `b`.`kode_barang`;

-- --------------------------------------------------------

--
-- Structure for view `st`
--
DROP TABLE IF EXISTS `st`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `st` AS select `br`.`kode_barang` AS `kode_barang`,sum(`b`.`jumlah`) AS `jumlah`,`br`.`nama_barang` AS `nama_barang`,`br`.`harga` AS `harga` from (`barang` `br` left join `detail_beli` `b` on((`b`.`kode_barang` = `br`.`kode_barang`))) group by `b`.`kode_barang`;

-- --------------------------------------------------------

--
-- Structure for view `stok`
--
DROP TABLE IF EXISTS `stok`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `stok` AS select `q`.`kode_barang` AS `kode_barang`,`q`.`id_kategori` AS `id_kategori`,`q`.`nama_barang` AS `nama_barang`,`q`.`harga` AS `harga`,(`q`.`jum` - coalesce(sum(`dj`.`jumlah`),0)) AS `jumlah` from (`q` left join `detail_jual` `dj` on((`q`.`kode_barang` = `dj`.`kode_barang`))) group by `q`.`kode_barang`;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_beli`
--
ALTER TABLE `detail_beli`
  ADD CONSTRAINT `detail_beli_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode_barang`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_jual`
--
ALTER TABLE `detail_jual`
  ADD CONSTRAINT `detail_jual_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode_barang`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_jual_ibfk_2` FOREIGN KEY (`no_faktur`) REFERENCES `penjualan` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`kode_supplier`) REFERENCES `supplier` (`kode_supplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
