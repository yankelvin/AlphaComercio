package alphacomercio;

public class Logradouro {

    private String cep;
    private Cidade cidade;
    private UnidadeFederacao unidadeFederacao;
    private Bairro bairro;
    private String nomeLogradouro;

    public Logradouro() {
        this.unidadeFederacao = new UnidadeFederacao();
    }

    public Logradouro(String cep, Cidade cidade, UnidadeFederacao unidadeFederacao, Bairro bairro, String nomeLogradouro) {
        this();
        this.cep = cep;
        this.cidade = cidade;
        this.unidadeFederacao = unidadeFederacao;
        this.bairro = bairro;
        this.nomeLogradouro = nomeLogradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public UnidadeFederacao getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    @Override
    public String toString() {
        return "Logradouro{" + "cep=" + cep + ", cidade=" + cidade + ", unidadeFederacao=" + unidadeFederacao + ", bairro=" + bairro + ", nomeLogradouro=" + nomeLogradouro + '}';
    }

}
