# 테코브러리 서버 전체 요구사항

## Github 관련 기능

1. user 클라이언트 (Github User OAUTH 요청) 리다이렉트 url 을 받아오는 기능
2. 요청한 user 의 토큰을 이용해 user 의 데이터를 받아오는 기능
3. 받아온 user 의 데이터를 데이터베이스에서 조회하는 기능
4. 1, 2, 3 에 대응하는 manage 클라이언트도 동일한 기능이 필요

## User 관련 기능

### Create
1. Github 에서 받아온 정보로 User 를 등록하는 기능

### Update
1. User 의 Name 을 수정하는 기능
2. User 의 권한을 수정하는 기능

### Delete
1. User 정보를 삭제하는 기능

## NaverApi 관련 기능
1. 검색어로 도서 목록을 받아오는 기능

## LibraryBook 관련 기능

### Create
1. 도서 등록 기능

### Read
1. 도서 목록 조회 기능
2. 전체 등록된 도서 수를 받아오는 기능
3. 도서 한 권의 정보를 받아오는 기능
4. 키워드로 검색하는 기능

## WishBook 관련 기능

* 처리 날짜를 파악하기 위해 Soft Delete 가 필요하다.

### Create
1. 희망 도서 등록 기능

### Read
1. 희망 도서 전체 목록 조회 기능
2. 희망 도서 전체 데이터 수 조회 기능

### Delete
1. 희망 도서 삭제 기능

## BookSerial 관련 기능

### Create
1. 특정 LibraryBook 일련 번호 등록 기능

### Read
1. 특정 LibraryBook 의 전체 일련 번호 조회 기능

### Delete
1. 특정 LibraryBook 일련 번호 삭제 기능


