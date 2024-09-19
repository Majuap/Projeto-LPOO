package controleJogo;

import entidades.Jogador;
import entidades.CampoBatalha;

public class Partida {
    private Jogador jogador1;
    private Jogador jogador2;
    private CampoBatalha campo;

    public Partida(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.campo = new CampoBatalha();
    }

    public void iniciar() {
        //inicial do jogo: distribuir cartas, definir ordem de turnos
    }

    public void turno(Jogador jogador) {
        jogador.comprarCarta();
        // O jogador faz suas jogadas, invoca cartas, ataca
    }

    public boolean verificarFimDeJogo() {
        // Verifica se a partida acabou
        return jogador1.getVida() <= 0 || jogador2.getVida() <= 0;
    }

    // Outros métodos para o controle da partida
}
