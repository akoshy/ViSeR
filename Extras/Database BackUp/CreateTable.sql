-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.57-community - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             6.0.0.4034
-- Date/time:                    2012-03-12 12:16:33
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for reranking
CREATE DATABASE IF NOT EXISTS `reranking` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `reranking`;


-- Dumping structure for table reranking.video
CREATE TABLE IF NOT EXISTS `video` (
  `id` varchar(50) NOT NULL,
  `seconds` int(11) NOT NULL,
  `viewCount` int(11) NOT NULL,
  `average` double NOT NULL,
  `videoName` varchar(50) NOT NULL,
  `url` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
