package entidades;

import java.util.ArrayList;
import java.util.List;

public class Mao {
    protected List<Cartas> cartasNaMao;

    public Mao() {
        this.cartasNaMao = new ArrayList<>();
    }

    // Metodo para adicionar uma carta à mão
    public void adicionarCarta(Cartas carta) {
        if (cartasNaMao.size() < 10) { // Supondo que a mão tem limite de 10 cartas
            cartasNaMao.add(carta);
        } else {
            System.out.println("Não é possível adicionar mais cartas à mão.");
        }
    }

    // Metodo para remover uma carta da mão
    public Cartas removerCarta(Cartas carta) {
        if (cartasNaMao.remove(carta)) {
            return carta;
        } else {
            System.out.println("A carta não está na mão.");
            return null;
        }
    }
     
    public Cartas removerCarta(int indice) {
        if (indice >= 0 && indice < cartasNaMao.size()) {
            return cartasNaMao.remove(indice);
        } else {
            System.out.println("Índice inválido.");
            return null;
        }
    }

    // metodo para verificar o número de cartas na mão
    public int getQuantidadeDeCartas() {
        return cartasNaMao.size();
    }

    // Metodo para exibir todas as cartas na mão
    public void exibirCartas() {
        if (cartasNaMao.isEmpty()) {
            System.out.println("A mão está vazia.");
        } else {
            System.out.println("Cartas na mão:");
            for (Cartas carta : cartasNaMao) {
                System.out.println(carta.getNomeCarta()); // Cartas tenha o metodo getNomeCarta()
            }
        }
    }

    public List<Cartas> getCartas() {
        return cartasNaMao;
    }
}

