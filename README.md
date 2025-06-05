# 📦 Backend – Rally Fotográfico

Backend de la aplicación web Rally Fotográfico Cazadores de Momentos.

---

## 📌 Descripción

Este backend expone una API REST que permite:

- Registrar usuarios
- Subir imágenes
- Realizar votaciones
- Consultar rankings

---

## 🛠️ Tecnologías

- Java 21
- Spring Boot 3.4.4
- Maven
- MySQL 8
- Railway (para despliegue en la nube)

---

## 🚀 Instalación y despliegue local

### 1. Prerrequisitos

- Java 21
- Maven
- MySQL Server

### 2. Clonar repositorio

```bash
git clone https://github.com/Rivero88/backend-rally.git
cd backend-rally
```
### 3. Crear base de datos.

Crea una base de datos llamada rallyDB en MySQL Workbench. 
Spring Boot generará las tablas automáticamente con ddl-auto=update y junto a la dependencia del pom.xml:

```
<dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
	<scope>runtime</scope>
</dependency>
```

### 4.  Configura el archivo application.properties.

#### application.properties general
```properties
spring.application.name=backend-rally
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
server.port=${PORT:8080}
```
---
## 🖥️ Despliegue
1.Sube tu código a GitHub.

2.En https://railway.app, crea un nuevo proyecto.

3.Importa tu repositorio.

4.Añade variables de entorno a los valores de application.properties:
Para el despliegue:
#### application.properties local
```properties local
spring.datasource.url=jdbc:mysql://localhost:3306/rallyDB
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
ruta.fotografias=TU_RUTA
```
#### application.properties produccion
Con variables de entorno creadas para el despliegue:
```properties prod
spring.datasource.url=jdbc:mysql:URL
spring.datasource.username=USUARIO
spring.datasource.password=CONTRASEÑA
ruta.fotografias=RUTA
```

5.Railway te dará la URL del backend.

---
## 🔧 Uso

Los endpoints REST están accesibles en:
```
http://localhost:8080/...
```
O en la URL de Railway si se ha desplegado.
Desde el frontend, asegúrate de que el apiUrl apunte correctamente a esta dirección.

---
## 👥 Créditos

Aplicación desarrollada por Ester Rivero Goldero como parte del proyecto de fin de módulo o FCT.
