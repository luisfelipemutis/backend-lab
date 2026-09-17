# Frontend - Inventario App

Este proyecto es el frontend de la aplicación full stack. Está desarrollado con Angular y se encarga de mostrar la interfaz de usuario para gestionar los productos del inventario. Su función principal es consumir la API REST del backend y permitir que el usuario interactúe con ella desde el navegador.

## ¿Para qué sirve?

La app permite:

- listar productos,
- agregar nuevos productos,
- editar información de un producto,
- eliminar registros,
- navegar entre componentes con rutas.

La interfaz se conecta con el backend en `http://localhost:8080/inventario-app` y presenta la información en una SPA moderna.

## Tecnologías utilizadas

- Angular 22
- TypeScript
- HTML 5
- CSS / Bootstrap
- Node.js
- npm
- Angular CLI
- RxJS
- Angular Router
- Angular Forms (`ngModel`)
- `HttpClient` de Angular

## ¿Por qué es necesario Node.js para Angular?

Angular es un framework frontend que se ejecuta principalmente en el navegador, pero para trabajar con él en desarrollo y despliegue necesitamos Node.js porque:

1. Angular CLI se ejecuta sobre Node.js.
2. npm instala y gestiona dependencias del proyecto.
3. Angular usa herramientas de build y compilación con Node.
4. El servidor de desarrollo permite ejecutar la app en `localhost:4200`.
5. Se usa para compilar, optimizar y ejecutar tareas automatizadas.

## Node.js y npm

Node.js es un entorno de ejecución para JavaScript que permite correr código del lado del servidor y del cliente. npm (node package manager) es el gestor de paquetes más usado y permite instalar bibliotecas, frameworks y dependencias del proyecto.

Algunas ventajas de Node.js:

- ejecución asíncrona y orientada a eventos,
- alta escalabilidad,
- gran comunidad de desarrollo,
- compatibilidad con herramientas modernas de frontend.

## Angular CLI (Command Line Interface)

Angular CLI es la herramienta principal para crear y administrar proyectos Angular. Es la forma más rápida de generar componentes, servicios, rutas y otros elementos del proyecto.

Comandos usados en este proyecto:

```bash
ng new inventario-app
ng serve -o
ng g class producto --skip-tests
ng g c producto-lista --skip-tests --type=component
ng g c agregar-producto --skip-tests --type=component
ng g c editar-producto --skip-tests --type=component
ng g s producto --skip-tests --type=service
```

### ¿Por qué `ng g s producto --type=service`?

Porque el proyecto ya tenía una clase/archivo llamado `producto.ts`. Si se genera el servicio sin especificar `type=service`, el nombre puede entrar en conflicto con el modelo y se crean archivos ambiguos. Usar `--type=service` garantiza que el resultado sea `producto.service.ts` y no `producto.ts` o un archivo con el mismo nombre del modelo.

## Versiones y buenas prácticas

### Node.js LTS

Se recomienda trabajar con una versión LTS (Long Term Support) porque es una versión estable y mantenida a largo plazo.

Actualizar Node.js:

```bash
nvm install --lts
nvm use 24.21.0
```

Actualizar Angular CLI a la última versión:

```bash
npm uninstall -g @angular/cli
npm install -g @angular/cli@latest
```

## Conceptos estudiados

- Full Stack
- Node.js
- npm
- Angular CLI
- Arquitectura SPA
- Observables
- `HttpClient`
- Angular Router
- `ngSubmit`
- `ngModel`
- Two-way binding
- Interpolación `{{ valor }}`
- Servicios en Angular
- Inyección de dependencias
- `ResponseEntity<T>` en backend (consumido desde Angular)
- `strictPropertyInitialization: false` / `id!: number;`
- `routerLink` y rutas

## Estructura del proyecto

```text
inventario-app/
├── src/
│   ├── app/
│   │   ├── agregar-producto/
│   │   │   ├── agregar-producto.component.ts
│   │   │   └── agregar-producto.component.html
│   │   ├── editar-producto/
│   │   │   ├── editar-producto.component.ts
│   │   │   └── editar-producto.component.html
│   │   ├── producto-lista/
│   │   │   ├── producto-lista.component.ts
│   │   │   └── producto-lista.component.html
│   │   ├── app.routes.ts
│   │   ├── app.html
│   │   ├── app.ts
│   │   ├── producto.ts
│   │   └── producto.service.ts
│   ├── index.html
│   ├── main.ts
│   └── styles.css
├── angular.json
├── package.json
├── tsconfig.json
├── README.md
└── node_modules/
```

## Desglose de conceptos

### 1. Angular y componentes

Angular organiza la UI por componentes. En este proyecto existen componentes como:

- `producto-lista`
- `agregar-producto`
- `editar-producto`

Cada componente tiene:

- un archivo `.ts` con la lógica,
- un archivo `.html` con la plantilla,
- opcionalmente estilos CSS.

### 2. Servicios y `HttpClient`

El servicio se encarga de consumir la API backend:

```ts
@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private urlBase = 'http://localhost:8080/inventario-app';

  constructor(private clientHttp: HttpClient) {}

  getListProducts(): Observable<Producto[]> {
    return this.clientHttp.get<Producto[]>(`${this.urlBase}/getProductos`);
  }
}
```

Este servicio usa `HttpClient` para realizar peticiones HTTP al backend. Angular maneja estas peticiones de forma asíncrona y devuelve `Observable`.

### 3. Observables

Los observables permiten trabajar con flujos de datos asíncronos sin bloquear la ejecución de la app. En Angular, la mayoría de las peticiones HTTP se manejan con `Observable` y `subscribe()`.

Ejemplo:

```ts
this.productoService.getListProducts().subscribe({
  next: (data) => {
    this.products = data;
  },
  error: (err) => {
    console.error('Error al cargar los productos', err);
  }
});
```

Esto permite que la app siga respondiendo mientras se espera la respuesta del servidor.

### 4. Two-way binding

Angular permite sincronizar datos entre la plantilla HTML y el componente TypeScript usando binding bidireccional.

```html
<input [(ngModel)]="producto.descripcion" />
```

Esto significa que:

- cada cambio que escribe el usuario se refleja en la propiedad `producto.descripcion`,
- y si el valor de la propiedad cambia en TypeScript, la vista también se actualiza.

Es especialmente útil en formularios.

### 5. `ngSubmit` y `ngModel`

En el formulario de creación y edición, se usan:

```html
<form (ngSubmit)="onSubmit()">
  <input [(ngModel)]="producto.descripcion" name="descripcion">
</form>
```

- `ngSubmit`: ejecuta una acción cuando el formulario se envía.
- `ngModel`: vincula un campo del formulario con una propiedad del componente.

### 6. Interpolación

La interpolación se usa para mostrar información en la vista:

```html
<h3>{{ producto.descripcion }}</h3>
```

Esto permite insertar valores de TS dentro del HTML de forma directa.

### 7. Rutas y `routerLink`

Angular Router permite definir rutas de navegación:

```ts
export const routes: Routes = [
  { path: 'productos', component: ProductoListaComponent },
  { path: 'agregar-producto', component: AgregarProductoComponent },
  { path: 'editar-producto/:id', component: EditarProductoComponent }
];
```

Esto hace que la app se comporte como una SPA, con navegación sin recargar la página completa.

### 8. `strictPropertyInitialization` y `id!: number`

En TypeScript, es común que se exija inicializar las propiedades de una clase. Para evitar tener que inicializar variables innecesariamente, se puede usar:

```json
archivo: tsconfig.json
"strictPropertyInitialization": false
```

O bien una aserción no nula:

```ts
id!: number;
```

Esto le dice al compilador que la variable se inicializará más adelante, aunque aún no se haya asignado en el constructor.

### 9. Formularios y envío de datos

Cuando el usuario agrega o edita un producto, el componente crea un objeto `Producto` y lo envía al backend:

```ts
this.productoService.saveProduct(this.producto).subscribe({
  next: () => this.redirectListProduct(),
  error: (err) => console.error('Error al guardar el producto:', err)
});
```

Es aquí donde Angular y la API REST trabajan juntos: el frontend obtiene los datos del usuario, los envía como JSON y el backend los guarda en la base de datos.

## Cómo ejecutar el frontend

### Requisitos

- Node.js LTS
- npm
- Angular CLI

### Comandos

```bash
cd inventario-fullstack/inventario-app
npm install
ng serve -o
```

La aplicación quedará disponible en:

```text
http://localhost:4200
```

## Conclusión

Este frontend es un ejemplo claro del uso de Angular para construir una interfaz moderna que consume una API REST. A través de componentes, servicios, rutas, formularios, observables y binding, se logra una aplicación que permite interactuar con datos reales de inventario de forma fluida y profesional.
