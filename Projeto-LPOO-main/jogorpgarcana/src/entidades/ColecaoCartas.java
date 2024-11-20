package entidades;

import exceptions.CartaNaoEncontrada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColecaoCartas {

    private List<Cartas> cartas;

    public ColecaoCartas(Jogador jogador, Jogador oponente) throws CartaNaoEncontrada{
        cartas = new ArrayList<>();
        //colocar os efeitos nas cartas

        cartas.add(new Criatura("Crocodilo da Terra", 2, "Uma besta ancestral que habita as profundezas dos pântanos, protegendo seu território com ferocidade.\nHabiliade Especial:Regeneração: Recupera 2 pontos de resistência.", 1, 5,(Runnable)() ->{Criatura carta = (Criatura) this.cartas.get(0);carta.setResistencia(carta.getResistencia() + 2,jogador,carta);} ));
        cartas.add(new Criatura("Natureza", 5, "A essência viva do mundo, capaz de criar e destruir com igual vigor.\nHabilidade Especial: Remove instantaneamente uma carta do campo do adversario", 1, 5, (Runnable)()-> {oponente.getCampo().getCemiterio().add(oponente.getCampo().getCampo().getLast());oponente.getCampo().getCampo().removeLast();}));
        cartas.add(new Criatura("Espectro", 2, "Um espírito errante preso entre mundos, trazendo medo e desespero.\nHabilidade Especial: Diminui a vida do oponente em 2", 1, 5, (Runnable)() ->oponente.setVida(oponente.getVida()-2)));
        cartas.add(new Criatura("Serpente Thalassius", 8, "Uma criatura marinha colosal que emerge das profundezas para devorar tudo em seu caminho.\nHabilidade Especial: Varre uma carta para fora do campo do adversario", 1, 5, (Runnable)()-> {oponente.getCampo().getCemiterio().add(oponente.getCampo().getCampo().getLast());oponente.getCampo().getCampo().removeLast();}));
        cartas.add(new Criatura("Dragão Itinerante", 6, "Um dragão que viaja pelos céus, espalhando chamas e caos.\nHabilidade Especial: explosão, causa 3 de dano ao oponente", 1, 5, (Runnable)()-> oponente.setVida(oponente.getVida()-3)));
        cartas.add(new Criatura("Quimera Petrificada", 1, "Uma quimera transformada em pedra, ainda emanando um poder sinistro.\nHabilidade Especial: Tira 2 de mana do adversario", 1, 10, (Runnable)()-> oponente.setMana(oponente.getMana()-2)));
        cartas.add(new Criatura("Pedaço de Azul", 8, "Um fragmento de um artefato azul.\nHabilidade Especial: Tira do adversario 2 de mana e de vida", 1, 5, (Runnable)()->{oponente.setMana(oponente.getMana()-2);oponente.setVida(oponente.getVida()-2);}));
        cartas.add(new Criatura("Soldado Imperium", 3, "Um guerreiro leal, treinado para lutar até o fim.\nHabilidade Especial: Sacrifica uma carta do campo inimigo e recupera 2 de vida do jogador", 1, 5,(Runnable)()->{oponente.getCampo().getCemiterio().add(oponente.getCampo().getCampo().getLast());oponente.getCampo().getCampo().removeLast();jogador.setVida(jogador.getVida()+2);} ));
        cartas.add(new Criatura("Soldado Legatus", 7, "Líder de um exército implacável, inspirando lealdade e força.\nHabilidade Especial: Sua resistencia aumenta em 4, e sua força em 2", 1, 5, (Runnable)() -> {Criatura carta = (Criatura) this.cartas.get(8);carta.setPoder(carta.getPoder() + 2);carta.setResistencia(carta.getResistencia() + 4,jogador,carta);}));
        cartas.add(new Criatura("O Executor", 9, "Um carrasco impiedoso, julga e executa os inimigos sem hesitação.\nHabilidade Especial: Tira 2 de Resistencia de uma carta no campo do adversario.", 1, 5, (Runnable)()->{
            for (Cartas carta : oponente.getCampo().getCampo()) {
                if (carta instanceof Criatura) {
                    Criatura criatura = (Criatura) carta;
                    criatura.setResistencia(criatura.getResistencia() - 2,oponente,criatura);
                    System.out.println("A resistência da criatura " + criatura.getNome() + " foi reduzida para " + criatura.getResistencia());
                    break;
            }
        }}));
        cartas.add(new Criatura("Anubis", 5, "O deus dos mortos, guardião das tumbas e dos segredos do além.\nHabilidade Especial: Traz uma carta de volta a vida, com o custo de 2 manas", 1, 5, (Runnable)()-> {if(!jogador.getCampo().getCemiterio().isEmpty()){
                jogador.getCampo().getCampo().add(jogador.getCampo().getCemiterio().getFirst());
                jogador.getCampo().getCemiterio().removeFirst();
                jogador.setMana(jogador.getMana()-2);
            }}));
        cartas.add(new Criatura("Rhiannon", 5, "A deusa da inspiração e da magia, traz esperança em tempos sombrios.\nHabilidade Especial:O inimigo recebe 4 de Dano", 1, 5, (Runnable)()-> oponente.setVida(oponente.getVida()-4)));


        cartas.add(new Feitico("O Dia do Caçador", 1,
                "Uma celebração da destreza e foco, onde os caçadores demonstram suas habilidades.\nEfeito: Uma criatura aleatória do campo do inimigo perde metade da sua vida.",
                (Runnable) () -> {
                    if (!oponente.getCampo().getCampo().isEmpty()) {
                        List<Cartas> criaturas = oponente.getCampo().getCampo().stream().filter(carta -> carta instanceof Criatura).collect(Collectors.toList());
                        if (!criaturas.isEmpty()) {
                            Criatura criaturaAleatoria = (Criatura) criaturas.get(new Random().nextInt(criaturas.size()));
                            criaturaAleatoria.setResistencia(criaturaAleatoria.getResistencia() / 2,oponente,criaturaAleatoria);
                            System.out.println("A resistência da criatura " + criaturaAleatoria.getNome() + " foi reduzida pela metade.");
                        }
                    }
                }
        ));

        cartas.add(new Feitico(
                "O Dia da Caça", 1, "Um dia de caos onde a natureza se torna uma arena de sobrevivência.\nEfeito: Todas as criaturas no campo ganham 2 de poder.",
                (Runnable) () -> {
                    Stream.concat(jogador.getCampo().getCampo().stream(), oponente.getCampo().getCampo().stream())
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> criatura.setPoder(criatura.getPoder() + 2));
                    System.out.println("Todas as criaturas no campo ganharam +2 de poder.");
                }
        ));

        cartas.add(new Feitico(
                "Líder", 1,
                "Uma figura inspiradora que galvaniza aliados em tempos de necessidade.\nEfeito: Todas as criaturas no campo do jogador ganham 1 de resistência.",
                (Runnable) () -> {
                    jogador.getCampo().getCampo().stream().filter(carta -> carta instanceof Criatura).map(carta -> (Criatura) carta).forEach(criatura -> criatura.setResistencia(criatura.getResistencia() + 1,jogador,criatura));
                    System.out.println("Todas as criaturas do jogador ganharam +1 de resistência.");
                }
        ));

        cartas.add(new Feitico(
                "Olho por Olho", 1,
                "Uma vingança fria e precisa, devolvendo o dano recebido ao agressor.\nEfeito: Devolve o dano de uma criatura atacante ao controlador.",
                (Runnable) () -> {
                    // Esse efeito deve ser ativado em resposta a um ataque
                    // Simulação de uma criatura atacante
                    Criatura atacante = oponente.getCampo().getCampo()
                            .stream()
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

        cartas.add(new Feitico(
                "Súbita Decadência", 1,
                "Uma maldição que enfraquece até os mais poderosos.\nEfeito: A criatura alvo recebe -3/-3 até o final do turno.",
                (Runnable) () -> {
                    if (!oponente.getCampo().getCampo().isEmpty()) {
                        Criatura alvo = (Criatura) oponente.getCampo().getCampo()
                                .stream()
                                .filter(carta -> carta instanceof Criatura)
                                .findFirst()
                                .orElse(null);
                        if (alvo != null) {
                            alvo.setPoder(alvo.getPoder() - 3);
                            alvo.setResistencia(alvo.getResistencia() - 3,jogador,alvo);
                            System.out.println("A criatura " + alvo.getNome() + " recebeu -3/-3 até o final do turno.");
                        }
                    }
                }
        ));

        cartas.add(new Feitico("Cobrança", 1,
                "Uma dívida é cobrada com juros implacáveis.\nEfeito: Causa 3 de dano ao oponente e ganhe 3 pontos de vida.",
                (Runnable)()->{oponente.setVida(oponente.getVida()-3);jogador.setVida(jogador.getVida()+3);} ));
        cartas.add(new Feitico("Olhar Grego", 1,
                "Um olhar místico que revela o destino.\nEfeito:Compre duas cartas sem gastar mana.",
                (Runnable)()->{
                for(int i=1;i<=2;i++){
                    try {
                        jogador.comprarCarta();
                    } catch (CartaNaoEncontrada e) {
                        System.out.println("O deck esta vazio.");
                    }
                }
        }));
        cartas.add(new Feitico("Discoteca Móvel", 1,
                "Um som contagiante que agita até os corações mais apáticos.\nEfeito: O jogador ganha 1 de mana e 2 de vida",
                (Runnable)()->{jogador.setVida(jogador.getVida()+2);jogador.setMana(jogador.getMana()+1);}
                ));
        cartas.add(new Feitico("Ar Puro", 1,
                "Uma brisa revigorante que limpa a mente e o corpo.\nEfeito: Ganhe 5 pontos de vida.",
                (Runnable)()->{jogador.setVida(jogador.getVida()+5);} ));
        cartas.add(new Feitico("Trabalhador Árduo", 1,
                "Dedicação e esforço trazem recompensas inesperadas.\nEfeito: Compre uma carta e ganhe 1 ponto de vida.",
                (Runnable)()->{
                try {
                    jogador.comprarCarta();
                } catch (CartaNaoEncontrada e) {
                    System.out.println("O deck esta vazio.");
                }
                jogador.setVida(jogador.getVida()+1);}));
        cartas.add(new Feitico("Custos Exitus", 1,
                "Tudo tem seu preço, mas a vitória compensa.\nEfeito: Pague 2 pontos de vida. Destrua uma carta do adversário",
                (Runnable)() -> {jogador.setVida(jogador.getVida()-2); oponente.getCampo().getCemiterio().add(oponente.getCampo().getCampo().getLast());oponente.getCampo().getCampo().removeLast();}
        ));


        cartas.add(new Encantamento(
                "Abismo",
                5,
                "Efeito Continuo: O adversário se encontra em um abismo, caindo. Confuso, o mesmo é incapaz de atacar por 2 turnos.",
                (Runnable) () -> {
                    //oponente.setPodeAtacar(false);
                    System.out.println("Abismo ativo: O oponente não pode atacar por 2 turnos.");
                    // Deve-se implementar a lógica para reverter após 2 turnos
                }
        ));

        cartas.add(new Encantamento(
                "Instinto Cruel",
                6,
                "Efeito Continuo: As criaturas do campo do adversário se sentem intimidadas, reduzindo seu poder em 0.25x por 3 turnos. Caso não haja criaturas, o adversário sofre 3 de dano por turno.",
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
        ));

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
                    oponente.setVida(oponente.getVida() + 1); // 1 ponto de vida por turno
                }
        ));

        cartas.add(new Encantamento(
                "Raízes",
                2,
                "Efeito Continuo: As criaturas do campo do inimigo terão sua resistência reduzida em 0.25x por 4 turnos.",
                (Runnable) () -> {
                    oponente.getCampo().getCampo().stream()
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> criatura.setResistencia(criatura.getResistencia() *(1/4),oponente,criatura));
                    System.out.println("Raízes ativo: Todas as criaturas inimigas tiveram sua resistência reduzida em 25%.");
                    // Deve-se implementar a lógica para restaurar a resistência após 4 turnos
                }
        ));

        cartas.add(new Encantamento(
                "O Predador e a Presa",
                3,
                "Efeito Continuo: Um jogo de gato e rato onde suas criaturas atacam primeiro, garantindo prioridade de combate por 4 turnos.",
                (Runnable) () -> {
                   // jogador.getCampo().setIniciativa(true); // Propriedade fictícia para priorizar ataques
                    System.out.println("O Predador e a Presa ativo: Suas criaturas ganham prioridade de combate.");
                }
        ));

        cartas.add(new Encantamento(
                "Coquetel Atípico",
                4,
                "Efeito Continuo: Uma mistura alquímica de efeitos inesperados. Ao ativar, causa 2 de dano a todas as criaturas no campo, incluindo as suas, por 3 turnos.",
                (Runnable) () -> {
                    Stream.concat(jogador.getCampo().getCampo().stream(), oponente.getCampo().getCampo().stream())
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> criatura.setResistencia(criatura.getResistencia() - 2,oponente,criatura));
                    System.out.println("Coquetel Atípico ativo: Todas as criaturas receberam 2 de dano.");
                    // Deve-se implementar a lógica de continuidade por 3 turnos
                }
        ));

        cartas.add(new Encantamento(
                "Poção A. Verna",
                1,
                "Efeito Continuo: Feita com ingredientes simples, esta poção envenena o adversário, fazendo-o perder 2 pontos de vida por turno, durante 3 turnos.",
                (Runnable) () -> {
                    oponente.setVida(oponente.getVida() - 2);
                    System.out.println("Poção A. Verna ativo: O oponente perdeu 2 pontos de vida.");
                    // Deve-se implementar a lógica para continuar o efeito por 3 turnos
                }
        ));

        cartas.add(new Encantamento(
                "Balança da Verdade",
                2,
                "Efeito Continuo: Um julgamento divino onde ambas as partes perdem algo valioso. Cada jogador sacrifica uma criatura e compra uma carta.",
                (Runnable) () -> {
                    if (!jogador.getCampo().getCampo().isEmpty()) {
                        jogador.getCampo().getCemiterio().add(jogador.getCampo().getCampo().removeFirst());
                    }
                    if (!oponente.getCampo().getCampo().isEmpty()) {
                        oponente.getCampo().getCemiterio().add(oponente.getCampo().getCampo().removeFirst());
                    }
                    try {
                        jogador.comprarCarta();
                        oponente.comprarCarta();
                    } catch (CartaNaoEncontrada e) {
                        System.out.println("O deck esta vazio.");
                    }
                    System.out.println("Balança da Verdade ativo: Ambos sacrificaram uma criatura e compraram uma carta.");
                }
        ));

        cartas.add(new Encantamento(
                "Exemplar Ancestral",
                9,
                "Efeito Continuo: Um artefato sagrado que concede um bônus imensurável. Todas as suas criaturas recebem +3/+3 enquanto este encantamento estiver ativo.",
                (Runnable) () -> {
                    jogador.getCampo().getCampo().stream()
                            .filter(carta -> carta instanceof Criatura)
                            .map(carta -> (Criatura) carta)
                            .forEach(criatura -> {
                                criatura.setPoder(criatura.getPoder() + 3);
                                criatura.setResistencia(criatura.getResistencia() + 3,jogador,criatura);
                            });
                    System.out.println("Exemplar Ancestral ativo: Todas as suas criaturas receberam +3/+3.");
                }
        ));

        cartas.add(new Encantamento(
                "Artefato Índigo",
                7,
                "Efeito Continuo: Um fragmento místico que pulsa com energia mágica. Enquanto ativo, você pode usar habilidades de cartas no cemitério como se estivessem no campo.",
                (Runnable) () -> {
                    //jogador.getCampo().setHabilidadesDoCemiterio(true); // Propriedade fictícia para gerenciar o efeito
                    System.out.println("Artefato Índigo ativo: Habilidades no cemitério estão disponíveis.");
                }
        ));

    }

    public List<Cartas> getCartas() {
        return cartas;
    }

}
