package entidades;
import java.util.ArrayList;
import java.util.List;

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

    public void comprarCarta() {
        if (!deck.isEmpty()) {
            mao.adicionarCarta(deck.remove(0)); // Usa `adicionarCarta` de `Mao`
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
        for (int i = 0; i < quantidade; i++) {
            if (!deck.isEmpty()) {
                Cartas carta = deck.remove(0);
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
        return mao.getCartas();  // Chama o método getCartas() da classe Mao
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
