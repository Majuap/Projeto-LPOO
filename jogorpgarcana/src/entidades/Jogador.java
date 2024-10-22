package entidades;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    protected String nome;
    protected int vida;
    protected int mana;
    protected List<Cartas> deck;
    protected List<Cartas> mao;

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

    public static void inicializarDeck() { // Revisar isso!
        Deck Cartas = new Deck();
        for (int i = 0; i < 30; i++) {
            System.out.println("Escolha as cartas que irão compor o seu deck");
            for(int j =0; j<48; j++){
                System.out.println(Cartas.getCartas().get(j));
            }
            //deck.add;
        }
    }
    public void distribuirCartas(List<Cartas> deck, int quantidade) {
        // Distribuir as cartas da lista de deck para a mão do jogador
        for (int i = 0; i < quantidade; i++) {
            if (!deck.isEmpty()) {
                Cartas carta = deck.remove(0); // Remove do baralho e adiciona na mão
                this.mao.add(carta);
            }
        }
    }

    public void adicionarCartaNaMão(Cartas carta) {
        this.mao.add(carta);
    }
    
    public void jogarCarta(int indiceCarta) {
        Cartas carta = mao.get(indiceCarta);
        if (carta.getCustoMana() <= mana) {
            carta.usarCarta();
            mana -= carta.getCustoMana();
            mao.remove(indiceCarta);
        }
    }
    public List<Cartas> getDeck() {
        return deck;
    }

    public String getNomeJogador() {
        return nome;
    }

    public List<Cartas> getCartasNaMao() {
        return ((Deck) mao).getCartas(); //Mão tem um método getCartas()
    }

    public int vida() {
        return vida;
    }
    public int getMana() {
        return mana;
    }
}
