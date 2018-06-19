package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.DAOs.CandidatoDAO;
import br.ufsc.ine5605.Urna.DAOs.EleitorDAO;
import br.ufsc.ine5605.Urna.Elementos.*;
import br.ufsc.ine5605.Urna.Telas.TelaTitulo;
import br.ufsc.ine5605.Urna.Telas.TelaUrna;

import java.util.ArrayList;
import java.util.Collection;

public class SistemaVotacao {

    private static SistemaVotacao sistemaVotacao;
    private TelaTitulo telaTitulo;
    private TelaUrna telaUrna;

    private SistemaVotacao(){}

    public static SistemaVotacao getInstance(){
         if (sistemaVotacao == null){
             sistemaVotacao = new SistemaVotacao();
         }
        return sistemaVotacao;
    }

    public void inicia() {
        telaTitulo = new TelaTitulo();
        telaTitulo.setVisible(true);
    }

    public void volta(){
        ControladorPrincipal.getInstancia().inicia();
    }

    public Eleitor verificaTitulo (String tituloStr){
        int titulo = Integer.parseInt(tituloStr);
        for (Eleitor eleitor : EleitorDAO.getInstancia().getList()){
            if (titulo == eleitor.getCodigo()){
                if (!eleitor.getVotou()){
                    iniciaVotacao(eleitor);
                    eleitor.jaVotou();
                    return eleitor;
                }else {
                    telaTitulo.jaVotou();
                }
            }
        }
        return null;
    }

    public void iniciaVotacao(Eleitor eleitor){
        telaUrna = new TelaUrna(eleitor.getSecao(), eleitor.getCodigo());
        telaUrna.setVisible(true);

    }

    public String verificaVencedor(){
        Collection<Candidato> candidatos = CandidatoDAO.getInstancia().getList();
        Candidato vencedor = new Candidato();
        try{
            for (Candidato candidato : candidatos){
                if (candidato.getVotos() > vencedor.getVotos()){
                    vencedor = candidato;
                }
            }
        }catch (Exception ex){

        }
        return vencedor.getNome();
    }
}
