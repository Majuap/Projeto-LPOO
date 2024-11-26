package entidades;

public abstract class Cartas {
    protected String nome;
    protected int custoMana;
    protected String descricao;

    public Cartas(String nome, int custoMana, String descricao) {
        this.nome = nome;
        this.custoMana = custoMana;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public String getNomeCarta() {
        return this.nome;
    }

    public void aplicarEfeito(){}

    public String getDescricao() {
        return descricao;
    }

    //public abstract void usarCarta();
}
