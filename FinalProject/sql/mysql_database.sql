-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Час створення: Трв 04 2020 р., 19:45
-- Версія сервера: 10.4.11-MariaDB
-- Версія PHP: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `fp_test`
--

-- --------------------------------------------------------

--
-- Структура таблиці `card_status`
--

CREATE TABLE `card_status` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `card_status`
--

INSERT INTO `card_status` (`id`, `name`) VALUES
(0, 'opened'),
(1, 'closed');

-- --------------------------------------------------------

--
-- Структура таблиці `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `name_ru` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `categories`
--

INSERT INTO `categories` (`id`, `name`, `name_ru`) VALUES
(0, 'pediatrician', 'педиатр'),
(1, 'traumatologist', 'травматолог'),
(2, 'surger', 'хирург'),
(4, 'therapist', 'терапевт');

-- --------------------------------------------------------

--
-- Структура таблиці `diagnosis`
--

CREATE TABLE `diagnosis` (
  `id` int(11) NOT NULL,
  `text` varchar(50) NOT NULL,
  `card_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `diagnosis`
--

INSERT INTO `diagnosis` (`id`, `text`, `card_id`, `status_id`) VALUES
(19, 'простуда', 15, 1),
(20, 'бронхит', 15, 1),
(21, 'ушиб ', 18, 1),
(22, 'Острый бронхит', 15, 0),
(23, 'Ушиб', 18, 0);

-- --------------------------------------------------------

--
-- Структура таблиці `diagnosis_status`
--

CREATE TABLE `diagnosis_status` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `diagnosis_status`
--

INSERT INTO `diagnosis_status` (`id`, `name`) VALUES
(0, 'final'),
(1, 'interim');

-- --------------------------------------------------------

--
-- Структура таблиці `doctors`
--

CREATE TABLE `doctors` (
  `id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `patients_count` int(11) NOT NULL DEFAULT 0,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `doctors`
--

INSERT INTO `doctors` (`id`, `position_id`, `category_id`, `patients_count`, `user_id`) VALUES
(27, 0, 0, 1, 49),
(31, 0, 4, 1, 53),
(32, 1, NULL, 0, 54),
(33, 1, NULL, 0, 55),
(34, 1, NULL, 0, 56),
(35, 0, 2, 1, 57),
(36, 0, 4, 1, 58),
(37, 0, 1, 1, 69);

-- --------------------------------------------------------

--
-- Структура таблиці `hospital_cards`
--

CREATE TABLE `hospital_cards` (
  `id` int(11) NOT NULL,
  `patient_Id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `hospital_cards`
--

INSERT INTO `hospital_cards` (`id`, `patient_Id`, `status_id`) VALUES
(15, 17, 0),
(16, 18, 0),
(18, 20, 0),
(19, 21, 0),
(20, 22, 0),
(21, 23, 0),
(23, 28, 0);

-- --------------------------------------------------------

--
-- Структура таблиці `patients`
--

CREATE TABLE `patients` (
  `id` int(11) NOT NULL,
  `birthday` date NOT NULL,
  `phonenumber` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL DEFAULT 0,
  `date_registration` date NOT NULL,
  `date_discharge` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `patients`
--

INSERT INTO `patients` (`id`, `birthday`, `phonenumber`, `user_id`, `status_id`, `date_registration`, `date_discharge`) VALUES
(17, '2000-08-09', '(093)8732459', 63, 2, '2020-04-21', '2020-05-04'),
(18, '2000-02-03', '(067)2122451', 64, 1, '2020-04-24', '2000-01-01'),
(20, '2000-10-07', '(090)9485221', 66, 2, '2020-04-22', '2020-05-04'),
(21, '2000-08-06', '(099)7634100', 67, 1, '2020-04-28', '2000-01-01'),
(22, '2000-12-01', '(093)2222332', 68, 1, '2020-04-30', '2000-01-01'),
(23, '2000-04-23', '(050)3489123', 70, 1, '2020-05-01', '2000-01-01'),
(28, '2000-07-09', '(093)0453278', 79, 1, '2020-05-04', '2000-01-01');

-- --------------------------------------------------------

--
-- Структура таблиці `patients_list`
--

CREATE TABLE `patients_list` (
  `doctor_id` int(10) NOT NULL,
  `patient_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `patients_list`
--

INSERT INTO `patients_list` (`doctor_id`, `patient_id`) VALUES
(27, 21),
(31, 18),
(35, 22),
(36, 17),
(36, 20),
(36, 23),
(37, 28);

-- --------------------------------------------------------

--
-- Структура таблиці `patient_status`
--

CREATE TABLE `patient_status` (
  `id` int(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `name_ru` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `patient_status`
--

INSERT INTO `patient_status` (`id`, `name`, `name_ru`) VALUES
(0, 'sick', 'больной'),
(1, 'treatment', 'идет лечение'),
(2, 'cured', 'вылечен');

-- --------------------------------------------------------

--
-- Структура таблиці `position`
--

CREATE TABLE `position` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `name_ru` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `position`
--

INSERT INTO `position` (`id`, `name`, `name_ru`) VALUES
(0, 'doctor', 'врач'),
(1, 'nurse', 'медсестра');

-- --------------------------------------------------------

--
-- Структура таблиці `prescriptions`
--

CREATE TABLE `prescriptions` (
  `id` int(11) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `diagnosis_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL DEFAULT 1,
  `type_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `prescriptions`
--

INSERT INTO `prescriptions` (`id`, `comment`, `diagnosis_id`, `status_id`, `type_id`) VALUES
(17, 'горячий чай', 19, 0, 1),
(18, 'Мукалтин', 20, 0, 0),
(19, 'наложить эластичный бинт', 21, 0, 1);

-- --------------------------------------------------------

--
-- Структура таблиці `prescription_status`
--

CREATE TABLE `prescription_status` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `name_ru` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `prescription_status`
--

INSERT INTO `prescription_status` (`id`, `name`, `name_ru`) VALUES
(0, 'done', 'выполнено'),
(1, 'undone', 'не выполнено');

-- --------------------------------------------------------

--
-- Структура таблиці `roles`
--

CREATE TABLE `roles` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `name_ru` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `roles`
--

INSERT INTO `roles` (`id`, `name`, `name_ru`) VALUES
(0, 'admin', 'админ'),
(1, 'patient', 'пациент'),
(2, 'doctor', 'врач');

-- --------------------------------------------------------

--
-- Структура таблиці `type`
--

CREATE TABLE `type` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `name_ru` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `type`
--

INSERT INTO `type` (`id`, `name`, `name_ru`) VALUES
(0, 'medicine', 'таблетки'),
(1, 'procedure', 'процедура'),
(2, 'operation', 'операция');

-- --------------------------------------------------------

--
-- Структура таблиці `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `login` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `role_id` int(11) NOT NULL,
  `locale_name` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп даних таблиці `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `first_name`, `last_name`, `role_id`, `locale_name`) VALUES
(0, 'admin', 'admin', 'Kostya', 'Belov', 0, 'ru'),
(7, 'testuser', 'testpass', 'Ruslan', 'Belov', 1, ''),
(49, 'doctor1', 'password', 'Матвей', 'Калужин', 2, 'ru'),
(53, 'doctor5', 'password', 'Сергей', 'Садовой', 2, 'ru'),
(54, 'nurse1', 'password', 'Марина', 'Лагода', 2, 'ru'),
(55, 'nurse2', 'password', 'Света', 'Луговая', 2, 'ru'),
(56, 'nurse3', 'password', 'Василиса', 'Дукалис', 2, 'ru'),
(57, 'doctor3', 'password', 'Владимир', 'Пушкин', 2, 'ru'),
(58, 'doctor4', 'password', 'Карл', 'Енгельс', 2, 'ru'),
(63, 'patient1', 'password', 'Татьяна', 'Гренкина', 1, 'ru'),
(64, 'patient2', 'password', 'Руслан', 'Минигар', 1, 'ru'),
(66, 'patient3', 'password', 'Андрей', 'Лаковлев', 1, 'ru'),
(67, 'patient4', 'password', 'Денис', 'Прищепин', 1, 'ru'),
(68, 'patient5', 'password', 'Давид', 'Кравец', 1, 'ru'),
(69, 'doctor2', 'password', 'Иван', 'Калач', 2, 'ru'),
(70, 'patient6', 'password', 'Тарас', 'Шевчук', 1, 'ru'),
(79, 'patient7', 'password', 'Евгений ', 'Карасюк', 1, 'ru');

--
-- Індекси збережених таблиць
--

--
-- Індекси таблиці `card_status`
--
ALTER TABLE `card_status`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `card_id` (`card_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Індекси таблиці `diagnosis_status`
--
ALTER TABLE `diagnosis_status`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`id`),
  ADD KEY `position_id` (`position_id`,`category_id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `doctors_ibfk_3` (`user_id`);

--
-- Індекси таблиці `hospital_cards`
--
ALTER TABLE `hospital_cards`
  ADD PRIMARY KEY (`id`),
  ADD KEY `patient_Id` (`patient_Id`),
  ADD KEY `status_id` (`status_id`);

--
-- Індекси таблиці `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `patients_ibfk_2` (`user_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Індекси таблиці `patients_list`
--
ALTER TABLE `patients_list`
  ADD KEY `user_id` (`doctor_id`,`patient_id`),
  ADD KEY `patients_list_ibfk_2` (`patient_id`);

--
-- Індекси таблиці `patient_status`
--
ALTER TABLE `patient_status`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `status_id` (`status_id`),
  ADD KEY `type_id` (`type_id`),
  ADD KEY `diagnosis_id` (`diagnosis_id`);

--
-- Індекси таблиці `prescription_status`
--
ALTER TABLE `prescription_status`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`id`);

--
-- Індекси таблиці `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `users_ibfk_1` (`role_id`);

--
-- AUTO_INCREMENT для збережених таблиць
--

--
-- AUTO_INCREMENT для таблиці `card_status`
--
ALTER TABLE `card_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблиці `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблиці `diagnosis`
--
ALTER TABLE `diagnosis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT для таблиці `diagnosis_status`
--
ALTER TABLE `diagnosis_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблиці `doctors`
--
ALTER TABLE `doctors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT для таблиці `hospital_cards`
--
ALTER TABLE `hospital_cards`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT для таблиці `patients`
--
ALTER TABLE `patients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT для таблиці `patient_status`
--
ALTER TABLE `patient_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблиці `position`
--
ALTER TABLE `position`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблиці `prescriptions`
--
ALTER TABLE `prescriptions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT для таблиці `prescription_status`
--
ALTER TABLE `prescription_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблиці `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблиці `type`
--
ALTER TABLE `type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблиці `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD CONSTRAINT `diagnosis_ibfk_1` FOREIGN KEY (`card_id`) REFERENCES `hospital_cards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `diagnosis_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `diagnosis_status` (`id`);

--
-- Обмеження зовнішнього ключа таблиці `doctors`
--
ALTER TABLE `doctors`
  ADD CONSTRAINT `doctors_ibfk_1` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctors_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctors_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `hospital_cards`
--
ALTER TABLE `hospital_cards`
  ADD CONSTRAINT `hospital_cards_ibfk_2` FOREIGN KEY (`patient_Id`) REFERENCES `patients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hospital_cards_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `card_status` (`id`);

--
-- Обмеження зовнішнього ключа таблиці `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `patients_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `patient_status` (`id`);

--
-- Обмеження зовнішнього ключа таблиці `patients_list`
--
ALTER TABLE `patients_list`
  ADD CONSTRAINT `patients_list_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_list_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD CONSTRAINT `prescriptions_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `prescription_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `prescriptions_ibfk_3` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `prescriptions_ibfk_4` FOREIGN KEY (`diagnosis_id`) REFERENCES `diagnosis` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
