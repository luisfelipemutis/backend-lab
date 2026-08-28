package com.felipe.backendlab.architecture.layered;

import com.felipe.backendlab.architecture.layered.controller.BookController;

/*

# Layered architecture (arquitectura en capas)

Este ejemplo implementa un sistema sencillo de libreria para practicar
arquitectura en capas con un flujo pequeno y facil de seguir.

Organiza el codigo base en capas horizontales, donde cada capa tiene una
responsabilidad especifica y solo se comunica con las capas directamente inferiores.

Objetivo del ejemplo:
- separar responsabilidades;
- facilitar pruebas;
- simplificar mantenimiento;
- mostrar como fluye la informacion desde la presentacion hasta los datos.

[ Client Request ]
              │
              ▼
┌───────────────────────────┐
│  Presentation Layer       │ ──► Receives HTTP request, validates format,
│  (Controllers / Routers)  │     extracts payload, delegates to Service.
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│  Business Logic Layer     │ ──► Processes calculations, evaluates rules,
│  (Services)               │     enforces constraints, coordinates workflows.
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│  Data Access Layer        │ ──► Executes SQL queries or ORM commands,
│  (Repositories / DAOs)    │     abstracts the underlying database.
└─────────────┬─────────────┘
              │
              ▼
      [ Database System ]
*/

public class Main {

    public static void main(String[] args) {
        BookController bookController = new BookController();
        bookController.startApplication();
    }
}
