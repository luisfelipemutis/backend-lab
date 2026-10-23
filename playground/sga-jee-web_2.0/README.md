# SGA JEE Web 2.0

Sistema de Gestión Académica (SGA) — aplicación web empresarial construida con **Jakarta EE 10** (antes Java EE) y desplegada en **GlassFish**. Administra **Personas** y **Usuarios** (relación `Persona 1 → N Usuario`) y los muestra en listados web.

## 1. ¿Qué es el proyecto?

Aplicación multicapa que demuestra la arquitectura Java EE clásica:

```
Navegador ─► Servlet ─► Servicio (EJB) ─► DAO (EJB + JPA) ─► MySQL
 (HTTP)      (Web)       (Negocio)          (Datos)
              │
              └─► JSP + JSTL (vista)
```

| Capa | Paquete / archivo | Tecnología |
|---|---|---|
| Presentación | `fm.sga.jee.web` (`PersonaServlet`, `UsuarioServlet`), `listadoPersonas.jsp`, `listadoUsuarios.jsp`, `index.html` | Servlets + JSP + JSTL |
| Negocio | `fm.sga.jee.servicio` (`PersonaServiceImpl`, `UsuarioServiceImpl`, interfaces local y `@Remote`) | EJB `@Stateless` + CDI `@Inject` |
| Datos | `fm.sga.jee.datos` (`PersonaDaoImpl`, `UsuarioDaoImpl`) | JPA (`EntityManager`) |
| Dominio | `fm.sga.jee.domain` (`Persona`, `Usuario`) | Entidades JPA, Bean Validation |

**Rutas:** `/personas` (listado de personas) y `/UsuarioServlet` (listado de usuarios). Punto de entrada: `index.html`.

## 2. Tecnologías utilizadas

| Tecnología | Versión | Uso en el proyecto |
|---|---|---|
| Java (JDK) | 17 | Lenguaje / compilación (`maven-compiler-plugin`) |
| Jakarta EE API | 10.0.0 (`provided`) | Servlets 6, EJB, CDI, JPA 3.0, Bean Validation |
| Servlets | Jakarta Servlet 6.0 | `@WebServlet`, controladores web |
| JSP + JSTL | — | Vistas (`<c:forEach>`) |
| EJB | 4.0 | `@Stateless` en servicios y DAOs, interfaz `@Remote` |
| CDI | 4.0 | `@Inject` (+ `WEB-INF/beans.xml`) |
| JPA | 3.0 | `@Entity`, `@NamedQuery`, `@PersistenceContext` |
| JTA | — | Transacciones gestionadas por el contenedor (`transaction-type="JTA"`) |
| JNDI | — | DataSource `jdbc/PersonaDb` (`persistence.xml`) |
| Bean Validation | — | `@Size(max = 45)` en entidades |
| MySQL + Connector/J | 8.0.32 (`provided`) | Base de datos |
| Log4j 2 | api 2.25.5 / core 2.7 | Logging (`log4j2.xml`) |
| Maven | — | Build, empaquetado `war` (`maven-war-plugin 3.4.0`) |
| GlassFish | 7 (Jakarta EE 10) | Servidor de aplicaciones (`glassfish-web.xml`) |
| Apache NetBeans | — | IDE (`nb-configuration.xml`) |

> Nota: `log4j-core 2.7` es una versión antigua con vulnerabilidades conocidas (Log4Shell); se recomienda igualar a `2.25.5` como `log4j-api`.

## 3. Requisitos y ejecución

1. JDK 17, Maven 3.9+, MySQL, GlassFish 7.
2. Crear en GlassFish un pool de conexiones y un recurso JDBC con JNDI **`jdbc/PersonaDb`** apuntando a MySQL (tablas `persona` y `usuario`).
3. Compilar: `mvn clean package` → `target/sga-jee-web_2.0-1.0.war`.
4. Desplegar el WAR en GlassFish (o desde NetBeans con *Run*).
5. Abrir `http://localhost:8080/sga-jee-web_2.0/`.

---

# Conceptos de Java EE / JSF

> Para cada concepto: qué es, ejemplo, uso actual y si se usa en este proyecto.
> **Resumen:** este proyecto usa **Servlets + JSP**, **EJB**, **CDI**, **JPA**, **JTA** y **JNDI**. **No** usa JSF, Facelets, Managed Beans, validadores/convertidores JSF ni AJAX.

## JavaServer Faces (JSF)

**Qué es:** tecnología estándar de Jakarta EE para construir interfaces de usuario web basadas en componentes en el servidor. Tiene soporte en IDEs como Eclipse, NetBeans e IntelliJ IDEA y, al ser estándar, cuenta con respaldo sólido de la industria.

**Ejemplo:**
```xhtml
<h:form>
    <h:inputText value="#{personaBean.nombre}"/>
    <h:commandButton value="Guardar" action="#{personaBean.guardar}"/>
</h:form>
```
**Actualidad:** sigue vigente (Jakarta Faces 4.x) en aplicaciones empresariales, con PrimeFaces/OmniFaces; frente a ello domina el uso de SPAs (React, Angular) con APIs REST.
**En el proyecto:** ❌ No. Se usan Servlets + JSP.

## MVC con JSF

- **Modelo:** Managed Bean de modelo, clases POJO (*Plain Old Java Object*).
- **Vista:** Facelets (XHTML) o JSP.
- **Controlador:** Managed Bean controlador (y el `FacesServlet`).

**Ejemplo:** `Persona` (POJO/modelo) → `persona.xhtml` (vista) → `PersonaBean` (controlador con `guardar()`).
**Actualidad:** MVC es el patrón dominante (Spring MVC, Jakarta MVC, frameworks front-end).
**En el proyecto:** ✅ MVC, pero **sin JSF**: Modelo = `Persona` (entidad), Vista = JSP, Controlador = `PersonaServlet`.

## Facelets

**Qué es:** motor de vistas predeterminado de JSF. Permite crear interfaces reutilizables con **plantillas XHTML**. Es el estándar de despliegue de JSF, diseñado considerando su ciclo de vida, **elimina la necesidad de JSP** y usa un **parser XML** en lugar del compilador de JSP.

**Ejemplo:**
```xhtml
<!-- plantilla.xhtml -->
<h:body>
    <ui:insert name="contenido">Contenido por defecto</ui:insert>
</h:body>

<!-- pagina.xhtml -->
<ui:composition template="/plantilla.xhtml">
    <ui:define name="contenido">Hola Facelets</ui:define>
</ui:composition>
```
**Actualidad:** es la forma estándar de escribir vistas JSF hoy; JSP está obsoleto.
**En el proyecto:** ❌ No (usa JSP).

## Managed Beans

**Qué es:** clases Java que cumplen las condiciones de **JavaBeans** y son administradas por el contenedor JSF, que las instancia y las conecta con la vista mediante *Expression Language* (`#{...}`).

**Características de un JavaBean:** constructor público sin argumentos, atributos privados con getters/setters, implementa `Serializable`.
**Usos:** controlador de la vista (acciones), almacén de datos del formulario, delegar lógica a EJBs.

**Ejemplo:**
```java
@Named("personaBean")
@RequestScoped
public class PersonaBean implements Serializable {
    private String nombre;
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String guardar() { /* lógica */ return "exito"; }
}
```
**Actualidad:** `@ManagedBean` está **obsoleto/eliminado**; hoy se usa **CDI** con `@Named` + un scope.
**En el proyecto:** ❌ No hay Managed Beans. Sí hay JavaBeans/POJOs: `Persona` y `Usuario` (entidades con getters/setters y `Serializable`).

## Navegación en JSF

### Navegación estática
No ejecuta código Java; solo redirige a otra página, sin lógica para decidir el destino.

```xhtml
<h:commandButton value="Ir a inicio" action="inicio"/>
<!-- navega a inicio.xhtml -->
```
### Navegación dinámica
Utiliza un método *action* que contiene la lógica para decidir el **outcome** (la siguiente página).

```xhtml
<h:commandButton value="Login" action="#{loginBean.validar}"/>
```
```java
@Named @RequestScoped
public class LoginBean {
    private String usuario, password;
    public String validar() {
        if ("admin".equals(usuario) && "1234".equals(password)) {
            return "bienvenido";   // -> bienvenido.xhtml
        }
        return "error";            // -> error.xhtml
    }
    // getters y setters
}
```
Si `validar()` devuelve `"bienvenido"`, JSF muestra `bienvenido.xhtml`; si devuelve `"error"`, muestra `error.xhtml`.

**Actualidad:** en JSF moderno se usa también *implicit navigation* y `?faces-redirect=true`; en SPAs se usa routing en el cliente.
**En el proyecto:** ❌ No JSF. Equivalente: enlaces estáticos en `index.html` (`href="personas"`) y navegación server-side con `request.getRequestDispatcher("/listadoPersonas.jsp").forward(...)` en el servlet.

## Validaciones en JSF

**Qué son:** etiquetas/validadores ya incluidos en JSF para validar datos de entrada: campos obligatorios, longitudes, rangos numéricos, etc. Se ejecutan en la fase *Process Validations* del ciclo de vida.

```xhtml
<h:inputText value="#{personaBean.edad}" required="true"
             requiredMessage="La edad es obligatoria">
    <f:validateLongRange minimum="18" maximum="99"/>
</h:inputText>
<h:message for="edad"/>
```
Otros: `f:validateLength`, `f:validateDoubleRange`, `f:validateRegex`, `f:validateRequired`.

**Actualidad:** se prefiere **Bean Validation** (`@NotNull`, `@Size`, `@Min`, `@Max`), que JSF también integra.
**En el proyecto:** ⚠️ Parcial: se usa Bean Validation (`@Size(max = 45)` en `Persona`), no las etiquetas JSF.

## Convertidores en JSF

**Qué son:** convierten automáticamente valores entre la vista (texto) y el modelo (tipos Java).

- **Implícito:** JSF convierte solo, según el tipo de la propiedad (`Integer`, `Boolean`, `Date`...).
- **Explícito:** con el atributo `converter` o con un componente/etiqueta de conversión.

```xhtml
<!-- implícito: edad es Integer -->
<h:inputText value="#{personaBean.edad}"/>

<!-- explícito con etiqueta -->
<h:inputText value="#{personaBean.fecha}">
    <f:convertDateTime pattern="dd/MM/yyyy"/>
</h:inputText>

<!-- explícito con atributo converter -->
<h:inputText value="#{personaBean.pais}" converter="paisConverter"/>
```
```java
@FacesConverter("paisConverter")
public class PaisConverter implements Converter<Pais> {
    public Pais getAsObject(FacesContext c, UIComponent u, String v) { return Pais.of(v); }
    public String getAsString(FacesContext c, UIComponent u, Pais p) { return p.getCodigo(); }
}
```
**Actualidad:** muy usados para `<select>` con objetos y fechas.
**En el proyecto:** ❌ No. (JPA convierte tipos Java ↔ SQL, pero es otro mecanismo.)

## Listeners en JSF

### ValueChangeListener
Se ejecuta cuando cambia el valor de un componente al hacer submit del formulario (o al disparar el evento).

```xhtml
<h:selectOneMenu value="#{bean.pais}" valueChangeListener="#{bean.paisCambio}" onchange="submit()">
    <f:selectItems value="#{bean.paises}"/>
</h:selectOneMenu>
```
```java
public void paisCambio(ValueChangeEvent e) {
    System.out.println("Antes: " + e.getOldValue() + " Ahora: " + e.getNewValue());
}
```
### ActionListener
Se ejecuta al activar un componente de acción (botón/enlace), antes del método `action`; no decide la navegación.

```xhtml
<h:commandButton value="Enviar" actionListener="#{bean.registrarClick}" action="#{bean.guardar}"/>
```
```java
public void registrarClick(ActionEvent e) { log.info("Botón presionado"); }
```
**Actualidad:** hoy se reemplazan con `<f:ajax listener="...">` en la mayoría de casos.
**En el proyecto:** ❌ No.

## Peticiones AJAX

**Qué es:** *Asynchronous JavaScript And XML*: permite enviar peticiones al servidor y actualizar **parte** de la página sin recargarla completa. En JSF se usa `<f:ajax>`.

```xhtml
<h:inputText value="#{bean.nombre}">
    <f:ajax event="keyup" render="saludo"/>
</h:inputText>
<h:outputText id="saludo" value="Hola #{bean.nombre}"/>
```
**Actualidad:** estándar absoluto, hoy con `fetch`/Axios y JSON hacia APIs REST.
**En el proyecto:** ❌ No; cada acción recarga la página.

---

# Java Empresarial (Java EE / Jakarta EE)

## API

**Qué es:** conjunto de clases e interfaces que resuelven una necesidad específica.
**Ejemplo:** `java.util.List` (colecciones) o `jakarta.servlet.http.HttpServlet` (web).
**En el proyecto:** ✅ Se usan varias (Servlet, EJB, JPA, CDI).

## Java EE / Jakarta EE

**Qué es:** conjunto de APIs para servicios empresariales: **transaccionalidad, seguridad, interoperabilidad**. Algunas: **JTA** (Java Transaction API), **JPA** (Java Persistence API), **JMS** (Java Message Service).

```java
// JMS: enviar un mensaje a una cola
jmsContext.createProducer().send(cola, "Nueva persona registrada");
```
**Actualidad:** Oracle lo cedió a Eclipse → **Jakarta EE** (paquete `jakarta.*` desde la v9). Vigente en banca, seguros, gobierno; Spring Boot y Quarkus usan muchas de sus APIs.
**En el proyecto:** ✅ Jakarta EE 10. JTA (transacciones del EJB) y JPA se usan; JMS ❌.

## Arquitectura multicapas en Java EE

| Capa | Tecnologías |
|---|---|
| Presentación | JSF, JSP o Servlets |
| Negocio | Enterprise JavaBeans (EJB), Web Services |
| Datos | JDBC / JPA |

**Actualidad:** sigue siendo la base (controller–service–repository en Spring).
**En el proyecto:** ✅ Presentación = Servlets+JSP; Negocio = `*ServiceImpl`; Datos = `*DaoImpl` con JPA.

## Enterprise JavaBeans (EJB)

**Qué es:** clase Java administrada por el contenedor, con características que la hacen más potente y robusta:

- Sus métodos son **transaccionales** (automáticamente).
- Pueden ser **remotos**.
- Facilitan la comunicación con la base de datos.
- Sus métodos pueden ser **seguros** (`@RolesAllowed`).

**Tipos:**
- **Stateless (`@Stateless`):** no guardan estado entre llamadas.
- **Stateful (`@Stateful`):** guardan estado por cliente.

```java
@Stateless
public class PersonaServiceImpl implements PersonaService {
    @Inject private PersonaDao personaDao;
    public List<Persona> listarPersonas() { return personaDao.finAllPersonas(); }
}

@Stateful
public class CarritoBean { private List<String> items = new ArrayList<>(); }
```
**Actualidad:** se usan en sistemas empresariales existentes; en proyectos nuevos suelen reemplazarse por beans CDI `@Transactional` o Spring.
**En el proyecto:** ✅ `PersonaServiceImpl`, `UsuarioServiceImpl`, `PersonaDaoImpl`, `UsuarioDaoImpl` son `@Stateless`; `PersonaServiceRemote` expone la interfaz `@Remote`. `@Stateful` no se usa.

## Inyección de dependencias en EJB: `@EJB` vs `@Inject`

- `@EJB`: inyecta específicamente EJBs.
- `@Inject` (**recomendada**): de CDI, inyecta cualquier bean, más flexible y con *type-safety*.

```java
@EJB  private PersonaService s1;   // forma antigua
@Inject private PersonaService s2; // forma recomendada
```
**Actualidad:** `@Inject` es el estándar (también en Spring).
**En el proyecto:** ✅ Solo `@Inject` (servlets → servicio → DAO).

## JNDI (Java Naming & Directory Interface)

**Qué es:** API que permite **ubicar componentes y recursos por nombre** en el servidor (por ejemplo GlassFish): EJBs, DataSources, colas JMS.

```java
Context ctx = new InitialContext();
PersonaServiceRemote svc = (PersonaServiceRemote)
    ctx.lookup("java:global/sga-jee-web_2.0/PersonaServiceImpl!fm.sga.jee.servicio.PersonaServiceRemote");
```
**Actualidad:** se usa sobre todo para DataSources y recursos; las referencias a EJB se resuelven con inyección.
**En el proyecto:** ✅ `persistence.xml` usa `<jta-data-source>jdbc/PersonaDb</jta-data-source>`, un nombre JNDI configurado en GlassFish.

## CDI (Context & Dependency Injection)

**Qué es:** especificación que permite **inyectar componentes** y gestionar su ciclo de vida mediante *scopes* (`@RequestScoped`, `@SessionScoped`, `@ApplicationScoped`).

```java
@ApplicationScoped
public class Reloj { public LocalDateTime ahora() { return LocalDateTime.now(); } }

@Inject Reloj reloj;
```
**Actualidad:** núcleo de Jakarta EE moderno y de Quarkus.
**En el proyecto:** ✅ `@Inject` en servlets y servicios; `WEB-INF/beans.xml` habilita CDI.

## JPA (Java Persistence API)

**Qué es:** estándar de persistencia de Java; implementa los conceptos de los frameworks **ORM** (*Object Relational Mapping*): mapea clases a tablas y objetos a filas, sin escribir SQL manual para lo básico.

```java
@Entity
public class Persona {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona") private Integer idPersona;
    private String nombre;
}

em.persist(persona);                              // INSERT
Persona p = em.find(Persona.class, 1);            // SELECT
em.merge(persona);                                // UPDATE
em.remove(em.merge(persona));                     // DELETE
em.createNamedQuery("Persona.findAll").getResultList();
```
**Actualidad:** estándar de facto en Java (Hibernate y EclipseLink; Spring Data JPA).
**En el proyecto:** ✅ Entidades `Persona`/`Usuario` (`@Entity`, `@NamedQueries`, `@OneToMany`), `@PersistenceContext(unitName = "personaPU")` en los DAOs y unidad `personaPU` en `persistence.xml` (EclipseLink en GlassFish).
