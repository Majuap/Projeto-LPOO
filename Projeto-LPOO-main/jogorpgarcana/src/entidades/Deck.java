package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Cartas> cartas;

    public Deck(){
        cartas = new ArrayList<>();
    }

    public List<Cartas> getCartas() {
        return cartas;
    }

    public void setCartas(List<Cartas> cartas) {
        this.cartas = cartas;
    }
    //embaralhar o deck antes da partida comecar
    public void embaralhar(List<Cartas> cartas) {
        Collections.shuffle(cartas);
    }

    public Cartas comprarCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);//retorna a carta do topo do deck
        }
        //cartas.removeFirst();// Remove a carta do topo do deck
        return null;  // Se não houver mais cartas
    }

    public boolean estaVazio() {
        return cartas.isEmpty();
    }
}

