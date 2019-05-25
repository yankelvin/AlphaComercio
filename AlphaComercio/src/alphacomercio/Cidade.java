package alphacomercio;

import java.util.HashMap;

public class Cidade {

    private String nomeCidade;
    private UnidadeFederacao unidadeFederacao;
    private final HashMap<String, Bairro> bairros;

    public Cidade() {
        this.bairros = new HashMap<>();
    }

    public Cidade(String nomeCidade) {
        this();
        this.nomeCidade = nomeCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public UnidadeFederacao getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public HashMap<String, Bairro> getBairros() {
        return bairros;
    }

    public void addBairro(Bairro bairro) {
        this.bairros.put(bairro.getNomeBairro(), bairro);
    }

    @Override
    public String toString() {
        return "Cidade{" + "nomeCidade=" + nomeCidade + ", unidadeFederacao=" + unidadeFederacao + ", bairros=" + bairros + '}';
    }

}
