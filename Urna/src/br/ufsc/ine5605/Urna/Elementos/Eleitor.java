package br.ufsc.ine5605.Urna.Elementos;

public class Eleitor {

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
