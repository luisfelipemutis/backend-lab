package com.felipe.backendlab.solid;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Ejemplo del principio abierto/cerrado (OCP).
 *
 * <p>OCP propone que una parte del software esté abierta a extenderse, pero
 * cerrada a modificar su código estable. Aquí, Checkout puede usar distintas
 * políticas de descuento mediante la interfaz DiscountPolicy. Para agregar
 * una nueva regla, se crea otra implementación sin cambiar Checkout.
 *
 */
public class OpenClosed {

    public static void main(String[] args) {
        BigDecimal subtotal = new BigDecimal("100.00");

        Checkout checkoutWithDiscount = new Checkout(new PercentageDiscount(new BigDecimal("0.10")));
        Checkout checkoutWithoutDiscount = new Checkout(new NoDiscount());

        System.out.println("Con descuento: $" + checkoutWithDiscount.total(subtotal));
        System.out.println("Sin descuento: $" + checkoutWithoutDiscount.total(subtotal));
    }

    /**
     * Define cómo se aplica una política de descuento al subtotal.
     */
    interface DiscountPolicy {
        BigDecimal applyTo(BigDecimal subtotal);
    }

    /**
     * Contexto estable: calcula el total delegando el descuento a su política.
     */
    static class Checkout {
        private final DiscountPolicy discountPolicy;

        Checkout(DiscountPolicy discountPolicy) {
            this.discountPolicy = discountPolicy;
        }

        BigDecimal total(BigDecimal subtotal) {
            return discountPolicy.applyTo(subtotal);
        }
    }

    /**
     * Política que no modifica el subtotal.
     */
    static class NoDiscount implements DiscountPolicy {
        @Override
        public BigDecimal applyTo(BigDecimal subtotal) {
            return subtotal;
        }
    }

    /**
     * Aplica una tasa entre 0 y 1; por ejemplo, 0.10 representa el 10 %.
     */
    static class PercentageDiscount implements DiscountPolicy {
        private final BigDecimal rate;

        PercentageDiscount(BigDecimal rate) {
            if (rate.compareTo(BigDecimal.ZERO) < 0 || rate.compareTo(BigDecimal.ONE) > 0) {
                throw new IllegalArgumentException("La tasa debe estar entre 0 y 1.");
            }
            this.rate = rate;
        }

        @Override
        public BigDecimal applyTo(BigDecimal subtotal) {
            return subtotal.multiply(BigDecimal.ONE.subtract(rate))
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }
}
