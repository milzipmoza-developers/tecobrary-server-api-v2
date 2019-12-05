# 테코브러리 전체 도메인 정리

## User Domain

### Data Field

1. UserNo (Id)
2. UserGithubInfo
    - github_id
    - name
    - email
    - avatar
3. UserAuthorization

### Method

1. UserGithubInfo.name 을 업데이트 하는 메서드
2. UserAuthorization.authorization 을 업데이트 하는 메서드

## LibraryBook Domain

### Data Field

1. LibraryBookNo (Id)
2. LibraryBookCoverUrl
3. LibraryBookInfo
    - title
    - author
    - publisher
    - isbn
    - desc

## WishBook Domain

### Data Field

1. WishBookNo (Id)
2. WishBookCoverUrl
3. WishBookInfo
    - title
    - author
    - publisher
    - isbn
    - desc
4. WishBookRequestUser
    - UserNo (user_id)
5. CreatedTime
6. ModifiedTime
7. DeletedTime

### Method

1. Soft Delete

## Serial Domain

### Data Field

1. SerialNo (Id)
2. SerialRentStatus
3. LibraryBook
    - LibraryBookNo (book_id)


## RentHistory

### Data Field

1. RentHistoryNo (Id)
2. RentSerial
    - SerialNo (serial_id)
3. RentUser
    - UserNo (user_id)
4. CreatedTime
5. ModifiedTime
6. DeletedTime

### Method

1. Soft Delete
