package Interface;

import entidades.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JogoDeCartasGUI extends JFrame {

    private JPanel painelPrincipal;
    private CardLayout layoutTelas;
    private List<Cartas> cartasDisponiveis;  // Lista de cartas disponíveis

    public JogoDeCartasGUI() {
        // Inicializa a janela principal
        Jogador jogador = new Jogador("Nome1");
        Oponente oponente = new Oponente("Demian");
        setTitle("Crônicas de Arcana - Jogo de Cartas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layoutTelas = new CardLayout();
        painelPrincipal = new JPanel(layoutTelas);

        // Adiciona telas ao painel principal
        painelPrincipal.add(criarTelaInicial(), "TelaInicial");
        painelPrincipal.add(criarMenu(), "Menu");
        painelPrincipal.add(criarTelaEscolherCartas(), "TelaEscolherCartas");

        add(painelPrincipal);

        // Inicia o jogo na Tela Inicial
        layoutTelas.show(painelPrincipal, "TelaInicial");

        cartasDisponiveis = new ArrayList<>();
        inicializarCartas(jogador,oponente);  // Inicializa as cartas
    }

    // Tela Inicial onde o jogador entra com seu nome
    private JPanel criarTelaInicial() {
        JPanel telaInicial = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Bem-vindo ao Jogo de Cartas!", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));

        JPanel painelCentral = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel labelNome = new JLabel("Digite seu nome:");
        JTextField campoNome = new JTextField();

        JButton botaoAvancar = new JButton("Avançar");
        botaoAvancar.addActionListener(e -> {
            String nomeJogador = campoNome.getText().trim();
            if (nomeJogador.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um nome válido!");
            } else {
                layoutTelas.show(painelPrincipal, "Menu");
            }
        });

        painelCentral.add(labelNome);
        painelCentral.add(campoNome);

        telaInicial.add(titulo, BorderLayout.NORTH);
        telaInicial.add(painelCentral, BorderLayout.CENTER);
        telaInicial.add(botaoAvancar, BorderLayout.SOUTH);

        return telaInicial;
    }

    // Menu Principal onde o jogador escolhe suas opções
    private JPanel criarMenu() {
        JPanel menu = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Menu do Jogo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));

        JButton botaoEscolherCartas = new JButton("Escolher Cartas");
        botaoEscolherCartas.addActionListener(e -> layoutTelas.show(painelPrincipal, "TelaEscolherCartas"));

        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(e -> System.exit(0));

        menu.add(titulo, BorderLayout.NORTH);
        menu.add(botaoEscolherCartas, BorderLayout.CENTER);
        menu.add(botaoSair, BorderLayout.SOUTH);

        return menu;
    }

    // Tela de Escolha de Cartas
    private JPanel criarTelaEscolherCartas() {
        JPanel telaEscolherCartas = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Escolha suas Cartas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel painelCartas = new JPanel(new GridLayout(0, 1)); // Lista de cartas em uma coluna

        // Exibe as cartas disponíveis
        for (Cartas carta : cartasDisponiveis) {
            JButton botaoCarta = new JButton(carta.getNome());
            // Adiciona MouseListener para mostrar o toString da carta quando o mouse passar
            botaoCarta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Exibe os detalhes da carta no JToolTip ao passar o mouse
                    botaoCarta.setToolTipText(carta.toString());
                }
            });

            botaoCarta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exibirDetalhesCarta(carta);
                }
            });
            painelCartas.add(botaoCarta);
        }

        telaEscolherCartas.add(titulo, BorderLayout.NORTH);
        telaEscolherCartas.add(painelCartas, BorderLayout.CENTER);

        return telaEscolherCartas;
    }

    // Exibe os detalhes sobre a carta selecionada
    private void exibirDetalhesCarta(Cartas carta) {
        JPanel painelDetalhes = new JPanel(new BorderLayout());

        // Exibe o nome, custo, e descrição da carta
        JTextArea areaDetalhes = new JTextArea();
        areaDetalhes.setText(carta.toString());
        areaDetalhes.setEditable(false);
        painelDetalhes.add(areaDetalhes, BorderLayout.CENTER);

        // Adiciona botão para aplicar o efeito da carta
        JButton botaoAplicarEfeito = new JButton("Aplicar Efeito");
        botaoAplicarEfeito.addActionListener(e -> {
            carta.aplicarEfeito();
            JOptionPane.showMessageDialog(this, "Efeito aplicado na carta: " + carta.getNome());
        });
        painelDetalhes.add(botaoAplicarEfeito, BorderLayout.SOUTH);

        // Exibe a tela de detalhes da carta
        JOptionPane.showMessageDialog(this, painelDetalhes, "Detalhes da Carta", JOptionPane.INFORMATION_MESSAGE);
    }

    // Inicializa as cartas do jogo
    private void inicializarCartas(Jogador jogador,Jogador oponente) {
        List<Cartas> cartas = new ArrayList<>();
        //colocar os efeitos nas cartas
        cartas.add(new Criatura("Crocodilo da Terra", 2,
                "Uma besta ancestral que habita as profundezas dos pântanos, protegendo seu território com ferocidade.\nHabilidade Especial: Regeneração: Recupera 2 pontos de resistência.",
                1, 5, (Runnable) () -> {
            if (!cartas.isEmpty() && cartas.get(0) instanceof Criatura) {
                Criatura carta = (Criatura) cartas.get(0);
                carta.setResistencia(carta.getResistencia() + 2, jogador, carta);
            } else {
                System.out.println("Carta inválida.");
            }
        }));
        cartas.add(new Criatura("Natureza", 5,
                "A essência viva do mundo, capaz de criar e destruir com igual vigor.\nHabilidade Especial: Remove instantaneamente uma carta do campo do adversário.",
                1, 5, (Runnable) () -> {
            if (!oponente.getCampo().getCampo().isEmpty()) {
                Cartas ultimaCarta = oponente.getCampo().getCampo().getLast();
                oponente.getCampo().getCemiterio().add(ultimaCarta);
                oponente.getCampo().getCampo().removeLast();
            } else {
                System.out.println("Não há cartas no campo do adversário.");
            }
        }));
        cartas.add(new Criatura("Espectro", 2,
                "Um espírito errante preso entre mundos, trazendo medo e desespero.\nHabilidade Especial: Diminui a vida do oponente em 2.",
                1, 5, (Runnable) () -> oponente.setVida(oponente.getVida() - 2)));
        cartas.add(new Criatura("Serpente Thalassius", 8,
                "Uma criatura marinha colossal que emerge das profundezas para devorar tudo em seu caminho.\nHabilidade Especial: Remove uma carta do campo do adversário.",
                1, 5, (Runnable) () -> {
            if (!oponente.getCampo().getCampo().isEmpty()) {
                Cartas ultimaCarta = oponente.getCampo().getCampo().getLast();
                oponente.getCampo().getCemiterio().add(ultimaCarta);
                oponente.getCampo().getCampo().removeLast();
            } else {
                System.out.println("Não há cartas no campo do adversário.");
            }
        }));
        cartas.add(new Criatura("Dragão Itinerante", 6,
                "Um dragão que viaja pelos céus, espalhando chamas e caos.\nHabilidade Especial: Explosão, causa 3 de dano ao oponente.",
                1, 5, (Runnable) () -> oponente.setVida(oponente.getVida() - 3)));
        cartas.add(new Criatura("Quimera Petrificada", 1,
                "Uma quimera transformada em pedra, ainda emanando um poder sinistro.\nHabilidade Especial: Tira 2 de mana do adversário.",
                1, 10, (Runnable) () -> oponente.setMana(oponente.getMana() - 2)));
        cartas.add(new Criatura("Pedaço de Azul", 8,
                "Um fragmento de um artefato azul.\nHabilidade Especial: Tira do adversário 2 de mana e 2 de vida.",
                1, 5, (Runnable) () -> {
            oponente.setMana(oponente.getMana() - 2);
            oponente.setVida(oponente.getVida() - 2);
        }));
        cartas.add(new Criatura("Soldado Imperium", 3,
                "Um guerreiro leal, treinado para lutar até o fim.\nHabilidade Especial: Sacrifica uma carta do campo inimigo e recupera 2 de vida.",
                1, 5, (Runnable) () -> {
            if (!oponente.getCampo().getCampo().isEmpty()) {
                Cartas ultimaCarta = oponente.getCampo().getCampo().getLast();
                oponente.getCampo().getCemiterio().add(ultimaCarta);
                oponente.getCampo().getCampo().removeLast();
                jogador.setVida(jogador.getVida() + 2);
            } else {
                System.out.println("Não há cartas no campo do adversário.");
            }
        }));
        cartas.add(new Criatura("Soldado Legatus", 7,
                "Líder de um exército implacável, inspirando lealdade e força.\nHabilidade Especial: Sua resistência aumenta em 4, e sua força em 2.",
                1, 5, (Runnable) () -> {
            Criatura carta = (Criatura) cartas.get(cartas.size() - 1); // Busca última carta
            carta.setPoder(carta.getPoder() + 2);
            carta.setResistencia(carta.getResistencia() + 4, jogador, carta);
        }));
        cartas.add(new Criatura("O Executor", 9,
                "Um carrasco impiedoso, julga e executa os inimigos sem hesitação.\nHabilidade Especial: Tira 2 de resistência de uma carta no campo do adversário.",
                1, 5, (Runnable) () -> {
            for (Cartas carta : oponente.getCampo().getCampo()) {
                if (carta instanceof Criatura) {
                    Criatura criatura = (Criatura) carta;
                    criatura.setResistencia(criatura.getResistencia() - 2, oponente, criatura);
                    System.out.println("A resistência da criatura " + criatura.getNome() + " foi reduzida para " + criatura.getResistencia());
                    break;
                }
            }
        }));
        cartas.add(new Criatura("Anubis", 5,
                "O deus dos mortos, guardião das tumbas e dos segredos do além.\nHabilidade Especial: Traz uma carta de volta à vida, com o custo de 2 manas.",
                1, 5, (Runnable) () -> {
            if (!jogador.getCampo().getCemiterio().isEmpty() && jogador.getMana() >= 2) {
                Cartas primeiraCarta = jogador.getCampo().getCemiterio().getFirst();
                jogador.getCampo().getCampo().add(primeiraCarta);
                jogador.getCampo().getCemiterio().removeFirst();
                jogador.setMana(jogador.getMana() - 2);
            } else {
                System.out.println("Não há cartas no cemitério ou mana insuficiente.");
            }
        }));
        cartas.add(new Criatura("Rhiannon", 5,
                "A deusa da inspiração e da magia, traz esperança em tempos sombrios.\nHabilidade Especial: O inimigo recebe 4 de dano.",
                1, 5, (Runnable) () -> oponente.setVida(oponente.getVida() - 4)));


        cartas.add(new Feitico("O Dia do Caçador", 1,
                "Uma celebração da destreza e foco, onde os caçadores demonstram suas habilidades.\nEfeito: Uma criatura aleatória do campo do inimigo perde metade da sua vida.",
                (Runnable) () -> {
                    if (!oponente.getCampo().getCampo().isEmpty()) {
                        List<Cartas> criaturas = oponente.getCampo().getCampo().stream()
                                .filter(carta -> carta instanceof Criatura)
                                .collect(Collectors.toList());
                        if (!criaturas.isEmpty()) {
                            Criatura criaturaAleatoria = (Criatura) criaturas.get(new Random().nextInt(criaturas.size()));
                            criaturaAleatoria.setResistencia(criaturaAleatoria.getResistencia() / 2, oponente, criaturaAleatoria);
                            System.out.println("A resistência da criatura " + criaturaAleatoria.getNome() + " foi reduzida pela metade.");
                        }
                    }
                }
        ));

        cartas.add(new Feitico("O Dia da Caça", 1,
                "Um dia de caos onde a natureza se torna uma arena de sobrevivência.\nEfeito: Todas as criaturas no campo ganham 2 de poder.",
                (Runnable) () -> {
                    Stream.concat(jogador.getCampo().getCampo().stream(), oponente.getCampo().getCampo().stream())
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> criatura.setPoder(criatura.getPoder() + 2));
                    System.out.println("Todas as criaturas no campo ganharam +2 de poder.");
                }
        ));

        cartas.add(new Feitico("Líder", 1,
                "Uma figura inspiradora que galvaniza aliados em tempos de necessidade.\nEfeito: Todas as criaturas no campo do jogador ganham 1 de resistência.",
                (Runnable) () -> {
                    jogador.getCampo().getCampo().stream()
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> criatura.setResistencia(criatura.getResistencia() + 1, jogador, criatura));
                    System.out.println("Todas as criaturas do jogador ganharam +1 de resistência.");
                }
        ));

        cartas.add(new Feitico("Olho por Olho", 1,
                "Uma vingança fria e precisa, devolvendo o dano recebido ao agressor.\nEfeito: Devolve o dano de uma criatura atacante ao controlador.",
                (Runnable) () -> {
                    Criatura atacante = oponente.getCampo().getCampo().stream()
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .findFirst()
                            .orElse(null);
                    if (atacante != null) {
                        int dano = atacante.getPoder();
                        oponente.setVida(oponente.getVida() - dano);
                        System.out.println("Olho por Olho: O oponente recebeu " + dano + " de dano.");
                    }
                }
        ));

        cartas.add(new Feitico("Súbita Decadência", 1,
                "Uma maldição que enfraquece até os mais poderosos.\nEfeito: A criatura alvo recebe -3/-3 até o final do turno.",
                (Runnable) () -> {
                    Criatura alvo = (Criatura) oponente.getCampo().getCampo().stream()
                            .filter(carta -> carta instanceof Criatura)
                            .findFirst()
                            .orElse(null);
                    if (alvo != null) {
                        alvo.setPoder(alvo.getPoder() - 3);
                        alvo.setResistencia(alvo.getResistencia() - 3, jogador, alvo);
                        System.out.println("A criatura " + alvo.getNome() + " recebeu -3/-3 até o final do turno.");
                    }
                }
        ));

        cartas.add(new Feitico("Cobrança", 1,
                "Uma dívida é cobrada com juros implacáveis.\nEfeito: Causa 3 de dano ao oponente e ganhe 3 pontos de vida.",
                (Runnable) () -> {
                    oponente.setVida(oponente.getVida() - 3);
                    jogador.setVida(jogador.getVida() + 3);
                }
        ));

        cartas.add(new Feitico("Olhar Grego", 1,
                "Um olhar místico que revela o destino.\nEfeito: Compre duas cartas sem gastar mana.",
                (Runnable) () -> {
                    for (int i = 0; i < 2; i++) {
                        jogador.comprarCarta();
                    }
                }
        ));

        cartas.add(new Feitico("Discoteca Móvel", 1,
                "Um som contagiante que agita até os corações mais apáticos.\nEfeito: O jogador ganha 1 de mana e 2 de vida.",
                (Runnable) () -> {
                    jogador.setVida(jogador.getVida() + 2);
                    jogador.setMana(jogador.getMana() + 1);
                }
        ));

        cartas.add(new Feitico("Ar Puro", 1,
                "Uma brisa revigorante que limpa a mente e o corpo.\nEfeito: Ganhe 5 pontos de vida.",
                (Runnable) () -> jogador.setVida(jogador.getVida() + 5)
        ));

        cartas.add(new Feitico("Trabalhador Árduo", 1,
                "Dedicação e esforço trazem recompensas inesperadas.\nEfeito: Compre uma carta e ganhe 1 ponto de vida.",
                (Runnable) () -> {
                    jogador.comprarCarta();
                    jogador.setVida(jogador.getVida() + 1);
                }
        ));

        cartas.add(new Feitico("Custos Exitus", 1,
                "Tudo tem seu preço, mas a vitória compensa.\nEfeito: Pague 2 pontos de vida. Destrua uma carta do adversário.",
                (Runnable) () -> {
                    jogador.setVida(jogador.getVida() - 2);
                    oponente.getCampo().getCemiterio().add(oponente.getCampo().getCampo().getLast());
                    oponente.getCampo().getCampo().removeLast();
                }
        ));


        cartas.add(new Encantamento(
                "Abismo",
                5,
                "Efeito Continuo: O adversário se encontra em um abismo, caindo. Confuso, o mesmo é incapaz de atacar por 2 turnos.",
                (Runnable) () -> {
                    // Adicione lógica para rastrear turnos e reverter o efeito
                    //oponente.setPodeAtacar(false);
                    System.out.println("Abismo ativo: O oponente não pode atacar por 2 turnos.");
                }
                ,2));

        cartas.add(new Encantamento(
                "Instinto Cruel",
                6,
                "Efeito Continuo: As criaturas do campo do adversário se sentem intimidadas, reduzindo seu poder em 25% por 3 turnos. Caso não haja criaturas, o adversário sofre 3 de dano por turno.",
                (Runnable) () -> {
                    if (oponente.getCampo().getCampo().stream().noneMatch(carta -> carta instanceof Criatura)) {
                        oponente.setVida(oponente.getVida() - 3);
                        System.out.println("Instinto Cruel ativo: O oponente sofreu 3 de dano por não ter criaturas no campo.");
                    } else {
                        oponente.getCampo().getCampo().stream()
                                .filter(carta -> carta instanceof Criatura)
                                .map(carta -> (Criatura) carta)
                                .forEach(criatura -> criatura.setPoder((int) (criatura.getPoder() * 0.75)));
                        System.out.println("Instinto Cruel ativo: Todas as criaturas do oponente tiveram seu poder reduzido em 25%.");
                    }
                }
                ,3));

        cartas.add(new Encantamento(
                "O Indelével",
                3,
                "Efeito Continuo: Preso no ciclo da natureza, uma carta aleatória do campo do adversário é enviada ao cemitério. Em troca, o adversário ganha 1 ponto de vida por 2 turnos.",
                (Runnable) () -> {
                    if (!oponente.getCampo().getCampo().isEmpty()) {
                        Cartas removida = oponente.getCampo().getCampo().remove(new Random().nextInt(oponente.getCampo().getCampo().size()));
                        oponente.getCampo().getCemiterio().add(removida);
                        System.out.println("O Indelével ativo: Uma carta aleatória foi enviada ao cemitério.");
                    }
                    oponente.setVida(oponente.getVida() + 1);
                }
                ,2));

        cartas.add(new Encantamento(
                "Raízes",
                5,
                "Efeito Continuo: As criaturas do campo do inimigo terão sua resistência reduzida em 25% por 4 turnos.",
                (Runnable) () -> {
                    oponente.getCampo().getCampo().stream()
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> criatura.setResistencia((int) (criatura.getResistencia() * 0.75), oponente, criatura));
                    System.out.println("Raízes ativo: Todas as criaturas inimigas tiveram sua resistência reduzida em 25%.");
                }
                ,4));

        cartas.add(new Encantamento(
                "O Predador e a Presa",
                3,
                "Efeito Continuo: Você ganha 2 de vida e o seu oponente 1 de mana por 2 turnos.",
                (Runnable) () -> {
                    jogador.setVida(jogador.getVida() + 2);
                    oponente.setMana(oponente.getMana() + 1);
                }
                ,2));

        cartas.add(new Encantamento(
                "Coquetel Atípico",
                4,
                "Efeito Continuo: Uma mistura alquímica de efeitos inesperados. Causa 2 de dano a todas as criaturas no campo, incluindo as suas, por 3 turnos.",
                (Runnable) () -> {
                    Stream.concat(jogador.getCampo().getCampo().stream(), oponente.getCampo().getCampo().stream())
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> criatura.setResistencia(criatura.getResistencia() - 2, oponente, criatura));
                    System.out.println("Coquetel Atípico ativo: Todas as criaturas receberam 2 de dano.");
                }
                ,3));

        cartas.add(new Encantamento(
                "Poção A. Verna",
                1,
                "Efeito Continuo: Feita com ingredientes simples, esta poção envenena o adversário, fazendo-o perder 2 pontos de vida por turno, durante 3 turnos.",
                (Runnable) () -> {
                    oponente.setVida(oponente.getVida() - 2);
                    System.out.println("Poção A. Verna ativo: O oponente perdeu 2 pontos de vida.");
                }
                ,3));

        cartas.add(new Encantamento(
                "Balança da Verdade",
                2,
                "Efeito Continuo: Um julgamento divino onde ambas as partes perdem algo valioso. Cada jogador sacrifica uma criatura e compra uma carta, por 2 turnos.",
                (Runnable) () -> {
                    if (!jogador.getCampo().getCampo().isEmpty()) {
                        jogador.getCampo().getCemiterio().add(jogador.getCampo().getCampo().removeFirst());
                    }
                    if (!oponente.getCampo().getCampo().isEmpty()) {
                        oponente.getCampo().getCemiterio().add(oponente.getCampo().getCampo().removeFirst());
                    }
                    jogador.comprarCarta();
                    oponente.comprarCarta();
                    System.out.println("Balança da Verdade ativo: Ambos sacrificaram uma criatura e compraram uma carta.");
                }
                ,2));

        cartas.add(new Encantamento(
                "Exemplar Ancestral",
                9,
                "Efeito Continuo: Um artefato sagrado que concede um bônus imensurável. Todas as suas criaturas recebem +3/+3 enquanto este encantamento estiver ativo, por 3 turnos.",
                (Runnable) () -> {
                    jogador.getCampo().getCampo().stream()
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> {
                                criatura.setPoder(criatura.getPoder() + 3);
                                criatura.setResistencia(criatura.getResistencia() + 3, jogador, criatura);
                            });
                    System.out.println("Exemplar Ancestral ativo: Todas as suas criaturas receberam +3/+3.");
                }
                ,3));

        cartas.add(new Encantamento(
                "Artefato Índigo",
                7,
                "Efeito Continuo: Um fragmento místico que pulsa com energia mágica. Enquanto ativo, você pode usar habilidades de cartas no cemitério como se estivessem no campo por 2 turnos.",
                (Runnable) () -> {
                    // Deve-se implementar lógica adicional para selecionar e usar habilidades do cemitério
                    if (!jogador.getCampo().getCemiterio().isEmpty()) {
                        jogador.getCampo().getCemiterio().getFirst().aplicarEfeito();
                        System.out.println("Artefato Índigo ativo: Uma habilidade presente no cemitério está disponível.");
                    }
                }
                ,2));

        //cartas mais fracas
        cartas.add(new Criatura("Golem de Sucata", 2, "Um golem ecológico.\nHabilidade Especial: Dá 2 de vida a você", 5, 2, (Runnable) () -> {
            jogador.setVida(jogador.getVida() + 2);
        }));

        cartas.add(new Criatura("Armadura Amaldiçoada", 1, "Uma armadura que de vez em quando anda por aí.\nHabilidade Especial: Tira 1 de mana do oponente", 1, 1, (Runnable) () -> {
            oponente.setMana(oponente.getMana() - 1);
        }));

        cartas.add(new Criatura("Cephalotus", 2, "Uma planta carnívora lendária. Habilidade Especial: Da 2 de dano ao adversário.", 1, 3, (Runnable) () -> {
            oponente.setVida(oponente.getVida()-1);
        }));

        cartas.add(new Criatura("Sísifo", 3, "Uma figura mitológica que carrega o peso infinito.\nHabilidade Especial: Reduz 1 de vida de ambos os jogadores ao entrar em campo.", 2, 4, (Runnable) () -> {
            jogador.setVida(jogador.getVida() - 1);
            oponente.setVida(oponente.getVida() - 1);
        }));

        cartas.add(new Feitico("Lago Estrelado", 1, "Recupera 2 de vida.", (Runnable) () -> {
            jogador.setVida(jogador.getVida() + 2);
        }));

        cartas.add(new Feitico("Chuva de Granizo", 2, "Causa 1 de dano a todas as criaturas no campo.", (Runnable) () -> {
            Stream.concat(jogador.getCampo().getCampo().stream(), oponente.getCampo().getCampo().stream())
                    .filter(carta -> carta instanceof Criatura)
                    .map(carta -> (Criatura) carta)
                    .forEach(criatura -> criatura.setResistencia(criatura.getResistencia() - 1, jogador, criatura));
        }));

        cartas.add(new Feitico("Troca", 1, "Você e o adversário trocam seus valores de vida.", (Runnable) () -> {
            int i = jogador.getVida();
            int j = oponente.getVida();
            oponente.setVida(i);
            jogador.setVida(j);
        }));
        cartas.add(new Feitico("Fogo Fátuo", 1, "Causa 2 de dano direto ao oponente.", (Runnable) () -> {
            oponente.setVida(oponente.getVida() - 2);
        }));

        cartas.add(new Feitico("Orvalho da Manhã", 1, "Cura 1 de vida em todas as suas criaturas.", (Runnable) () -> {
            jogador.getCampo().getCampo().stream()
                    .filter(carta -> carta instanceof Criatura)
                    .map(carta -> (Criatura) carta)
                    .forEach(criatura -> criatura.setResistencia(criatura.getResistencia() + 1, jogador, criatura));
        }));
        cartas.add(new Feitico("Raio Singelo", 1, "Causa 1 de dano a uma criatura inimiga.", (Runnable) () -> {
            if (!oponente.getCampo().getCampo().isEmpty()) {
                Criatura alvo = (Criatura) oponente.getCampo().getCampo().get(0);
                alvo.setResistencia(alvo.getResistencia() - 1, oponente, alvo);
            }
        }));
        cartas.add(new Encantamento("Brisa Calmante", 1, "Efeito Contínuo: Reduz o dano causado por criaturas inimigas em 1 por 2 turnos.",  (Runnable) () -> {
            oponente.getCampo().getCampo().stream()
                    .filter(carta -> carta instanceof Criatura)
                    .map(carta -> (Criatura) carta)
                    .forEach(criatura -> criatura.setPoder(criatura.getPoder() - 1));
        },2));
        cartas.add(new Encantamento("Armadura de Pedra", 1, "Efeito Contínuo: Suas criaturas recebem +1 de resistência por 3 turnos.",  (Runnable) () -> {
            jogador.getCampo().getCampo().stream()
                    .filter(carta -> carta instanceof Criatura)
                    .map(carta -> (Criatura) carta)
                    .forEach(criatura -> criatura.setResistencia(criatura.getResistencia() + 1, jogador, criatura));
        },3));
        cartas.add(new Criatura("Fada Luminosa", 1, "Ilumina o campo e cura 1 de vida ao jogador ao entrar.", 1, 1, (Runnable) () -> {
            jogador.setVida(jogador.getVida() + 1);
        }));

        cartas.add(new Criatura("Sapo Venenoso", 1, "Causa 1 de dano a uma criatura ao entrar em campo.", 1, 1, (Runnable) () -> {
            if (!oponente.getCampo().getCampo().isEmpty()) {
                Criatura alvo = (Criatura) oponente.getCampo().getCampo().get(0);
                alvo.setResistencia(alvo.getResistencia() - 1, oponente, alvo);
            }
        }));
        cartas.add(new Feitico("Chama Breve", 1, "Causa 2 de dano em uma criatura específica.", (Runnable) () -> {
            if (!oponente.getCampo().getCampo().isEmpty()) {
                Criatura alvo = (Criatura) oponente.getCampo().getCampo().get(0);
                alvo.setResistencia(alvo.getResistencia() - 2, oponente, alvo);
            }
        }));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JogoDeCartasGUI jogoGUI = new JogoDeCartasGUI();
            jogoGUI.setVisible(true);
        });
    }
}
