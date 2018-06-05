package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Telas.TelaCadastro;
import br.ufsc.ine5605.Urna.Telas.TelaExclusao;
import br.ufsc.ine5605.Urna.Telas.TelaImpressao;
import br.ufsc.ine5605.Urna.Telas.TelaOpcoes;

public class ControladorEntidades {

    private TelaCadastro telaCadastro;
    private TelaImpressao telaImpressao;
    private TelaExclusao telaExclusao;
    private TelaOpcoes telaOpcoes;
    private ControladorPartidos ctrlPartidos;
    private ControladorCandidatos ctrlCandidatos;
    private ControladorEleitores ctrlEleitores;
    private ControladorPrincipal ctrlPrincipal;


    public ControladorEntidades(ControladorPrincipal ctrlPrincipal){
        telaCadastro = new TelaCadastro();
        telaImpressao = new TelaImpressao();
        telaExclusao = new TelaExclusao();
        telaOpcoes = new TelaOpcoes();
        ctrlEleitores = new ControladorEleitores();
        ctrlPartidos = new ControladorPartidos();
        ctrlCandidatos = new ControladorCandidatos();
        this.ctrlPrincipal = ctrlPrincipal;
    }

    public ControladorCandidatos getCtrlCandidatos() {
        return ctrlCandidatos;
    }

    public ControladorEleitores getCtrlEleitores() {
        return ctrlEleitores;
    }

    public void inicia(){
        int opcao = telaOpcoes.escolha();
        switch (opcao) {
            case 1:
                menuEscolhas(ctrlPartidos);
                break;
            case 2:
                menuEscolhas(ctrlCandidatos);
                break;
            case 3:
                menuEscolhas(ctrlEleitores);
                break;
            default:
                ctrlPrincipal.inicia();
                break;
        }
    }

    public void menuEscolhas(IControlador controlador){
        int opcao = telaOpcoes.menuEscolha(controlador.getNome());
        switch (opcao) {
            case 1:
                telaOpcoes.resultado(controlador.novoCadastro(telaCadastro));
                inicia();
                break;
            case 2:
                controlador.imprime(telaImpressao);
                inicia();
                break;
            case 3:
                telaOpcoes.resultado(controlador.exclui(telaCadastro));
                inicia();
                break;
            default:
                inicia();
                break;
        }
    }

}
