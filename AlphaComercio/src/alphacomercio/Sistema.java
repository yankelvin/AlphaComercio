package alphacomercio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Sistema {

    public Sistema() {
    }

    public void iniciar() {
    }

    public void lerArquivo() {
        String arquivo = "src/alphacomercio/ceps.txt";

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try (FileReader arq = new FileReader(arquivo)) {
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            System.out.println(Arrays.toString(linha.split("	")));

            while (linha != null) {
                linha = lerArq.readLine();
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();
    }

}
