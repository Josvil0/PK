package com.example.pk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CombateViewModel extends ViewModel {
    private final MutableLiveData<Pokemon> jugador = new MutableLiveData<>();
    private final MutableLiveData<Pokemon> oponente = new MutableLiveData<>();
    private final MutableLiveData<String> textoCombate = new MutableLiveData<>();
    private MutableLiveData<Boolean> turno = new MutableLiveData<>(true); // Usar esta variable para manejar el turno

    // Getter y setter para el turno
    public MutableLiveData<Boolean> getTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno.setValue(turno);
    }

    // Getter para jugador y oponente
    public LiveData<Pokemon> getJugador() {
        return jugador;
    }

    public void setJugador(Pokemon jugador) {
        this.jugador.setValue(jugador);
    }

    public LiveData<Pokemon> getOponente() {
        return oponente;
    }

    public LiveData<String> getTextoCombate() {
        return textoCombate;
    }

    public CombateViewModel() {
        textoCombate.setValue("");

        // Movimientos del jugador (Lucario)
        List<Movimiento> movimientosJugador = new ArrayList<>();
        movimientosJugador.add(new Movimiento("Bola sombra", 80, "Especial"));
        movimientosJugador.add(new Movimiento("Gigaimpacto", 150, "Físico"));
        movimientosJugador.add(new Movimiento("Danza espada", 0, "Estadística"));
        movimientosJugador.add(new Movimiento("Protección", 0, "Estadística"));
        Pokemon lucario = new Pokemon("Lucario", 177, 177, 178, 134, 183, 134, movimientosJugador);

        // Movimientos del oponente (Braviary)
        List<Movimiento> movimientosOponente = new ArrayList<>();
        movimientosOponente.add(new Movimiento("Acrobata", 55, "Físico"));
        movimientosOponente.add(new Movimiento("Aire Afilado", 60, "Especial"));
        movimientosOponente.add(new Movimiento("Corpulencia", 0, "Estadística"));
        movimientosOponente.add(new Movimiento("Protección", 0, "Estadística"));
        Pokemon braviary = new Pokemon("Braviary", 207, 207, 192, 139, 119, 139, movimientosOponente);

        // Asignar Pokémon al combate
        jugador.setValue(lucario);
        oponente.setValue(braviary);
    }

    // Método de reinicio del combate
    public void reiniciarCombate() {
        List<Movimiento> movimientosJugador = new ArrayList<>();
        movimientosJugador.add(new Movimiento("Bola sombra", 80, "Especial"));
        movimientosJugador.add(new Movimiento("Gigaimpacto", 150, "Físico"));
        movimientosJugador.add(new Movimiento("Danza espada", 0, "Estadística"));
        movimientosJugador.add(new Movimiento("Protección", 0, "Estadística"));
        Pokemon lucario = new Pokemon("Lucario", 177, 177, 178, 134, 183, 134, movimientosJugador);
        List<Movimiento> movimientosOponente = new ArrayList<>();
        movimientosOponente.add(new Movimiento("Acrobata", 55, "Físico"));
        movimientosOponente.add(new Movimiento("Aire Afilado", 60, "Especial"));
        movimientosOponente.add(new Movimiento("Corpulencia", 0, "Estadística"));
        movimientosOponente.add(new Movimiento("Protección", 0, "Estadística"));
        Pokemon braviary = new Pokemon("Braviary", 207, 207, 192, 139, 119, 139, movimientosOponente);

        jugador.setValue(lucario);
        oponente.setValue(braviary);

        // Restablecer el texto de combate
        textoCombate.setValue("El combate ha sido reiniciado.");

        setTurno(true); // Restablecer el turno al jugador
    }

    public void realizarMovimiento(Movimiento movimiento) {
        if (jugador.getValue() != null && oponente.getValue() != null && turno.getValue()) {
            String resultado = "";

            // Verificar si el movimiento es de tipo Estadística
            if (movimiento.getTipo().equals("Estadística")) {
                resultado = realizarMovimientoEstadistica(movimiento, jugador.getValue());
            } else {
                // Si el movimiento es Físico o Especial, realizamos el ataque normal
                resultado = ModeloCombate.realizarAtaque(jugador.getValue(), oponente.getValue(), movimiento);
            }

            // Mostrar el resultado en el texto de combate
            textoCombate.setValue(resultado);

            // Verificar si el oponente ha sido derrotado

            oponente.setValue(oponente.getValue());
            if (oponente.getValue().getHp() <= 0) {
                textoCombate.setValue(textoCombate.getValue() + "\n¡Lucario gana el combate!");
                return; // Fin del combate si el oponente es derrotado
            }

            // Pasar el turno al oponente
            setTurno(false); // Cambiar turno
            realizarAtaqueOponente();
        }
    }

    private void realizarAtaqueOponente() {
        if (oponente.getValue() != null && jugador.getValue() != null) {
            // Elegir un movimiento aleatorio
            List<Movimiento> movimientosOponente = oponente.getValue().getMovimientos();
            Movimiento movimientoOponente = movimientosOponente.get((int) (Math.random() * movimientosOponente.size()));

            String resultado = "";

            // Verificar si el movimiento del oponente es de tipo Estadística
            if (movimientoOponente.getTipo().equals("Estadística")) {
                resultado = realizarMovimientoEstadistica(movimientoOponente, oponente.getValue());
            } else {
                // Realizar ataque normal si el movimiento no es de tipo Estadística
                resultado = ModeloCombate.realizarAtaque(oponente.getValue(), jugador.getValue(), movimientoOponente);
            }

            textoCombate.setValue(textoCombate.getValue() + "\n" + resultado);

            jugador.setValue(jugador.getValue());

            // Verificar si el jugador ha sido derrotado
            if (jugador.getValue().getHp() <= 0) {
                textoCombate.setValue(textoCombate.getValue() + "\n¡Braviary gana el combate!");
                return; // Combate terminado
            }

            // Pasar turno al jugador
            setTurno(true);
        }
    }

    private String realizarMovimientoEstadistica(Movimiento movimiento, Pokemon pokemon) {
        String resultado = "";

        if (movimiento.getNombre().equals("Danza espada") && pokemon == jugador.getValue()) {
            jugador.getValue().setAtaque(jugador.getValue().getAtaque() + 10);  // Aumentar ataque
            resultado = "¡El Ataque de Lucario ha aumentado!";
        } else if (movimiento.getNombre().equals("Danza espada") && pokemon == oponente.getValue()) {
            oponente.getValue().setAtaque(oponente.getValue().getAtaque() + 10);  // Aumentar ataque del oponente
            resultado = "¡El Ataque de Braviary ha aumentado!";
        } else if (movimiento.getNombre().equals("Protección") && pokemon == jugador.getValue()) {
            // Si Protección es usado por el jugador
            resultado = "¡Lucario se ha protegido!";
        } else if (movimiento.getNombre().equals("Protección") && pokemon == oponente.getValue()) {
            // Si Protección es usado por el oponente
            resultado = "¡Braviary se ha protegido!";
        } else if (movimiento.getNombre().equals("Corpulencia") && pokemon == oponente.getValue()) {
            oponente.getValue().setDefensa(oponente.getValue().getDefensa() + 10);  // Aumentar defensa del oponente
            resultado = "¡La Defensa de Braviary ha aumentado!";
        }

        return resultado;
    }

}


