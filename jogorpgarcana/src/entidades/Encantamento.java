package entidades;

public class Encantamento extends Cartas {
    private String efeitoContinuo;

    public Encantamento(String nome, int custoMana, String efeitoContinuo) {
        super(nome, custoMana);
        this.efeitoContinuo = efeitoContinuo;
    }
    
    @Override
    public void usarCarta() {
        System.out.println("O encantamento " + getNomeCarta() + " foi ativado! Efeito: " + efeitoContinuo);
    }
    
    public String getEfeitoContinuo(){
        return efeitoContinuo;
    }
}
