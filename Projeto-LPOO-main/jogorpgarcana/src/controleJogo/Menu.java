package controleJogo;

import entidades.*;
import exceptions.CartaNaoEncontrada;
import exceptions.ManaInsuficiente;
import exceptions.SemVida;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Jogador jogador;
    private Oponente oponente;
    private List<Cartas> cartas;

    public Jogador getJogador() {
        return jogador;
    }

    public Oponente getOponente() {
        return oponente;
    }

    public void setOponente(Oponente oponente) {
        this.oponente = oponente;
    }

    public Menu(){
        this.jogador = getJogador();
        this.oponente = getOponente();
    }

    public void oponentes(Jogador jogador) throws CartaNaoEncontrada{
        Scanner scanner = new Scanner(System.in);
        ColecaoCartas colecao = new ColecaoCartas(oponente,jogador);
        //instanciar oponentes aqui, e o jogador podera escolhe-los como adversarios em uma partida
        Oponente oponenteUm = new Oponente("Demian");
        //Deck deckum = new Deck();
        ArrayList<Cartas> deckUm = new ArrayList<>();//Adicionar cartas diferentes para cada personagem
        deckUm.add(colecao.getCartas().get(0));
        deckUm.add(colecao.getCartas().get(1));
        deckUm.add(colecao.getCartas().get(2));
        deckUm.add(colecao.getCartas().get(3));
        deckUm.add(colecao.getCartas().get(4));
        deckUm.add(colecao.getCartas().get(5));
        oponenteUm.setDeck(deckUm);

        Oponente oponenteDois = new Oponente("Uriel");
        ArrayList<Cartas> deckDois = new ArrayList<>();// Especifica o tipo para ArrayList
        oponenteDois.setDeck(deckDois);

        Oponente oponenteTres = new Oponente("Maximilliam");
        ArrayList<Cartas> deckTres = new ArrayList<>();// Especifica o tipo para ArrayList
        oponenteTres.setDeck(deckTres);
        System.out.println("Escolha um dos oponentes a seguir para ter um duelo:");
        System.out.println("1. Demian");
        System.out.println("2. Uriel");
        System.out.println("3. Max");
        System.out.println("Digite a seguir o numero relativo ao oponente desejado:");
        int escolhaOponente = scanner.nextInt();
        switch (escolhaOponente) {
            case 1:
                setOponente(oponenteUm);
                break;
            case 2:
                setOponente(oponenteDois);
                break;
            case 3:
                setOponente(oponenteTres);
                break;
            default:
                System.out.println("Numero invalido");
                break;
        }
    }

    public void modoArena(Jogador jogador) throws CartaNaoEncontrada, ManaInsuficiente, SemVida {
        ColecaoCartas colecao = new ColecaoCartas(oponente,jogador);
        //instanciar oponentes aqui, e o jogador podera escolhe-los como adversarios em uma partida
        Oponente oponenteUm = new Oponente("Demian");
        //Deck deckum = new Deck();
        ArrayList<Cartas> deckUm = new ArrayList<>();//Adicionar cartas diferentes para cada personagem
        deckUm.add(colecao.getCartas().get(0));
        deckUm.add(colecao.getCartas().get(1));
        deckUm.add(colecao.getCartas().get(2));
        deckUm.add(colecao.getCartas().get(3));
        deckUm.add(colecao.getCartas().get(4));
        deckUm.add(colecao.getCartas().get(5));
        oponenteUm.setDeck(deckUm);

        Oponente oponenteDois = new Oponente("Uriel");
        ArrayList<Cartas> deckDois = new ArrayList<>();// Especifica o tipo para ArrayList
        oponenteDois.setDeck(deckDois);

        Oponente oponenteTres = new Oponente("Maximilliam");
        ArrayList<Cartas> deckTres = new ArrayList<>();// Especifica o tipo para ArrayList
        oponenteTres.setDeck(deckTres);
        boolean continua = true;
        while(continua){
            iniciarPartida(jogador,oponenteUm);
            if(jogador.getVida()==30) {
                iniciarPartida(jogador, oponenteDois);
                if(jogador.getVida()==30) {
                    iniciarPartida(jogador, oponenteTres);
                }
                else{
                    continua = false;
                }
            }
            else{
                continua = false;
            }
        }
    }

    public void iniciarPartida(Jogador jogador,Oponente oponente) throws CartaNaoEncontrada, ManaInsuficiente, SemVida {
        System.out.println("A partida vai começar!");
        // Aqui você pode instanciar a classe Partida e chamar o metodo que inicia o jogo
        Partida partida = new Partida();
        partida.iniciar(jogador,oponente);
        while(true) {
            partida.turno();
            partida.turnoAutomatizado();
            if(jogador.getVida() == 0){
                throw new SemVida("Voce Perdeu");
            }
            if(oponente.getVida() == 0){
                jogador.setVida(30);
                throw new SemVida("Voce Ganhou");
            }
        }
    }
}
