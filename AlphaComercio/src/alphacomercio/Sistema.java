package alphacomercio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Sistema {

    private final HashMap<String, Logradouro> logradouros;
    private final HashMap<String, ArrayList<Cidade>> cidades;
    private final HashMap<String, ArrayList<Bairro>> bairros;

    public Sistema() {
        this.logradouros = new HashMap<>();
        this.cidades = new HashMap<>();
        this.bairros = new HashMap<>();
    }

    public void iniciar() {
        System.out.println("------ MENU ------");
        System.out.println("1 - Ler arquivo");
        System.out.println("2 - Fazer busca por CEP\n");
        System.out.println("");
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
                UF.addCidade(cidade);

                logradouro = new Logradouro();
                logradouro.setCep(cep);
                logradouro.setCidade(cidade);
                logradouro.setUnidadeFederacao(UF);

                // Bairro -> String nomeBairro & Logradouro
                if (aux.length >= 3) {
                    String nomeBairro = aux[2];
                    Bairro bairro = new Bairro(nomeBairro);
                    logradouro.setBairro(bairro);

                    cidade.addBairro(bairro);
                    // Logradouro -> Todas as informações.
                    if (aux.length >= 4) {
                        String nomeLogradouro = aux[3];
                        logradouro.setNomeLogradouro(nomeLogradouro);
                    }
                    bairro.addLogradouro(logradouro);
                }

                this.logradouros.put(cep, logradouro);
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();
    }

    public void buscarLogradouro(String cep) {
        Logradouro logradouro = this.logradouros.get(cep);
        System.out.println("Nome cidade: " + logradouro.getCidade().getNomeCidade());
        System.out.println("Nome bairro: " + logradouro.getBairro().getNomeBairro());
        System.out.println("UF: " + logradouro.getUnidadeFederacao().getSiglaUnidadeFederacao());
        System.out.println("Nome Logradouro: " + logradouro.getNomeLogradouro());
    }

}
