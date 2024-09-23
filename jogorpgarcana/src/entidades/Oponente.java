package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Radom

public class Oponenete extends Jogador {



    public Oponenete(String nome) {
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

  /*
   private void inicializarDeck() { // Revisar isso!
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

 public void jogar(CampoBatalha campo) {
        // O oponente compra uma carta
        comprarCarta();

        // Lógica simples para jogar uma carta ou atacar
        if (!mao.isEmpty()) {
            Random rand = new Random();
            int indiceCarta = rand.nextInt(mao.size());
            Cartas carta = mao.get(indiceCarta);

            if (carta.getCustoMana() <= mana) {
                jogarCarta(indiceCarta);
            } else if (!campo.getCriaturas(this).isEmpty()) {
                // Atacar com uma criatura
                
                int indiceCriatura = rand.nextInt(campo.getCriaturas(this).size());
                Criatura criaturaAtacante = campo.getCriaturas(this).get(indiceCriatura);

            }
        }
    }


    public List<Cartas> getDeck() {
        return deck;
    }
}

    // Getters e setters
}