package br.ufsc.ine5605.Urna.Elementos;

public class Candidato {

    private String nome;
    private CARGO cargo;
    private int numCandidato;
    private PartidoPolitico partido;
    private int votos;

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

    public int getNumCandidato(){
        return numCandidato;
    }
}
