# Control de Clientes

Aplicación web educativa para administrar clientes y su saldo. Implementa operaciones CRUD (crear, consultar, actualizar y eliminar) usando Java, Jakarta Servlets, JSP, el patrón MVC, el patrón DAO y MySQL.

La aplicación permite listar clientes, mostrar el saldo y la cantidad total, registrar clientes desde un formulario Bootstrap, editar sus datos y eliminarlos. Es apropiada para estudiar el flujo completo de una aplicación web del lado del servidor: el navegador envía una petición HTTP, un servlet la procesa, el DAO consulta o modifica la base de datos y un JSP presenta el resultado.

## Tecnologías utilizadas

| Tecnología | Uso en el proyecto | Por qué se utiliza |
| --- | --- | --- |
| Java 21 | Lenguaje principal | Permite aplicar orientación a objetos, colecciones, excepciones y características modernas como `Stream`. |
| Maven | Construcción y dependencias | Estandariza la compilación y genera el archivo WAR. |
| Jakarta EE 11 | Plataforma empresarial | Proporciona APIs para construir aplicaciones web Java desplegables en GlassFish. |
| Jakarta Servlets | Control HTTP | Es adecuada para aplicaciones donde el servidor controla el flujo y la seguridad de las peticiones. |
| JSP | Vistas HTML dinámicas | Permite construir la presentación y mostrar datos enviados por los servlets. |
| JSTL y EL | Iteraciones, formato y acceso a datos en JSP | Evitan incluir lógica Java extensa dentro de las vistas. |
| MySQL | Persistencia | Base de datos relacional ampliamente utilizada para aplicaciones CRUD. |
| JDBC | Comunicación con MySQL | Permite ejecutar consultas SQL desde Java. |
| Apache Commons DBCP2 | Pool de conexiones | Reutiliza conexiones y evita abrir una conexión nueva para cada operación. |
| GlassFish | Servidor de aplicaciones | Ejecuta el WAR y proporciona el contenedor de servlets y JSP. |
| Bootstrap 5.3 | Interfaz | Facilita una interfaz responsive sin crear todo el CSS desde cero. |

Las hojas de estilo, los iconos y el JavaScript de Bootstrap se cargan desde CDN; el navegador necesita Internet para obtenerlos, salvo que se sustituyan por archivos locales.

## Arquitectura y flujo

El proyecto utiliza el patrón **MVC (Model-View-Controller)**:

```text
Navegador
   |
   | HTTP
   v
RootServlet / ServletControlador  (Controller)
   |
   v
ClienteDAO + Conexion             (acceso a datos)
   |
   v
MySQL                             (persistencia)
   |
   v
Cliente                           (Model)
   |
   v
clientes.jsp y JSP incluidos      (View)
```

### Ejemplo del flujo de listado

1. El usuario visita `/ControlClientes/`.
2. `RootServlet` recibe la petición y ejecuta `sendRedirect` hacia `ServletControlador`.
3. `ServletControlador` obtiene `accion`; si no existe, utiliza `listar`.
4. `ClienteDAO.listar()` ejecuta un `SELECT` mediante `PreparedStatement`.
5. El servlet guarda la lista, el total de clientes y el saldo total en la sesión.
6. El servlet hace `forward` a `clientes.jsp`.
7. La vista usa EL y JSTL para recorrer la lista y mostrar los valores.

### Clases principales

- `modelo.Cliente`: JavaBean con identificador, nombre, apellido, IMEI, teléfono y saldo.
- `datos.Conexion`: configura URL, usuario, contraseña y pool de conexiones para MySQL.
- `datos.ClienteDAO`: encapsula las consultas SQL del CRUD, separándolas del servlet.
- `web.RootServlet`: punto de entrada `/`; redirige al controlador principal.
- `web.ServletControlador`: recibe acciones GET y POST, coordina el flujo y comunica modelo y vistas.

## Estructura

```text
ControlClientes/
├── pom.xml
├── README.md
├── nb-configuration.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── datos/ClienteDAO.java
    │   │   ├── datos/Conexion.java
    │   │   ├── modelo/Cliente.java
    │   │   └── web/
    │   │       ├── RootServlet.java
    │   │       └── ServletControlador.java
    │   ├── resources/META-INF/persistence.xml
    │   └── webapp/
    │       ├── clientes.jsp
    │       └── WEB-INF/
    │           ├── beans.xml
    │           ├── glassfish-web.xml
    │           ├── web.xml
    │           └── paginas/
    │               ├── cliente/
    │               └── templates/
    └── test/java/
```

`target/` se genera durante la compilación y no debe utilizarse para guardar código fuente ni scripts permanentes.

## Requisitos previos

- JDK 21.
- Apache Maven.
- MySQL Server.
- GlassFish 8 compatible con Jakarta EE 11.
- NetBeans u otro IDE Java, opcional.

```bash
java -version
mvn -version
```

## Base de datos

`Conexion` utiliza actualmente:

```text
Servidor: localhost
Puerto: 3306
Base de datos: control_clientes
Usuario: root
Contraseña: root
```

Antes de iniciar la aplicación debe existir la base de datos y la tabla `clientes`:

```sql
CREATE DATABASE control_clientes;
USE control_clientes;

CREATE TABLE clientes (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    imei VARCHAR(50) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    saldo DECIMAL(12, 2) NOT NULL DEFAULT 0
);
```

Para un entorno real no conviene utilizar `root` ni dejar credenciales en el código fuente. Debe crearse un usuario con permisos limitados y mover la configuración a variables de entorno, un secreto del servidor o un pool administrado por GlassFish.

## Compilación y despliegue

Desde la raíz:

```bash
mvn clean package
```

El resultado esperado es `target/ControlClientes-1.0.war`.

Se puede desplegar desde NetBeans seleccionando GlassFish y ejecutando **Run**. También se puede utilizar `asadmin`:

```bash
asadmin start-domain domain1
asadmin deploy --force=true target/ControlClientes-1.0.war
```

La aplicación normalmente estará disponible en:

```text
http://localhost:8080/ControlClientes/
```

El contexto depende del nombre final del WAR o de la configuración del servidor.

### Rutas de prueba

- Inicio/listado: `/`
- Listado explícito: `/ServletControlador?accion=listar`
- Edición: `/ServletControlador?accion=editar&idCliente=1`
- Eliminación: `/ServletControlador?accion=eliminar&idCliente=1`
- Inserción y modificación: formularios POST enviados por los JSP.

## Cabeceros de petición (`Request headers`)

Los cabeceros HTTP son metadatos enviados por el cliente al servidor. Ejemplos:

```text
Remote-Addr: 127.0.0.1
Host: localhost:8080
User-Agent: Mozilla/5.0 ...
Accept: text/html,application/xhtml+xml
```

Pueden indicar la IP observada por el servidor, el dominio o servidor solicitado, el navegador y los formatos aceptados. La IP debe interpretarse con cuidado si existen proxies o balanceadores.

### Métodos de `HttpServletRequest`

| Método | Función | Ejemplo |
| --- | --- | --- |
| `getHeader(String)` | Obtiene el primer valor de un cabecero. | `request.getHeader("User-Agent")` |
| `getHeaders(String)` | Obtiene todos los valores de un cabecero. | `request.getHeaders("Accept")` |
| `getHeaderNames()` | Recorre los nombres de todos los cabeceros. | Diagnóstico o auditoría. |
| `getMethod()` | Indica el método HTTP. | `GET`, `POST`, `PUT`, `DELETE`. |
| `getRequestURI()` | Devuelve la ruta solicitada. | `/ControlClientes/ServletControlador` |
| `getRequestURL()` | Devuelve la URL completa construida por el servidor. | `http://localhost:8080/...` |
| `getQueryString()` | Obtiene la parte posterior a `?`. | `accion=editar&idCliente=1` |
| `getParameter(String)` | Obtiene un parámetro de formulario o URL. | `request.getParameter("accion")` |
| `getParameterNames()` | Recorre los nombres de parámetros. | Formularios dinámicos. |
| `getRemoteAddr()` | Obtiene la IP observada del cliente. | `request.getRemoteAddr()` |
| `getRemoteHost()` | Obtiene el nombre del host remoto. | Puede ser más costoso que la IP. |
| `getServerName()` | Obtiene el nombre del servidor solicitado. | `localhost` |
| `getServerPort()` | Obtiene el puerto del servidor. | `8080` |
| `getContextPath()` | Obtiene el contexto de la aplicación. | `/ControlClientes` |
| `getLocale()` | Obtiene el idioma preferido. | `es_CO` |
| `getContentType()` | Indica el MIME del cuerpo recibido. | `application/x-www-form-urlencoded` |

### Métodos especializados

| Método | Para qué sirve |
| --- | --- |
| `getCookies()` | Devuelve cookies como `JSESSIONID`. |
| `getAuthType()` | Indica el mecanismo de autenticación: BASIC, FORM, etc. |
| `getRemoteUser()` | Devuelve el usuario autenticado, si existe. |
| `getUserPrincipal()` | Devuelve la identidad completa del usuario autenticado. |
| `isUserInRole(String)` | Comprueba si el usuario pertenece a un rol. |
| `getSession()` | Obtiene o crea la sesión HTTP. |
| `getSession(false)` | Obtiene la sesión sin crear una nueva. |
| `getRequestDispatcher(String)` | Permite hacer `forward` o `include`. |
| `getInputStream()` | Lee el cuerpo binario de la petición. |
| `getReader()` | Lee el cuerpo de la petición como texto. |

Ejemplo:

```java
String navegador = request.getHeader("User-Agent");
String host = request.getHeader("Host");
String recurso = request.getRequestURI();
String ip = request.getRemoteAddr();
```

No se debe confiar ciegamente en `User-Agent` o `X-Forwarded-For` para decisiones de seguridad porque el cliente puede modificarlos.

## Códigos de estado HTTP

Indican qué ocurrió al procesar la petición:

- `2xx`: éxito, como `200 OK` o `201 Created`.
- `3xx`: redirección, como `302 Found`.
- `4xx`: error de la petición, como `400 Bad Request`, `401 Unauthorized` o `404 Not Found`.
- `5xx`: error del servidor, como `500 Internal Server Error`.

### API de respuestas en servlets

```java
response.setStatus(HttpServletResponse.SC_OK);
```

`setStatus(int)` establece el código. Las constantes como `SC_OK`, `SC_CREATED` y `SC_NOT_FOUND` evitan números sin significado.

```java
response.sendError(HttpServletResponse.SC_NOT_FOUND,
        "Cliente no encontrado");
```

`sendError()` indica un error y permite utilizar una página de error configurada.

```java
response.sendRedirect(request.getContextPath() + "/ServletControlador");
```

`sendRedirect()` ordena al navegador realizar una nueva petición a otra URL. Es útil después de guardar datos para evitar el reenvío de formularios (Post/Redirect/Get). `RootServlet` lo usa para enviar la raíz al controlador.

## Cabeceros de respuesta y MIME

Los cabeceros de respuesta indican al navegador cómo comportarse. `Content-Type` describe el formato del cuerpo. MIME significa *Multipurpose Internet Mail Extensions* y sus especificaciones se utilizan en HTTP para intercambiar texto, documentos, imágenes, audio y video.

| Cabecero o valor | Uso |
| --- | --- |
| `Content-Type: text/html; charset=UTF-8` | Página HTML. |
| `Content-Type: application/json` | Respuesta JSON. |
| `Content-Type: application/pdf` | Documento PDF. |
| `Content-Type: application/vnd.ms-excel` | Archivo compatible con Excel. |
| `Content-Type: text/csv; charset=UTF-8` | Datos CSV. |
| `Content-Type: image/png` | Imagen PNG. |
| `Content-Type: audio/mpeg` | Audio MP3. |
| `Content-Type: video/mp4` | Video MP4. |

```java
response.setContentType("application/pdf");
response.setHeader("Content-Disposition",
        "attachment; filename=reporte.pdf");
response.setCharacterEncoding("UTF-8");
```

`Content-Disposition: attachment` solicita una descarga; `inline` puede solicitar mostrar el recurso. `Cache-Control` controla la caché y `Location` indica el destino de una redirección.

## Cookies y sesiones HTTP

Una cookie es un dato de clave-valor que el servidor solicita guardar al navegador. El navegador la envía en peticiones posteriores al mismo dominio y ruta:

```java
Cookie tema = new Cookie("tema", "oscuro");
tema.setMaxAge(60 * 60 * 24 * 30);
response.addCookie(tema);
```

Para leerlas:

```java
Cookie[] cookies = request.getCookies();
```

Se usan para recordar preferencias, identificar una sesión o mantener un carrito. No deben guardar contraseñas ni información sensible directamente.

Una **sesión HTTP** representa el estado asociado a un usuario entre varias peticiones, aunque HTTP sea sin estado. El servidor conserva los datos y entrega normalmente una cookie `JSESSIONID` como identificador.

En este proyecto el controlador utiliza:

```java
HttpSession sesion = request.getSession();
sesion.setAttribute("clientes", clientes);
sesion.setAttribute("totalClientes", clientes.size());
sesion.setAttribute("saldoTotal", saldoTotal);
```

El JSP lee `${clientes}`, `${totalClientes}` y `${saldoTotal}`. `web.xml` configura una expiración de sesión de 30 minutos.

### URL rewriting

URL rewriting agrega el identificador de sesión a la URL si las cookies están deshabilitadas:

```java
String url = response.encodeURL("clientes.jsp");
```

El resultado podría ser `clientes.jsp;jsessionid=ABC123`. Es un respaldo; las cookies suelen ser preferibles porque mantienen las URL limpias y no exponen el identificador en enlaces, historial o registros.

## Servlets y JSP

Un servlet es un componente Java del lado servidor que recibe una petición, ejecuta lógica y construye o delega la respuesta. En este proyecto controla acciones, obtiene parámetros, invoca el DAO y decide la vista.

JSP (*JavaServer Pages*) es una tecnología de presentación del lado servidor. Permite escribir HTML dinámico y se transforma internamente en un servlet administrado por el contenedor. Su beneficio es concentrarse en HTML y dejar el control del flujo y la persistencia a servlets y clases Java.

No conviene colocar la lógica completa en un JSP: consultas SQL, reglas de negocio y validaciones importantes deben permanecer en Java.

### Scriptlets y declaraciones

Un **scriptlet** inserta sentencias Java en un JSP:

```jsp
<%
    String nombre = request.getParameter("nombre");
%>
<p>Hola <%= nombre %></p>
```

`<% ... %>` se ejecuta dentro del método generado para la petición. Aunque funciona, se desaconseja en proyectos nuevos porque mezcla presentación y lógica.

Una **declaración** usa `<%! ... %>` y agrega miembros al servlet generado, no instrucciones locales:

```jsp
<%!
    private int contador = 0;
%>
```

Los miembros pueden compartirse entre peticiones y requieren sincronización, por lo que no son una buena opción para guardar estado de usuarios.

## JSPX

Un JSPX es una variante de JSP con sintaxis XML más formal. Es útil cuando se requieren documentos bien formados o herramientas XML:

```xml
<jsp:text>Saludo desde un JSPX</jsp:text>
<jsp:expression>2 * 4</jsp:expression>
<jsp:scriptlet>
    String nombreApp = request.getContextPath();
</jsp:scriptlet>
```

Las vistas actuales utilizan JSP tradicional con HTML, por lo que JSPX es un concepto de referencia.

## Directivas JSP

Las directivas configuran la traducción y el comportamiento de una página. `page` permite importar clases, configurar MIME, indicar si existe sesión y establecer errores:

```jsp
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<%@ page isErrorPage="true" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp" %>
```

`taglib` registra una biblioteca:

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

La inclusión de una página de error centraliza su presentación y evita revelar consultas SQL, rutas o trazas al usuario.

## Inclusión estática y dinámica

La inclusión estática se procesa durante la traducción; el contenido forma un único servlet:

```jsp
<%@ include file="pages/noticias1.html" %>
<%@ include file="pages/noticias2.jsp" %>
```

Es útil para fragmentos que cambian junto con la página, como una estructura fija.

La inclusión dinámica ejecuta otro recurso y mantiene servlets separados:

```jsp
<jsp:include page="/WEB-INF/paginas/templates/footer.jsp" />

<jsp:include page="/WEB-INF/recursoPrivado.jsp">
    <jsp:param name="colorFondo" value="yellow" />
</jsp:include>
```

`clientes.jsp` usa inclusión dinámica para encabezado, navegación, listado, formulario y pie de página. `WEB-INF` evita que el navegador solicite directamente esos fragmentos.

## Alcance de atributos (scope)

| Alcance | Duración y visibilidad | Ejemplo |
| --- | --- | --- |
| `page` | Solo el JSP actual. | `<c:set var="mensaje" value="Hola" scope="page"/>` |
| `request` | Una petición, incluidos `forward` e inclusiones. | `request.setAttribute("cliente", cliente)` |
| `session` | Varias peticiones del mismo usuario. | `session.setAttribute("totalClientes", 3)` |
| `application` | Toda la aplicación y todos sus usuarios. | `servletContext.setAttribute("version", "1.0")` |

El controlador usa sesión para que `clientes.jsp` lea la lista y los totales. Para datos de una sola vista suele ser mejor `request`; usar `application` para listas de usuarios mezclaría datos entre usuarios.

## JavaBeans en JSP

Un JavaBean tiene constructor público sin argumentos y propiedades mediante `get` y `set`. `Cliente` sigue esta idea y puede transportarse entre capas:

```jsp
<jsp:useBean id="rectangulo" class="beans.Rectangulo" scope="session" />
<jsp:setProperty name="rectangulo" property="base" value="5" />
<jsp:getProperty name="rectangulo" property="base" />
```

EL permite acceder a las propiedades:

```jsp
Valor base: ${rectangulo.base}
Valor altura: ${rectangulo.altura}
Valor area: ${rectangulo.area}
```

## Expression Language (EL)

EL reduce el código Java dentro de una vista y accede a atributos, propiedades, parámetros, cabeceros y datos de sesión:

```jsp
${pageContext.request.contextPath}
${header["User-Agent"]}
${cookie.JSESSIONID.value}
${pageContext.servletContext.serverInfo}
${param.usuario}
```

En el proyecto se usan `${cliente.nombre}`, `${cliente.saldo}`, `${pageContext.request.contextPath}` y `${saldoTotal}`. EL mejora la legibilidad y favorece MVC.

## JSTL

JSTL (*JavaServer Pages Standard Tag Library*) proporciona etiquetas para tareas frecuentes:

- `core`: condiciones, iteraciones, variables y URL.
- `fmt`: formatos numéricos, fechas y localización.
- `sql`: acceso SQL desde JSP; se evita aquí porque el DAO separa mejor responsabilidades.
- `xml`: procesamiento XML.

El listado usa `core` y `fmt`:

```jsp
<c:forEach var="client" items="${clientes}" varStatus="status">
    <li>${status.count}: ${client.nombre}</li>
</c:forEach>

<fmt:formatNumber value="${client.saldo}" type="currency" />
```

También es posible usar condiciones:

```jsp
<c:if test="${param.opcion != null}">
    <c:choose>
        <c:when test="${param.opcion == 1}">
            Opción 1 seleccionada
        </c:when>
        <c:when test="${param.opcion == 2}">
            Opción 2 seleccionada
        </c:when>
        <c:otherwise>
            Opción desconocida: ${param.opcion}
        </c:otherwise>
    </c:choose>
</c:if>
```

## Patrón DAO y CRUD

DAO (*Data Access Object*) concentra el acceso a una fuente de datos. `ClienteDAO` contiene `SELECT`, `INSERT`, `UPDATE` y `DELETE`, mientras `ServletControlador` trabaja con objetos `Cliente`.

Esto separa SQL de la lógica web, centraliza `PreparedStatement`, facilita cambiar la persistencia y mejora el mantenimiento.

```java
PreparedStatement stmt = conn.prepareStatement(
        "SELECT * FROM clientes WHERE id_cliente = ?");
stmt.setInt(1, idCliente);
```

`PreparedStatement` separa valores de la cadena SQL y reduce el riesgo de inyección SQL.

## MVC aplicado

- **Modelo:** `Cliente` representa datos y `ClienteDAO` gestiona persistencia.
- **Vista:** `clientes.jsp`, `agregarCliente.jsp`, `editarCliente.jsp` y plantillas HTML.
- **Controlador:** `RootServlet` y `ServletControlador` reciben peticiones, seleccionan acciones y coordinan modelo y vistas.

MVC se aplica en sistemas administrativos, intranets, portales y aplicaciones empresariales donde se necesita separar interfaz, flujo HTTP y datos. Su valor didáctico es mostrar qué responsabilidad corresponde a cada componente.

## Buenas prácticas y mejoras posibles

Este es un proyecto de estudio. Para producción convendría:

- validar tipos, rangos y campos obligatorios en el servidor;
- manejar errores con respuestas HTTP y páginas de error, sin mostrar trazas;
- usar `BigDecimal` para importes monetarios en vez de `double`;
- usar POST para eliminar y protección CSRF;
- validar que `idCliente` sea válido antes de convertirlo;
- configurar credenciales fuera del código fuente;
- cerrar el pool al apagar la aplicación;
- agregar pruebas unitarias y de integración;
- agregar una capa de servicio si crecen las reglas de negocio;
- controlar autenticación y autorización antes de exponer el CRUD;
- declarar y revisar explícitamente las dependencias de JSTL si el servidor no las proporciona.

Estas mejoras ayudan a evolucionar el CRUD hacia una aplicación mantenible y segura sin cambiar su objetivo educativo.
