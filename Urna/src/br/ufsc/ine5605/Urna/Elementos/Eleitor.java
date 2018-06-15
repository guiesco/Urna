package br.ufsc.ine5605.Urna.Elementos;

import java.io.Serializable;

public class Eleitor implements Serializable {

    private static final long serialVersionUID = 1L;

    private int secao;
    private int codigo;
    private Zona zona;
    private boolean votou;

    public Eleitor(int secao, int codigo, Zona zona){
        this.zona = zona;
        this.secao = secao;
        this.codigo = codigo;
        votou = false;
    }

    public void jaVotou(){
        votou = true;
    }

    public boolean getVotou(){
        return votou;
    }

    public int getSecao() {
        return secao;
    }

    public int getCodigo() {
        return codigo;
    }

    public Zona getZona() {
        return zona;
    }
}
