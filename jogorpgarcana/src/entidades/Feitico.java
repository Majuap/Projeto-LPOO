package entidades;

public class Feitico extends Cartas {
    private String efeito;

    public Feitico(String nome, int custoMana, String efeito) {
        super(nome, custoMana);
        this.efeito = efeito;
    }

    @Override
    public void usarCarta() {
        // Implementar a lógica para quando o feitiço for usado no jogo
        System.out.println("O feitiço " + getNomeCarta() + " foi lançado! Efeito: " + efeito);
    }
    
    public String getefeito(){
        return efeito;
    }
}