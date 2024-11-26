package entidades;

public class Criatura extends Cartas {
    private int poder;
    private int resistencia;
    private Object habilidadeEspecial;

    public Criatura(String nome, int custoMana, String descricao, int poder, int resistencia, Object habilidadeEspecial) {
        super(nome, custoMana, descricao);
        this.poder = poder;
        this.resistencia = resistencia;
        this.habilidadeEspecial = habilidadeEspecial;
    }


    public void aplicarEfeito() {
        if (habilidadeEspecial instanceof Runnable) {
            ((Runnable) habilidadeEspecial).run();
        }
    }
    @Override
    public String toString() {
        return "\nNome: " + nome + "\n" +
                "Custo: " + custoMana + "\n" +
                "Descrição: " + descricao + "\n" +
                "Poder: " +poder+ "\n"+
                "Resistencia: "+resistencia+"\n";
    }


    public int getPoder(){
        return poder;
    }
    public int getResistencia(){
        return resistencia;
    }
    public Object getHabilidadeEspecial(){
        return habilidadeEspecial;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public void setResistencia(int resistencia,Jogador jogador,Criatura criatura) {
        if (resistencia > 0) {
            this.resistencia = resistencia;
        }
        else {
            jogador.getCampo().getCemiterio().add(criatura);
            jogador.getCampo().getCriaturasNoCampo().remove(criatura);
        }
    }
}

