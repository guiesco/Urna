package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.DAOs.PartidoDAO;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;
import br.ufsc.ine5605.Urna.Telas.TelaCadastroPartido;
import br.ufsc.ine5605.Urna.Telas.TelaPartidos;

import java.util.ArrayList;
import java.util.Collection;

public class ControladorPartidos {

    private static ControladorPartidos controladorPartidos;
    private TelaPartidos telaPartidos;
    private TelaCadastroPartido telaCadastroPartido;


    private ControladorPartidos(){
    }

    public static ControladorPartidos getInstance(){
        if (controladorPartidos == null){
            controladorPartidos = new ControladorPartidos();
        }
        return controladorPartidos;
    }

    public void inicia() {
        telaPartidos = new TelaPartidos();
        telaPartidos.setVisible(true);
    }

    public void novoCadastro() {
        telaPartidos.setVisible(false);
        telaCadastroPartido = new TelaCadastroPartido();
        telaCadastroPartido.setVisible(true);
    }

    public PartidoPolitico adiciona (String nomePartido, String codigo) throws CodigoNaoNumericoException{
        try {
            int codigoPartido = Integer.parseInt(codigo);
            if(!existe(nomePartido, codigoPartido)) {
                PartidoPolitico novoPartido = new PartidoPolitico(nomePartido, codigoPartido);
                PartidoDAO.getInstancia().put(novoPartido);
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

    private boolean existe(String nome, int codigoPartido) {
        for (PartidoPolitico pp : PartidoDAO.getInstancia().getList()){
            if (pp.getCodigo() == codigoPartido && pp.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }


    public PartidoPolitico exclui(Object nomeObj) {
        String nome = nomeObj.toString();
        for (PartidoPolitico pp : PartidoDAO.getInstancia().getList()){
            if (pp.getNome().equalsIgnoreCase(nome)){
                PartidoDAO.getInstancia().remove(pp);
                return pp;
            }
        }
        return null;
    }

    public Collection<PartidoPolitico> getPartidos(){
        return PartidoDAO.getInstancia().getList();
    }

    public void volta(){
        telaPartidos.setVisible(false);
        ControladorPrincipal.getInstancia().inicia();
    }


}
