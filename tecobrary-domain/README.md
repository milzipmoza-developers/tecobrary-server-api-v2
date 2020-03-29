## 테코브러리 도메인

### User 도메인

```sql
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `github_id` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  `email` varchar(100) NOT NULL,
  `avatar_url` varchar(100) NOT NULL,
  `authorization` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_github_id` (`github_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### LibraryBook 도메인

```sql
CREATE TABLE `library_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author` varchar(50) NOT NULL,
  `publisher` varchar(20) NOT NULL,
  `isbn` varchar(30) NOT NULL,
  `image` varchar(150) NOT NULL,
  `description` varchar(500) DEFAULT '내용 없음',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_library_book_isbn` (`isbn`),
  INDEX `idx_library_book_title` (`title`),
  INDEX `idx_library_book_isbn` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### Serial 도메인

```sql
CREATE TABLE `serial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serial_number` bigint(20) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `library_book_id` bigint(20) DEFAULT NULL COMMENT '시리얼 등록 도서',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_serial_serial_number` (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### WishBook 도메인

```sql
CREATE TABLE `wish_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author` varchar(50) NOT NULL,
  `publisher` varchar(20) NOT NULL,
  `isbn` varchar(30) NOT NULL,
  `image` varchar(150) NOT NULL,
  `description` varchar(500) DEFAULT '내용 없음',
  `wish_book_request_user_id` bigint(20) DEFAULT NULL COMMENT '신청 유저',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL COMMENT '신청 처리 시간',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_wish_book_isbn` (`isbn`),
  INDEX `idx_wish_book_isbn` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### RentHistory 도메인

```sql
CREATE TABLE `rent_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rent_serial_id` bigint(20) DEFAULT NULL COMMENT '대여 시리얼 넘버',
  `rent_user_id` bigint(20) DEFAULT NULL COMMENT '대여 유저',
  `created_at` datetime NOT NULL COMMENT '대여 시간',
  `updated_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL COMMENT '반납 시간',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```