package entidades;
import java.util.ArrayList;
import java.util.List;

public class CampoBatalha {
    //private List<Criatura> criaturas;
    private List<Cartas> cemiterio;
    private List<Efeitos> efeitoCartas;
    //não somente criaturas se encontrarão no campo de batalha, como também as cartas com efeito(somente por um turno) e efeito continuo

    public CampoBatalha() {
        //criaturas = new ArrayList<>();
        cemiterio = new ArrayList<>();
        efeitoCartas = new ArrayList<>();
        //efeitoCartas.add(...), adicionar os efeitos das cartas ou verificar outra forma dela "funcionar"
    }

    public void adicionarCriatura(Criatura criatura) {criaturas.add(criatura);
    }

    public void moverParaCemiterio(Cartas carta) {cemiterio.add(carta);
    }

    public List<Efeitos> getEfeitoCartas() {
        return efeitoCartas;
    }
    public List<Cartas> getCemiterio() {
        return cemiterio;
    }

    // Outros métodos para gerenciar o campo
}
