package entidades;

public class Encantamento extends Cartas {
    private Object efeitoContinuo;
    private int turnodoefeitoContinuo;


    public Encantamento(String nome, int custoMana, String descricao,Object efeitoContinuo,int turnodoefeitoContinuo) {
        super(nome, custoMana, descricao);
        this.efeitoContinuo = efeitoContinuo;
        this.turnodoefeitoContinuo = turnodoefeitoContinuo;
    }
    public void aplicarEfeito() {
        if (efeitoContinuo instanceof Runnable) {
            ((Runnable) efeitoContinuo).run();
            System.out.println("O encantamento " + getNomeCarta() + " foi ativado!");
        }
    }

    @Override
    public String toString() {
        return "\nNome: " + nome + "\n" +
                "Custo: " + custoMana + "\n" +
                "Descrição: " + descricao + "\n";
    }

}
