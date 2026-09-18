# Recursos Humanos App React

Frontend del sistema de recursos humanos. Permite gestionar empleados desde una interfaz web y conectarse a un backend Spring Boot.

## Descripción del proyecto

La aplicación permite:

- Ver lista de empleados
- Crear empleado
- Editar empleado
- Eliminar empleado

Consume la API REST del backend en `http://localhost:8080/rh-app/empleados`.

---

## Lista de conocimientos trabajados en el proyecto

1. React
2. Comandos principales de React
3. Axios
4. React Router DOM
5. React Number Format
6. Hook `useState`
7. Hook `useEffect`
8. Consumo de API y comparación conceptual con `fetch`
9. Integración frontend-backend

---

## Explicación de cada conocimiento (1 por 1)

### 1) React

**¿Qué es?**
React es una librería de JavaScript para construir interfaces de usuario basadas en componentes reutilizables.

**Beneficios:**

- UI modular y reutilizable
- Flujo de datos predecible
- Ecosistema amplio

**Ventajas frente a Angular:**

- Menor curva de entrada (React es librería, Angular es framework completo)
- Más libertad para elegir librerías complementarias
- Muy usado para SPAs ligeras y escalables por componentes

**Desventajas frente a Angular:**

- Menos estructura por defecto
- Requiere decidir herramientas externas (routing, manejo avanzado de estado, etc.)

**Ejemplo simple de componente:**

```jsx
function Saludo({ nombre }) {
  return <h1>Hola, {nombre}</h1>;
}
```

### 2) Comandos principales de React

- Crear proyecto:

```bash
npx create-react-app .
```

- Iniciar aplicación:

```bash
npm start
```

### 3) Axios

**¿Qué es?**
Cliente HTTP para navegador/Node usado para consumir APIs REST.

**Instalación:**

```bash
npm i axios
```

**Ejemplo real del proyecto:**

```jsx
const urlBase = 'http://localhost:8080/rh-app/empleados';
const response = await axios.get(urlBase);
setEmpleados(response.data);
```

### 4) React Router DOM

Permite direccionamiento y navegación entre páginas en una SPA.

**Instalación:**

```bash
npm i react-router-dom
```

**Ejemplo del proyecto:**

```jsx
<Routes>
  <Route path='/' element={<ListadoEmpleados />} />
  <Route path='/agregar' element={<AgregarEmpleado />} />
  <Route path='/editar/:id' element={<EditarEmpleado />} />
</Routes>
```

### 5) React Number Format

Formatea valores numéricos (como sueldo) para visualización.

**Instalación:**

```bash
npm i react-number-format
```

**Ejemplo del proyecto:**

```jsx
<NumericFormat
  value={employee.sueldo}
  displayType={'text'}
  thousandSeparator=','
  prefix={'$'}
  decimalScale={2}
  fixedDecimalScale
/>
```

### 6) Hook `useState`

Gestiona estado local del componente.

**Ventajas:**

- Sencillo para formularios y estados locales
- Reactivo: re-renderiza al cambiar estado

**Desventajas:**

- Puede complicarse en estados muy grandes/anidados

**Ejemplo del proyecto:**

```jsx
const [empleado, setEmpleado] = useState({
  nombre: '',
  departamento: '',
  sueldo: ''
});
```

### 7) Hook `useEffect`

Ejecuta efectos secundarios (por ejemplo, cargar datos al montar componente).

**Ventajas:**

- Control de ciclo de vida en componentes funcionales
- Ideal para llamadas HTTP iniciales

**Desventajas:**

- Dependencias mal definidas pueden causar re-ejecuciones no deseadas

**Ejemplo del proyecto:**

```jsx
useEffect(() => {
  loadEmployees();
}, []);
```

### 8) Conectarse al backend con hooks + comparación con `fetch`

En este proyecto se usa el concepto de hooks (`useState`, `useEffect`) para controlar estado y ciclo de carga, mientras Axios hace la petición HTTP.

**Cómo funciona:**

1. `useEffect` dispara la consulta inicial.
2. Axios obtiene datos del backend.
3. `useState` guarda resultados y React renderiza la tabla.

**Ventajas de Axios sobre fetch:**

- API más simple para JSON
- Mejor manejo de interceptores y configuración global
- Manejo de errores HTTP más cómodo

**Desventajas:**

- Dependencia extra (fetch ya viene en el navegador)

**Ejemplo comparativo mínimo:**

```jsx
// Axios
const response = await axios.get(urlBase);
setEmpleados(response.data);

// Fetch
const response = await fetch(urlBase);
const data = await response.json();
setEmpleados(data);
```

### 9) Integración frontend-backend

El frontend React consume endpoints REST del backend Spring Boot en `localhost:8080` y muestra/actualiza la información en la UI.

---

## Ejecución del frontend

```bash
npm install
npm start
```

Abrir en navegador:

- `http://localhost:3000`

## Ejecución conjunta con backend

1. Inicia backend Spring Boot en puerto 8080.
2. Inicia frontend React en puerto 3000.
3. El frontend consumirá la API por CORS habilitado en el controlador (`@CrossOrigin("http://localhost:3000")`).
