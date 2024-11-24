package com.example.pk;

import java.util.List;

public class Pokemon {
    private String nombre;
    private int hp,hpMax,ataque,defensa,ataqueEspecial,DefensaEspecial;
    private List<Movimiento> movimientos;

    public Pokemon(String nombre, int hp,int hpMax, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, List<Movimiento> movimientos) {
        this.nombre = nombre;
        this.hp = hp;
        this.hpMax =  hpMax;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        DefensaEspecial = defensaEspecial;
        this.movimientos = movimientos;

    }

    public Pokemon(Pokemon oponenteOriginal) {
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHpMax() { return hpMax;}

    public int setHpMax(int hpMax) { return this.hpMax = hpMax;}

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefensaEspecial() {
        return DefensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        DefensaEspecial = defensaEspecial;
    }


}
