package alphacomercio;

public class AlphaComercio {

    public static void main(String[] args) {
        Sistema sis = new Sistema();
        sis.lerArquivo();
        sis.buscarLogradouro("99980974");
    }

}
