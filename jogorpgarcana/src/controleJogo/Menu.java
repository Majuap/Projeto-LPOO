package controleJogo;

import entidades.Jogador;

public class Menu {
    public void oponentes(){
        //instanciar oponentes aqui, e o jogador podera escolhe-los como adversarios em uma partida
    }
    public void iniciarPartida() {
        System.out.println("A partida vai começar!");
        // Aqui você pode instanciar a classe Partida e chamar o metodo que inicia o jogo
        Partida partida = new Partida();
        //partida.iniciar();
    }
}
