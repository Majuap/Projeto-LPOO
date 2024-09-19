package entidades;

public abstract class Cartas {
    private String nome;
    private int custoMana;

    public Cartas(String nome, int custoMana) {
        this.nome = nome;
        this.custoMana = custoMana;
    }

    public String getNome() {
        return nome;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public abstract void usar();
}
