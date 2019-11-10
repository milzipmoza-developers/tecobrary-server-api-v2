# Controller

## 기본 규칙

* nginx proxy_pass 를 이용해 ``/api/v2`` 를 url prefix 로 붙인다.
* redirect 의 경우 url prefix 를 붙이지 않는다.

## ApiIndexController

* [클래스로 이동](../src/main/java/com/woowacourse/tecobrary/common/ui/ApiIndexController.java)

1. ``/`` : Api Index

## GithubOAuthController

* [클래스로 이동](../src/main/java/com/woowacourse/tecobrary/user/ui/GithubOAuthController.java)

1. ``/github/user/oauth`` => map to nginx ``/tecobrary/github/user``
    
    * redirect to github login page

## GithubUserApiController

* [클래스로 이동](../src/main/java/com/woowacourse/tecobrary/user/ui/GithubUserApiController.java)

1. ``/auth/user`` => map to nginx ``/api/v2/auth/user``

    * Tecobrary 유저 클라이언트 인증 기능
    
## JwtAuthenticationController

* [클래스로 이동](../src/main/java/com/woowacourse/tecobrary/user/ui/JwtAuthenticationController.java)

1. ``/token/auth`` => map to nginx ``/api/v2/token/auth``

    * 자동 로그인 시 jwt 를 이용해 로그인할 수 있도록 하는 기능
    
# Filter

## JwtValidateFilter

* [클래스로 이동](../src/main/java/com/woowacourse/tecobrary/common/infra/filter/JwtValidateFilter.java)

1. token 의 expired 여부를 판단하여 servlet 단에서 요청에 대한 인증을 처리

# Interceptor

* [클래스로 이동](../src/main/java/com/woowacourse/tecobrary/user/ui/JwtAuthenticationController.java)

## JwtAuthenticationInterceptor

1. 