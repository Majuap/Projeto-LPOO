package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import entidades.Mao;

public class Oponente extends Jogador {

    public Oponente (String nome){
        super(nome);
        //Pré definir as cartas do adversario no main?? Pose ser aqui mesmo.
    }
    public void jogar(CampoBatalha campo) {
        // O oponente compra uma carta
        comprarCarta();

        // Lógica simples para jogar uma carta ou atacar
        if (!mao.isEmpty()) {
            Random rand = new Random();
            int indiceCarta = rand.nextInt(mao.size());
            Cartas carta = mao.get(indiceCarta);

            if (carta.getCustoMana() <= mana) {
                jogarCarta(indiceCarta);
            } else if (!campo.getCriaturas(this).isEmpty()) {
                // Atacar com uma criatura
                
                int indiceCriatura = rand.nextInt(campo.getCriaturas(this).size());
                Criatura criaturaAtacante = campo.getCriaturas(this).get(indiceCriatura);

            }
        }
    }

}