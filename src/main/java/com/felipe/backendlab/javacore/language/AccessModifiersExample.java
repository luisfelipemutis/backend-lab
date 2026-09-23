package com.felipe.backendlab.javacore.language;

/**
 * Modificadores de acceso en Java.
 *
 * Controlan la visibilidad de clases, atributos, constructores y métodos.
 *
 * Para clases:
 * - public: accesible desde cualquier clase
 * - default (sin modificador): accesible desde el mismo paquete
 *
 * Para atributos y métodos:
 * - public: accesible desde cualquier clase
 * - private: accesible solo desde la misma clase
 * - default: accesible desde el mismo paquete
 * - protected: accesible desde el mismo paquete y desde subclases
 */
public class AccessModifiersExample {
    public static void main(String[] args) {
        PublicPerson person = new PublicPerson();
        person.name = "Ana";
        person.setAge(28);
        person.showInfo();

        System.out.println();
        System.out.println("=== Acceso a atributos de diferentes niveles ===");
        System.out.println("Nombre publico: " + person.name);
        System.out.println("Edad privada via getter: " + person.getAge());

        DefaultPerson defaultPerson = new DefaultPerson();
        defaultPerson.city = "Bogota";
        defaultPerson.showCity();

        ProtectedPerson protectedPerson = new ProtectedPerson();
        protectedPerson.country = "Colombia";
        protectedPerson.showCountry();
    }

    public static class PublicPerson {
        public String name;
        private int age;
        protected String country;
        String city;

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void showInfo() {
            System.out.println("Persona: " + name + ", edad=" + age);
        }
    }

    static class DefaultPerson {
        String city;

        public void showCity() {
            System.out.println("Ciudad default: " + city);
        }
    }

    protected static class ProtectedPerson {
        protected String country;

        protected void showCountry() {
            System.out.println("Pais protected: " + country);
        }
    }

    private static class PrivatePerson {
        private String secret;

        private void showSecret() {
            System.out.println("Secreto privado: " + secret);
        }
    }
}
