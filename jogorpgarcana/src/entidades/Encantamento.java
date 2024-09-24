package entidades;

public class Encantamento extends Cartas {
    private String efeitoContinuo;

    public Encantamento(String nome, int custoMana, String efeitoContinuo) {
        super(nome, custoMana);
        this.efeitoContinuo = efeitoContinuo;
    }

    @Override
    public void usar() {

    }
    public String getefeitoContinuo{
        return efeitoContinuo;
    }
}
