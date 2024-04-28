-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2024-04-27 09:31:19
-- 伺服器版本： 10.4.28-MariaDB
-- PHP 版本： 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `sehs4701`
--

-- --------------------------------------------------------

--
-- 資料表結構 `applications`
--

CREATE TABLE `applications` (
  `id` int(11) NOT NULL,
  `scholarship_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `submit_date` date DEFAULT NULL,
  `status` enum('pending','approved','rejected','canceled') NOT NULL,
  `gpa` decimal(3,2) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `scholarships`
--

CREATE TABLE `scholarships` (
  `id` int(11) NOT NULL,
  `scholarship_name` varchar(50) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `announce_date` date DEFAULT NULL,
  `amount` int(11) DEFAULT 0,
  `quota` int(11) DEFAULT 0,
  `used_quota` int(11) DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `scholarships`
--

INSERT INTO `scholarships` (`id`, `scholarship_name`, `start_date`, `end_date`, `announce_date`, `amount`, `quota`, `used_quota`, `status`) VALUES
(1, 'The D. H. Chen Foundation Scholarship', '2023-12-30', '2024-06-28', '2024-07-13', 10000, 5, 0, 1),
(2, 'Tung and Ngai Foundation Scholarship update', '2024-01-29', '2024-07-28', '2024-08-12', 15000, 3, 0, 1);

-- --------------------------------------------------------

--
-- 資料表結構 `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `major` varchar(200) DEFAULT NULL,
  `role` enum('Student','Committee') NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `major`, `role`, `password`) VALUES
(1, 'Tom', 'Lee', 'tom.lee@admin.com', NULL, 'Committee', '$2a$10$9Pbpb.gTx7Vq2oKcS3h8nOZeSF3N9JOlW8VbBK2KBmXwRRRW3E89K'),
(2, 'siu', 'ming', 'ming@gmail.com', 'Computer Science', 'Student', '$2a$10$1RPmlaYPmHvBavIg1xhoueGHQbyKlFjCdEYNCcw4ofbBDZQxJz.Au');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `scholarship_id` (`scholarship_id`),
  ADD KEY `user_id` (`user_id`);

--
-- 資料表索引 `scholarships`
--
ALTER TABLE `scholarships`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `applications`
--
ALTER TABLE `applications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `scholarships`
--
ALTER TABLE `scholarships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `applications`
--
ALTER TABLE `applications`
  ADD CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `applications_ibfk_2` FOREIGN KEY (`scholarship_id`) REFERENCES `scholarships` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
