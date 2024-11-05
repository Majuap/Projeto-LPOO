package entidades;

import exceptions.CartaNaoEncontrada;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Oponente extends Jogador {

    public Oponente(String nome) {
        super(nome);
        // Aqui você pode inicializar o deck com as cartas do oponente se desejar.
    }

    public void jogar(CampoBatalha campo) throws CartaNaoEncontrada {
        // O oponente compra uma carta
        comprarCarta();

        // Lógica para decidir jogar uma carta ou atacar
        if (!mao.cartasNaMao.isEmpty()) {
            Random rand = new Random();
            int indiceCarta = rand.nextInt(mao.cartasNaMao.size());
            Cartas carta = mao.cartasNaMao.get(indiceCarta);

            if (carta.getCustoMana() <= mana) {
                jogarCarta(indiceCarta); // Usa a carta se o custo de mana for suficiente
            } else if (!campo.getCriaturas().isEmpty()) {
                // Caso não possa jogar a carta, tenta atacar com uma criatura
                int indiceCriatura = rand.nextInt(campo.getCriaturas().size());
                Criatura criaturaAtacante = campo.getCriaturas().get(indiceCriatura);
                
                // Supondo que você tem um metodo atacar() para a criatura
                //criaturaAtacante.atacar();//consertar depois
            }
        }
    }

    public List<Cartas> getCartasNaMao() {
        return mao.getCartas();  // Chama o metodo getCartas() da classe Mao
    }
}
