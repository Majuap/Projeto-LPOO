package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColecaoCartas {
    private List<Cartas> cartas;

    public ColecaoCartas(){
        cartas = new ArrayList<>();

        cartas.add(new Criatura("Crocodilo da Terra", 2, 1, 5, ""));
        cartas.add(new Criatura("Natureza", 5, 1, 5, ""));
        cartas.add(new Criatura("Espectro", 2, 1, 5, ""));
        cartas.add(new Criatura("Serpente Thalassius", 8, 1, 5, ""));
        cartas.add(new Criatura("Dragão Itinerante", 6, 1, 5, ""));
        cartas.add(new Criatura("Quimera Petrificada", 1, 1, 10, ""));
        cartas.add(new Criatura("Pedaço de Azul", 8, 1, 5, ""));
        cartas.add(new Criatura("Soldado Imperium", 3, 1, 5, ""));
        cartas.add(new Criatura("Soldado Legatus", 7, 1, 5, ""));
        cartas.add(new Criatura("O executor", 9, 1, 5, ""));
        cartas.add(new Criatura("Anubis", 5, 1, 5, ""));
        cartas.add(new Criatura("Rhiannon",5, 1, 5, ""));

        cartas.add(new Feitico( "O dia do Caçador", 1, ""));
        cartas.add(new Feitico( "O dia da Caça", 1, ""));
        cartas.add(new Feitico( "Líder", 1, ""));
        cartas.add(new Feitico( "Olho por Olho", 1, ""));
        cartas.add(new Feitico( "Súbita Decadência", 1, ""));
        cartas.add(new Feitico( "Cobrança", 1, ""));
        cartas.add(new Feitico( "Olhar Grego", 1, ""));
        cartas.add(new Feitico( "Discoteca Móvel", 1, ""));
        cartas.add(new Feitico( "Ar Puro", 1,""));
        cartas.add(new Feitico( "Trabalhador Árduo", 1, ""));
        cartas.add(new Feitico( "Custos Exitus", 1,""));

        cartas.add(new Encantamento( "Abismo",5,"O adversário se encontra em um abismo, caindo. Confuso, o mesmo é incapaz de atacar por 2 turnos."));
        cartas.add(new Encantamento( "Instinto Cruel",6, "As cartas do tipo criatura no campo do adversário irão se sentir intimidadas, seu ataque diminui em 0.25x por 3 turnos, caso não haja nenhuma destas o adversário sofre 3 de dano por turno."));
        cartas.add(new Encantamento( "O Indelevel",3," Preso no precioso ciclo da natureza seu adversário encara a morte e a vida, uma carta aleatória de seu campo é levada ao cemitério, porém sua vida aumenta em 1, por 2 turnos."));
        cartas.add(new Encantamento( "Raizes", 2, "As cartas do tipo criatura no campo do inimigo terão sua resistência enfraquecida 0.25x por 4 turnos."));
        cartas.add(new Encantamento( "O predador e a Presa", 3, "Neste jogo de gato e rato vence o mais ágil, sua cartas têm prioridade de ataque por 4 turnos."));
        cartas.add(new Encantamento( "Coquetel Atipico", 4, ""));
        cartas.add(new Encantamento( "Poção A. Verna", 1, "Os elementos que compoem esta poção são facilmente encontrados nos arredores da cidade, causa envenenamento, o adversário perderá  de vida por 3 turnos"));
        cartas.add(new Encantamento( "Balança da Verdade", 2, "Na balança seus pecados são expostos. Se durante 3 turnos alguma carta sua for para o cemitério, uma carta aleatória , que esteja em campo,do adversário também irá."));
        cartas.add(new Encantamento( "Exemplar Ancestral", 9, ""));
        cartas.add(new Encantamento( "Artefato Índigo", 7, "String efeitocontinuo"));
    }

    public List<Cartas> getCartas() {
        return cartas;
    }

    public void setCartas(List<Cartas> cartas) {
        this.cartas = cartas;
    }
}
