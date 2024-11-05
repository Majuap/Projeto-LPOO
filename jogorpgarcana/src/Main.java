import java.util.Scanner;
import controleJogo.*;
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
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();


            switch (escolha) {
                case 1:
                    System.out.println("Antes da partida comecar e necessario que voce selecione as cartas que irao compor o seu deck");
                    System.out.println("Escolha sabiamente pois sua vitoria depende disto, lembre-se que durante a partida nao e possivel adicionar cartas ao deck.");
                    jogadorUm.inicializarDeck();

                    //menu.iniciarPartida(); // Aqui o jogo seria iniciado
                    partida.iniciar(jogadorUm);
                    partida.turno(jogadorUm);
                    break;
                case 2:
                    running = false; // Fecha o jogo
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