package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CampoBatalha {
    // Listas de cartas no campo de batalha e no cemitério
    private List<Cartas> campo;
    private List<Cartas> cemiterio;
    //private List<Cartas> efeitoCartas; // Pode conter Feitico ou Encantamento

    public CampoBatalha() {
        campo = new ArrayList<>();
        cemiterio = new ArrayList<>();
        //efeitoCartas = new ArrayList<>();
    }

    // Metodo para adicionar uma criatura ao campo de batalha
    public void adicionarCarta(Cartas carta) {
        campo.add(carta);
    }

    // Metodo para mover uma carta para o cemitério

    public List<Criatura> getCriaturasNoCampo() {
        return campo.stream()
                .filter(carta -> carta instanceof Criatura)
                .map(carta -> (Criatura) carta)
                .collect(Collectors.toList());
    }


    public List<Cartas> getCampo() {
        return campo;
    }
    public void setCampo(List<Cartas> campo) {
        this.campo = campo;
    }
    public List<Cartas> getCemiterio() {
        return cemiterio;
    }
    public void setCemiterio(List<Cartas> cemiterio) {
        this.cemiterio = cemiterio;
    }
    //não precisa de um metodo invocar feitico, nem criatura nem encantamento, somente o invocar carta
}

