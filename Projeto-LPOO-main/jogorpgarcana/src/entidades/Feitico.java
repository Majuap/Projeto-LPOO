package entidades;

public class Feitico extends Cartas {
    private Object efeito;

    public Feitico(String nome, int custoMana, String descricao,Object efeito) {
        super(nome, custoMana, descricao);
        this.efeito = efeito;
    }

    public void aplicarEfeito() {
        if (efeito instanceof Runnable) {
            ((Runnable) efeito).run();
        }
    }

    @Override
    public String toString() {
        return "\nNome: " + nome + "\n" +
                "Custo: " + custoMana + "\n" +
                "Descrição: " + descricao + "\n";
    }


}