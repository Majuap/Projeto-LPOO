package entidades;

import exceptions.CartaNaoEncontrada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    protected String nome;
    protected int vida;
    protected int mana;
    protected List<Cartas> deck;
    protected Mao mao;
    private CampoBatalha campo;

    public Jogador(String nome) {
        this.nome = nome;
        this.vida = 30; // Vida inicial
        this.mana = 1;  // Mana inicial
        this.deck = new ArrayList<>();
        this.mao = new Mao(); // Inicialize `mao` como uma nova instância de `Mao`
        this.campo = new CampoBatalha();
    }
    public void setDeck(List<Cartas> deck) {
        this.deck = deck;
    }

    public List<Cartas> inicializarDeck(Jogador jogador, Jogador oponente){
        //Deck cartas = new Deck();
        ColecaoCartas colecao = new ColecaoCartas(jogador, oponente);
        ArrayList<Cartas> deckEscolhido = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int indiceLista = 0;
        while (indiceLista< colecao.getCartas().size()) {
            System.out.println(indiceLista+"._"+colecao.getCartas().get(indiceLista));
            indiceLista++;
        }
        for (int i = 0; i < 30; i++) {
            System.out.println("Escolha as cartas que irão compor o seu deck");
            System.out.println("Selecione o número da carta desejada");
            int indice = scanner.nextInt();
            if (indice < 0 || indice >= colecao.getCartas().size()) {
                System.out.println("Índice inválido");
                System.out.println("Escolha as cartas que irão compor o seu deck");
                System.out.println("Selecione o número da carta desejada");
                indice = scanner.nextInt();
            }
            else if (deckEscolhido.contains(colecao.getCartas().get(indice))){
                //quando o elemento do deck escolhido esta duplicado
                System.out.println("A carta selecionada ja pertence ao seu Deck");
                System.out.println("Escolha uma carta diferente para compor o seu Deck:");
                indice = scanner.nextInt();
            }
            else{
                deckEscolhido.add(colecao.getCartas().get(indice));
            }
        }

        setDeck(deckEscolhido);
        return deckEscolhido;
    }
    //ok

    public void embaralhar() {
        Collections.shuffle(deck);
    }
    //ok

    public void comprarCarta(){
        if (!deck.isEmpty()) {
            mao.adicionarCarta(deck.get(0));// Usa `adicionarCarta` de `Mao`
            deck.remove(0);
        }
        else{
                System.out.println("O Deck esta vazio");
        }
    }

    public void distribuirCartas(List<Cartas> deck, int quantidade) {// primeira coisa que acontece na partida
        for (int i = 0; i < quantidade; i++) {
            if (!deck.isEmpty()) {
                Cartas carta = deck.get(0);
                mao.adicionarCarta(carta);
                deck.remove(0);
            }
        }
    }
    //Ok

    public void limparMao() {
        this.mao.getCartas().clear(); // Esvazia a lista de cartas na mão
    }
    public void limparCampo() {
        this.campo.getCampo().clear(); // Esvazia a lista de cartas no campo
    }

    public void limparCemiterio() {
        this.campo.getCemiterio().clear(); // Esvazia a lista de cartas no cemitério
    }

    public void redefinirDeck() {
        // Reunindo todas as cartas
        List<Cartas> novoDeck = new ArrayList<>();

        if (mao != null && !mao.getCartas().isEmpty()) {
            novoDeck.addAll(mao.getCartas());
        }

        if (campo != null && !campo.getCampo().isEmpty()) {
            novoDeck.addAll(campo.getCampo());
        }

        if (campo != null && !campo.getCemiterio().isEmpty()) {
            novoDeck.addAll(campo.getCemiterio());
        }

        // Atualizando o deck
        this.deck = novoDeck;

        // Limpando a mão, campo e cemitério
        if (mao != null) {
            limparMao();
        }
        if (campo != null) {
            limparCampo();
            limparCemiterio();
        }
    }




    public void jogarCarta(int indiceCarta) {
        Cartas carta = mao.getCartas().get(indiceCarta);
        if (carta.getCustoMana() <= mana) {
            setMana(mana -= carta.getCustoMana());
            campo.getCampo().add(getCartasNaMao().get(indiceCarta));
            mao.removerCarta(indiceCarta); // Usa `removerCarta` de `Mao`
        }
        else {
            System.out.println("Mana Insuficiente");
        }
    }

    public String statusDoJogador(){
        return "Jogador: " + getNomeJogador() + "\n" +
                "Vida: " + getVida() + "\n" +
                "Mana: " + getMana() + "\n";
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

    public int getVida(){
        return vida;
    }

    public void setVida(int vida) {
        if (vida == 30) {
            this.vida = 30;
        }
        else if(0<=vida && vida<30) {
            this.vida = vida;
        }
        else if(vida>30){
            this.vida=30;
            System.out.println("Sua vida chegou ao valor maximo");
        }
        else if(vida<0){
            this.vida=0;
        }
        //colocar os casos da vida
    }

    public int getMana() {
        return mana;
    }

    public CampoBatalha getCampo() {
        return campo;
    }

    public void setMana(int mana) {
        if (mana == 10) {
            this.mana=10;
        }
        else if (mana>10) {
            this.mana = 10;
            System.out.println("Voce tem o maximo de cristais de mana");
        }
        else if (0<=mana && mana<10){
            this.mana=mana;
        }
        else if(mana<0){
            this.mana = 0;
        }
    }
}
