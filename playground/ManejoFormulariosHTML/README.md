# ManejoFormulariosHTML

Proyecto práctico para estudiar el manejo de formularios HTML en Java Web, conectando el cliente (navegador) con el servidor mediante **Servlets**.

## ¿Qué se trabaja en este proyecto?

Este proyecto se enfoca en:

- Capturar información enviada desde formularios HTML.
- Procesar peticiones HTTP desde Java.
- Generar respuestas dinámicas al cliente.
- Entender el rol del servlet como controlador dentro de una aplicación web.

## Tecnología estudiada: Servlets

Un **Servlet** es un componente del lado del servidor que procesa solicitudes del cliente y genera una respuesta.

### ¿Qué permite un Servlet?

1. Procesar peticiones Java por medio de HTTP.
2. Leer información del cliente web (por ejemplo, parámetros de una petición).
3. Generar respuestas para el cliente, como:
   - HTML
   - audio
   - PDF

### Funciones del Servlet

#### 1) Controlador (punto de entrada)

El servlet puede actuar como **controlador** de la aplicación: recibe la solicitud, valida/procesa datos y decide la respuesta.

**Ejemplo:**

```java
@WebServlet("/procesar-formulario")
public class FormularioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>Datos recibidos</h1>");
        out.println("<p>Nombre: " + nombre + "</p>");
        out.println("<p>Correo: " + correo + "</p>");
    }
}
```

#### 2) Código Java + HTML embebido

Un servlet contiene código Java y también puede generar HTML embebido con `PrintWriter`.

> Nota: aunque es posible, no es la mejor práctica en proyectos grandes, porque el HTML queda dentro del archivo Java y se dificulta el mantenimiento.

**Ejemplo:**

```java
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
out.println("<html><body>");
out.println("<h2>Respuesta generada por Servlet</h2>");
out.println("</body></html>");
```

## Métodos HTTP y procesamiento con Servlets

Los servlets pueden atender distintos métodos HTTP:

- `OPTIONS`
- `GET`
- `HEAD`
- `POST`
- `PUT`
- `DELETE`
- `TRACE`
- `CONNECT`

En `HttpServlet`, normalmente se sobrescriben métodos como `doGet()`, `doPost()`, `doPut()`, `doDelete()`, etc.

### Ejemplo básico por método

```java
@WebServlet("/api/usuario")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Consultar usuario (GET)");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Crear usuario (POST)");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Actualizar usuario (PUT)");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Eliminar usuario (DELETE)");
    }
}
```

## Flujo general en este tipo de proyecto

1. El usuario llena un formulario HTML.
2. El navegador envía la petición HTTP al servidor.
3. El servlet recibe y procesa los datos.
4. El servlet construye y devuelve la respuesta al cliente.

---

Este proyecto sirve como base para pasar de páginas estáticas a aplicaciones web Java dinámicas usando arquitectura cliente-servidor.
