# Recursos Humano Fullstack

Este proyecto es una solución **fullstack de gestión de empleados**. Permite registrar, listar, editar y eliminar empleados desde una interfaz web (React) conectada a una API REST (Spring Boot) que persiste la información en MySQL.

## ¿Qué es y para qué sirve?

Sirve como sistema base de **recursos humanos** para administrar información de empleados (nombre, departamento y sueldo), y también como ejemplo práctico de integración entre frontend y backend.

## Arquitectura general

- **Frontend:** `recursos-humanos-app-react` (React)
- **Backend:** `recursos-humano-backend` (Java + Spring Boot)
- **Base de datos:** MySQL

### ¿Cómo funcionan juntos?

1. El usuario interactúa con la app React en `http://localhost:3000`.
2. React consume la API REST del backend en `http://localhost:8080/rh-app/empleados`.
3. El backend procesa la lógica y accede a MySQL con Spring Data JPA.
4. La respuesta vuelve al frontend para actualizar la UI.

---

## Tecnologías utilizadas

### Frontend
- React 19
- React Router DOM
- Axios
- React Number Format
- Bootstrap (clases CSS usadas en componentes)

### Backend
- Java 23
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- MySQL Connector/J
- Lombok
- Maven

### Base de datos
- MySQL

---

## Ejecución del proyecto completo

### 1. Levantar MySQL

Asegura que MySQL esté en ejecución y que el usuario/clave configurados en backend sean correctos.

### 2. Ejecutar backend (Spring Boot)

Desde `recursos-humano-backend`:

```bash
mvn spring-boot:run
```

La API quedará en:

- `http://localhost:8080/rh-app/empleados`

### 3. Ejecutar frontend (React)

Desde `recursos-humanos-app-react`:

```bash
npm install
npm start
```

La app quedará en:

- `http://localhost:3000`

---

## Referencias de README específicos

- Backend: `recursos-humano-backend/README.md`
- Frontend: `recursos-humanos-app-react/README.md`
