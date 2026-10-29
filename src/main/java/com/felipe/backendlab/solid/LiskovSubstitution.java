package com.felipe.backendlab.solid;

/**
 * Ejemplo del principio de sustitución de Liskov (LSP).
 *
 * <p>Una implementación que se usa donde se espera un tipo debe respetar lo
 * que ese tipo promete, sin sorpresas ni excepciones especiales. Por eso,
 * aquí las aves que vuelan implementan FlyingBird, mientras que el pingüino
 * no se hace pasar por un ave voladora. Así, cualquier FlyingBird puede
 * sustituir a otra en FlightRoutine.
 *
 */
public class LiskovSubstitution {

    public static void main(String[] args) {
        FlightRoutine flightRoutine = new FlightRoutine();
        flightRoutine.startFlight(new Eagle());
        flightRoutine.startFlight(new Sparrow());

        Penguin penguin = new Penguin();
        penguin.move();
        penguin.swim();
    }

    /**
     * Comportamiento que todas las aves del ejemplo pueden cumplir.
     */
    interface Bird {
        void move();
    }

    /**
     * Contrato para aves que sí pueden volar.
     */
    interface FlyingBird extends Bird {
        void fly();
    }

    static class Eagle implements FlyingBird {
        @Override
        public void move() {
            System.out.println("El águila se mueve.");
        }

        @Override
        public void fly() {
            System.out.println("El águila vuela alto.");
        }
    }

    static class Sparrow implements FlyingBird {
        @Override
        public void move() {
            System.out.println("El gorrión se mueve.");
        }

        @Override
        public void fly() {
            System.out.println("El gorrión vuela entre los árboles.");
        }
    }

    static class Penguin implements Bird {
        @Override
        public void move() {
            System.out.println("El pingüino camina.");
        }

        void swim() {
            System.out.println("El pingüino nada.");
        }
    }

    /**
     * Solo acepta aves que cumplen el contrato de vuelo.
     */
    static class FlightRoutine {
        void startFlight(FlyingBird bird) {
            bird.fly();
        }
    }
}
