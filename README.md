# Создать ресторан
curl --location --request POST 'http://localhost:8080/api/v1/admin/restaurant' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImZ1bGxOYW1lIjoiYWRtaW4iLCJsb2dpbiI6ImFkbWluIiwiZXhwIjoxNjM3NjA5ODY4LCJ1dWlkIjoiODVlYjI2ZGQtMzFkOS00NTdhLWIxMTYtYzc4ZmU1MmFiZTM3IiwiaWF0IjoxNjM3NTIzNDY4fQ.a00GxSf1eQjY3VtPYKeauTr50TZArzT_8uYA2WA9St7Eiu_u7S1TrW3IC1D5MvhGCI2TUMW_D9Ut8qf2ZozP3g' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=490FF3BFEB7C301B5C948C8ED070DBF0' \
--data-raw '{
"address": "Адрес 2",
"name": "Ресторан 2"
}'
# Создать меню
curl --location --request POST 'http://localhost:8080/api/v1/admin/menu' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImZ1bGxOYW1lIjoiYWRtaW4iLCJsb2dpbiI6ImFkbWluIiwiZXhwIjoxNjM3NjA5ODY4LCJ1dWlkIjoiODVlYjI2ZGQtMzFkOS00NTdhLWIxMTYtYzc4ZmU1MmFiZTM3IiwiaWF0IjoxNjM3NTIzNDY4fQ.a00GxSf1eQjY3VtPYKeauTr50TZArzT_8uYA2WA9St7Eiu_u7S1TrW3IC1D5MvhGCI2TUMW_D9Ut8qf2ZozP3g' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=490FF3BFEB7C301B5C948C8ED070DBF0' \
--data-raw '{
"dishes": [
{
"cost": 1,
"name": "dish 1"
},
{
"cost": 2,
"name": "dish 2"
},
{
"cost": 3,
"name": "dish 3"
}
],
"name": "string",
"restaurantId": "4d8ac8e5-d306-477e-9827-d73ee9cfdc20"
}'
# login
curl --location --request POST 'http://localhost:8080/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=490FF3BFEB7C301B5C948C8ED070DBF0' \
--data-raw '{
"login": "",
"password": "",
"fullName": "f"
}'
# Документация
curl --location --request GET 'http://localhost:8080/v2/api-docs'

# Голосовать
curl --location --request POST 'http://localhost:8080/api/v1/user/vote' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb2dpbiIsInJvbGUiOiJVU0VSIiwiZnVsbE5hbWUiOiJmdWxsTmFtZSIsImxvZ2luIjoibG9naW4iLCJleHAiOjE2Mzc2MTE3MjQsInV1aWQiOiI4NWViMjZkZC0zMWQ5LTQ1N2EtYjExNi1jNzhmZTUyYWJlMzYiLCJpYXQiOjE2Mzc1MjUzMjR9.6pzBNDihBw4RGrrORNYAmjI8c6f_tCwQKwP_favDD0ea-2KvX-04eqmebQPyySRPttz3GI_qTLN-9Xug8z7o2g' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=490FF3BFEB7C301B5C948C8ED070DBF0' \
--data-raw '{
"menuId": "0d1b007a-e626-412f-8ea2-9ad87ef3b59f",
"restaurantId": "4d8ac8e5-d306-477e-9827-d73ee9cfdc20",
"vote": true
}'

# Топ по голосам рестораны
curl --location --request POST 'http://localhost:8080/api/v1/user/vote' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb2dpbiIsInJvbGUiOiJVU0VSIiwiZnVsbE5hbWUiOiJmdWxsTmFtZSIsImxvZ2luIjoibG9naW4iLCJleHAiOjE2Mzc2MTE3MjQsInV1aWQiOiI4NWViMjZkZC0zMWQ5LTQ1N2EtYjExNi1jNzhmZTUyYWJlMzYiLCJpYXQiOjE2Mzc1MjUzMjR9.6pzBNDihBw4RGrrORNYAmjI8c6f_tCwQKwP_favDD0ea-2KvX-04eqmebQPyySRPttz3GI_qTLN-9Xug8z7o2g' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=490FF3BFEB7C301B5C948C8ED070DBF0' \
--data-raw '{
"menuId": "0d1b007a-e626-412f-8ea2-9ad87ef3b59f",
"restaurantId": "4d8ac8e5-d306-477e-9827-d73ee9cfdc20",
"vote": true
}'

# Список всех ресторанов
curl --location --request GET 'http://localhost:8080/api/v1/user/restaurants' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb2dpbiIsInJvbGUiOiJVU0VSIiwiZnVsbE5hbWUiOiJmdWxsTmFtZSIsImxvZ2luIjoibG9naW4iLCJleHAiOjE2Mzc2MTE3MjQsInV1aWQiOiI4NWViMjZkZC0zMWQ5LTQ1N2EtYjExNi1jNzhmZTUyYWJlMzYiLCJpYXQiOjE2Mzc1MjUzMjR9.6pzBNDihBw4RGrrORNYAmjI8c6f_tCwQKwP_favDD0ea-2KvX-04eqmebQPyySRPttz3GI_qTLN-9Xug8z7o2g' \
--header 'Cookie: JSESSIONID=490FF3BFEB7C301B5C948C8ED070DBF0'