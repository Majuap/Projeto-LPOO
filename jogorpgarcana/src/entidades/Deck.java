package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Cartas> cartas;

    public Deck(List<Cartas> cartas) {
        this.cartas = new ArrayList<>(cartas);
        embaralhar();
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Cartas comprarCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);  // Remove a carta do topo do deck
        }
        return null;  // Se não houver mais cartas
    }

    public boolean estaVazio() {
        return cartas.isEmpty();
    }
}

