package com.felipe.backendlab.playground.snackMachineFile.domain;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {

    private static int countSnack = 0;
    private int idSnack;
    private String name;
    private double price;

    public Snack() {
        this.idSnack = ++countSnack;
    }

    public Snack(String name, double price) {
        this(); // Debe ser la primer linea la llamada al constructor..
        this.name = name;
        this.price = price;
    }

    public static int getCountSnack() {
        return countSnack;
    }

    public int getIdSnack() {
        return idSnack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "idSnack=" + idSnack +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String writingSnack() {
        return this.idSnack + "," + this.name + "," + this.price;
    }

    // Métodos para comparar los objetos antes de insertar en la lista.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(price, snack.price) == 0 && Objects.equals(name, snack.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, name, price);
    }
}
