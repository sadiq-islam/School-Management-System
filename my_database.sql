-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 27, 2017 at 04:40 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `my database`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `Date` varchar(100) NOT NULL,
  `Course` varchar(100) NOT NULL,
  `StudentID` varchar(100) NOT NULL,
  `Attendance` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`Date`, `Course`, `StudentID`, `Attendance`) VALUES
('01-01-18', 'English-9', '3-01', '1'),
('01-01-18', 'English-9', '3-02', '0'),
('01-01-18', 'English-8', '3-03', '1'),
('01-01-18', 'English-8', '3-05', '1'),
('01-01-18', 'English-8', '3-06', '1'),
('02-01-18', 'English-8', '3-03', '1'),
('21312', 'English-8', '3-03', '0'),
('fsdf', 'English-8', '3-03', '0'),
('fsdf', 'English-8', '3-05', '0'),
('fsdf', 'English-8', '3-06', '0'),
('e', 'English-8', '3-05', '0'),
('e', 'English-8', '3-06', '0'),
('e', 'English-9', '3-01', '0'),
('e', 'English-9', '3-02', '0'),
('e', 'Physics-9', '3-02', '0'),
('e', 'Physics-9', '3-01', '0'),
('12', 'English-8', '3-03', '0'),
('12', 'English-8', '3-05', '1'),
('12', 'English-8', '3-06', '0'),
('3', 'English-8', '3-03', '0'),
('3', 'English-8', '3-05', '0'),
('3', 'English-8', '3-06', '0'),
('5', 'English-8', '3-03', '0'),
('5', 'English-8', '3-05', '0'),
('5', 'English-8', '3-06', '0'),
('7', 'English-8', '3-03', '0'),
('7', 'English-8', '3-05', '0'),
('7', 'English-8', '3-06', '0'),
('8', 'English-8', '3-03', '0'),
('8', 'English-8', '3-05', '0'),
('8', 'English-8', '3-06', '0'),
('58', 'English-8', '3-03', '0'),
('58', 'English-8', '3-05', '0'),
('58', 'English-8', '3-06', '0'),
('58', 'English-9', '3-01', '0'),
('58', 'English-9', '3-02', '0'),
('25-12-2017', 'English-9', '3-01', '0'),
('25-12-2017', 'English-9', '3-02', '0'),
('25-12-2017', 'Physics-9', '3-01', '1'),
('25-12-2017', 'Physics-9', '3-02', '1'),
('25-12-2017', 'English-8', '3-03', '0'),
('25-12-2017', 'English-8', '3-06', '0'),
('05-12-2017', 'English-8', '3-03', '0'),
('25-12-2001', 'English-9', '3-01', '0'),
('25-12-2001', 'English-9', '3-02', '0'),
('13-12-2017', 'English-9', '3-01', '1'),
('07-12-2017', 'English-9', '3-01', '1'),
('01-12-2017', 'English-9', '3-01', '1'),
('10-12-2017', 'English-9', '3-01', '1');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `Course Name` varchar(100) NOT NULL,
  `Level` varchar(100) NOT NULL,
  `Notice` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`Course Name`, `Level`, `Notice`) VALUES
('Bangla-8', '8', ''),
('Bangla-9', '9', ''),
('Biology-9', '9', ''),
('Chemistry-9', '9', ''),
('English-8', '8', ''),
('English-9', '9', 'vsdgsdgsdgd'),
('General Science-8', '8', ''),
('ICT-9', '9', ''),
('Math-8', '8', ''),
('Math-9', '9', ''),
('Physics-9', '9', 'Abksckjsdjf'),
('Social Science-8', '8', '');

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `Course` varchar(100) NOT NULL,
  `StudentID` varchar(100) NOT NULL,
  `Grade` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`Course`, `StudentID`, `Grade`) VALUES
('English-9', '3-01', '4.00'),
('English-9', '3-02', '3.50'),
('Physics-9', '3-01', '3.75'),
('Physics-9', '3-02', '4.00'),
('English-8', '3-03', '3.50'),
('English-8', '3-05', '3.25');

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

CREATE TABLE `notice` (
  `Course` varchar(100) NOT NULL,
  `Topic` varchar(150) NOT NULL,
  `Notice` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notice`
--

INSERT INTO `notice` (`Course`, `Topic`, `Notice`) VALUES
('Physics-9', 'Extra Class!!', 'A extra class will be taken next thurseday at 2:00 pm!\nAll the students should be present or marks will be deducted!'),
('English-9', 'Welcome Students!', 'Hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `PID` varchar(150) NOT NULL,
  `Pname` varchar(150) NOT NULL,
  `Quantity` varchar(150) NOT NULL,
  `Price` varchar(100) NOT NULL,
  `Catagory` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PID`, `Pname`, `Quantity`, `Price`, `Catagory`) VALUES
('3', 'Books', '20', '2000', 'Education'),
('1', 'Rope', '5', '100', 'Tools'),
('4', 'Saw', '15', '1245', 'Tools'),
('7', 'Hacksaw', '56', '3456', 'Tools');

-- --------------------------------------------------------

--
-- Table structure for table `teachercourse`
--

CREATE TABLE `teachercourse` (
  `Teacher ID` varchar(100) NOT NULL,
  `Course` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachercourse`
--

INSERT INTO `teachercourse` (`Teacher ID`, `Course`) VALUES
('2-01', 'English-9'),
('2-02', 'Math'),
('2-01', 'Physics-9'),
('2-02', 'Biology-9'),
('2-01', 'English-8'),
('2-03', 'Bangla-8');

-- --------------------------------------------------------

--
-- Table structure for table `uploadedfile`
--

CREATE TABLE `uploadedfile` (
  `Course` varchar(100) NOT NULL,
  `FileName` varchar(150) NOT NULL,
  `FilePath` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uploadedfile`
--

INSERT INTO `uploadedfile` (`Course`, `FileName`, `FilePath`) VALUES
('English-9', 'SE_Lecture3C_Agile_SCRUM.ppt', 'D:\\SE_Lecture3C_Agile_SCRUM.ppt'),
('English-9', 'New Rich Text Document.rtf', 'D:\\New Rich Text Document.rtf'),
('Physics-9', '2.pdf', 'D:\\2.pdf'),
('Physics-9', 'students-128.png', 'C:\\UsersSadiqDesktopPROJECTSSchool Management SystemSCHOOL MANAGEMENT SYSTEMstudents-128.png');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Name` varchar(100) NOT NULL,
  `DOB` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `OrgID` varchar(100) NOT NULL,
  `StdLevel` varchar(100) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `Picture` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Name`, `DOB`, `Email`, `OrgID`, `StdLevel`, `Username`, `Password`, `Type`, `Picture`) VALUES
('Admin', '01-01-80', 'admin@gmail.com', '1-01', '', 'a', 'a', 'admin', 'D:Admin.png'),
('Abir Raihan', '08-08-78', 'raihan@gmail.com', '1-02', '', 'ar', 'ar', 'admin', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Admin.png'),
('Teacher', '09-09-89', 'teacher@gmail.com', '2-01', '', 't', 't', 'teacher', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Chief.png'),
('Rajib Hasan', '06-07-1992', 'rajib1@gmail.com', '2-02', '', 'rj', 'rj', 'teacher', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Chief.png'),
('Tahmina Begum', '09-09-89', 'tahmina@gmail.com', '2-03', '', 'tb', 'tb', 'teacher', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Chief.png'),
('Abid Islam', '06-01-88', 'abid@gmail.com', '2-04', '', 'ai', 'ai', 'teacher', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Chief.png'),
('Babul Sarkar', '12-05-88', 'babul@gmail.com', '2-05', '', 'bs', 'bs', 'teacher', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Chief.png'),
('Shamim', 'Hasan', 'sh@gmail.com', '2-06', '', 'sh', 'sh', 'teacher', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Chief.png'),
('Fahim', 'Hossain', 'fahim@gmail.com', '2-07', '', 'fh', 'fh', 'teacher', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Chief.png'),
('Student', '09-09-99', 'student@gmail.com', '3-01', '9', 's', 's', 'student', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Angel.png'),
('Rajib', '09-08-99', 'rajib@gmail.com', '3-02', '9', 'r', 'r', 'student', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Angel.png'),
('Subroto Ghosh', '12-07-98', 'subroto@gmail.com', '3-03', '8', 'sg', 'e', 'student', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Angel.png'),
('Samir Raihan', '06-07-99', 'samir@gmail.com', '3-05', '8', 'sa', 'sa', 'student', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Angel.png'),
('Reza Islam', '06-07-97', 'reza@gmail.com', '3-06', '8', 're', 're', 'student', 'C:\\Users\\Sadiq\\Desktop\\PROJECTS\\School Management System\\SCHOOL MANAGEMENT SYSTEM\\pictures\\user\\Angel.png'),
('Khalil', 'Ahmed', 'kh@gmail.com', '3-08', '9', 'kh', 'kh', 'student', 'C:UsersSadiqPicturesScreenshotsScreenshot (5).png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD UNIQUE KEY `Course Name` (`Course Name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD UNIQUE KEY `OrgID` (`OrgID`),
  ADD UNIQUE KEY `Password` (`Password`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Username` (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
