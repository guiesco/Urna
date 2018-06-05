package br.ufsc.ine5605.Urna.Elementos;

import br.ufsc.ine5605.Urna.Telas.TelaCadastroCandidato;

import java.util.ArrayList;

public class Urna {

    private int turno;
    private int secao;
    private int votoBrancoD;
    private int votoBrancoG;
    private ArrayList<Candidato> candidatos;
    private Zona zona;
    private ArrayList<Eleitor> eleitores;
    private TelaCadastroCandidato telaCadastro;

    public Urna(){

    }

    public int getVotoBrancoD(){
        return votoBrancoD;
    }

    public void setCandidatos(ArrayList<Candidato> c2){
        this.candidatos = c2;
    }

    public void setTurno(int turno){
        this.turno = turno;
    }

    public int getVotoBrancoG(){
        return votoBrancoG;
    }


    public void vota(){
    }

    public int getSecao() {
        return secao;
    }
}
