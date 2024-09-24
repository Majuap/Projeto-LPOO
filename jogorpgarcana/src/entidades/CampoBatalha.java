package entidades;

import java.util.ArrayList;
import java.util.List;

public class CampoBatalha {
    private List<Criatura> criaturas;
    private List<Cartas> cemiterio;
    private List<Efeitos> efeitosCartas:

    public CampoBatalha() {
        criaturas = new ArrayList<>();
        cemiterio = new ArrayList<>();
        efeitoCartas = new ArrayList<>();
        //efeitoCartas.add(...), adicionar os efeitos das cartas ou verificar outra forma dela "funcionar"
    }

    public void adicionarCriatura(Criatura criatura) {
        criaturas.add(criatura);
    }

    public void moverParaCemiterio(Cartas carta) {
        cemiterio.add(carta);
    }

    // Outros métodos para gerenciar o campo
}
