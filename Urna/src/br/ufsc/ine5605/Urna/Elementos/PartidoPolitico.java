package br.ufsc.ine5605.Urna.Elementos;

public class PartidoPolitico {

    private String nome;
    private int codigo;

    public PartidoPolitico(String nome, int codigo){
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }
}
