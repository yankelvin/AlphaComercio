package alphacomercio;

import java.util.ArrayList;
import java.util.HashMap;

public class Bairro {

    private String nomeBairro;
    //private final HashMap<String, Logradouro> logradouros;
    private final ArrayList<Logradouro> logradouros;

    public Bairro() {
        this.logradouros = new ArrayList<>();
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

    public ArrayList<Logradouro> getLogradouros() {
        return logradouros;
    }

    public void addLogradouro(Logradouro logradouro) {
        this.logradouros.add(logradouro);
    }

    @Override
    public String toString() {
        return "Bairro{" + "nomeBairro=" + nomeBairro + ", logradouros=" + logradouros + '}';
    }

}
