package com.felipe.backendlab.solid;

import java.math.BigDecimal;
import java.util.List;

/**
 * Ejemplo del principio de responsabilidad única (SRP).
 *
 * <p>SRP dice que una clase debe tener una sola responsabilidad, es decir,
 * un solo motivo principal para cambiar. No significa que la clase solo pueda
 * tener un método. Por ejemplo, si una misma clase calcula el total de una
 * factura, decide cómo mostrarla y además la imprime, tendría motivos de cambio
 * relacionados con las reglas de cálculo, el formato y la salida.
 *
 */
public class SingleResponsibility {

    public static void main(String[] args) {
        Invoice invoice = new Invoice("F-100", List.of(
                new InvoiceItem("Cuaderno", new BigDecimal("3.50")),
                new InvoiceItem("Lapicero", new BigDecimal("1.25"))
        ));

        BigDecimal total = new InvoiceCalculator().calculateTotal(invoice);
        String text = new InvoiceFormatter().format(invoice, total);
        new InvoicePrinter().print(text);
    }

    /**
     * Representa los datos de una factura.
     */
    static class Invoice {
        private final String number;
        private final List<InvoiceItem> items;

        Invoice(String number, List<InvoiceItem> items) {
            this.number = number;
            this.items = List.copyOf(items);
        }

        String getNumber() {
            return number;
        }

        List<InvoiceItem> getItems() {
            return items;
        }
    }

    /**
     * Representa un producto incluido en la factura.
     */
    static class InvoiceItem {
        private final String name;
        private final BigDecimal price;

        InvoiceItem(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }

        String getName() {
            return name;
        }

        BigDecimal getPrice() {
            return price;
        }
    }

    /**
     * Se ocupa únicamente de las reglas para calcular el total.
     */
    static class InvoiceCalculator {
        BigDecimal calculateTotal(Invoice invoice) {
            return invoice.getItems().stream()
                    .map(InvoiceItem::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    /**
     * Se ocupa únicamente de presentar la factura como texto.
     */
    static class InvoiceFormatter {
        String format(Invoice invoice, BigDecimal total) {
            StringBuilder result = new StringBuilder("Factura ")
                    .append(invoice.getNumber())
                    .append(System.lineSeparator());

            for (InvoiceItem item : invoice.getItems()) {
                result.append("- ")
                        .append(item.getName())
                        .append(": $")
                        .append(item.getPrice().toPlainString())
                        .append(System.lineSeparator());
            }

            return result.append("Total: $")
                    .append(total.toPlainString())
                    .toString();
        }
    }

    /**
     * Se ocupa únicamente de enviar el texto a la consola.
     */
    static class InvoicePrinter {
        void print(String text) {
            System.out.println(text);
        }
    }
}
