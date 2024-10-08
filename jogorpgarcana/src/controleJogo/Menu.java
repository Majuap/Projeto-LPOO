package controleJogo;

public class Menu {

    public void iniciarPartida() {
        System.out.println("A partida vai começar!");

        // Aqui você pode instanciar a classe Partida e chamar o método que inicia o jogo
        Partida partida = new Partida();
        partida.iniciar();
    }
}
