package br.ufsc.ine5605.Urna.Elementos;

public enum CARGO {

    GOVERNADOR("GOV"),
    PREFEITO("Prefeito");

    public String nome;
    CARGO(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
