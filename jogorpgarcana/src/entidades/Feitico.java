package entidades;

public class Feitico extends Cartas {
    private String efeito;

    public Feitico(String nome, int custoMana, String efeito) {
        super(nome, custoMana);
        this.efeito = efeito;
    }

    @Override
    public void usar() {

    }
    public String getefeito{
        return efeito;
    }
}

