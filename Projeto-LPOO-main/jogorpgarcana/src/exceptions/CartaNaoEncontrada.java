package exceptions;

public class CartaNaoEncontrada extends Exception {

    public CartaNaoEncontrada(String mensagem) {
        super(mensagem);
    }

    public CartaNaoEncontrada() {
        super("Carta não encontrada.");
    }
}

