# examen-java-backend

Backend Spring Boot para login conectado a MySQL.

## Configuracion de BD

Este proyecto usa:

- host: `localhost`
- base de datos: `examen_login`
- usuario: `root`
- clave: `grupocoril`

Puedes crear la base con:

```sql
SOURCE database.sql;
```

o dejar que Spring la cree automaticamente por `createDatabaseIfNotExist=true`.

## Ejecutar backend

```bash
mvn spring-boot:run
```

El backend corre en `http://localhost:8080`.

## Endpoint de login

`POST /api/auth/login`

Body JSON:

```json
{
  "username": "admin",
  "password": "123456"
}
```

Usuario de prueba:

- usuario: `admin`
- clave: `123456`

Este usuario se crea automaticamente al iniciar el backend por primera vez.
