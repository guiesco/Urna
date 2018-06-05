package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Elementos.Eleitor;
import br.ufsc.ine5605.Urna.Elementos.Zona;
import br.ufsc.ine5605.Urna.Telas.TelaEleitores;

import java.util.ArrayList;

public class ControladorEleitores {

    private ArrayList<Eleitor> eleitores;
    private TelaEleitores telaEleitores;
    private String objeto = "eleitor";

    public ControladorEleitores(){
        eleitores = new ArrayList<>();
        telaEleitores = new TelaEleitores(this);
    }

    public ArrayList<Eleitor> getEleitores() {
        return eleitores;
    }


    public String getNome() {
        return this.objeto;
    }


    public String novoCadastro( ) {
        return null;
    }


    public String exclui(String nome) {
        return null;
    }



    public void inicia() {
        telaEleitores.setVisible(true);
    }
}
