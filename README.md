# Backend Lab

Laboratorio personal para estudiar Java backend de forma practica. Este repositorio se usa para practicar conceptos de Java Core, OOP, colecciones, concurrencia, streams, principios SOLID, patrones de diseno y ejercicios de playground.

## Objetivo del proyecto

- Aprender conceptos de backend con evidencia en codigo.
- Consolidar fundamentos antes de profundizar en Spring Boot, Docker, CI/CD y arquitectura avanzada.
- Mantener historial de aprendizaje incremental por temas.

## Estado actual del repositorio (revision completa)

- Build tool: Maven ([pom.xml](pom.xml)).
- Version de Java configurada: 23.

## Convencion usada en este README

- Estado Implementada: la clase tiene logica o demostracion ejecutable.
- Estado Vacia: la clase existe como placeholder porque el concepto aun no se ha estudiado a profundidad.

## Inventario completo de clases

### Design Patterns

#### Package com.felipe.backendlab.designpatterns.behavioral

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| ObserverExample | [src/main/java/com/felipe/backendlab/designpatterns/behavioral/ObserverExample.java](src/main/java/com/felipe/backendlab/designpatterns/behavioral/ObserverExample.java) | Vacia | Placeholder de estudio |
| StrategyExample | [src/main/java/com/felipe/backendlab/designpatterns/behavioral/StrategyExample.java](src/main/java/com/felipe/backendlab/designpatterns/behavioral/StrategyExample.java) | Vacia | Placeholder de estudio |

#### Package com.felipe.backendlab.designpatterns.creational

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| BuilderExample | [src/main/java/com/felipe/backendlab/designpatterns/creational/BuilderExample.java](src/main/java/com/felipe/backendlab/designpatterns/creational/BuilderExample.java) | Vacia | Placeholder de estudio |
| FactoryExample | [src/main/java/com/felipe/backendlab/designpatterns/creational/FactoryExample.java](src/main/java/com/felipe/backendlab/designpatterns/creational/FactoryExample.java) | Vacia | Placeholder de estudio |
| SingletonExample | [src/main/java/com/felipe/backendlab/designpatterns/creational/SingletonExample.java](src/main/java/com/felipe/backendlab/designpatterns/creational/SingletonExample.java) | Vacia | Placeholder de estudio |

### Java Core

#### Package com.felipe.backendlab.javacore.collections

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| ArrayListExample | [src/main/java/com/felipe/backendlab/javacore/collections/ArrayListExample.java](src/main/java/com/felipe/backendlab/javacore/collections/ArrayListExample.java) | Implementada | Ejemplo funcional |
| HashMapExample | [src/main/java/com/felipe/backendlab/javacore/collections/HashMapExample.java](src/main/java/com/felipe/backendlab/javacore/collections/HashMapExample.java) | Implementada | Ejemplo funcional |
| HashSetExample | [src/main/java/com/felipe/backendlab/javacore/collections/HashSetExample.java](src/main/java/com/felipe/backendlab/javacore/collections/HashSetExample.java) | Vacia | Placeholder de estudio |
| LinkedListExample | [src/main/java/com/felipe/backendlab/javacore/collections/LinkedListExample.java](src/main/java/com/felipe/backendlab/javacore/collections/LinkedListExample.java) | Vacia | Placeholder de estudio |
| QueueExample | [src/main/java/com/felipe/backendlab/javacore/collections/QueueExample.java](src/main/java/com/felipe/backendlab/javacore/collections/QueueExample.java) | Vacia | Placeholder de estudio |
| SetsExample | [src/main/java/com/felipe/backendlab/javacore/collections/SetsExample.java](src/main/java/com/felipe/backendlab/javacore/collections/SetsExample.java) | Implementada | TreeSet y operaciones basicas |
| StackExample | [src/main/java/com/felipe/backendlab/javacore/collections/StackExample.java](src/main/java/com/felipe/backendlab/javacore/collections/StackExample.java) | Vacia | Placeholder de estudio |
| TreeMapExample | [src/main/java/com/felipe/backendlab/javacore/collections/TreeMapExample.java](src/main/java/com/felipe/backendlab/javacore/collections/TreeMapExample.java) | Vacia | Placeholder de estudio |

#### Package com.felipe.backendlab.javacore.concurrency

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| CompletableFutureExample | [src/main/java/com/felipe/backendlab/javacore/concurrency/CompletableFutureExample.java](src/main/java/com/felipe/backendlab/javacore/concurrency/CompletableFutureExample.java) | Vacia | Placeholder de estudio |
| ExecutorServiceExample | [src/main/java/com/felipe/backendlab/javacore/concurrency/ExecutorServiceExample.java](src/main/java/com/felipe/backendlab/javacore/concurrency/ExecutorServiceExample.java) | Vacia | Placeholder de estudio |
| RunnableExample | [src/main/java/com/felipe/backendlab/javacore/concurrency/RunnableExample.java](src/main/java/com/felipe/backendlab/javacore/concurrency/RunnableExample.java) | Vacia | Placeholder de estudio |
| SynchronizationExample | [src/main/java/com/felipe/backendlab/javacore/concurrency/SynchronizationExample.java](src/main/java/com/felipe/backendlab/javacore/concurrency/SynchronizationExample.java) | Vacia | Placeholder de estudio |
| ThreadExample | [src/main/java/com/felipe/backendlab/javacore/concurrency/ThreadExample.java](src/main/java/com/felipe/backendlab/javacore/concurrency/ThreadExample.java) | Vacia | Placeholder de estudio |

#### Package com.felipe.backendlab.javacore.controlflow

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| ForExample | [src/main/java/com/felipe/backendlab/javacore/controlflow/ForExample.java](src/main/java/com/felipe/backendlab/javacore/controlflow/ForExample.java) | Implementada | Estructuras de control |

#### Package com.felipe.backendlab.javacore.language

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| VarargsExample | [src/main/java/com/felipe/backendlab/javacore/language/VarargsExample.java](src/main/java/com/felipe/backendlab/javacore/language/VarargsExample.java) | Implementada | Varargs |

#### Package com.felipe.backendlab.javacore.oop

| Clase principal | Archivo | Tipos declarados en el archivo | Estado | Comentario |
|---|---|---|---|---|
| AbstractClassExample | [src/main/java/com/felipe/backendlab/javacore/oop/AbstractClassExample.java](src/main/java/com/felipe/backendlab/javacore/oop/AbstractClassExample.java) | AbstractClassExample, Animal2, Perro2, Gato2 | Implementada | Ejemplo OOP completo |
| ClassExample | [src/main/java/com/felipe/backendlab/javacore/oop/ClassExample.java](src/main/java/com/felipe/backendlab/javacore/oop/ClassExample.java) | ClassExample, Persona | Implementada | Clases y objetos |
| ClassInheritance | [src/main/java/com/felipe/backendlab/javacore/oop/ClassInheritance.java](src/main/java/com/felipe/backendlab/javacore/oop/ClassInheritance.java) | ClassInheritance, Animal, Perro | Implementada | Herencia |
| ClassPolymorphism | [src/main/java/com/felipe/backendlab/javacore/oop/ClassPolymorphism.java](src/main/java/com/felipe/backendlab/javacore/oop/ClassPolymorphism.java) | ClassPolymorphism, Animal1, Perro1, Cat1 | Implementada | Polimorfismo |
| ExampleSalesSystem | [src/main/java/com/felipe/backendlab/javacore/oop/ExampleSalesSystem.java](src/main/java/com/felipe/backendlab/javacore/oop/ExampleSalesSystem.java) | ExampleSalesSystem, Order, Product | Implementada | Mini dominio de ventas |
| FilesInJava | [src/main/java/com/felipe/backendlab/javacore/oop/FilesInJava.java](src/main/java/com/felipe/backendlab/javacore/oop/FilesInJava.java) | FilesInJava | Implementada | IO de archivos |
| InterfaceExample | [src/main/java/com/felipe/backendlab/javacore/oop/InterfaceExample.java](src/main/java/com/felipe/backendlab/javacore/oop/InterfaceExample.java) | InterfaceExample, PaymentMethod, CreditCardPayment, PayPalPayment | Implementada | Interfaces |
| JavaBeansExample | [src/main/java/com/felipe/backendlab/javacore/oop/JavaBeansExample.java](src/main/java/com/felipe/backendlab/javacore/oop/JavaBeansExample.java) | JavaBeansExample, UsuarioBean, Usuario | Implementada | JavaBeans y encapsulamiento |
| JavaExceptions | [src/main/java/com/felipe/backendlab/javacore/oop/JavaExceptions.java](src/main/java/com/felipe/backendlab/javacore/oop/JavaExceptions.java) | JavaExceptions | Implementada | Manejo basico de excepciones |
| JavaExceptionsExample | [src/main/java/com/felipe/backendlab/javacore/oop/JavaExceptionsExample.java](src/main/java/com/felipe/backendlab/javacore/oop/JavaExceptionsExample.java) | JavaExceptionsExample, Aritmetic | Implementada | Excepciones + finally |
| StaticAndDynamicContext | [src/main/java/com/felipe/backendlab/javacore/oop/StaticAndDynamicContext.java](src/main/java/com/felipe/backendlab/javacore/oop/StaticAndDynamicContext.java) | StaticAndDynamicContext, Person | Implementada | Contexto estatico y dinamico |
| StaticAndDynamicContextExId | [src/main/java/com/felipe/backendlab/javacore/oop/StaticAndDynamicContextExId.java](src/main/java/com/felipe/backendlab/javacore/oop/StaticAndDynamicContextExId.java) | StaticAndDynamicContextExId, Person1 | Implementada | Contexto estatico aplicado |

#### Package com.felipe.backendlab.javacore.streams

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| CollectExample | [src/main/java/com/felipe/backendlab/javacore/streams/CollectExample.java](src/main/java/com/felipe/backendlab/javacore/streams/CollectExample.java) | Vacia | Placeholder de estudio |
| FilterExample | [src/main/java/com/felipe/backendlab/javacore/streams/FilterExample.java](src/main/java/com/felipe/backendlab/javacore/streams/FilterExample.java) | Vacia | Placeholder de estudio |
| FlatMapExample | [src/main/java/com/felipe/backendlab/javacore/streams/FlatMapExample.java](src/main/java/com/felipe/backendlab/javacore/streams/FlatMapExample.java) | Vacia | Placeholder de estudio |
| MapExample | [src/main/java/com/felipe/backendlab/javacore/streams/MapExample.java](src/main/java/com/felipe/backendlab/javacore/streams/MapExample.java) | Vacia | Placeholder de estudio |
| ReduceExample | [src/main/java/com/felipe/backendlab/javacore/streams/ReduceExample.java](src/main/java/com/felipe/backendlab/javacore/streams/ReduceExample.java) | Vacia | Placeholder de estudio |

### Playground

El Playground es el patio de juegos o patio de experimentacion del repositorio: aqui se prueban ideas, se comparan enfoques y se construyen prototipos pequenos antes de formalizar conceptos en otras secciones.

Importante: snackMachine y snackMachineFile no son la misma implementacion.

#### Implementacion A: snackMachine (sin arquitectura por capas)

Package actual: com.felipe.backendlab.playground.snackMachine

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| Snack | [src/main/java/com/felipe/backendlab/playground/snackMachine/Snack.java](src/main/java/com/felipe/backendlab/playground/snackMachine/Snack.java) | Implementada | Entidad snack |
| Snacks | [src/main/java/com/felipe/backendlab/playground/snackMachine/Snacks.java](src/main/java/com/felipe/backendlab/playground/snackMachine/Snacks.java) | Implementada | Catalogo en memoria |
| SnacksMachine | [src/main/java/com/felipe/backendlab/playground/snackMachine/SnacksMachine.java](src/main/java/com/felipe/backendlab/playground/snackMachine/SnacksMachine.java) | Implementada | App consola simple, sin capas |

#### Implementacion B: snackMachineFile (con arquitectura en capas)

Package actual: com.felipe.backendlab.playground.snackMachineFile

Subpaquetes actuales:
- com.felipe.backendlab.playground.snackMachineFile.domain
- com.felipe.backendlab.playground.snackMachineFile.service
- com.felipe.backendlab.playground.snackMachineFile.presentation

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| Snack | [src/main/java/com/felipe/backendlab/playground/snackMachineFile/domain/Snack.java](src/main/java/com/felipe/backendlab/playground/snackMachineFile/domain/Snack.java) | Implementada | Capa domain |
| SnacksMachine | [src/main/java/com/felipe/backendlab/playground/snackMachineFile/presentation/SnacksMachine.java](src/main/java/com/felipe/backendlab/playground/snackMachineFile/presentation/SnacksMachine.java) | Implementada | Capa presentation |
| IServiceSnacks | [src/main/java/com/felipe/backendlab/playground/snackMachineFile/service/IServiceSnacks.java](src/main/java/com/felipe/backendlab/playground/snackMachineFile/service/IServiceSnacks.java) | Implementada | Contrato de la capa service |
| ServiceSnacksFiles | [src/main/java/com/felipe/backendlab/playground/snackMachineFile/service/ServiceSnacksFiles.java](src/main/java/com/felipe/backendlab/playground/snackMachineFile/service/ServiceSnacksFiles.java) | Implementada | Servicio con persistencia en archivo |
| ServiceSnacksList | [src/main/java/com/felipe/backendlab/playground/snackMachineFile/service/ServiceSnacksList.java](src/main/java/com/felipe/backendlab/playground/snackMachineFile/service/ServiceSnacksList.java) | Implementada | Servicio en memoria |

### SOLID

#### Package com.felipe.backendlab.solid

| Clase | Archivo | Estado | Comentario |
|---|---|---|---|
| DependencyInversionExample | [src/main/java/com/felipe/backendlab/solid/DependencyInversionExample.java](src/main/java/com/felipe/backendlab/solid/DependencyInversionExample.java) | Vacia | Placeholder de estudio |
| InterfaceSegregationExample | [src/main/java/com/felipe/backendlab/solid/InterfaceSegregationExample.java](src/main/java/com/felipe/backendlab/solid/InterfaceSegregationExample.java) | Vacia | Placeholder de estudio |
| LiskovExample | [src/main/java/com/felipe/backendlab/solid/LiskovExample.java](src/main/java/com/felipe/backendlab/solid/LiskovExample.java) | Vacia | Placeholder de estudio |
| OpenClosedExample | [src/main/java/com/felipe/backendlab/solid/OpenClosedExample.java](src/main/java/com/felipe/backendlab/solid/OpenClosedExample.java) | Vacia | Placeholder de estudio |
| SingleResponsibilityExample | [src/main/java/com/felipe/backendlab/solid/SingleResponsibilityExample.java](src/main/java/com/felipe/backendlab/solid/SingleResponsibilityExample.java) | Vacia | Placeholder de estudio |

