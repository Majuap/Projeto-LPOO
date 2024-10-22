package entidades;

public abstract class Cartas {
    protected String nome;
    protected int custoMana;

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

    public String getNomeCarta() {
        return this.getNomeCarta();
    }

    public abstract void usarCarta();
}
