package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Telas.TelaPrincipal;

public class ControladorPrincipal {

    private TelaPrincipal telaPrincipal;
    private ControladorPartidos ctrlPartidos;
    private ControladorCandidatos ctrlCandidatos;
    private ControladorEleitores ctrlEleitores;
    private SistemaVotacao sistVotacao;

    public ControladorPrincipal(){
        telaPrincipal = new TelaPrincipal(this);
        ctrlPartidos = new ControladorPartidos();
        ctrlCandidatos = new ControladorCandidatos(ctrlPartidos);
        ctrlEleitores = new ControladorEleitores();
        sistVotacao = new SistemaVotacao();
    }

    public void inicia(){
        telaPrincipal.setVisible(true);
    }


    public void selecionaModo (String modo){
        switch (modo) {
            case "1":
                ctrlPartidos.inicia();
                break;
            case "2":
                ctrlCandidatos.inicia();
                break;
            case "3":
                ctrlEleitores.inicia();
                break;
            case "4":
                sistVotacao.inicia();
            default:
                this.inicia();
                break;
        }
    }

}
