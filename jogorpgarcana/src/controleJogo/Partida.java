package controleJogo;

import entidades.Jogador;
import entidades.CampoBatalha;
import entidades.Oponente;

public class Partida {
    private Jogador jogador;
    private Oponente oponente;
    private CampoBatalha campo;

    public Partida() {
        Jogador jogadorUm = new Jogador("Lucan");
        Jogador jogadorDois = new Jogador("Prometheus");
        this.campo = new CampoBatalha();
    }

    public void iniciar() {
        //inicial do jogo: distribuir cartas, definir ordem de turnos
    }

    private int escolherAcao(int escolha){
        //as opções usadas na partida
        return escolha;
    }

    public void turno(Jogador jogador) {
        
        jogador.comprarCarta();
        String acao = this.escolherAcao();
        switch (acao) {
        case "invocar":
            // O jogador escolhe uma carta para invocar
            Carta cartaInvocada = escolherCarta(); 
            campo.invocarCarta(jogador, cartaInvocada);
            break;
        case "atacar":
            // O jogador escolhe um oponente para atacar
            if (!campo.getCriaturas(jogador).isEmpty()) {
                Criatura criaturaAtacante = escolherCriatura(); // Escolher a criatura para atacar
                oponente.receberAtaque(criaturaAtacante); // O oponente recebe o ataque
            } else {
                System.out.println("Você não tem criaturas para atacar!");
            }
            break;
        case "passar":
            System.out.println("Você passou a vez.");
            break;
        default:
            System.out.println("Ação inválida!");
            break;
    }
    
    }
    public boolean verificarFimDeJogo() {
        // Verifica se a partida acabou
         return jogador.getVida() <= 0 || oponente.getVida() <= 0;
    }

    // Outros métodos para o controle da partida
}
