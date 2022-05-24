Функционал

1. Регистрация сайта с выдачей login и password
2. Авторизация.
3. Регистрация URL с получением преобразованной ссылки
4. Переадресация. Выполняется без авторизации.
5. Вывод статистики по запросам ссылок.

Запуск с помощью docker.

1. Собрать сервис: mvn install
2. Собрать образ: docker build -t shortcut .
3. Запустить docker-compose: docker-compose up

Использование через REST API
1. Регистрация:
   curl --location --request POST 'http://localhost:8080/registration' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "site": "google.com"
   }'

2. Получение токена:
   curl --location --request POST 'http://localhost:8080/login' \
   --header 'Accept: application/json' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "login": "your_login",
   "password": "your_password"
   }'

3. Конвертация url:
   curl --location --request POST 'http://localhost:8080/convert' \
   --header 'Authorization: Bearer your_token\
   --header 'Content-Type: application/json' \
   --data-raw '{
   "url": "http://job4j.ru:8888/TrackStudio/staticframeset.html#253134"
   }'

4. Получение статистики:
   curl --location --request GET 'http://localhost:8080/statistic' \
   --header 'Authorization: Bearer your_token\