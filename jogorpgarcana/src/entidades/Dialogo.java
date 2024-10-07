package entidades;

import java.util.ArrayList;
import java.util.List;

public class Dialogo{
    private List<String> dialogoInicial;
    private List<String> dialogoPrePartida;
    private List<String> dialogoPosPartida;

    public Dialogo(){
        dialogoInicial = new ArrayList<>();
        dialogoPrePartida = new ArrayList<>();
        dialogoPosPartida = new ArrayList<>();
    }

}