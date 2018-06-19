package br.ufsc.ine5605.Urna.Elementos;

import java.io.Serializable;

public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private CARGO cargo;
    private int numCandidato;
    private PartidoPolitico partido;
    private int votos;

    public Candidato(){
        votos = 0;
    }

    public Candidato(String nome, int numero, CARGO cargo, PartidoPolitico partido){
        this.nome = nome;
        this.numCandidato = numero;
        this.partido = partido;
        this.cargo = cargo;
        votos = 0;
    }

    public void recebeVoto(){
        votos++;
    }

    public int getVotos(){
        return votos;
    }

    public String getNome() {
        return nome;
    }

    public CARGO getCargo(){
        return cargo;
    }

    public int getCodigo(){
        return numCandidato;
    }

    public PartidoPolitico getPartido() {
        return partido;
    }
}
