import java.util.Scanner;
import controleJogo.*;
import controleJogo.Menu;
import entidades.Jogador;
import exceptions.CartaNaoEncontrada;
import exceptions.ManaInsuficiente;
import exceptions.SemVida;

public class Main {
     public static void main(String[] args) throws CartaNaoEncontrada, ManaInsuficiente, SemVida {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        boolean running = true;
        while (running) {
            System.out.println("Bem-vindo ao RPG Arcana!");

            System.out.println("Insira a seguir o seu nome:");
            String nomeJogador = scanner.next();
            Jogador jogadorUm = new Jogador(nomeJogador);
            Partida partida = new Partida();
            System.out.println("1. Jogar");
            System.out.println("2. Modo Arena");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    menu.oponentes(jogadorUm);
                    System.out.println("Antes da partida comecar e necessario que voce selecione as cartas que irao compor o seu deck");
                    System.out.println("Escolha sabiamente pois sua vitoria depende disto,\nlembre-se que durante a partida nao e possivel adicionar cartas ao deck.");
                    jogadorUm.inicializarDeck(jogadorUm, menu.getOponente());
                    menu.iniciarPartida(jogadorUm,menu.getOponente()); // Aqui o jogo seria iniciado

                case 2:
                    System.out.println("Antes da partida comecar e necessario que voce selecione as cartas que irao compor o seu deck");
                    System.out.println("Escolha sabiamente pois sua vitoria depende disto,\nlembre-se que durante a partida nao e possivel adicionar cartas ao deck.");
                    jogadorUm.inicializarDeck(jogadorUm, menu.getOponente());
                    menu.modoArena(jogadorUm); // Aqui o jogo seria iniciado
                case 3:
                    System.out.println("Saindo do jogo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}