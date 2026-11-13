# Device Service

Минимальный CRUD-сервис на Spring Boot WebFlux, Kotlin Coroutines, Spring Data R2DBC и PostgreSQL.
Реализует управление устройствами и их местоположением.
Предполагается, что сервис работает через API gateway, поэтому userId, который приходит на вход, не проверяется.

При старте приложения Liquibase применит миграции к PostgreSQL через JDBC. CRUD-операции выполняются через R2DBC.

- API: http://localhost:8082/api/devices
- Swagger UI: http://localhost:8082/swagger-ui.html
- OpenAPI YAML: http://localhost:8082/v3/api-docs
