package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Telas.TelaPrincipal;

public class ControladorPrincipal {

    private static ControladorPrincipal controladorPrincipal;
    private TelaPrincipal telaPrincipal;
    private ControladorEleitores ctrlEleitores;
    private SistemaVotacao sistVotacao;


    private ControladorPrincipal(){
        telaPrincipal = new TelaPrincipal();
        sistVotacao = new SistemaVotacao();
    }

    public static ControladorPrincipal getInstancia(){
        if (controladorPrincipal == null){
            controladorPrincipal = new ControladorPrincipal();
        }
        return controladorPrincipal;
    }

    public void inicia(){
        telaPrincipal.setVisible(true);
    }


    public void selecionaModo (String modo){
        switch (modo) {
            case "1":
                ControladorPartidos.getInstance().inicia();
                break;
            case "2":
                ControladorCandidatos.getInstancia().inicia();
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
