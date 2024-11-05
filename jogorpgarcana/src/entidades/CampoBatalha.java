package entidades;

import java.util.ArrayList;
import java.util.List;

public class CampoBatalha {
    // Listas de cartas no campo de batalha e no cemitério
    private List<Criatura> criaturas;
    private List<Cartas> cemiterio;
    private List<Cartas> efeitoCartas; // Pode conter Feitico ou Encantamento

    public CampoBatalha() {
        criaturas = new ArrayList<>();
        cemiterio = new ArrayList<>();
        efeitoCartas = new ArrayList<>();
    }

    // Método para adicionar uma criatura ao campo de batalha
    public void adicionarCriatura(Criatura criatura) {
        criaturas.add(criatura);
    }

    // Método para mover uma carta para o cemitério
    public void moverParaCemiterio(Cartas carta) {
        cemiterio.add(carta);
    }

    // Método para adicionar uma carta de efeito ao campo (Feitico ou Encantamento)
    public void adicionarEfeito(Cartas cartaComEfeito) {
        if (cartaComEfeito instanceof Feitico || cartaComEfeito instanceof Encantamento) {
            efeitoCartas.add(cartaComEfeito);
        } else {
            System.out.println("Somente Feitiços ou Encantamentos podem ser adicionados como efeitos.");
        }
    }

    // Getters
    public List<Criatura> getCriaturas() {
        return criaturas;
    }

    public List<Cartas> getCemiterio() {
        return cemiterio;
    }

    public List<Cartas> getEfeitoCartas() {
        return efeitoCartas;
    }

    // Exemplo de método invocar uma criatura
    public void invocarCriatura(Jogador jogador, Criatura criatura) {
        adicionarCriatura(criatura);
        System.out.println(jogador.getNomeJogador() + " invocou a criatura " + criatura.getNomeCarta());
    }

    // Exemplo de método invocar um feitiço
    public void invocarFeitico(Jogador jogador, Feitico feitico) {
        adicionarEfeito(feitico);
        System.out.println(jogador.getNomeJogador() + " lançou o feitiço " + feitico.getNomeCarta());
    }
    //não precisa de um metodo invocar feitico, nem criatura nem encantamento, somente o invocar carta

    // Exemplo de método invocar um encantamento
    public void invocarEncantamento(Jogador jogador, Encantamento encantamento) {
        adicionarEfeito(encantamento);
        System.out.println(jogador.getNomeJogador() + " ativou o encantamento " + encantamento.getNomeCarta());
    }
    public void escolherCriatura(List<Criatura> getCriaturas){

    }
    public void receberAtaque(List<Criatura> getCriaturas){

    }
}

