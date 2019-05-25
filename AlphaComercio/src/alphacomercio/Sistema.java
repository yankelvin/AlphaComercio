package alphacomercio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Sistema {

    private final HashMap<String, Logradouro> logradouros;

    public Sistema() {
        this.logradouros = new HashMap<>();
    }

    public void iniciar() {
    }

    public void lerArquivo() {
        String arquivo = "src/alphacomercio/ceps.txt";

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try (FileReader arq = new FileReader(arquivo)) {
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            while (linha != null) {
                // Ordem de leitura:
                // CEP -> Cidade/Estado -> Bairro -> Logradouro.
                String[] aux = linha.split("	");
                Logradouro logradouro;
                // Cep : String
                String cep = aux[0];

                // Cidade -> String nomeCidade & String unidadeFederacao para
                // criar UnidadeFederacao & adicionar Bairros
                String nomeCidade = aux[1];
                Cidade cidade = new Cidade(nomeCidade);
                String siglaUF = aux[1].substring(aux[1].length() - 2);
                UnidadeFederacao UF = new UnidadeFederacao(siglaUF);

                logradouro = new Logradouro();
                logradouro.setCep(cep);
                logradouro.setCidade(cidade);
                logradouro.setUnidadeFederacao(UF);

                // Bairro -> String nomeBairro & Logradouro
                if (aux.length >= 3) {
                    String nomeBairro = aux[2];
                    Bairro bairro = new Bairro(nomeBairro);

                    // Logradouro -> Todas as informações.
                    if (aux.length >= 4) {
                        String nomeLogradouro = aux[3];
                        logradouro = new Logradouro(cep, cidade, UF, bairro, nomeLogradouro);
                    }
                }

                this.logradouros.put(cep, logradouro);
                linha = lerArq.readLine();
            }

            System.out.println(this.logradouros.get("01001000"));
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();
    }

    public void organizarInformacoes() {
    }

}
