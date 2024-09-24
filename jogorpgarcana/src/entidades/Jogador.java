package entidades;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private int vida;
    private int mana;
    private List<Cartas> deck;
    private List<Cartas> mao;
    inicializarDeck();

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

   /* private void inicializarDeck() { // Revisar isso!
        for (int i = 0; i < 10; i++) {
            deck.add(new Criatura(" " + i, 5, 3, 2, "Habilidade Especial"));
        }
        for (int i = 0; i < 10; i++) {
            deck.add(new Feitico("Feitiço " + i, 2, "Efeito de Dano"));
        }
        for (int i = 0; i < 10; i++) {
            deck.add(new Encantamento("Encantamento " + i, 3, "Efeito Contínuo"));
        }
    }
    */


    public void jogarCarta(int indiceCarta) {
        Cartas carta = mao.get(indiceCarta);
        if (carta.getCustoMana() <= mana) {
            carta.usar();
            mana -= carta.getCustoMana();
            mao.remove(indiceCarta);
        }
    }


    public List<Cartas> getDeck() {
        return deck;
    }
}

    // Getters e setters
}
