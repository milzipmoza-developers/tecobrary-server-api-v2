--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `tecobrary`.`Users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tecobrary`.`Users`
(
    `id`            int(11)                               NOT NULL AUTO_INCREMENT,
    `githubId`      varchar(100)                          NOT NULL,
    `email`         varchar(100)                          NOT NULL,
    `name`          varchar(100)                                   DEFAULT NULL,
    `avatarUrl`     varchar(255)                          NOT NULL,
    `authorization` enum ('NONE','USER','MANAGER','KING') NOT NULL DEFAULT 'NONE',
    `createdAt`     datetime                              NOT NULL,
    `updatedAt`     datetime                              NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `github_id` (githubId)
) ENGINE = InnoDB
  AUTO_INCREMENT = 37
  DEFAULT CHARACTER SET = utf8mb4
  DEFAULT COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `tecobrary`.`Users` WRITE;
/*!40000 ALTER TABLE `tecobrary`.`Users`
    DISABLE KEYS */;
INSERT INTO `tecobrary`.`Users` (`id`, `githubId`, `email`, `name`, `avatarUrl`, `authorization`, `createdAt`,
                                 `updatedAt`)
VALUES (1, '12345678', 'erasede@tecobrary.com', '루피', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'KING',
        '2019-10-11 04:38:01', '2019-10-11 10:12:19'),
       (2, '91011121', 'qweqwew@tecobrary.com', '던던', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-11 07:00:57', '2019-10-11 07:54:51'),
       (3, '31415161', 'asdfsdf@tecobrary.com', '띠용', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'KING',
        '2019-10-11 08:05:04', '2019-10-11 08:05:09'),
       (4, '71819202', 'oepwqwf@tecobrary.com', '야옹', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-11 08:07:13', '2019-10-11 08:08:30'),
       (5, '12223242', 'kikikik@tecobrary.com', '메롱', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'KING',
        '2019-10-11 10:18:15', '2019-11-15 04:40:32'),
       (6, '52627282', 'yfgeftw@tecobrary.com', '두둥', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-11 10:19:47', '2019-10-11 11:53:33'),
       (7, '93031323', 'ewwqaaf@tecobrary.com', '', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-11 12:41:48', '2019-10-12 04:53:34'),
       (8, '33435363', '12331aa@tecobrary.com', '깡깡', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-14 04:40:01', '2019-10-14 08:14:50'),
       (9, '73839404', 'dumdumd@tecobrary.com', '', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-14 09:21:34', '2019-10-15 00:08:23'),
       (10, '14243444', 'zzzzzzz@tecobrary.com', '지건', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-15 00:46:58', '2019-10-15 04:08:50'),
       (11, '54647484', 'masw1we@tecobrary.com', '짝짝', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-15 00:53:01', '2019-10-15 04:08:54'),
       (12, '95051525', '12easdf@tecobrary.com', '캬악', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-15 09:15:37', '2019-10-15 09:17:29'),
       (13, '35455565', '__tting@tecobrary.com', '어흥', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-15 23:49:53', '2019-10-15 23:55:57'),
       (14, '75859606', 'uyea121@tecobrary.com', '꼮꼬', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-16 05:21:11', '2019-10-16 09:46:54'),
       (15, '16263646', 'baedale@tecobrary.com', '꽤액', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-17 01:59:38', '2019-10-17 02:09:14'),
       (16, '66676869', 'jjujake@tecobrary.com', '흐엉', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-18 04:38:44', '2019-11-10 01:18:19'),
       (17, '70717273', 'dafadsf@tecobrary.com', '따옹', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-18 06:38:42', '2019-10-18 06:44:46'),
       (18, '74757677', '123adfe@tecobrary.com', '애용', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-18 12:42:52', '2019-10-19 07:43:03'),
       (19, '78798081', 'kimchi2@tecobrary.com', '떼껄', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-20 00:00:00', '2019-10-27 05:25:26'),
       (21, '82838485', 'jjavkew@tecobrary.com', '엘사', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-28 04:55:27', '2019-10-28 04:56:12'),
       (22, '86878889', '9981mul@tecobrary.com', '안나', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-28 05:02:57', '2019-10-28 05:47:18'),
       (23, '90919293', 'superma@tecobrary.com', '노바', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-28 05:30:34', '2019-10-28 05:47:16'),
       (24, '94959697', 'rketism@tecobrary.com', '토니', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-28 05:56:17', '2019-10-28 05:59:31'),
       (28, '98991001', 'yfavori@tecobrary.com', '타노', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-28 06:59:32', '2019-10-28 07:45:35'),
       (29, '01102103', 'tespace@tecobrary.com', '상디', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-10-28 08:46:12', '2019-11-05 07:00:47'),
       (30, '10410510', 'inthewo@tecobrary.com', '조로', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'KING',
        '2019-11-08 06:20:40', '2019-11-26 03:33:17'),
       (31, '61071081', 'rldthis@tecobrary.com', '나미', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-11-14 02:10:04', '2019-11-14 05:28:50'),
       (32, '09110111', 'emailsa@tecobrary.com', '', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-11-14 22:34:51', '2019-11-15 01:46:49'),
       (33, '11211311', 'redummy@tecobrary.com', '브룩', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'USER',
        '2019-11-15 03:05:39', '2019-11-15 04:05:07'),
       (34, '41151161', 'notreal@tecobrary.com', '징베', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'KING',
        '2019-11-26 03:32:36', '2019-11-26 03:52:55'),
       (35, '17118119', 'ifwhous@tecobrary.com', '쵸파', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'KING',
        '2019-11-26 03:37:10', '2019-11-26 03:42:42'),
       (36, '12012112', 'ethissh@tecobrary.com', '람각', 'https://avatars3.githubusercontent.com/u/32266963?v=4', 'KING',
        '2019-11-26 03:38:44', '2019-11-26 03:38:53');
/*!40000 ALTER TABLE `tecobrary`.`Users`
    ENABLE KEYS */;
UNLOCK TABLES;