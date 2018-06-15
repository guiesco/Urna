package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.DAOs.EleitorDAO;
import br.ufsc.ine5605.Urna.Elementos.Eleitor;
import br.ufsc.ine5605.Urna.Elementos.Zona;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;
import br.ufsc.ine5605.Urna.Telas.TelaCadastroEleitor;
import br.ufsc.ine5605.Urna.Telas.TelaEleitores;

import java.util.Collection;

public class ControladorEleitores {

    private static ControladorEleitores controladorEleitores;
    private TelaEleitores telaEleitores;
    private TelaCadastroEleitor telaCadastroEleitor;
    
    private ControladorEleitores() {
    }

    public static ControladorEleitores getInstance(){
        if (controladorEleitores == null){
            controladorEleitores = new ControladorEleitores();
        }
        return controladorEleitores;
    }

    public void inicia() {
        telaEleitores = new TelaEleitores();
        telaEleitores.setVisible(true);
    }
    
    public void novoCadastro() {
        telaEleitores.setVisible(false);
        telaCadastroEleitor = new TelaCadastroEleitor(this);
        telaCadastroEleitor.setVisible(true);
    }

    public void volta(){
        telaEleitores.setVisible(false);
        ControladorPrincipal.getInstancia().inicia();
    }

    public Eleitor adiciona (int secao, int tituloEleitor, Zona zona) throws CodigoNaoNumericoException{
        try {
            if(!existe(tituloEleitor)) {
                 Eleitor novoEleitor = new Eleitor(secao, tituloEleitor, zona);
                 EleitorDAO.getInstancia().put(novoEleitor);
                telaCadastroEleitor.setVisible(false);
                inicia();
                return novoEleitor;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new CodigoNaoNumericoException();
        }
    }

    public boolean existe(int tituloEleitor) {
        for (Eleitor eleitores : EleitorDAO.getInstancia().getList()){
            if (eleitores.getCodigo() == tituloEleitor){
                return true;
            }
        }
        return false;
    }


    public Eleitor exclui(Object tituloEleitor) {
        int titulo = Integer.parseInt(tituloEleitor.toString());
        for (Eleitor eleitor : EleitorDAO.getInstancia().getList()){
            if (eleitor.getCodigo() == titulo){
                EleitorDAO.getInstancia().remove(eleitor);
                return eleitor;
            }
        }
        return null;
    }

    public Collection<Eleitor> getEleitores(){
        return EleitorDAO.getInstancia().getList();
    }
}
