# Controller

## 기본 규칙

* nginx proxy_pass 를 이용해 ``/api/v2`` 를 url prefix 로 붙인다.
* redirect 의 경우 url prefix 를 붙이지 않는다.

## ApiIndexController

1. ``/`` : Api Index

## GithubOAuthController

1. ``/github/user/oauth`` => map to nginx ``/tecobrary/github/user``
    
    * redirect to github login page


## GithubUserApiController

1. ``/auth/user`` => map to nginx ``/api/v2/auth/user``

    * Tecobrary 유저 클라이언트 인증 기능