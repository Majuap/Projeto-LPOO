package controleJogo;

import entidades.Jogador;

import java.util.List;

import entidades.CampoBatalha;
import entidades.Cartas;
import entidades.Oponente;
import entidades.Feitico;
import entidades.Encantamento;
import entidades.Criatura;


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
        // Inicialização dos jogadores e do campo
        System.out.println("Iniciando a partida...");
        
        List<Cartas> mao;
        // Distribuição de cartas para os jogadores
        jogador.distribuirCartas(mao,5); // mao nao pode ser tratado como variavel. CORRIGIR isso depois.
        oponente.distribuirCartas(mao, 5);
        
        // Exibir informações iniciais
        System.out.println("Jogador 1: " + jogador.getNomeJogador() + " com " + jogador.getCartasNaMão().size() + " cartas.");
        System.out.println("Jogador 2: " + oponente.getNomeJogador() + " com " + oponente.getCartasNaMão().size() + " cartas.");
    }
    
    private int escolherAcao(int escolha){
        //as opções usadas na partida
        return escolha;
    }

   public void turno(Jogador jogador) {
    jogador.comprarCarta();
    int escolha = this.escolherAcao(escolha);  // Deve passar o int para escolher uma ação
    switch (escolha) {
        case 1: // invocar
            Cartas cartaInvocada = escolherCarta(); // Deve retornar uma subclasse de Cartas
            if (cartaInvocada instanceof Feitico) {
                Feitico feiticoLancado = (Feitico) cartaInvocada;
                campo.invocarFeitico(jogador, feiticoLancado);
            } else if (cartaInvocada instanceof Encantamento) {
                Encantamento encantamentoUsado = (Encantamento) cartaInvocada;
                campo.invocarEncantamento(jogador, encantamentoUsado);
            } else if (cartaInvocada instanceof Criatura) {
                Criatura criaturaInvocada = (Criatura) cartaInvocada;
                campo.invocarCriatura(jogador, criaturaInvocada);
            }
            break;

        case 2: // atacar
            if (!campo.getCriaturas(jogador).isEmpty()) {
                Criatura criaturaAtacante = escolherCriatura(); // Escolher a criatura para atacar
                oponente.receberAtaque(criaturaAtacante); // O oponente recebe o ataque
            } else {
                System.out.println("Você não tem criaturas para atacar!");
            }
            break;

        case 3: // passar
            System.out.println("Você passou a vez.");
            break;

        default:
            System.out.println("Ação inválida!");
            break;
    }
}
    
    
    private Cartas escolherCarta() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'escolherCarta'");
}

    public boolean verificarFimDeJogo() {
        // Verifica se a partida acabou
         return jogador.getVida() <= 0 || oponente.getVida() <= 0;
    }

    
}
