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

import java.util.Random;
import java.util.Scanner;

public class Partida {
    private Jogador jogador;
    private Oponente oponente;
    private CampoBatalha campo;

    public Partida() {
        this.jogador = getJogador();
        this.oponente = getOponente();
        this.campo = new CampoBatalha();
    }

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

    public void iniciar(Jogador jogador,Oponente oponente) {
        // Inicialização dos jogadores e do campo
        System.out.println("Iniciando a partida...");
        setJogador(jogador);
        setOponente(oponente);
        //embaralhar o deck
        jogador.embaralhar();
        oponente.embaralhar();
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
        System.out.println("4. Usar habilidade Especial de uma Criatura no campo");
        System.out.println("5. Comprar carta do Deck");
        System.out.println("6. Passar a vez");
        return scanner.nextInt();
    }


    public void turno(){
        jogador.setMana(jogador.getMana() + 1); // Adiciona 1 de mana no início do turno
        Scanner scanner = new Scanner(System.in);
        boolean continuarTurno = true; // Controla se o turno continua

        while (continuarTurno) { // Continua pedindo ações enquanto o jogador não passar a vez
            System.out.println(jogador.statusDoJogador());
            System.out.println("Cartas na Mao:");
            for (int indiceA = 0;indiceA<jogador.getCartasNaMao().size();indiceA++){
                System.out.println(indiceA+".-"+jogador.getCartasNaMao().get(indiceA));
            }
            System.out.println("Cartas no Campo:");
            if (!jogador.getCampo().getCampo().isEmpty()) {
                int indiceB = 0;
                while (indiceB < jogador.getCampo().getCampo().size()) {
                    System.out.println(indiceB + ".-" + jogador.getCampo().getCampo().get(indiceB));
                    indiceB++;
                }
            }
            int escolha = escolherAcao(); // Captura a escolha do jogador

            switch (escolha) {
                case 1: // Invocar, colocar a carta no campo
                    Cartas cartaInvocada = escolherCarta(jogador);

                    if (cartaInvocada instanceof Feitico && cartaInvocada!=null) {
                        Feitico feiticoInvocado = (Feitico) cartaInvocada;
                        jogador.setMana(jogador.getMana() - feiticoInvocado.getCustoMana());
                        jogador.getCartasNaMao().remove(cartaInvocada);
                        feiticoInvocado.aplicarEfeito();
                        jogador.getCampo().getCemiterio().add(feiticoInvocado);
                    } else if (cartaInvocada instanceof Encantamento && cartaInvocada!=null) {
                        Encantamento encantamentoInvocado = (Encantamento) cartaInvocada;
                        jogador.setMana(jogador.getMana() - encantamentoInvocado.getCustoMana());
                        jogador.getCampo().getCampo().add(encantamentoInvocado);
                        jogador.getCartasNaMao().remove(cartaInvocada);
                        encantamentoInvocado.aplicarEfeito();
                    } else if (cartaInvocada instanceof Criatura && cartaInvocada!=null) {
                        Criatura criaturaInvocada = (Criatura) cartaInvocada;
                        jogador.setMana(jogador.getMana() - criaturaInvocada.getCustoMana());
                        jogador.getCampo().getCampo().add(criaturaInvocada);
                        jogador.getCartasNaMao().remove(cartaInvocada);
                    }
                    break;

                case 2: // Atacar o oponente
                    if (!jogador.getCampo().getCriaturasNoCampo().isEmpty()) {
                        int indiceC = 0;
                        while (indiceC < jogador.getCampo().getCriaturasNoCampo().size()) {
                            System.out.println(indiceC + ".-" + jogador.getCampo().getCriaturasNoCampo().get(indiceC));
                            indiceC++;
                        }
                        System.out.println("Escolha o índice da criatura para atacar");
                        int indice = scanner.nextInt();
                        if (indice < jogador.getCampo().getCriaturasNoCampo().size()) {
                            Criatura criaturaAtacante = jogador.getCampo().getCriaturasNoCampo().get(indice);
                            oponente.setVida(oponente.getVida() - criaturaAtacante.getPoder());
                        } else {
                            System.out.println("Índice inválido!");
                        }
                    } else {
                        System.out.println("Você não tem criaturas no seu campo!");
                    }
                    break;

                case 3: // Atacar uma criatura no campo
                    if (!jogador.getCampo().getCriaturasNoCampo().isEmpty() && !oponente.getCampo().getCriaturasNoCampo().isEmpty()) {
                        int indiceD = 0;
                        while (indiceD < jogador.getCampo().getCriaturasNoCampo().size()) {
                            System.out.println(indiceD + ".-" + jogador.getCampo().getCriaturasNoCampo().get(indiceD));
                            indiceD++;
                        }
                        System.out.println("Escolha o índice da criatura para atacar");
                        int indice = scanner.nextInt();
                        if (indice<jogador.getCampo().getCriaturasNoCampo().size()) {
                            int indiceE = 0;
                            while (indiceE < oponente.getCampo().getCriaturasNoCampo().size()) {
                                System.out.println(indiceE + ".-" + oponente.getCampo().getCriaturasNoCampo().get(indiceE));
                                indiceE++;
                            }
                            System.out.println("Escolha o índice de uma criatura no campo inimigo");
                            int indiceAlvo = scanner.nextInt();
                            if (indiceAlvo<oponente.getCampo().getCriaturasNoCampo().size()) {
                                oponente.getCampo().getCriaturasNoCampo().get(indiceAlvo).setResistencia(oponente.getCampo().getCriaturasNoCampo().get(indiceAlvo).getResistencia() - jogador.getCampo().getCriaturasNoCampo().get(indice).getPoder(), oponente, oponente.getCampo().getCriaturasNoCampo().get(indiceAlvo));
                            }
                            else {
                                System.out.println("Indice Invalido");
                            }
                        }

                        else {
                            System.out.println("Indice Invalido");
                        }
                    }
                        else if (jogador.getCampo().getCriaturasNoCampo().isEmpty()) {
                        System.out.println("Você não tem criaturas no seu campo!");
                    }
                        else {
                        System.out.println("O oponente não tem criaturas no campo!");
                    }
                    break;

                case 4: // Usar habilidade especial de uma criatura no campo
                    if (!jogador.getCampo().getCriaturasNoCampo().isEmpty()) {
                        int indiceF=0;
                        while (indiceF < jogador.getCampo().getCriaturasNoCampo().size()) {
                            System.out.println(indiceF + ".-" + jogador.getCampo().getCriaturasNoCampo().get(indiceF));
                            indiceF++;
                        }
                        System.out.println("Escolha o índice da criatura para usar a habilidade especial");
                        int indice = scanner.nextInt();
                        if (indice < jogador.getCampo().getCriaturasNoCampo().size()) {
                            jogador.getCampo().getCriaturasNoCampo().get(indice).aplicarEfeito();
                        }
                        else{
                            System.out.println("Indice Invalido");
                        }
                    }
                    else {
                        System.out.println("Você não tem criaturas no seu campo!");
                    }
                    break;

                case 5: // Comprar carta do deck
                    jogador.comprarCarta();
                    break;

                case 6: // Passar a vez
                    System.out.println("Você passou a vez.");
                    continuarTurno = false; // Finaliza o turno
                    break;

                default:
                    System.out.println("Ação inválida!");
                    break;
            }
        }
    }

    public void turnoAutomatizado() {
        Random random = new Random();
        oponente.setMana(oponente.getMana() + 1); // Oponente recebe 1 de mana no início do turno

        boolean continuarTurno = true; // Controle do loop do turno

        while (continuarTurno) { // Enquanto o oponente não passar a vez
            System.out.println(oponente.statusDoJogador());
            System.out.println("Cartas no Campo:\n"+oponente.getCampo().getCampo()+"\n");
            int escolha = random.nextInt(6); // Escolhe uma ação entre 0 e 5
            switch (escolha) {
                case 0: // Invocar uma carta
                    if (!oponente.getCartasNaMao().isEmpty()) {
                        Cartas cartaInvocada = oponente.getCartasNaMao().get(random.nextInt(oponente.getCartasNaMao().size()));
                        if (oponente.getMana() >= cartaInvocada.getCustoMana()) {
                            oponente.setMana(oponente.getMana() - cartaInvocada.getCustoMana());
                            oponente.getCartasNaMao().remove(cartaInvocada);

                            if (cartaInvocada instanceof Feitico) {
                                Feitico feitico = (Feitico) cartaInvocada;
                                feitico.aplicarEfeito();
                                System.out.println("Oponente usou o feitiço: " + feitico.getNome());
                            } else if (cartaInvocada instanceof Encantamento) {
                                Encantamento encantamento = (Encantamento) cartaInvocada;
                                oponente.getCampo().adicionarCarta(encantamento);
                                encantamento.aplicarEfeito();
                                System.out.println("Oponente invocou o encantamento: " + encantamento.getNome());
                            } else if (cartaInvocada instanceof Criatura) {
                                Criatura criatura = (Criatura) cartaInvocada;
                                oponente.getCampo().adicionarCarta(criatura);
                                System.out.println("Oponente invocou a criatura: " + criatura.getNome());
                            }
                        } else {
                            System.out.println("Oponente não tem mana suficiente para invocar uma carta.");
                        }
                    } else {
                        System.out.println("Oponente não tem cartas na mão para invocar.");
                    }
                    break;

                case 1: // Atacar o jogador
                    if (!oponente.getCampo().getCriaturasNoCampo().isEmpty()) {
                        Criatura criaturaAtacante = oponente.getCampo().getCriaturasNoCampo()
                                .get(random.nextInt(oponente.getCampo().getCriaturasNoCampo().size()));
                        jogador.setVida(jogador.getVida() - criaturaAtacante.getPoder());
                        System.out.println("Oponente atacou você com: " + criaturaAtacante.getNome() +
                                " causando " + criaturaAtacante.getPoder() + " de dano.");
                    } else {
                        System.out.println("Oponente não tem criaturas no campo para atacar.");
                    }
                    break;

                case 2: // Atacar uma criatura no campo do jogador
                    if (!oponente.getCampo().getCriaturasNoCampo().isEmpty() && !jogador.getCampo().getCriaturasNoCampo().isEmpty()) {
                        Criatura atacante = oponente.getCampo().getCriaturasNoCampo()
                                .get(random.nextInt(oponente.getCampo().getCriaturasNoCampo().size()));
                        Criatura alvo = jogador.getCampo().getCriaturasNoCampo()
                                .get(random.nextInt(jogador.getCampo().getCriaturasNoCampo().size()));
                        alvo.setResistencia(alvo.getResistencia() - atacante.getPoder(), jogador, alvo);
                        System.out.println("Oponente atacou sua criatura " + alvo.getNome() +
                                " com " + atacante.getNome() + ".");
                    } else {
                        System.out.println("Oponente ou você não tem criaturas suficientes para um combate no campo.");
                    }
                    break;

                case 3: // Usar habilidade especial de uma criatura
                    if (!oponente.getCampo().getCriaturasNoCampo().isEmpty()) {
                        Criatura criatura = oponente.getCampo().getCriaturasNoCampo()
                                .get(random.nextInt(oponente.getCampo().getCriaturasNoCampo().size()));
                        criatura.aplicarEfeito();
                        System.out.println("Oponente usou habilidade especial de: " + criatura.getNome());
                    } else {
                        System.out.println("Oponente não tem criaturas no campo para usar habilidades.");
                    }
                    break;

                case 4: // Comprar carta do deck
                    if (!oponente.getDeck().isEmpty()) {
                        oponente.comprarCarta();
                        System.out.println("Oponente comprou uma carta.");
                    } else {
                        System.out.println("Oponente não pode comprar mais cartas. Deck vazio.");
                    }
                    break;

                case 5: // Passar a vez
                    System.out.println("Oponente passou a vez.");
                    continuarTurno = false; // Encerra o loop do turno
                    break;
            }
        }
    }

    private Cartas escolherCarta(Jogador jogador) {
        // Exibe as cartas disponíveis na mão para escolha
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escolha o índice da carta para jogar: ");
        int indice = scanner.nextInt();
        if(indice< jogador.getCartasNaMao().size()) {
            Cartas carta = jogador.getCartasNaMao().get(indice);
            if (carta.getCustoMana() <= jogador.getMana()) {
                return jogador.getCartasNaMao().get(indice); // Retorna a carta escolhida
            } else {
                System.out.println("Mana Insuficiente");
                return null;
            }
        }
        else{
                System.out.println("Indice Invalido");
                return null;
            }
    }

    public boolean verificarFimDeJogo() {
        // Verifica se a partida acabou
        return jogador.getVida() <= 0 || oponente.getVida() <= 0;
    }
}

