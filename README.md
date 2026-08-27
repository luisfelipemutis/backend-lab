# Backend Lab

Laboratorio personal de estudio y práctica de **Software Engineering**, enfocado actualmente en el desarrollo de habilidades como **Backend Software Engineer con Java**.

El objetivo del proyecto en esta etapa es consolidar fundamentos sólidos en Java y su ecosistema, antes de expandirse a otras tecnologías.

El repositorio contiene implementaciones prácticas, experimentos, ejercicios y ejemplos ejecutables relacionados principalmente con Java, backend, arquitectura, patrones de diseño, algoritmos y buenas prácticas de ingeniería de software.

---

# 🎯 Objetivo

Este repositorio funciona como un **laboratorio técnico de aprendizaje enfocado en Java**.

La finalidad no es construir una aplicación de negocio específica, sino estudiar conceptos de ingeniería de software mediante implementaciones pequeñas, aisladas y ejecutables.

Los objetivos principales son:

* Dominar los fundamentos de **Java como lenguaje principal**.
* Comprender conceptos de programación y arquitectura.
* Convertir conceptos teóricos en implementaciones prácticas.
* Experimentar con diferentes alternativas de diseño.
* Resolver ejercicios relacionados con los conceptos estudiados.
* Validar el conocimiento mediante pruebas y experimentación.
* Preparar conocimientos para entrevistas técnicas.
* Practicar Clean Code y SOLID.
* Analizar performance, escalabilidad y mantenibilidad.
* Mantener un historial práctico del aprendizaje mediante Git.

---

# 🧠 Filosofía de aprendizaje

El proyecto sigue un ciclo de aprendizaje práctico:

```text
ESTUDIAR
    ↓
CODIFICAR
    ↓
PRACTICAR
    ↓
PROBAR
    ↓
ANALIZAR
    ↓
VOLVER A ESTUDIAR
    ↓
CODIFICAR
    ↓
...
```

El objetivo no es simplemente leer un concepto y marcarlo como aprendido.

Un concepto se considera consolidado cuando existe evidencia práctica de que puede:

* Explicarse con claridad.
* Implementarse desde cero.
* Utilizarse en diferentes escenarios.
* Modificarse sin depender de una solución previamente copiada.
* Probarse y verificarse.
* Compararse con alternativas.
* Justificar técnicamente cuándo utilizarlo y cuándo evitarlo.

### Principio fundamental

> **Estudiar → Codificar → Practicar → Probar → Analizar → Repetir.**

El código es parte del proceso de aprendizaje y debe servir simultáneamente como:

* implementación;
* experimento;
* referencia;
* evidencia del aprendizaje;
* material de repaso.

---

# 📊 Progreso

## Estados

| Símbolo | Estado      |
| ------- | ----------- |
| `[ ]`   | Pendiente   |
| `[~]`   | En estudio  |
| `[x]`   | Consolidado |

### Significado

**Pendiente**

El concepto todavía no ha sido estudiado.

**En estudio**

El concepto está siendo estudiado, pero todavía se encuentra en proceso de comprensión, implementación o experimentación.

**Consolidado**

El concepto ha sido estudiado, implementado, practicado y probado, y existe suficiente comprensión para explicarlo y utilizarlo de forma independiente.

> `[x]` no significa simplemente "ya lo vi". Significa que el concepto ha pasado por el ciclo práctico de aprendizaje.

---

# ☕ Java Core

## OOP — Object-Oriented Programming

* [x] Classes

    * Código: [`ClassExample.java`](src/main/java/com/felipe/backendlab/javacore/oop/ClassExample.java)

* [x] Inheritance

    * Código: [`ClassInheritance.java`](src/main/java/com/felipe/backendlab/javacore/oop/ClassInheritance.java)

* [x] Polymorphism

    * Código: [`ClassPolymorphism.java`](src/main/java/com/felipe/backendlab/javacore/oop/ClassPolymorphism.java)

* [ ] Encapsulation

* [ ] Abstraction

* [ ] Composition

* [x] Interfaces
- Código: [`InterfaceExample.java`](src/main/java/com/felipe/backendlab/javacore/oop/InterfaceExample.java)

* [ ] Association

* [ ] Aggregation

* [x] JavaBeans
- Código: [`JavaBeansExample.java`](src/main/java/com/felipe/backendlab/javacore/oop/JavaBeansExample.java)

* [x] JavaExceptions
- Código: [`JavaExceptions.java`](src/main/java/com/felipe/backendlab/javacore/oop/JavaExceptions.java)
- Código: [`JavaExceptionsExample.java`](src/main/java/com/felipe/backendlab/javacore/oop/JavaExceptionsExample.java)

---

## Collections
- Package: [`collections`](src/main/java/com/felipe/backendlab/javacore/collections)

* [x] ArrayList

    * Código: [`ArrayListExample.java`](src/main/java/com/felipe/backendlab/javacore/collections/ArrayListExample.java)

* [x] HashSet

    * Código: [`SetsExample.java`](src/main/java/com/felipe/backendlab/javacore/collections/SetsExample.java)

* [] HashMap

    * Código: [`HashMapExample.java`](src/main/java/com/felipe/backendlab/javacore/collections/HashMapExample.java)

* [] LinkedList

    * Código: [`LinkedListExample.java`](src/main/java/com/felipe/backendlab/javacore/collections/LinkedListExample.java)

* [x] Queue

    * Código: [`QueueExample.java`](src/main/java/com/felipe/backendlab/javacore/collections/QueueExample.java)

* [x] Stack

    * Código: [`StackExample.java`](src/main/java/com/felipe/backendlab/javacore/collections/StackExample.java)

* [x] TreeMap

    * Código: [`TreeMapExample.java`](src/main/java/com/felipe/backendlab/javacore/collections/TreeMapExample.java)

* [ ] TreeSet

* [ ] PriorityQueue

* [ ] Deque

* [ ] ConcurrentHashMap

---

## Concurrency

* [x] Thread

    * Código: [`ThreadExample.java`](src/main/java/com/felipe/backendlab/javacore/concurrency/ThreadExample.java)

* [x] Runnable

    * Código: [`RunnableExample.java`](src/main/java/com/felipe/backendlab/javacore/concurrency/RunnableExample.java)

* [x] ExecutorService

    * Código: [`ExecutorServiceExample.java`](src/main/java/com/felipe/backendlab/javacore/concurrency/ExecutorServiceExample.java)

* [x] CompletableFuture

    * Código: [`CompletableFutureExample.java`](src/main/java/com/felipe/backendlab/javacore/concurrency/CompletableFutureExample.java)

* [x] Synchronization

    * Código: [`SynchronizationExample.java`](src/main/java/com/felipe/backendlab/javacore/concurrency/SynchronizationExample.java)

* [ ] Callable

* [ ] Future

* [ ] Locks

* [ ] Atomic Variables

* [ ] Concurrent Collections

* [ ] Race Conditions

* [ ] Deadlocks

* [ ] Thread Pools

* [ ] Virtual Threads

---

## Streams

* [x] map

    * Código: [`MapExample.java`](src/main/java/com/felipe/backendlab/javacore/streams/MapExample.java)

* [x] filter

    * Código: [`FilterExample.java`](src/main/java/com/felipe/backendlab/javacore/streams/FilterExample.java)

* [x] flatMap

    * Código: [`FlatMapExample.java`](src/main/java/com/felipe/backendlab/javacore/streams/FlatMapExample.java)

* [x] reduce

    * Código: [`ReduceExample.java`](src/main/java/com/felipe/backendlab/javacore/streams/ReduceExample.java)

* [x] collect

    * Código: [`CollectExample.java`](src/main/java/com/felipe/backendlab/javacore/streams/CollectExample.java)

* [ ] sorted

* [ ] distinct

* [ ] peek

* [ ] groupingBy

* [ ] partitioningBy

* [ ] Optional

* [ ] Parallel Streams

---

## Language

Características propias del lenguaje Java.

* [x] Varargs

    * Código: [`VarargsExample.java`](src/main/java/com/felipe/backendlab/javacore/language/VarargsExample.java)

* [ ] Generics

* [ ] Enums

* [ ] Annotations

* [ ] Records

* [ ] Sealed Classes

* [ ] Nested Classes

* [ ] Inner Classes

* [ ] Static Members

* [ ] Final

* [ ] Exception Handling

* [ ] Checked vs Unchecked Exceptions

* [ ] Autoboxing / Unboxing

* [ ] Immutable Objects

---

# 🧩 Design Patterns

Patrones de diseño orientados a resolver problemas recurrentes de diseño de software.

## Creational Patterns

* [x] Builder

    * Código: [`BuilderExample.java`](src/main/java/com/felipe/backendlab/designpatterns/BuilderExample.java)

* [x] Factory

    * Código: [`FactoryExample.java`](src/main/java/com/felipe/backendlab/designpatterns/FactoryExample.java)

* [x] Singleton

    * Código: [`SingletonExample.java`](src/main/java/com/felipe/backendlab/designpatterns/SingletonExample.java)

* [ ] Abstract Factory

* [ ] Prototype

## Structural Patterns

* [ ] Adapter

* [ ] Decorator

* [ ] Facade

* [ ] Proxy

* [ ] Composite

* [ ] Bridge

* [ ] Flyweight

## Behavioral Patterns

* [x] Observer

    * Código: [`ObserverExample.java`](src/main/java/com/felipe/backendlab/designpatterns/ObserverExample.java)

* [x] Strategy

    * Código: [`StrategyExample.java`](src/main/java/com/felipe/backendlab/designpatterns/StrategyExample.java)

* [ ] Chain of Responsibility

* [ ] Command

* [ ] Template Method

* [ ] State

* [ ] Mediator

* [ ] Iterator

* [ ] Visitor

* [ ] Memento

* [ ] Interpreter

---

# 🧱 SOLID

Principios utilizados para desarrollar software mantenible, extensible y con bajo acoplamiento.

* [x] Single Responsibility Principle

    * Código: [`SingleResponsibilityExample.java`](src/main/java/com/felipe/backendlab/solid/SingleResponsibilityExample.java)

* [x] Open/Closed Principle

    * Código: [`OpenClosedExample.java`](src/main/java/com/felipe/backendlab/solid/OpenClosedExample.java)

* [x] Liskov Substitution Principle

    * Código: [`LiskovExample.java`](src/main/java/com/felipe/backendlab/solid/LiskovExample.java)

* [x] Interface Segregation Principle

    * Código: [`InterfaceSegregationExample.java`](src/main/java/com/felipe/backendlab/solid/InterfaceSegregationExample.java)

* [x] Dependency Inversion Principle

    * Código: [`DependencyInversionExample.java`](src/main/java/com/felipe/backendlab/solid/DependencyInversionExample.java)

---

# 🧮 Algorithms

Algoritmos y estructuras utilizadas para fortalecer la resolución de problemas, el análisis de complejidad y el pensamiento algorítmico.

* [ ] Big O Notation

* [ ] Arrays

* [ ] Strings

* [ ] Searching

* [ ] Sorting

* [ ] Recursion

* [ ] Two Pointers

* [ ] Sliding Window

* [ ] Hashing

* [ ] Stack / Queue Problems

* [ ] Trees

* [ ] Graphs

* [ ] Dynamic Programming

---

# 🏗️ Architecture

Conceptos relacionados con arquitectura y diseño de sistemas.

* [ ] Layered Architecture

* [ ] Clean Architecture

* [ ] Hexagonal Architecture

* [ ] Onion Architecture

* [ ] Domain-Driven Design

* [ ] Modular Monolith

* [ ] Microservices

* [ ] Event-Driven Architecture

* [ ] CQRS

* [ ] Event Sourcing

* [ ] Integration Patterns

* [ ] API Gateway

* [ ] Service Discovery

* [ ] Circuit Breaker

* [ ] Saga Pattern

* [ ] Outbox Pattern

---

# 🌱 Spring / Spring Boot

Área destinada a experimentos y conceptos relacionados con Spring y Spring Boot.

* [ ] Spring Core

* [ ] Dependency Injection

* [ ] IoC Container

* [ ] Bean Lifecycle

* [ ] Spring Boot

* [ ] Spring MVC

* [ ] Spring Security

* [ ] Spring Data JPA

* [ ] Hibernate

* [ ] Transactions

* [ ] Validation

* [ ] Exception Handling

* [ ] REST APIs

* [ ] Actuator

---

# 🗄️ Databases

Conceptos relacionados con bases de datos relacionales y persistencia.

* [ ] Relational Modeling

* [ ] Normalization

* [ ] Denormalization

* [ ] Primary Keys

* [ ] Foreign Keys

* [ ] Indexes

* [ ] Composite Indexes

* [ ] Query Optimization

* [ ] Execution Plans

* [ ] Transactions

* [ ] ACID

* [ ] Isolation Levels

* [ ] Locks

* [ ] PostgreSQL

* [ ] MySQL

* [ ] SQL Server

---

# 🧪 Testing

* [ ] Unit Testing

* [ ] Integration Testing

* [ ] JUnit

* [ ] Mockito

* [ ] Testcontainers

* [ ] Contract Testing

* [ ] Test Pyramid

* [ ] Test Doubles

* [ ] Mock vs Stub vs Spy

---

# 🐳 DevOps

* [ ] Docker

* [ ] Docker Compose

* [ ] Git

* [ ] Branching Strategies

* [ ] CI/CD

* [ ] Logging

* [ ] Monitoring

* [ ] Observability

* [ ] Metrics

* [ ] Distributed Tracing

---

# 🎯 Interview

Preguntas y ejercicios orientados a preparación para entrevistas técnicas.

## Java

* [ ] Java Core

* [ ] OOP

* [ ] Collections

* [ ] Streams

* [ ] Concurrency

* [ ] JVM

* [ ] Memory Management

## Spring

* [ ] Spring Core

* [ ] Spring Boot

* [ ] Spring Security

* [ ] Spring Data

* [ ] Transactions

## SQL

* [ ] Queries

* [ ] Joins

* [ ] Indexes

* [ ] Transactions

* [ ] Optimization

## Architecture

* [ ] Design Patterns

* [ ] SOLID

* [ ] Clean Architecture

* [ ] System Design

* [ ] Scalability

* [ ] Distributed Systems

---

# 🧪 Playground

Área destinada a experimentos libres.

A diferencia de las demás secciones, aquí se permite combinar múltiples conceptos.

Ejemplos:

```text
playground/
├── java/
├── spring/
├── architecture/
└── database/
```

El `playground` puede utilizarse para:

* Probar una idea.
* Comparar implementaciones.
* Realizar experimentos de performance.
* Investigar el comportamiento de una API.
* Combinar varios conceptos.
* Crear prototipos antes de llevarlos a una implementación más estructurada.

> Los experimentos que se conviertan en conocimiento consolidado deberían posteriormente trasladarse a su categoría correspondiente.

---

# snackMachine
```text
> Proyecto maquina de snacks.

Conceptos
Este proyecto implmenta varios conceptos de OPP y collections.
Es un proyecto pequeño, su utilizar probar conceptos.
```


# 📚 Convención para nuevos ejemplos

Los ejemplos deben utilizar nombres descriptivos.

Formato recomendado:

```text
<Concept>Example.java
```

Ejemplos:

```text
VarargsExample.java
HashMapExample.java
CompletableFutureExample.java
BuilderExample.java
DependencyInversionExample.java
```

Cada ejemplo debe intentar demostrar **un único concepto principal**.

Cuando sea necesario combinar múltiples conceptos, utilizar `playground`.

---

# 💡 Cómo estudiar un nuevo concepto

Cada nuevo concepto debería seguir un ciclo práctico.

## 1. Estudiar

Comprender:

* Qué problema resuelve.
* Qué es.
* Cómo funciona.
* Cuándo utilizarlo.
* Cuándo evitarlo.
* Sus ventajas y desventajas.
* Sus implicaciones de performance y diseño.

## 2. Codificar

Crear un ejemplo pequeño y aislado que permita observar el concepto.

Ejemplo:

```text
javacore/
└── language/
    └── VarargsExample.java
```

## 3. Practicar

Modificar el ejemplo, crear variantes y resolver ejercicios relacionados.

El objetivo es dejar de depender del ejemplo original.

## 4. Probar

Validar el comportamiento mediante:

* ejecución manual;
* casos límite;
* JUnit;
* experimentos;
* benchmarks cuando sea necesario.

## 5. Analizar

Preguntarse:

* ¿Entiendo realmente lo que está sucediendo?
* ¿Podría implementarlo nuevamente sin consultar el ejemplo?
* ¿Puedo explicar por qué funciona?
* ¿Conozco sus limitaciones?
* ¿Sé cuándo utilizarlo y cuándo no?
* ¿Puedo responder una pregunta de entrevista sobre el tema?

## 6. Repetir

Si alguna respuesta es negativa:

```text
Analizar
   ↓
Identificar la brecha
   ↓
Volver a estudiar
   ↓
Modificar el código
   ↓
Practicar
   ↓
Probar nuevamente
```

---

# 📁 Estructura actual

```text
backend-lab/
│
├── README.md
├── .gitignore
├── pom.xml
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/felipe/backendlab/
    │   │       ├── algorithms/
    │   │       ├── designpatterns/
    │   │       ├── javacore/
    │   │       │   ├── abstraction/
    │   │       │   ├── collections/
    │   │       │   ├── concurrency/
    │   │       │   ├── language/
    │   │       │   ├── oop/
    │   │       │   └── streams/
    │   │       ├── interview/
    │   │       ├── playground/
    │   │       └── solid/
    │   │
    │   └── resources/
    │
    └── test/
        └── java/
```

---

# 🚀 Roadmap

El laboratorio evoluciona progresivamente con foco inicial en **Java Backend Development**:

```text
Java Core
    ↓
Clean Code + SOLID
    ↓
Design Patterns
    ↓
Testing
    ↓
Spring Boot
    ↓
Persistence / JPA / Hibernate
    ↓
REST APIs / Security
    ↓
Architecture
    ↓
Concurrency / Performance
    ↓
Distributed Systems
    ↓
Docker / CI/CD / Observability
    ↓
System Design
```

La prioridad no es completar rápidamente todas las categorías.

La prioridad es alcanzar **comprensión profunda y capacidad de aplicación práctica**.

---

# 📌 Regla principal

> **No se considera aprendido un concepto únicamente por haberlo leído.**

El objetivo es avanzar desde:

```text
Leer
 ↓
Comprender
 ↓
Codificar
 ↓
Practicar
 ↓
Probar
 ↓
Analizar
 ↓
Explicar
 ↓
Aplicar
```

hasta poder utilizar el conocimiento de forma independiente en problemas reales.

> **El código es la documentación práctica del aprendizaje.**
