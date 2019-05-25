package alphacomercio;

import java.util.HashMap;

public class Bairro {

    private String nomeBairro;
    private final HashMap<String, Logradouro> logradouros;

    public Bairro() {
        this.logradouros = new HashMap<>();
    }

    public Bairro(String nomeBairro) {
        this();
        this.nomeBairro = nomeBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public HashMap<String, Logradouro> getLogradouros() {
        return logradouros;
    }

    public void addLogradouro(Logradouro logradouro) {
        this.logradouros.put(logradouro.getCep(), logradouro);
    }

    @Override
    public String toString() {
        return "Bairro{" + "nomeBairro=" + nomeBairro + ", logradouros=" + logradouros + '}';
    }

}
