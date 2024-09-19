package entidades;

import java.util.ArrayList;
import java.util.List;

public class CampoBatalha {
    private List<Criatura> criaturas;
    private List<Cartas> cemiterio;

    public CampoBatalha() {
        criaturas = new ArrayList<>();
        cemiterio = new ArrayList<>();
    }

    public void adicionarCriatura(Criatura criatura) {
        criaturas.add(criatura);
    }

    public void moverParaCemiterio(Cartas carta) {
        cemiterio.add(carta);
    }

    // Outros métodos para gerenciar o campo
}
