package entidades;

public class Encantamento extends Cartas {
    private Object efeitoContinuo;
    //private String efeitoContinuo;

    public Encantamento(String nome, int custoMana, String descricao,Object efeitoContinuo) {
        super(nome, custoMana, descricao);
        this.efeitoContinuo = efeitoContinuo;
    }
    public void aplicarEfeitoContinuo() {
        if (efeitoContinuo instanceof Runnable) {
            ((Runnable) efeitoContinuo).run();
            //colocar aqui o contador de turno
            //System.out.println("O encantamento " + getNomeCarta() + " foi ativado! Efeito: " + efeitoContinuo);
        }
    }

    @Override
    public String toString() {
        return "\nNome: " + nome + "\n" +
                "Custo: " + custoMana + "\n" +
                "Descrição: " + descricao + "\n";
    }
    
    public Object getEfeitoContinuo(){
        return efeitoContinuo;
    }

}
