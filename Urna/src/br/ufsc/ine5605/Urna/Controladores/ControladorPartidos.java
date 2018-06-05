package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Telas.TelaCadastro;
import br.ufsc.ine5605.Urna.Telas.TelaCadastroPartido;
import br.ufsc.ine5605.Urna.Telas.TelaImpressao;
import br.ufsc.ine5605.Urna.Telas.TelaPartidos;

import java.util.ArrayList;

public class ControladorPartidos {

    private ArrayList<PartidoPolitico> partidos;
    private TelaPartidos telaPartidos;
    private TelaCadastroPartido telaCadastroPartido;


    public ControladorPartidos(){
        partidos = new ArrayList<>();
        telaPartidos = new TelaPartidos(this, partidos);
        telaCadastroPartido = new TelaCadastroPartido();
    }

    public ArrayList<PartidoPolitico> getPartidos() {
        return partidos;
    }


    public void inicia() {
        telaPartidos.setVisible(true);
    }

    public PartidoPolitico adiciona (String nome, int codigo){
        PartidoPolitico novoPartido = new PartidoPolitico(nome, codigo);
        partidos.add(novoPartido);
        return novoPartido;
    }


    public String novoCadastro() {
        telaCadastroPartido.setVisible(true);
        String nomePartido = telaCadastroPartido.getNome().getText();
        int codigoPartido = Integer.parseInt(telaCadastroPartido.getCodigo().getText());
        if(!existe(nomePartido, codigoPartido)){
            adiciona(nomePartido, codigoPartido);
        }
        return null;
    }

    public boolean existe(String nome, int codigoPartido) {
        for (PartidoPolitico pp : partidos){
            if (pp.getCodigo() == codigoPartido){
                return true;
            }
        }
        return false;
    }


    public PartidoPolitico exclui(String nome, int codigo) {
        for (PartidoPolitico partido : partidos){
            if (partido.getCodigo() == codigo && partido.getNome().equalsIgnoreCase(nome)){
                partidos.remove(partido);
                return partido;
            }
        }
        return null;
    }


}
