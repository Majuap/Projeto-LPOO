package entidades;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private int vida;
    private int mana;
    private List<Cartas> deck;
    private List<Cartas> mao;

    public Jogador(String nome) {
        this.nome = nome;
        this.vida = 30; // Vida inicial
        this.mana = 1;  // Mana inicial
        this.deck = new ArrayList<>();
        this.mao = new ArrayList<>();
    }

    public void comprarCarta() {
        if (!deck.isEmpty()) {
            mao.add(deck.remove(0)); // Compra a primeira carta do deck
        }
    }

    public void jogarCarta(int indiceCarta) {
        Cartas carta = mao.get(indiceCarta);
        if (carta.getCustoMana() <= mana) {
            carta.usar();
            mana -= carta.getCustoMana();
            mao.remove(indiceCarta);
        }
    }

    // Getters e setters
}
