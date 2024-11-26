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

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Menu(){
        this.jogador = getJogador();
        this.oponente = getOponente();
    }

    public void oponentes(Jogador jogador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um dos oponentes a seguir para ter um duelo:");
        System.out.println("1. Demian");
        System.out.println("2. Uriel");
        System.out.println("3. Max");
        System.out.println("Digite a seguir o numero relativo ao oponente desejado:");
        int escolhaOponente = scanner.nextInt();
        boolean verificar = true;
        while(verificar) {
            switch (escolhaOponente) {
                case 1:
                    setOponente(oponenteModoArena(jogador).get(4));
                    verificar = false;
                    break;
                case 2:
                    setOponente(oponenteModoArena(jogador).get(3));
                    verificar = false;
                    break;
                case 3:
                    setOponente(oponenteModoArena(jogador).get(5));
                    verificar = false;
                    break;
                default:
                    System.out.println("Numero invalido,\ncoloque um numero entre 1 e 3.");
                    break;
            }
        }
    }

    protected List<Oponente> oponenteModoArena(Jogador jogador){
        List<Oponente> oponentesArena = new ArrayList<Oponente>();
        oponentesArena.add(new Oponente("Oponente Ordinario"));
        oponentesArena.add(new Oponente("Cidadão Comum"));
        oponentesArena.add(new Oponente("Soldado Arcano"));
        oponentesArena.add(new Oponente("Uriel"));
        oponentesArena.add(new Oponente("Demian"));
        oponentesArena.add(new Oponente("Maxmilliam"));
        oponentesArena.add(new Oponente("Azul"));

        ColecaoCartas colecaoUm = new ColecaoCartas(oponentesArena.get(0),jogador);
        ArrayList<Cartas> deckUm = new ArrayList<>();//Adicionar cartas diferentes para cada personagem
        deckUm.add(colecaoUm.getCartas().get(24));
        deckUm.add(colecaoUm.getCartas().get(30));
        deckUm.add(colecaoUm.getCartas().get(31));
        deckUm.add(colecaoUm.getCartas().get(34));
        deckUm.add(colecaoUm.getCartas().get(37));
        deckUm.add(colecaoUm.getCartas().get(38));
        deckUm.add(colecaoUm.getCartas().get(41));
        deckUm.add(colecaoUm.getCartas().get(42));
        deckUm.add(colecaoUm.getCartas().get(45));
        deckUm.add(colecaoUm.getCartas().get(47));
        oponentesArena.get(0).setDeck(deckUm);

        ColecaoCartas colecaoDois = new ColecaoCartas(oponentesArena.get(1),jogador);
        ArrayList<Cartas> deckDois = new ArrayList<>();// Especifica o tipo para ArrayList
        deckDois.add(colecaoDois.getCartas().get(14));
        deckDois.add(colecaoDois.getCartas().get(23));
        deckDois.add(colecaoDois.getCartas().get(25));
        deckDois.add(colecaoDois.getCartas().get(27));
        deckDois.add(colecaoDois.getCartas().get(1));
        deckDois.add(colecaoDois.getCartas().get(5));
        deckDois.add(colecaoDois.getCartas().get(10));
        deckDois.add(colecaoDois.getCartas().get(18));
        deckDois.add(colecaoDois.getCartas().get(9));
        deckDois.add(colecaoDois.getCartas().get(19));
        deckDois.add(colecaoDois.getCartas().get(20));
        deckDois.add(colecaoDois.getCartas().get(45));
        deckDois.add(colecaoDois.getCartas().get(41));
        deckDois.add(colecaoDois.getCartas().get(40));
        deckDois.add(colecaoDois.getCartas().get(29));
        oponentesArena.get(1).setDeck(deckDois);

        ColecaoCartas colecaoTres = new ColecaoCartas(oponentesArena.get(2),jogador);
        ArrayList<Cartas> deckTres = new ArrayList<>();// Especifica o tipo para ArrayList
        deckTres.add(colecaoTres.getCartas().get(24));
        deckTres.add(colecaoTres.getCartas().get(30));
        deckTres.add(colecaoTres.getCartas().get(31));
        deckTres.add(colecaoTres.getCartas().get(47));
        deckTres.add(colecaoTres.getCartas().get(46));
        deckTres.add(colecaoTres.getCartas().get(45));
        deckTres.add(colecaoTres.getCartas().get(44));
        deckTres.add(colecaoTres.getCartas().get(14));
        deckTres.add(colecaoTres.getCartas().get(23));
        deckTres.add(colecaoTres.getCartas().get(25));
        deckTres.add(colecaoTres.getCartas().get(32));
        deckTres.add(colecaoTres.getCartas().get(1));
        deckTres.add(colecaoTres.getCartas().get(5));
        deckTres.add(colecaoTres.getCartas().get(10));
        deckTres.add(colecaoTres.getCartas().get(20));
        oponentesArena.get(2).setDeck(deckTres);

        ColecaoCartas colecaoQuatro = new ColecaoCartas(oponentesArena.get(3),jogador);
        ArrayList<Cartas> deckQuatro = new ArrayList<>();
        deckQuatro.add(colecaoQuatro.getCartas().get(2));
        deckQuatro.add(colecaoQuatro.getCartas().get(3));
        deckQuatro.add(colecaoQuatro.getCartas().get(5));
        deckQuatro.add(colecaoQuatro.getCartas().get(6));
        deckQuatro.add(colecaoQuatro.getCartas().get(8));
        deckQuatro.add(colecaoQuatro.getCartas().get(9));
        deckQuatro.add(colecaoQuatro.getCartas().get(11));
        deckQuatro.add(colecaoQuatro.getCartas().get(13));
        deckQuatro.add(colecaoQuatro.getCartas().get(15));
        deckQuatro.add(colecaoQuatro.getCartas().get(16));
        deckQuatro.add(colecaoQuatro.getCartas().get(17));
        deckQuatro.add(colecaoQuatro.getCartas().get(18));
        deckQuatro.add(colecaoQuatro.getCartas().get(19));
        deckQuatro.add(colecaoQuatro.getCartas().get(23));
        deckQuatro.add(colecaoQuatro.getCartas().get(25));
        deckQuatro.add(colecaoQuatro.getCartas().get(26));
        deckQuatro.add(colecaoQuatro.getCartas().get(27));
        deckQuatro.add(colecaoQuatro.getCartas().get(28));
        deckQuatro.add(colecaoQuatro.getCartas().get(29));
        deckQuatro.add(colecaoQuatro.getCartas().get(30));
        deckQuatro.add(colecaoQuatro.getCartas().get(35));
        deckQuatro.add(colecaoQuatro.getCartas().get(37));
        deckQuatro.add(colecaoQuatro.getCartas().get(39));
        deckQuatro.add(colecaoQuatro.getCartas().get(40));
        deckQuatro.add(colecaoQuatro.getCartas().get(41));
        deckQuatro.add(colecaoQuatro.getCartas().get(44));
        deckQuatro.add(colecaoQuatro.getCartas().get(45));
        deckQuatro.add(colecaoQuatro.getCartas().get(46));
        deckQuatro.add(colecaoQuatro.getCartas().get(47));
        deckQuatro.add(colecaoQuatro.getCartas().get(22));
        oponentesArena.get(3).setDeck(deckQuatro);

        ColecaoCartas colecaoCinco = new ColecaoCartas(oponentesArena.get(4),jogador);
        ArrayList<Cartas> deckCinco = new ArrayList<>();
        deckCinco.add(colecaoCinco.getCartas().get(1));
        deckCinco.add(colecaoCinco.getCartas().get(3));
        deckCinco.add(colecaoCinco.getCartas().get(4));
        deckCinco.add(colecaoCinco.getCartas().get(6));
        deckCinco.add(colecaoCinco.getCartas().get(8));
        deckCinco.add(colecaoCinco.getCartas().get(9));
        deckCinco.add(colecaoCinco.getCartas().get(10));
        deckCinco.add(colecaoCinco.getCartas().get(12));
        deckCinco.add(colecaoCinco.getCartas().get(14));
        deckCinco.add(colecaoCinco.getCartas().get(16));
        deckCinco.add(colecaoCinco.getCartas().get(17));
        deckCinco.add(colecaoCinco.getCartas().get(18));
        deckCinco.add(colecaoCinco.getCartas().get(19));
        deckCinco.add(colecaoCinco.getCartas().get(20));
        deckCinco.add(colecaoCinco.getCartas().get(22));
        deckCinco.add(colecaoCinco.getCartas().get(23));
        deckCinco.add(colecaoCinco.getCartas().get(24));
        deckCinco.add(colecaoCinco.getCartas().get(26));
        deckCinco.add(colecaoCinco.getCartas().get(28));
        deckCinco.add(colecaoCinco.getCartas().get(30));
        deckCinco.add(colecaoCinco.getCartas().get(31));
        deckCinco.add(colecaoCinco.getCartas().get(32));
        deckCinco.add(colecaoCinco.getCartas().get(33));
        deckCinco.add(colecaoCinco.getCartas().get(34));
        deckCinco.add(colecaoCinco.getCartas().get(36));
        deckCinco.add(colecaoCinco.getCartas().get(37));
        deckCinco.add(colecaoCinco.getCartas().get(38));
        deckCinco.add(colecaoCinco.getCartas().get(40));
        deckCinco.add(colecaoCinco.getCartas().get(44));
        deckCinco.add(colecaoCinco.getCartas().get(46));
        oponentesArena.get(4).setDeck(deckCinco);

        ColecaoCartas colecaoSeis = new ColecaoCartas(oponentesArena.get(5),jogador);
        ArrayList<Cartas> deckSeis = new ArrayList<>();
        deckSeis.add(colecaoSeis.getCartas().get(2));
        deckSeis.add(colecaoSeis.getCartas().get(3));
        deckSeis.add(colecaoSeis.getCartas().get(4));
        deckSeis.add(colecaoSeis.getCartas().get(5));
        deckSeis.add(colecaoSeis.getCartas().get(6));
        deckSeis.add(colecaoSeis.getCartas().get(9));
        deckSeis.add(colecaoSeis.getCartas().get(10));
        deckSeis.add(colecaoSeis.getCartas().get(12));
        deckSeis.add(colecaoSeis.getCartas().get(13));
        deckSeis.add(colecaoSeis.getCartas().get(16));
        deckSeis.add(colecaoSeis.getCartas().get(18));
        deckSeis.add(colecaoSeis.getCartas().get(19));
        deckSeis.add(colecaoSeis.getCartas().get(20));
        deckSeis.add(colecaoSeis.getCartas().get(21));
        deckSeis.add(colecaoSeis.getCartas().get(22));
        deckSeis.add(colecaoSeis.getCartas().get(23));
        deckSeis.add(colecaoSeis.getCartas().get(25));
        deckSeis.add(colecaoSeis.getCartas().get(26));
        deckSeis.add(colecaoSeis.getCartas().get(28));
        deckSeis.add(colecaoSeis.getCartas().get(29));
        deckSeis.add(colecaoSeis.getCartas().get(30));
        deckSeis.add(colecaoSeis.getCartas().get(32));
        deckSeis.add(colecaoSeis.getCartas().get(33));
        deckSeis.add(colecaoSeis.getCartas().get(35));
        deckSeis.add(colecaoSeis.getCartas().get(36));
        deckSeis.add(colecaoSeis.getCartas().get(38));
        deckSeis.add(colecaoSeis.getCartas().get(39));
        deckSeis.add(colecaoSeis.getCartas().get(40));
        deckSeis.add(colecaoSeis.getCartas().get(41));
        deckSeis.add(colecaoSeis.getCartas().get(45));
        oponentesArena.get(5).setDeck(deckSeis);

        ColecaoCartas colecaoSete = new ColecaoCartas(oponentesArena.get(6),jogador);
        ArrayList<Cartas> deckSete = new ArrayList<>();
        deckSete.add(colecaoSete.getCartas().get(1));
        deckSete.add(colecaoSete.getCartas().get(2));
        deckSete.add(colecaoSete.getCartas().get(3));
        deckSete.add(colecaoSete.getCartas().get(5));
        deckSete.add(colecaoSete.getCartas().get(7));
        deckSete.add(colecaoSete.getCartas().get(9));
        deckSete.add(colecaoSete.getCartas().get(11));
        deckSete.add(colecaoSete.getCartas().get(13));
        deckSete.add(colecaoSete.getCartas().get(14));
        deckSete.add(colecaoSete.getCartas().get(15));
        deckSete.add(colecaoSete.getCartas().get(16));
        deckSete.add(colecaoSete.getCartas().get(18));
        deckSete.add(colecaoSete.getCartas().get(19));
        deckSete.add(colecaoSete.getCartas().get(21));
        deckSete.add(colecaoSete.getCartas().get(23));
        deckSete.add(colecaoSete.getCartas().get(24));
        deckSete.add(colecaoSete.getCartas().get(25));
        deckSete.add(colecaoSete.getCartas().get(26));
        deckSete.add(colecaoSete.getCartas().get(27));
        deckSete.add(colecaoSete.getCartas().get(28));
        deckSete.add(colecaoSete.getCartas().get(29));
        deckSete.add(colecaoSete.getCartas().get(30));
        deckSete.add(colecaoSete.getCartas().get(31));
        deckSete.add(colecaoSete.getCartas().get(33));
        deckSete.add(colecaoSete.getCartas().get(34));
        deckSete.add(colecaoSete.getCartas().get(36));
        deckSete.add(colecaoSete.getCartas().get(38));
        deckSete.add(colecaoSete.getCartas().get(39));
        deckSete.add(colecaoSete.getCartas().get(40));
        deckSete.add(colecaoSete.getCartas().get(41));
        deckSete.add(colecaoSete.getCartas().get(42));
        deckSete.add(colecaoSete.getCartas().get(43));
        deckSete.add(colecaoSete.getCartas().get(44));
        deckSete.add(colecaoSete.getCartas().get(45));
        deckSete.add(colecaoSete.getCartas().get(46));
        oponentesArena.get(6).setDeck(deckSete);
        return oponentesArena;
    }

    public void modoArena(Jogador jogador){
        boolean continua = true;
        int i = 0;
        while(continua && i<oponenteModoArena(jogador).size()){
            iniciarPartida(jogador,oponenteModoArena(jogador).get(i));
            i++;
            if (jogador.getVida() == 30){
                System.out.println("Você Venceu esta rodada\nParabens");
            }
            else {
                System.out.println("Voce perdeu esta rodada, e portanto o modo arena foi encerrado");
                continua = false;
            }
        }
    }

    public void iniciarPartida(Jogador jogador,Oponente oponente) {
        System.out.println("A partida vai começar!");
        Partida partida = new Partida();
        partida.setOponente(oponente);
        partida.iniciar(jogador,oponente);
        int i = 0;
        boolean continuaPartida = true;
        while(continuaPartida) {
            ++i;
            partida.turno();
            ++i;
            partida.turnoAutomatizado();
            if (jogador.getVida() == 0) {
                System.out.println("Voce Perdeu!!");
                continuaPartida = false;
            }
            else if (oponente.getVida() == 0) {
                jogador.setVida(30);
                System.out.println("Voce Ganhou!!");
                continuaPartida = false;
                oponente.setVida(30);
                jogador.redefinirDeck();
                oponente.redefinirDeck();
            }
            else if (jogador.getDeck().isEmpty()) {
                System.out.println("Nao ha cartas no seu Deck, Voce perdeu!!");
                continuaPartida = false;
            }
            else if (oponente.getDeck().isEmpty()) {
                System.out.println(oponente.getNomeJogador() + " ficou sem cartas no Deck, Voce Ganhou!!");
                continuaPartida = false;
                jogador.setVida(30);
                oponente.setVida(30);
                jogador.redefinirDeck();
                oponente.redefinirDeck();
            }

        }
    }
}
