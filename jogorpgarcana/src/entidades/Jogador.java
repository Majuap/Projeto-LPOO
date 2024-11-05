package entidades;

import exceptions.CartaNaoEncontrada;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    protected String nome;
    protected int vida;
    protected int mana;
    protected List<Cartas> deck;
    protected Mao mao;

    public Jogador(String nome) {
        this.nome = nome;
        this.vida = 30; // Vida inicial
        this.mana = 1;  // Mana inicial
        this.deck = new ArrayList<>();
        this.mao = new Mao(); // Inicialize `mao` como uma nova instância de `Mao`
    }

    public void setDeck(List<Cartas> deck) {
        this.deck = deck;
    }

    public void inicializarDeck() {
        Deck cartas = new Deck();
        ColecaoCartas colecao = new ColecaoCartas();
        ArrayList<Cartas> deckEscolhido = new ArrayList<>();  // Especifica o tipo para ArrayList
        ArrayList<Cartas> listaCopia = new ArrayList<Cartas>(colecao.getCartas());  // Cria uma cópia da coleção de cartas
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("Escolha as cartas que irão compor o seu deck");
            for (Cartas elemento : listaCopia) {
                System.out.println(elemento);
            }  // Exibe a lista de cartas disponíveis
            System.out.println("Selecione o número da carta desejada");
            int indice = scanner.nextInt();
            indice = indice-1;
            if (indice >= 0 && indice < listaCopia.size()) {
                // Adiciona a carta escolhida ao deckEscolhido e remove da listaCopia
                deckEscolhido.add(listaCopia.get(indice));
                listaCopia.add(indice, null);
            } else {
                System.out.println("Índice inválido");
                System.out.println("Escolha as cartas que irão compor o seu deck");
                System.out.println(listaCopia);  // Exibe a lista de cartas disponíveis
                System.out.println("Selecione o número da carta desejada");
                indice = scanner.nextInt();
            }
        }

        cartas.setCartas(deckEscolhido);
        setDeck(deckEscolhido);
    }

    public void comprarCarta() throws CartaNaoEncontrada {
        if (!deck.isEmpty()) {
            mao.adicionarCarta(deck.get(0));// Usa `adicionarCarta` de `Mao`
            deck.remove(0);
        }
        else{
            throw new CartaNaoEncontrada("O Deck esta vazio");
        }
    }

    public void distribuirCartas(List<Cartas> deck, int quantidade) {// primeira coisa que acontece na partida
        for (int i = 0; i < quantidade; i++) {
            if (!deck.isEmpty()) {
                Cartas carta = deck.get(0);
                deck.remove(0);
                mao.adicionarCarta(carta);
            }
        }
    }

    public void adicionarCartaNaMao(Cartas carta) {
        mao.adicionarCarta(carta); // Usa `adicionarCarta` de `Mao`
    }
    
    public void jogarCarta(int indiceCarta) {
        Cartas carta = mao.getCartas().get(indiceCarta);
        if (carta.getCustoMana() <= mana) {
            carta.usarCarta();
            mana -= carta.getCustoMana();
            mao.removerCarta(indiceCarta); // Usa `removerCarta` de `Mao`
        }
    }

    public List<Cartas> getDeck() {
        return deck;
    }

    public String getNomeJogador() {
        return nome;
    }

    public List<Cartas> getCartasNaMao() {
        return mao.getCartas();  // Chama o metodo getCartas() da classe Mao
    }

    public int vida() {
        return vida;
    }
    public int getVida(){
        return vida;
    }
    
    public int getMana() {
        return mana;
    }
}
