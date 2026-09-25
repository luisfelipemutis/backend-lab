package com.felipe.backendlab.javacore.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Box Example
 * <p>
 * Los genericos permiten crear clases, interfaces y metodos que funcionan con
 * cualquier tipo de dato sin perder la seguridad de tipos.
 * <p>
 * En lugar de trabajar con Object y hacer casteos manuales, Java nos permite
 * declarar una clase con un parametro de tipo T.
 * <p>
 * Beneficios:
 * - reutilizacion de codigo;
 * - mayor seguridad en compilacion;
 * - menos errores de cast;
 * - codigo mas legible y mantenible.
 * <p>
 * Algunos nombres comunes para tipos genericos son:
 * T -> Tipo generico
 * E -> Elemento
 * K -> Clave
 * V -> Valor
 * <p>
 * En este ejemplo se muestran varios tipos genericos:
 * - GenericBox<T>: encapsula un valor de cualquier tipo.
 * - GenericPair<K, V>: encapsula dos valores relacionados.
 * - GenericRepository<T>: almacena elementos genericos en memoria.
 */
public class GenericBoxExample {
    public static void main(String[] args) {
        GenericBox<String> cajaTexto = new GenericBox<>("Hola Java");
        GenericBox<Integer> cajaNumero = new GenericBox<>(42);

        System.out.println("Texto: " + cajaTexto.getValue());
        System.out.println("Numero: " + cajaNumero.getValue());

        GenericPair<String, Integer> usuario = new GenericPair<>("Luis", 30);
        System.out.println("Usuario: " + usuario.getKey() + " - " + usuario.getValue());

        GenericRepository<String> nombres = new GenericRepository<>();
        nombres.add("Java");
        nombres.add("Spring");
        nombres.add("Maven");

        System.out.println("Nombres guardados: " + nombres.getAll());

        List<Integer> numeros = List.of(10, 20, 30, 40);
        printList(numeros);
    }

    public static <T> void printList(List<T> lista) {
        System.out.println("Imprimiendo lista generica: ");
        for (T element : lista) {
            System.out.println("- " + element);
        }
    }
}

class GenericBox<T> {
    private T value;

    public GenericBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

class GenericPair<K, V> {
    private K key;
    private V value;

    public GenericPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class GenericRepository<T> {
    private final List<T> data = new ArrayList<>();

    public void add(T item) {
        data.add(item);
    }

    public List<T> getAll() {
        return data;
    }
}
