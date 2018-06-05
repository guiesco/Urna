package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Elementos.Eleitor;
import br.ufsc.ine5605.Urna.Elementos.Zona;
import br.ufsc.ine5605.Urna.Telas.TelaCadastro;
import br.ufsc.ine5605.Urna.Telas.TelaEleitores;
import br.ufsc.ine5605.Urna.Telas.TelaImpressao;

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


    public String novoCadastro() {
        return null;
    }


    public String novoCadastro(TelaCadastro telaCadastro) {
        boolean certo = true;
        int secaoEleitor = 0;
        int codigoEleitor = 0;
        Zona zona;
        String confirmacao = "";
        do {
            secaoEleitor = telaCadastro.recebeSecao();
            codigoEleitor = telaCadastro.recebeCodigo(objeto);
            zona = telaCadastro.recebeZona();
            confirmacao = "Deseja criar um novo "+objeto+" na secao "+secaoEleitor+" com o codigo "+codigoEleitor+" e zona "+zona;
            certo = telaCadastro.confirma(confirmacao);
        }while (!certo);

        Eleitor novoEleitor = new Eleitor(secaoEleitor, codigoEleitor, zona);

        if (eleitores.contains(novoEleitor)){
            return "O "+objeto+ " ja existe.";
        }else{
            for (Eleitor eleitor : eleitores){
                if (eleitor.getCodigo() == codigoEleitor){
                    return "Ja existe "+objeto+" com esse código";
                }
            }
        }

        eleitores.add(novoEleitor);
        return "Sucesso ao cadastrar " +objeto;
    }


    public void imprime(TelaImpressao telaImpressao) {
        telaImpressao.imprimeEleitores(eleitores);
    }


    public String exclui(String nome) {
        return null;
    }


    public String exclui(TelaCadastro telaCadastro) {
        boolean certo = true;
        int secaoEleitor = 0;
        int codigoEleitor = 0;
        String confirmacao = "";
        do {
            secaoEleitor = telaCadastro.recebeSecao();
            codigoEleitor = telaCadastro.recebeCodigo(objeto);
            confirmacao = "Deseja excluir o "+objeto+" cadastrado na secao "+secaoEleitor+", de codigo "+codigoEleitor;
            certo = telaCadastro.confirma(confirmacao);
        }while (!certo);

        for (Eleitor eleitor : eleitores){
            if (eleitor.getCodigo() == codigoEleitor && eleitor.getSecao() == secaoEleitor){
                eleitores.remove(eleitor);
                return "O "+objeto+" foi removido com sucesso.";
            }
        }
        return "O "+objeto+" não foi encontrado, tente novamente.";
    }


    public void inicia() {
        telaEleitores.setVisible(true);
    }
}
