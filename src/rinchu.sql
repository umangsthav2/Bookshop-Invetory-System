-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 27, 2023 at 08:11 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rinchu`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_isbn` int(11) NOT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `book_author` varchar(100) DEFAULT NULL,
  `book_price` float DEFAULT NULL,
  `book_quantity` int(11) DEFAULT NULL,
  `data_editor` varchar(255) DEFAULT NULL,
  `data_edit_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_isbn`, `book_name`, `book_author`, `book_price`, `book_quantity`, `data_editor`, `data_edit_date`) VALUES
(1, 'In Search of List Time', 'Marcel Proust', 250, 40, 'IT Department', '2023-08-21'),
(2, 'Ulysses', 'James Joyce', 3500, 30, 'IT Department', '2023-08-21'),
(3, 'Don Quixote', 'Miguel de Cervantes', 900, 15, 'Yogesh Kumar Kanth', '2023-08-21'),
(4, 'One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 250, 70, 'Yogesh Kumar Kanth', '2023-08-21'),
(5, 'Moby Dick', 'Herman Melville', 320, 13, 'null', '2023-08-20'),
(6, 'War and Peace', 'Leo Tolstoy', 525, 15, 'null', '2023-08-20'),
(7, 'Hamlet', 'William Shakespeare', 1200, 25, 'Rinchen Moktan', '2023-08-20'),
(8, 'The Odyssey', 'Homer', 120, 10, 'null', '2023-08-20'),
(9, 'Madame Bovary', 'Gustave Flaubert', 625, 12, 'Umang Shrestha', '2023-08-20'),
(10, 'Doglapan', 'Ashneer Grover', 900, 50, 'Umang Shrestha', '2023-08-21'),
(11, 'Think Like a Monk', 'Jay Shetty', 680, 50, 'null', '2023-08-21'),
(12, 'Rich Dad Poor Dad', 'Robert Kiyosaki', 580, 20, 'IT Department', '2023-08-21'),
(13, 'Zero To One', 'Peter Thiel', 780, 25, 'null', '2023-08-20'),
(14, 'Everything is Fucked', 'Mark Manson', 680, 20, 'null', '2023-08-20'),
(15, 'Ugly Love', 'Colleen Hoover', 660, 25, 'null', '2023-08-20'),
(16, 'The 100 Dollar Startup', 'Chris Guillebeau', 780, 100, 'null', '2023-08-20'),
(17, 'Atomic Habbits', 'James Clear', 680, 50, 'null', '2023-08-20'),
(18, 'The Mountain is You', 'Brianna West', 670, 248, 'null', '2023-08-20'),
(19, 'Ego is The Enemy', 'Ryan Holiday', 760, 120, 'null', '2023-08-20'),
(20, 'Can\'t Hurt Me', 'David Goggins', 920, 25, 'null', '2023-08-20'),
(21, 'Rework', 'Jason Fried', 799, 12, 'null', '2023-08-20'),
(22, 'Think and Grow Rich', 'Napoleon Hill', 399, 15, 'null', '2023-08-20'),
(23, 'The Physcology of Money', 'Morgan Housel', 599, 15, 'Umang Shrestha', '2023-08-21'),
(24, 'Cashflow Quadrant', 'Robert Kiyosaki', 799, 255, 'null', '2023-08-20');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` varchar(50) NOT NULL,
  `order_name` varchar(255) DEFAULT NULL,
  `order_contact` varchar(20) DEFAULT NULL,
  `order_demand` varchar(255) DEFAULT NULL,
  `order_quantity` int(11) DEFAULT NULL,
  `order_price` float DEFAULT NULL,
  `data_editor` varchar(255) DEFAULT NULL,
  `data_edit_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `order_name`, `order_contact`, `order_demand`, `order_quantity`, `order_price`, `data_editor`, `data_edit_date`) VALUES
('OR0001', 'Roshan Singh', '9806523191', 'The Mountain is You', 2, 1340, 'IT Department', '2023-08-31'),
('OR002', 'Umanga Shrestha', '9864835437', 'Everything is Fucked', 5, 3400, 'IT Department', '2023-08-31'),
('OR003', 'asdf', 'asdf', 'Rework', 3, 2397, 'Rinchhen Moktan', '2023-09-27');

-- --------------------------------------------------------

--
-- Table structure for table `staffs`
--

CREATE TABLE `staffs` (
  `staff_id` varchar(50) NOT NULL,
  `staff_name` varchar(100) DEFAULT NULL,
  `staff_designation` varchar(255) DEFAULT NULL,
  `staff_salary` float DEFAULT NULL,
  `staff_key` varchar(255) DEFAULT NULL,
  `data_editor` varchar(255) DEFAULT NULL,
  `data_edit_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staffs`
--

INSERT INTO `staffs` (`staff_id`, `staff_name`, `staff_designation`, `staff_salary`, `staff_key`, `data_editor`, `data_edit_date`) VALUES
('BIS001', 'Rinchhen Moktan', 'Owner', 100000, 'admin', 'Umanga Shrestha', '2023-08-31'),
('BIS002', 'Umanga Shrestha', 'C.T.O', 350000, 'admin', 'IT Department', '2023-08-31'),
('BIS003', 'Bishwash Ramtel', 'CEO', 100000, 'admin', 'IT Department', '2023-08-31'),
('BIS004', 'Yogesh Kumar Kanth', 'CFO', 100000, 'admin', 'IT Department', '2023-08-31'),
('superadmin', 'IT Department', 'Whole Department', 500000, 'superadmin', 'Umanga Shrestha', '2023-08-31');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_isbn`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `staffs`
--
ALTER TABLE `staffs`
  ADD PRIMARY KEY (`staff_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
