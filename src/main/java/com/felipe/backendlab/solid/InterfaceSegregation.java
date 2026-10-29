package com.felipe.backendlab.solid;

/**
 * Ejemplo del principio de segregación de interfaces (ISP).
 *
 * <p>ISP recomienda preferir varias interfaces pequeñas y enfocadas en
 * capacidades concretas, en lugar de una interfaz grande que obligue a las
 * clases a implementar operaciones que no necesitan.
 *
 */
public class InterfaceSegregation {

    public static void main(String[] args) {
        Printer officePrinter = new SimplePrinter();
        officePrinter.print("Contrato");

        MultiFunctionPrinter multiFunctionPrinter = new MultiFunctionPrinter();
        multiFunctionPrinter.print("Informe");
        multiFunctionPrinter.scan("Documento");
        multiFunctionPrinter.fax("Formulario");
    }

    /**
     * Capacidad de imprimir.
     */
    interface Printer {
        void print(String document);
    }

    /**
     * Capacidad de escanear.
     */
    interface Scanner {
        void scan(String document);
    }

    /**
     * Capacidad de enviar un fax.
     */
    interface Fax {
        void fax(String document);
    }

    /**
     * Una impresora sencilla solo necesita cumplir la capacidad de imprimir.
     */
    static class SimplePrinter implements Printer {
        @Override
        public void print(String document) {
            System.out.println("Imprimiendo: " + document);
        }
    }

    /**
     * Este equipo sí ofrece las tres capacidades.
     */
    static class MultiFunctionPrinter implements Printer, Scanner, Fax {
        @Override
        public void print(String document) {
            System.out.println("Imprimiendo: " + document);
        }

        @Override
        public void scan(String document) {
            System.out.println("Escaneando: " + document);
        }

        @Override
        public void fax(String document) {
            System.out.println("Enviando por fax: " + document);
        }
    }
}
