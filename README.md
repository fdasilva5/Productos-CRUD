# Productos-CRUD API

API REST desarrollada con Spring Boot para la gestión de productos y proveedores.

## Descripción

Este proyecto implementa una API REST completa para administrar productos y sus proveedores asociados. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) tanto para productos como para proveedores.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.4.1
- Spring Data JPA
- MySQL
- Maven
- Swagger/OpenAPI para documentación

## Características

### Gestión de Productos
- Listar todos los productos
- Obtener producto por ID
- Crear nuevos productos
- Actualizar productos existentes
- Eliminar productos

### Gestión de Proveedores
- Listar todos los proveedores
- Obtener proveedor por ID
- Crear nuevos proveedores
- Actualizar proveedores existentes
- Eliminar proveedores

## Requisitos

- Java 21 o superior
- MySQL
- Maven

## Configuración

1. Clonar el repositorio:
```bash
git clone [url-del-repositorio]
```

2. Configurar la base de datos MySQL en `application.properties`

3. Ejecutar la aplicación:
```bash
./mvnw spring-boot:run
```

