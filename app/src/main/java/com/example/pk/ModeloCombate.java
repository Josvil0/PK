package com.example.pk;

public class ModeloCombate {

    public static int calcularDamage(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {

        if (movimiento.getTipo() == "Especial"){
            int dañoBase = movimiento.getFuerza()+ atacante.getAtaqueEspecial()- defensor.getDefensaEspecial();
            int daño = (int) (Math.max(dañoBase,1)*calcularModificador());
            return daño;
        }else{
            int dañobase = movimiento.getFuerza() + atacante.getAtaque() - defensor.getDefensa();
            int daño = (int) (Math.max(dañobase, 1) * calcularModificador());
            return daño;
        }
    }

    private static double calcularModificador() {
        double criticalHit = Math.random() < 0.1 ? 1.5 : 1.0; // 10% de probabilidad de crítico
        return criticalHit;
    }

    public static String realizarAtaque(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        int damage = calcularDamage(atacante, defensor, movimiento);
        int remainingHp = Math.max(defensor.getHp() - damage, 0);
        defensor.setHp(remainingHp);

        // Construir el mensaje
        StringBuilder resultado = new StringBuilder();
        resultado.append(atacante.getNombre())
                .append(" usó ")
                .append(movimiento.getNombre())
                .append(" y causó ")
                .append(damage)
                .append(" de daño a ")
                .append(defensor.getNombre())
                .append(".");

        if (remainingHp == 0) {
            resultado.append("\n").append(defensor.getNombre()).append(" ha sido derrotado.");
        }

        return resultado.toString();
    }
}
