package entidades;

import exceptions.CartaNaoEncontrada;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Oponente extends Jogador {

    public Oponente(String nome) {
        super(nome);
    }
    public String statusDoJogador(){
        return "Jogador: " + getNomeJogador() + "\n" +
                "Vida: " + getVida() + "\n";
    }
}
