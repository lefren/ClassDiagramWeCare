-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2023 at 04:28 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectoop`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `hospital` varchar(255) NOT NULL,
  `patient` varchar(255) NOT NULL,
  `doctor` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`id`, `hospital`, `patient`, `doctor`, `status`) VALUES
(1, 'eka hospital', 'ichsan', 'ginting', 'success'),
(2, 'EMC', 'ichsan', 'wardana', 'success'),
(4, 'Eka Hospital', 'ichsan', 'jacky setiawan', 'success'),
(5, 'Eka Hospital', 'isan', 'abc', 'success');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `doctor_name` varchar(255) NOT NULL,
  `nomor_str` varchar(255) NOT NULL,
  `phone_num` varchar(255) NOT NULL,
  `hospital_place` varchar(255) NOT NULL,
  `pengalaman_kerja` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `doctor_name`, `nomor_str`, `phone_num`, `hospital_place`, `pengalaman_kerja`, `password`) VALUES
(17, 'jacky setiawan', '123', '198471', 'Eka Hospital', '23', '234'),
(18, 'Stefanie Angeline Sanjaya', '123887', '124441212441', 'EMC', '12 Years', '123');

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `id` int(11) NOT NULL,
  `hospital_name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `notelp_rs` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`id`, `hospital_name`, `address`, `notelp_rs`) VALUES
(3, 'Eka Hospital', 'Tangerang', '18234'),
(4, 'EMC', 'Sentul Selatan', '122345678');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `patient_name` varchar(255) NOT NULL,
  `doctor_name` varchar(255) NOT NULL,
  `hospital_name` varchar(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `age` int(150) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `nik` varchar(255) NOT NULL,
  `password` varchar(150) NOT NULL,
  `roles` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `age`, `phone`, `nik`, `password`, `roles`) VALUES
(1, 'ichsan', 18, '12345678', '0', 'abc', 'admin'),
(2, 'ichsan', 18, '123435465', '124354675', 'abc', ''),
(3, 'ichsan', 18, '123456789', '11459', 'abc', ''),
(4, 'icsa', 123, '124151', '151661', 'ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad', ''),
(5, 'ichsa', 14, '81274', '12567789', '52f9e379f257e230274f95104459b6637e47519dd4db0073a5e2673a69a4e93f', ''),
(6, 'ichsan', 18, '2345678', '123456789', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', ''),
(7, 'ichsan', 124, '18', '1234567891', '90dbca35cc682d5b4c2e53bd171e6cfba19168539c6f0799df31613fd5638a0f', ''),
(8, 'isan', 18, '125677654', '123456781', '1b5a6f8f2abac45a0e504b475f1a522a484f13653721ec08d56fcb38270df5bc', ''),
(9, 'ichsa', 125, '126389', '1234567', '4102b7c87b16b30cee51e3cd881034b5b2ccd6ef7af31fafbfe8091764146f72', ''),
(10, 'q', 1, '1', '1', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', ''),
(11, 'acong', 18, '1', '12', '3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278', ''),
(12, 'acong', 18, '82112488', '123456', '36bbe50ed96841d10443bcb670d6554f0a34b761be67ec9c4a8ad2c0c44ca42c', ''),
(13, 'ichsan', 18, '082112488179', '12345678912345678', '7d1a54127b222502f5b79b5fb0803061152a44f92b37e23c6527baf665d4da9a', ''),
(14, 'ichsan', 18, '082112388113', '18248661998233412', '3553e98a453e33242d8c135a24e7caa7f06f9c606c5f98a16313438233475870', ''),
(17, 'isan', 23, '01842851029', '12332145665478998798', '76fc402112460b090c06baf04e5519db34d23f206e11e2ddc555a2bba50e3afc', ''),
(18, 'sabal', 10, '081234567891', '1234567890098765123', 'e63dc95d7d683dc6ae919f4ee7ef2e5a317277179c3b557fe1f455a4ad69ba84', ''),
(19, 'stefanie angeline sanjaya', 129, '081511707080', '19130128490147141', 'df67349d2d1f830d7db134384101f8e4e12200fd02bfdb9a36d5c3ffedbae505', ''),
(23, 'Ginting', 1, 'anonymous', '47166155151', '123', 'doctor'),
(25, 'stef', 1, 'anonymous', '1231', '12', 'doctor'),
(26, 'stefanie angeline', 1, 'anonymous', '123', '123', 'doctor'),
(27, 'Stefanie Angeline Sanjaya', 1, 'anonymous', '123887', '123', 'doctor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
