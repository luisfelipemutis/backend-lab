package com.felipe.backendlab.javacore.language;

/**
 * Modificadores no de acceso en Java.
 *
 * Los modificadores no de acceso cambian el comportamiento de clases, atributos o métodos
 * sin cambiar su visibilidad.
 *
 * Clase:
 * - final: no puede ser heredada
 * - abstract: no puede ser instanciada directamente
 *
 * Atributos y métodos:
 * - final: no se puede modificar ni sobrescribir
 * - static: pertenece a la clase, no al objeto
 * - abstract: solo en métodos de clases abstractas
 * - transient: no se serializa
 * - synchronized: un hilo a la vez
 * - volatile: lectura directa desde memoria principal
 */
public class NonAccessModifiersExample {
    public static void main(String[] args) {
        System.out.println("=== static ===");
        System.out.println("Pi: " + MathUtil.PI);
        System.out.println("Suma: " + MathUtil.add(10, 5));

        System.out.println("\n=== final ===");
        BankAccount account = new BankAccount("12345");
        System.out.println("Cuenta: " + account.getNumber());

        System.out.println("\n=== abstract ===");
        Animal dog = new Dog();
        dog.sound();

        System.out.println("\n=== transient ===");
        UserProfile profile = new UserProfile("alice", "secret123");
        System.out.println(profile);

        System.out.println("\n=== synchronized + volatile ===");
        SharedCounter counter = new SharedCounter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Valor final del contador: " + counter.getValue());
    }

    static class MathUtil {
        static final double PI = 3.14159;

        static int add(int a, int b) {
            return a + b;
        }
    }

    final static class BankAccount {
        private final String number;

        BankAccount(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }
    }

    abstract static class Animal {
        abstract void sound();
    }

    static class Dog extends Animal {
        @Override
        void sound() {
            System.out.println("El perro hace guau");
        }
    }

    static class UserProfile {
        private String username;
        private transient String password;

        UserProfile(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString() {
            return "UserProfile{username='" + username + "', password='" + password + "'}";
        }
    }

    static class SharedCounter {
        private volatile int value = 0;

        public synchronized void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }
    }
}
