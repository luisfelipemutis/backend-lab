package com.felipe.backendlab.solid;

/**
 * Ejemplo del principio de inversión de dependencias (DIP).
 *
 * <p>La lógica importante del negocio no debe depender directamente de una
 * tecnología concreta. Tanto la lógica de alto nivel como los detalles de
 * bajo nivel dependen de una abstracción. Aquí, OrderService depende de
 * OrderNotifier, no de una implementación particular como EmailNotifier.
 *
 */
public class DependencyInversion {

    public static void main(String[] args) {
        OrderService emailOrderService = new OrderService(new EmailNotifier());
        emailOrderService.confirmOrder("A-123");

        OrderService smsOrderService = new OrderService(new SmsNotifier());
        smsOrderService.confirmOrder("B-456");
    }

    /**
     * Abstracción que define cómo notificar una confirmación de pedido.
     */
    interface OrderNotifier {
        void notifyOrderConfirmed(String orderId);
    }

    /**
     * Lógica de negocio: confirma pedidos y delega la notificación.
     */
    static class OrderService {
        private final OrderNotifier notifier;

        OrderService(OrderNotifier notifier) {
            this.notifier = notifier;
        }

        void confirmOrder(String orderId) {
            System.out.println("Pedido " + orderId + " confirmado.");
            notifier.notifyOrderConfirmed(orderId);
        }
    }

    /**
     * Detalle de bajo nivel: notifica por correo electrónico.
     */
    static class EmailNotifier implements OrderNotifier {
        @Override
        public void notifyOrderConfirmed(String orderId) {
            System.out.println("Correo enviado para el pedido " + orderId + ".");
        }
    }

    /**
     * Otro detalle de bajo nivel: notifica por mensaje de texto.
     */
    static class SmsNotifier implements OrderNotifier {
        @Override
        public void notifyOrderConfirmed(String orderId) {
            System.out.println("SMS enviado para el pedido " + orderId + ".");
        }
    }
}
