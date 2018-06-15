package br.ufsc.ine5605.Urna.Elementos;

import java.io.Serializable;

public class PartidoPolitico implements Serializable {

    private static final long serialVersionUID = 1L;

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
