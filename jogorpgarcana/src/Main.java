import java.util.Scanner;
import controleJogo.*;

public class Main {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        boolean running = true;
        while (running) {
            System.out.println("Bem-vindo ao RPG Arcana!");
            System.out.println("1. Jogar");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    menu.iniciarPartida(); // Aqui o jogo seria iniciado
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