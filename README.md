# Template proyecto usando Arquitectura Hexagonal: Ecommerce Catalog Service

Este es un microservicio para la gestión del catálogo de productos de un sistema de E-commerce, desarrollado con **Java 21** y **Spring Boot 4**.

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación.
- **Spring Boot 4.0.5**: Framework base para el desarrollo del microservicio.
- **Gradle**: Gestor de dependencias y automatización de construcción.
- **Spring Data JPA & H2 Database**: Persistencia relacional en memoria con consolas web activas.
- **Lombok**: Biblioteca para reducir el código repetitivo (Boilerplate).
- **Jakarta Validation**: Para la validación de datos de entrada.
- **Hexagonal Architecture**: Patrón de arquitectura para mantener el desacoplamiento entre la lógica de negocio y los detalles de infraestructura.

## Arquitectura

El proyecto sigue una **Arquitectura Hexagonal (Ports & Adapters)**, organizada de la siguiente manera:

- **Domain**: Contiene las entidades de negocio y las reglas de dominio (ej. `Product`).
- **Application**: Contiene los casos de uso (Use Cases como `CreateProductUseCase`, `ListProductsUseCase`, `GetProductByIdUseCase`) y las interfaces de entrada/salida (Ports).
- **Adapters**:
    - **Web**: Adaptadores de entrada para exponer la API REST (`CatalogController` y DTOs).
    - **Persistence**: Adaptadores de salida para el almacenamiento de datos (`ProductH2JpaAdapter` y `ProductInMemoryRepository`).

## Endpoints de la API

### Catálogo de Productos

#### 1. Listar Productos
Obtiene una lista de todos los productos disponibles en el catálogo.

- **URL:** `/api/v1/catalog/products`
- **Método:** `GET`
- **Respuesta Exitosa (200 OK):**
```json
[
  {
    "id": "p-100",
    "name": "Wireless Mouse",
    "category": "Accessories",
    "price": 25.90,
    "active": true
  }
]
```

#### 2. Obtener Producto por ID
Obtiene los detalles de un producto específico mediante su identificador único.

- **URL:** `/api/v1/catalog/products/{id}`
- **Método:** `GET`
- **Respuesta Exitosa (200 OK):**
```json
{
  "id": "p-100",
  "name": "Wireless Mouse",
  "category": "Accessories",
  "price": 25.90,
  "active": true
}
```

#### 3. Crear Producto
Registra un nuevo producto en el catálogo.

- **URL:** `/api/v1/catalog/products`
- **Método:** `POST`
- **Cuerpo de la Solicitud (JSON):**
```json
{
  "id": "p-200",
  "name": "Monitor Gamer 27",
  "category": "Displays",
  "priceAmount": 299.99,
  "active": true
}
```
- **Respuesta Exitosa (201 Created):**
```json
{
  "id": "p-200",
  "name": "Monitor Gamer 27",
  "category": "Displays",
  "price": 299.99,
  "active": true
}
```

## Ejemplos de CURL

### Listar todos los productos
```bash
curl -X GET http://localhost:8080/api/v1/catalog/products
```

### Obtener un producto por ID
```bash
curl -X GET http://localhost:8080/api/v1/catalog/products/p-100
```

### Crear un producto
```bash
curl -X POST http://localhost:8080/api/v1/catalog/products \
  -H "Content-Type: application/json" \
  -d '{
    "id": "p-200",
    "name": "Monitor Gamer 27",
    "category": "Displays",
    "priceAmount": 299.99,
    "active": true
  }'
```

## Configuración y Switcheo de Repositorios (Fuentes de Datos)

El microservicio permite intercalar entre dos implementaciones de persistencia usando **Spring Profiles**:

1. **`h2` (Por defecto)**: Utiliza **Spring Data JPA** con base de datos H2 en memoria (`ProductH2JpaAdapter`).
   - Consola Web de H2 disponible en: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:ecommerce`
   - Usuario: `sa` | Contraseña: *(vacío)*

2. **`memory`**: Utiliza una colección interna en memoria `ConcurrentHashMap` (`ProductInMemoryRepository`).

### ¿Cómo cambiar entre repositorios?

- **Opción 1: En `application.yaml`**
  ```yaml
  spring:
    profiles:
      active: h2  # Cambiar a 'memory' para usar el repositorio en memoria
  ```

- **Opción 2: Desde la línea de comandos con Gradle**
  ```bash
  # Para usar H2 (default):
  ./gradlew bootRun

  # Para usar memoria:
  ./gradlew bootRun --args='--spring.profiles.active=memory'
  ```

## Ejecución del Proyecto

Para ejecutar el proyecto localmente, utiliza el siguiente comando de Gradle:

```bash
./gradlew bootRun
```

