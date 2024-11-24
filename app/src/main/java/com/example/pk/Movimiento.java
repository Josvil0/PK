package com.example.pk;

public class Movimiento {
    private String nombre;
    private int fuerza;
    private String tipo;

    public Movimiento(String nombre, int fuerza, String tipo) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
