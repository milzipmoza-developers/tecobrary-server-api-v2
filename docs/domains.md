# 테코브러리 전체 도메인 정리

## User Domain

### Data Field

1. UserNumber (Id)
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

1. LibraryBookNumber (Id)
2. LibraryBookCoverUrl
3. LibraryBookInfo
    - title
    - author
    - publisher
    - isbn
    - desc

## WishBook Domain

### Data Field

1. WishBookNumber (Id)
2. WishBookCoverUrl
3. WishBookInfo
    - title
    - author
    - publisher
    - isbn
    - desc
4. WishBookRequestUser
    - user_id
5. CreatedTime
6. ModifiedTime
7. DeletedTime

### Method

1. Soft Delete

## SerialNumber Domain

### Data Field

1. SerialNumber (Id)
2. LibraryBookNumber
    - book_id
3. SerialNumberRentStatus

## RentHistory

### Data Field

1. RentHistoryNumber (Id)
2. RentSerialNumber
    - serial_id
3. RentUserNumber
    - user_id
4. CreatedTime
5. ModifiedTime
6. DeletedTime

### Method

1. Soft Delete
