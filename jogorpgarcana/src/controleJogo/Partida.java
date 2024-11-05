package controleJogo;

import entidades.Jogador;
import entidades.CampoBatalha;
import entidades.Cartas;
import entidades.Oponente;
import entidades.Feitico;
import entidades.Encantamento;
import entidades.Criatura;
import exceptions.CartaNaoEncontrada;
import exceptions.ManaInsuficiente;
import exceptions.SemVida;

import java.util.Scanner;

public class Partida {
    private Jogador jogador;
    private Oponente oponente;
    private CampoBatalha campo;

    public Partida() {
        this.jogador =new Jogador("");
        this.oponente = new Oponente("Demian");
        this.campo = new CampoBatalha();
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void iniciar(Jogador jogadorA) {
        // Inicialização dos jogadores e do campo
        System.out.println("Iniciando a partida...");
        setJogador(jogadorA);
        // Distribuição de cartas para os jogadores
        jogador.distribuirCartas(jogador.getDeck(), 5);
        oponente.distribuirCartas(oponente.getDeck(), 5);
        
        // Exibir informações iniciais
        System.out.println("Jogador 1: " + jogador.getNomeJogador() + " com " + jogador.getCartasNaMao().size() + " cartas.");
        System.out.println("Jogador 2: " + oponente.getNomeJogador() + " com " + oponente.getCartasNaMao().size() + " cartas.");
    }
    
    private int escolherAcao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma ação: ");
        System.out.println("1. Invocar uma carta");
        System.out.println("2. Atacar o oponente");
        System.out.println("3. Atacar uma criatura no campo");
        System.out.println("4. Comprar carta do Deck");
        System.out.println("5. Passar a vez");
        return scanner.nextInt();
    }

    public void turno(Jogador jogador) throws CartaNaoEncontrada,ManaInsuficiente, SemVida {
        //jogador.comprarCarta();, o jogador nao necessariamente precisa comprar carta no turno
        int escolha = escolherAcao();// Captura a escolha do jogador
        jogador.getDeck();
        jogador.getCartasNaMao();
        //em um turno da para fazer mais de uma coisa
        switch (escolha) {
            case 1: // Invocar
                Cartas cartaInvocada = escolherCarta(jogador); // Agora passamos o jogador como parâmetro
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

            case 2: // Atacar o oponente
                if (!campo.getCriaturas().isEmpty()) {
                    //Criatura criaturaAtacante = escolherCriatura(); // Escolher a criatura para atacar
                    //oponente.receberAtaque(); // O oponente recebe o ataque
                } else {
                    System.out.println("Você não tem criaturas para atacar!");
                }
                break;

            case 3://atacar uma criatura no campo
                if (!campo.getCriaturas().isEmpty()) {
                    //Criatura criaturaAtacante = escolherCriatura(); // Escolher a criatura para atacar
                    //oponente.receberAtaque(); // a carta no campo do adversario recebe o ataque
                } else {
                    System.out.println("Você não tem criaturas para atacar!");
                }
                break;
            case 4://comprar carta do deck
                jogador.comprarCarta();
                break;
            case 5: // Passar
                System.out.println("Você passou a vez.");
                break;
            default:
                System.out.println("Ação inválida!");
                break;
        }
    }
    
    private Cartas escolherCarta(Jogador jogador) {
        // Exibe as cartas disponíveis na mão para escolha
        jogador.getCartasNaMao();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escolha o índice da carta para jogar: ");
        int indice = scanner.nextInt();
        return jogador.getCartasNaMao().get(indice); // Retorna a carta escolhida
    }

    public boolean verificarFimDeJogo() {
        // Verifica se a partida acabou
        return jogador.getVida() <= 0 || oponente.getVida() <= 0;
    }
}

