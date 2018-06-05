package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;
import br.ufsc.ine5605.Urna.Telas.TelaCadastroPartido;
import br.ufsc.ine5605.Urna.Telas.TelaPartidos;

import java.util.ArrayList;

public class ControladorPartidos {

    private ArrayList<PartidoPolitico> partidos;
    private TelaPartidos telaPartidos;
    private TelaCadastroPartido telaCadastroPartido;


    public ControladorPartidos(){
        partidos = new ArrayList<>();
        telaPartidos = new TelaPartidos(this);
        telaCadastroPartido = new TelaCadastroPartido(this);
    }

    public ArrayList<PartidoPolitico> getPartidos() {
        return partidos;
    }


    public void inicia() {
        telaPartidos = new TelaPartidos(this);
        telaPartidos.setVisible(true);
    }

    public void novoCadastro() {
        telaPartidos.setVisible(false);
        telaCadastroPartido = new TelaCadastroPartido(this);
        telaCadastroPartido.setVisible(true);
    }

    public PartidoPolitico adiciona (String nomePartido, String codigo) throws CodigoNaoNumericoException{
        try {
            int codigoPartido = Integer.parseInt(codigo);
            if(!existe(nomePartido, codigoPartido)) {
                PartidoPolitico novoPartido = new PartidoPolitico(nomePartido, codigoPartido);
                partidos.add(novoPartido);
                telaCadastroPartido.setVisible(false);
                inicia();
                return novoPartido;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new CodigoNaoNumericoException();
        }
    }

    public boolean existe(String nome, int codigoPartido) {
        for (PartidoPolitico pp : partidos){
            if (pp.getCodigo() == codigoPartido && pp.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }


    public PartidoPolitico exclui(int index, String nome) {
        PartidoPolitico partido = partidos.get(index);
        if (partido.getNome().equalsIgnoreCase(nome)){
            partidos.remove(partido);
            return partido;
        }
        return null;
    }


}
