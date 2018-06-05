package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Elementos.CARGO;
import br.ufsc.ine5605.Urna.Elementos.Candidato;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Telas.TelaCadastro;
import br.ufsc.ine5605.Urna.Telas.TelaImpressao;

import java.util.ArrayList;

public class ControladorCandidatos implements IControlador {

    private ArrayList<Candidato> candidatos;
    private String objeto = "candidato";
    private ControladorPartidos ctrlPartidos;

    public ControladorCandidatos(){
        candidatos = new ArrayList<>();
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }


    public String getNome() {
        return this.objeto;
    }


    public String novoCadastro() {
        return null;
    }


    public String novoCadastro(TelaCadastro telaCadastro) {
        boolean certo = true;
        String nomeCandidato = "";
        int numCandidato = 0;
        CARGO cargo = CARGO.DEPUTADO;
        PartidoPolitico partido = null;
        String confirmacao = "";
        do {
            nomeCandidato = telaCadastro.recebeNome(objeto);
            numCandidato = telaCadastro.recebeCodigo(objeto);
            cargo = telaCadastro.recebeCargo();
            partido = telaCadastro.recebePartido(ctrlPartidos.getPartidos());
            confirmacao = "Deseja criar um novo "+objeto+" com o nome "+nomeCandidato+" e numero "+numCandidato+" que concorrerá à "+cargo+" pelo partido "+partido.getNome();
            certo = telaCadastro.confirma(confirmacao);
        }while (!certo);



        Candidato novoCandidato = new Candidato(nomeCandidato, numCandidato, cargo, partido);

        if (candidatos.contains(novoCandidato)){
            return "O "+objeto+ " ja existe.";
        }else{
            for (Candidato candidato : candidatos){
                if (candidato.getNumCandidato() == numCandidato || candidato.getNome().equalsIgnoreCase(nomeCandidato)){
                    return "Ja existe "+objeto+" com esse código ou nome";
                }
            }
        }

        candidatos.add(novoCandidato);
        return "Sucesso ao cadastrar " +objeto;
    }

    @Override
    public void imprime(TelaImpressao telaImpressao) {
        telaImpressao.imprimeCandidatos(candidatos);
    }

    @Override
    public String exclui(String nome) {
        return null;
    }


    public String exclui(TelaCadastro telaCadastro) {
        boolean certo = true;
        String nomeCandidato = "";
        int numCandidato = 0;
        String confirmacao = "";
        do {
            nomeCandidato = telaCadastro.recebeNome(objeto);
            numCandidato = telaCadastro.recebeCodigo(objeto);
            confirmacao = "Deseja excluir o "+objeto+" de nome "+nomeCandidato+" e numero "+numCandidato;
            certo = telaCadastro.confirma(confirmacao);
        }while (!certo);

        for (Candidato candidato : candidatos){
            if (candidato.getNumCandidato() == numCandidato && candidato.getNome().equalsIgnoreCase(nomeCandidato)){
                candidatos.remove(candidato);
                return "O "+objeto+" foi removido com sucesso.";
            }
        }
        return "O "+objeto+" não foi encontrado, tente novamente.";
    }

    @Override
    public void inicia() {

    }


}
