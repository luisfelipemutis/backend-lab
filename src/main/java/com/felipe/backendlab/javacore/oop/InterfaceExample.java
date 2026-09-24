package com.felipe.backendlab.javacore.oop;

/**
 * INTERFACE
 * <p>
 * Es otra forma de abstracción en Java
 * Una interface define un contrato que las clases pueden implementar.
 * <p>
 * Su objetivo principal es definir QUÉ debe hacer una clase sin obligar
 * a que todas las implementaciones utilicen la misma forma de hacerlo.
 * <p>
 * Sintaxis:
 * <p>
 * public interface PaymentMethod {
 * void pay(double amount);
 * }
 * <p>
 * Una clase implementa una interface utilizando implements:
 * <p>
 * public class CreditCardPayment implements PaymentMethod {
 *
 * @Override public void pay(double amount) {
 * System.out.println("Paying with credit card");
 * }
 * }
 * <p>
 * CARACTERÍSTICAS:
 * <p>
 * - Una clase puede implementar múltiples interfaces.
 * - Una interface puede ser utilizada como tipo de referencia.
 * - Los métodos abstractos de una interface deben ser implementados por una clase concreta.
 * - Una interface puede contener métodos abstractos.
 * - Desde Java 8 puede contener métodos default y static.
 * - Desde Java 9 puede contener métodos private.
 * - Las variables declaradas en una interface son implícitamente public, static y final.
 * <p>
 * POLIMORFISMO:
 * <p>
 * Una variable cuyo tipo es una interface puede contener cualquier objeto
 * cuya clase implemente dicha interface.
 * <p>
 * PaymentMethod payment = new CreditCardPayment();
 * payment.pay(100);
 * <p>
 * Esto permite que el código dependa de una abstracción y no de una
 * implementación concreta.
 * <p>
 * CUÁNDO UTILIZAR:
 * <p>
 * - Cuando queremos definir un contrato.
 * - Cuando diferentes clases pueden realizar una misma operación de diferentes maneras.
 * - Cuando necesitamos desacoplar una clase de una implementación concreta.
 * - Cuando queremos facilitar testing mediante implementaciones alternativas o mocks.
 * - Cuando una clase necesita implementar múltiples comportamientos.
 * <p>
 * CUÁNDO NO UTILIZAR:
 * <p>
 * - No crear interfaces únicamente por costumbre.
 * - No crear una interface para una clase cuando no existe una necesidad
 * real de abstraer o desacoplar el comportamiento.
 * - Si necesitamos compartir estado y una implementación considerable entre
 * clases relacionadas, una abstract class puede ser más apropiada.
 * <p>
 * BUENAS PRÁCTICAS:
 * <p>
 * - Diseñar interfaces pequeñas y cohesivas.
 * - Aplicar Interface Segregation Principle cuando corresponda.
 * - Hacer que las interfaces representen capacidades o contratos claros.
 * - Preferir depender de interfaces cuando exista una necesidad real de desacoplamiento.
 * - Utilizar nombres que representen comportamiento o capacidad.
 * <p>
 * INTERFACE VS ABSTRACT CLASS:
 * <p>
 * Una interface representa principalmente un contrato.
 * <p>
 * Una abstract class puede representar una abstracción con estado,
 * comportamiento compartido, constructores y métodos concretos.
 * <p>
 * La elección depende del problema de diseño y no de una regla que indique
 * que una opción siempre sea mejor que la otra.
 * <p>
 * CONCEPTOS RELACIONADOS:
 * <p>
 * - Abstraction
 * - Polymorphism
 * - Inheritance
 * - Abstract Class
 * - SOLID
 * - Dependency Inversion Principle
 * - Dependency Injection
 */
public class InterfaceExample {

    public static void main(String[] args) {
        PaymentMethod payment = new CreditCardPayment();
        payment.pay(11.22);
        payment.showTotal();
        payment = new PayPalPayment();
        payment.pay(33.22);
        payment.showTotal();
    }

}

interface PaymentMethod {
    void pay(double amount);

    // Se puede adicionar un método default que no es obligatorio implementar en la case que implementa la interface.
    default void showTotal() {
        System.out.println("total...");
    }
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Payment with credit card: " + amount);
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Payment with paypal: " + amount);
    }

    @Override
    public void showTotal() {
        System.out.println("Total PayPalPayment...");
    }
}


