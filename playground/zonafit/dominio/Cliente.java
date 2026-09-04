package com.felipe.backendlab.playground.zonafit.dominio;

import java.util.Objects;

public class Cliente {

    private int id;
    private String nombre;
    private String apellido;
    private int membresia;

    public Cliente() {

    }

    public Cliente(int id) {
        this.id = id;
    }

    public Cliente(String nombre, String apellido, int membresia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresia = membresia;
    }

    public Cliente(int id, String nombre, String apellido, int membresia) {
        this(nombre, apellido, membresia);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getMembresia() {
        return membresia;
    }

    public void setMembresia(int membresia) {
        this.membresia = membresia;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", membresia=" + membresia +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cliente otroCliente = (Cliente) obj;
        return this.id == otroCliente.id &&
                this.nombre.equals(otroCliente.nombre) &&
                this.apellido.equals(otroCliente.apellido) &&
                this.membresia == otroCliente.membresia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, membresia);
    }
}
