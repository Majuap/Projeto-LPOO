package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Cartas> cartas;

    public Deck(){
        //instanciando cartas, algumas estão sem os seus atributos especificos, adicionar depois
        //numero atual de cartas: 
        cartas = new ArrayList<>();
        
        cartas.add(new Criatura( “Crocodilo da Terra”, , int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Natureza”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Espectro”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Serpente Thalassius”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Dragão Itinerante”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Quimera Petrificada”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Pedaço de Azul”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Soldado Imperium”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Soldado Legatus”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“O executor”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Anubis”, int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Criatura(“Rhiannon” int custoMana, int poder, int resistencia, String habilidadeEspecial));
        cartas.add(new Feitico( ”O dia do Caçador”, int customana, String efeito));
        cartas.add(new Feitico( “O dia da Caça”, int customana, String efeito));
        cartas.add(new Feitico( “Líder” int customana, String efeito));
        cartas.add(new Feitico( “Olho por Olho”, int customana, String efeito));
        cartas.add(new Feitico( “Súbita Decadência”, int customana, String efeito));
        cartas.add(new Feitico( “Cobrança”, int customana, String efeito));
        cartas.add(new Feitico( “Olhar Grego”, int customana, String efeito));
        cartas.add(new Feitico( “Discoteca Móvel”, int customana, String efeito));
        cartas.add(new Feitico( “Ar Puro”, int customana, String efeito));
        cartas.add(new Feitico( “Trabalhador Árduo”, int customana, String efeito));
        cartas.add(new Feitico( “Custos Exitus”, int customana, String efeito));
        cartas.add(new Encantamento( “Abismo”,5,”O adversário se encontra em um abismo, caindo. Confuso, o mesmo é incapaz de atacar por 2 turnos.”));
        cartas.add(new Encantamento( “Instinto Cruel”,6,” As cartas do tipo criatura no campo do adversário irão se sentir intimidadas, seu ataque diminui em 0.25x por 3 turnos, caso não haja nenhuma destas o adversário sofre 3 de dano por turno.”));
        cartas.add(new Encantamento( “O Indelevel”,3,” Preso no precioso ciclo da natureza seu adversário encara a morte e a vida, uma carta aleatória de seu campo é levada ao cemitério, porém sua vida aumenta em 1, por 2 turnos.”));
        cartas.add(new Encantamento( “Raizes”, 2, “As cartas do tipo criatura no campo do inimigo terão sua resistência enfraquecida 0.25x por 4 turnos.”));
        cartas.add(new Encantamento( “O predador e a Presa”, 3, “Neste jogo de gato e rato vence o mais ágil, sua cartas têm prioridade de ataque por 4 turnos.”));
        cartas.add(new Encantamento( “Coquetel Atipico”, int customana, String efeitocontinuo));
        cartas.add(new Encantamento( “Poção A. Verna”, int customana, String efeitocontinuo));
        cartas.add(new Encantamento( “Balança da Verdade”, 2, “Na balança seus pecados são expostos. Se durante 3 turnos alguma carta sua for para o cemitério, uma carta aleatória , que esteja em campo,do adversário também irá.”));
        cartas.add(new Encantamento( “Exemplar Ancestral”, int customana, String efeitocontinuo));
        cartas.add(new Encantamento( “Artefato Índigo”, int customana, String efeitocontinuo));
    }

    /* public Deck(List<Cartas> cartas) {
        this.cartas = new ArrayList<>(cartas);
        embaralhar();
    }*/

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Cartas comprarCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);  // Remove a carta do topo do deck
        }
        return null;  // Se não houver mais cartas
    }

    public boolean estaVazio() {
        return cartas.isEmpty();
    }
}

