package entidades;

public class Criatura extends Cartas {
    private int poder;
    private int resistencia;
    private String habilidadeEspecial;

    public Criatura(String nome, int custoMana, int poder, int resistencia, String habilidadeEspecial) {
        super(nome, custoMana);
        this.poder = poder;
        this.resistencia = resistencia;
        this.habilidadeEspecial = habilidadeEspecial;
    }

    @Override
    public void usar() {

    }

    // Getters e setters
}

