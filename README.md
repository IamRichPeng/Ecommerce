# Ecommerce
An online shopping website like Target. Using Spring Framework

### Use Postman for testing

-http://localhost:8080/api/v1/user/register
```
{
    "loginName": "18928618297",
    "password": "aaa"
}
```

-http://localhost:8080/api/v1/user/login
```
{
    "loginName": "18928618297",
    "password": "aaa"
}
```
-token = 1234567890123456789012

-http://localhost:8080/api/v1/user/logout

add token at headers

-http://localhost:8080/api/v1/user/info

(PUT: updated user info)
```
{
    "password": "aaa",
    "nickName": "Nibaba",
    "introduceSign": "Hello Motor"
}
```
add token at headers

-http://localhost:8080/api/v1/user/info

(GET)

-http://localhost:8080/api/v1/goods/detail/10004

need to add data through H2 database first
