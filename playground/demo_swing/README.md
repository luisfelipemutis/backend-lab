# demo_swing

Playground para practicar **Java Swing** y construcción de interfaces gráficas de escritorio (GUI).

## ¿Qué es Swing?

**Swing** es una librería de Java para crear aplicaciones gráficas (GUI), también conocidas como aplicaciones de escritorio.

En este proyecto se están practicando componentes como:

- `JFrame` (ventana principal)
- `JPanel` (contenedores)
- `JLabel` (texto en pantalla)
- `JTextField` (entrada de texto)
- `JPasswordField` (entrada de contraseña)
- `JButton` (acciones)

## Enfoque MVC aplicado en Swing

Swing permite organizar el proyecto con el patrón **MVC**:

- **Vista**: formularios con componentes visuales (`.form`, `JFrame`, `JPanel`, etc.).
- **Controlador**: eventos y lógica de interacción (`ActionListener`, `KeyListener`, validaciones).
- **Modelo**: clases del dominio del problema (datos y reglas de negocio).

> En este playground el foco actual está en **Vista + Controlador** para entender eventos y comportamiento de UI.

## Estructura actual del proyecto

- `src/main/java/Forma.java`  
  Ejemplo de formulario que replica en tiempo real el texto escrito en un `JTextField` hacia un `JLabel` usando eventos de teclado.

- `src/main/java/LoginForm.java`  
  Ejemplo de formulario de login con validación simple y mensajes con `JOptionPane`.

- `src/main/java/*.form`  
  Diseños de interfaz creados con el UI Designer de IntelliJ.

## Dependencias utilizadas

### FlatLaf (modo oscuro)

En `pom.xml` se usa:

- **GroupId**: `com.formdev`
- **ArtifactId**: `flatlaf`
- **Version**: `3.0`

FlatLaf se usa para aplicar un **look and feel moderno** en modo oscuro con:

```java
FlatDarculaLaf.setup();
```

Esto está aplicado en los `main` de `Forma` y `LoginForm`.

## Requisitos

- Java 23
- Maven 3.x
- IntelliJ IDEA (recomendado para editar `.form`)

## Ejecutar

Desde IntelliJ, ejecuta cualquiera de estas clases:

- `Forma`
- `LoginForm`

O con Maven (compilar):

```bash
mvn clean compile
```

## Objetivo del playground

Aprender Swing de forma práctica, entendiendo:

1. creación de ventanas y componentes,
2. manejo de eventos de UI,
3. separación básica por responsabilidades (MVC),
4. personalización visual con `LookAndFeel` (FlatLaf dark mode).