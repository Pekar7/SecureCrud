# SecureCrud

1) Auth
POST: https://securecrud-production.up.railway.app/api/v1/login   (вход/тело: username, password)

POST: https://securecrud-production.up.railway.app/api/v1/register (регестрация/тело: username/password)

2) Products
GET: https://securecrud-production.up.railway.app/api/v1/products (Список всех продуктов/BASIC AUTH username:password) USER

GET: https://securecrud-production.up.railway.app/api/v1/products/{id} (Продукт по id/BASIC AUTH username:password) USER 

POST: https://securecrud-production.up.railway.app/api/v1/products (Добавление продукта/ тело: id, title, description, price, img) ADMIN

DELETE: https://securecrud-production.up.railway.app/api/v1/products/{id} (Удаление продукта по id)

3) Users все методы не ограничены правами доступа
GET: https://securecrud-production.up.railway.app/api/v1/users (Список всех зарегистрированных пользователей)

GET: https://securecrud-production.up.railway.app/api/v1/users/{id} (Зарегистрированный пользователь с id)

POST: https://securecrud-production.up.railway.app/api/v1/users (Создать нового пользователя/ id; username; password; role)

DELETE:  https://securecrud-production.up.railway.app/api/v1/users/{id} (Удалить пользователя по id)
