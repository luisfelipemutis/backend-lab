package com.felipe.backendlab.javacore.language;

/**
 * La palabra reservada static en Java se usa para atributos y métodos que pertenecen
 * a la clase y no a una instancia individual.
 *
 * Características:
 * - Se puede acceder sin crear un objeto
 * - Se comparte entre todas las instancias
 * - Se inicializa una sola vez al cargar la clase
 */
public class StaticKeywordExample {
    public static void main(String[] args) {
        System.out.println("Sin instanciar: " + Student.getTotalStudents());

        Student student1 = new Student("Ana");
        Student student2 = new Student("Luis");
        Student student3 = new Student("Sofia");

        System.out.println("Estudiante 1: " + student1);
        System.out.println("Estudiante 2: " + student2);
        System.out.println("Estudiante 3: " + student3);
        System.out.println("Total usando variable static: " + Student.totalStudents);
        System.out.println("Total usando metodo static: " + Student.getTotalStudents());

        System.out.println("\n=== Bloque static ===");
        System.out.println("Sistema listo. Colección activa: " + Config.PROVIDER);
    }

    static class Student {
        private String name;
        private static int totalStudents = 0;

        Student(String name) {
            this.name = name;
            totalStudents++;
        }

        public static int getTotalStudents() {
            return totalStudents;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "'}";
        }
    }

    static class Config {
        static final String PROVIDER = "MySQL";

        static {
            System.out.println("Se ejecuta el bloque static de Config");
        }
    }
}
