# Template proyecto usando Arquitectura Hexagonal: Ecommerce Catalog Service

Este es un microservicio para la gestión del catálogo de productos de un sistema de E-commerce, desarrollado con **Java 21** y **Spring Boot 4**.

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación.
- **Spring Boot 4.0.5**: Framework base para el desarrollo del microservicio.
- **Gradle**: Gestor de dependencias y automatización de construcción.
- **Lombok**: Biblioteca para reducir el código repetitivo (Boilerplate).
- **Jakarta Validation**: Para la validación de datos de entrada.
- **Hexagonal Architecture**: Patrón de arquitectura para mantener el desacoplamiento entre la lógica de negocio y los detalles de infraestructura.

## Arquitectura

El proyecto sigue una **Arquitectura Hexagonal (Ports & Adapters)**, organizada de la siguiente manera:

- **Domain**: Contiene las entidades de negocio y las reglas de dominio (ej. `Product`).
- **Application**: Contiene los casos de uso (Use Cases) y las interfaces de entrada/salida (Ports).
- **Adapters**:
    - **Web**: Adaptadores de entrada para exponer la API REST (Controllers y DTOs).
    - **Persistence**: Adaptadores de salida para el almacenamiento de datos (actualmente utiliza un repositorio en memoria).

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
    "id": "uuid-1",
    "name": "Producto de Ejemplo",
    "category": "Electrónica",
    "price": 99.99,
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
  "id": "uuid-1",
  "name": "Producto de Ejemplo",
  "category": "Electrónica",
  "price": 99.99,
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

## Ejecución del Proyecto

Para ejecutar el proyecto localmente, utiliza el siguiente comando de Gradle:

```bash
./gradlew bootRun
```
