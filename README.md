# Proyecto Cazadores

## 1. Resumen
Proyecto Spring Boot (Maven) que gestiona **pilares**, **mensajes** y una operación de **inteligencia** (triangulación).  
Este README explica cómo ejecutar el proyecto, qué scripts SQL vienen con él y una explicación simple de cada endpoint disponible.

---

## 2. Cómo ejecutar el proyecto

Requisitos:
- Java 17+ (compatible con Spring Boot 4)
- Maven (o usar el wrapper `mvnw`)
- MySQL (o cambiar a otra base de datos en `application.properties`)

Pasos rápidos (desde la raíz del subproyecto `proyecto-cazadores`):

1. Crear la base de datos (opcional ya que `spring.jpa.hibernate.ddl-auto` está en `update`):

2. Editar `src/main/resources/application.properties` si necesitas cambiar usuario/contraseña o URL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cazadoresdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=admin
```

3. Ejecutar con Maven wrapper (Linux/macOS):
```bash
cd proyecto-cazadores
./mvnw spring-boot:run
```
Windows:
```cmd
cd proyecto-cazadores
mvnw.cmd spring-boot:run
```

O empacar y ejecutar el jar:
```bash
./mvnw clean package
java -jar target/proyecto-cazadores-0.0.1-SNAPSHOT.jar
```

El servidor arranca por defecto en `http://localhost:8080`.

---

## 3. Scripts SQL / Migraciones

El proyecto incluye un archivo `data.sql` que se ejecuta al inicio (ver `src/main/resources/data.sql`). Contenido (inicial) — inserta pilares de ejemplo:

```sql
INSERT INTO pilares (nombre, posx, posy, estado) VALUES
('Giyu Tomioka', -500, -200, 'Combatiendo'),
('Sanemi Shinazugawa', 100, -100, 'Combatiendo'),
('Mitsuri Kanroji', 500, 100, 'Combatiendo');
```

Notas:
- `spring.sql.init.mode=always` en `application.properties` hace que Spring ejecute `data.sql` al iniciar.
- El proyecto usa `spring.jpa.hibernate.ddl-auto=update`, por lo que Hibernate creará/actualizará tablas automáticamente.

Si necesitas un script para crear la tabla `pilares` (ejemplo), puedes usar:

```sql
CREATE TABLE IF NOT EXISTS pilares (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255),
  posx DOUBLE,
  posy DOUBLE,
  estado VARCHAR(100)
);
```

---

## 4. Endpoints

Base path: `http://localhost:8080`

### 1) Obtener un pilar (detalle)
- **URL:** `GET /api/pilares/{id}`
- **Descripción:** Devuelve el pilar identificado por `id`.
- **Ejemplo curl:**
```bash
curl -X GET "http://localhost:8080/api/pilares/1"
```
- **Respuesta:** JSON con los campos del pilar (id, nombre, posx, posy, estado, ...).

---

### 2) Actualizar posición de un pilar
- **URL:** `POST /api/pilares/actualizar-posicion`
- **Descripción:** Actualiza la posición (posX, posY) y estado de un pilar. Espera un body JSON con la información de actualización.
- **Body esperado (ejemplo):**
```json
{
  "pilarId": 1,
  "posX": 123.45,
  "posY": -67.89,
  "estado": "Combatiendo"
}
```
- **Ejemplo curl:**
```bash
curl -X POST "http://localhost:8080/api/pilares/actualizar-posicion"   -H "Content-Type: application/json"   -d '{"pilarId":1,"posX":123.45,"posY":-67.89,"estado":"Activo"}'
```
- **Respuesta:** Código 201 CREATED con un objeto que incluye `"mensaje"` y el `"pilar"` actualizado.

---

### 3) Reconstruir un mensaje
- **URL:** `PUT /api/mensajes/{id}/reconstruir`
- **Descripción:** Endpoint que invoca la lógica para "reconstruir" (o procesar) un mensaje identificado por `id`. La acción concreta está implementada en el servicio de mensajes.
- **Ejemplo curl:**
```bash
curl -X PUT "http://localhost:8080/api/mensajes/5/reconstruir"
```
- **Respuesta:** Depende de la implementación; típicamente un 200 OK o 201 con datos de la reconstrucción.

---

### 4) Triangulación (Inteligencia)
- **URL:** `GET /api/inteligencia/triangulacion`
- **Descripción:** Ejecuta la lógica de triangulación (usa posiciones de los pilares y posiblemente mensajes) y devuelve una estructura `TriangulacionResponse` con la posición calculada / información relevante.
- **Ejemplo curl:**
```bash
curl -X GET "http://localhost:8080/api/inteligencia/triangulacion"
```
- **Respuesta:** JSON con los resultados de la triangulación (ver clase `TriangulacionResponse` en `application/service` para estructura exacta).

---

## 5. Ubicaciones importantes en el proyecto
- `src/main/resources/application.properties` — configuración principal (DB, JPA).
- `src/main/resources/data.sql` — datos iniciales.
- Controladores:
  - `src/main/java/.../adapters/in/web/PilarController.java` — endpoints de pilares.
  - `src/main/java/.../adapters/in/web/MensajeController.java` — endpoints de mensajes.
  - `src/main/java/.../adapters/in/web/InteligenciaController.java` — endpoint de triangulación.

---
