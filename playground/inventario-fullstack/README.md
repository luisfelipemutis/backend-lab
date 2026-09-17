# Inventario Fullstack

Esta carpeta contiene dos proyectos que trabajan juntos para formar una aplicación full stack: una API REST en Spring Boot y una interfaz en Angular.

## ¿Qué contiene esta carpeta?

- `inventarios/`: backend desarrollado con Java + Spring Boot.
- `inventario-app/`: frontend desarrollado con Angular + TypeScript.

Ambos proyectos se integran para crear una solución completa de gestión de inventario, donde:

- el frontend permite visualizar, crear, editar y eliminar productos,
- el backend expone endpoints REST para gestionar la lógica de negocio,
- la base de datos MySQL almacena la información persistente.

## Arquitectura general

```text
Angular (SPA)
    |
    | HTTP / REST
    v
Spring Boot API
    |
    | JPA + Hibernate
    v
MySQL
```

El flujo funciona así:

1. El usuario accede a la app Angular en `http://localhost:4200`.
2. Angular consume los servicios REST del backend en `http://localhost:8080`.
3. Spring Boot procesa la petición, valida la lógica y accede a la base de datos.
4. La respuesta regresa al frontend y se renderiza en la vista.

## ¿Para qué sirve esta solución?

La app sirve como ejemplo de un sistema CRUD (Crear, Leer, Actualizar y Eliminar) para productos en inventario. Es una aplicación de estudio que ayuda a comprender:

- cómo se comunica un frontend con un backend,
- cómo se expone una API REST,
- cómo se trabaja con JPA y MySQL,
- cómo se estructura una aplicación full stack real.

## Tecnologías principales del proyecto completo

- Java 23
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL Driver
- Lombok
- Maven
- Angular 22
- TypeScript
- Node.js + npm
- RxJS
- Angular Router
- Angular Forms

## Proyecto backend

El backend está en `inventarios/` y se encarga de ofrecer los endpoints que gestionan los productos. Implementa un patrón de capas:

- `controller`: recibe peticiones HTTP
- `service`: contiene la lógica de negocio
- `repository`: interactúa con la base de datos
- `model`: representa la entidad `Producto`

## Proyecto frontend

El frontend está en `inventario-app/` y se encarga de mostrar la interfaz gráfica, consumir la API y permitir la gestión de productos desde la web.

## Conceptos estudiados en esta carpeta

- Full Stack
- API REST
- CRUD
- CORS
- Spring Boot
- Spring Data JPA
- MySQL
- Angular
- HTTP Client
- Observables
- Routing
- Two-way binding
- Formularios reactivos y basados en plantillas
- JSON y comunicación cliente-servidor

## Cómo se ejecutan juntos

1. Levantar el backend:

```bash
cd inventario-fullstack/inventarios
./mvnw spring-boot:run
```

2. Levantar el frontend:

```bash
cd inventario-fullstack/inventario-app
npm install
ng serve -o
```

3. Abrir la app en:

```text
http://localhost:4200
```

4. La API estará disponible en:

```text
http://localhost:8080/inventario-app
```

## Resultado esperado

La aplicación permite visualizar productos, agregar nuevos registros, editar existencias y precios, y borrar elementos del inventario, con ambos lados del sistema funcionando de forma coordinada.

Este proyecto es una base excelente para comprender cómo se construye una aplicación full stack moderna con Java y Angular.
