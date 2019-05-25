package alphacomercio;

import java.util.HashMap;

public class UnidadeFederacao {

    private String siglaUnidadeFederacao;
    private final HashMap<String, Cidade> cidades;

    public UnidadeFederacao() {
        this.cidades = new HashMap<>();
    }

    public UnidadeFederacao(String siglaUnidadeFederacao) {
        this();
        this.siglaUnidadeFederacao = siglaUnidadeFederacao;
    }

    public String getSiglaUnidadeFederacao() {
        return siglaUnidadeFederacao;
    }

    public void setSiglaUnidadeFederacao(String siglaUnidadeFederacao) {
        this.siglaUnidadeFederacao = siglaUnidadeFederacao;
    }

    public HashMap<String, Cidade> getCidades() {
        return cidades;
    }

    public void addCidade(Cidade cidade) {
        this.cidades.put(cidade.getNomeCidade(), cidade);
    }

    @Override
    public String toString() {
        return "UnidadeFederacao{" + "siglaUnidadeFederacao=" + siglaUnidadeFederacao + ", cidades=" + cidades + '}';
    }

}
