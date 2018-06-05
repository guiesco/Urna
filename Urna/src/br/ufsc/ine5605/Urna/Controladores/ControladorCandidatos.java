package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Elementos.CARGO;
import br.ufsc.ine5605.Urna.Elementos.Candidato;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;
import br.ufsc.ine5605.Urna.Telas.TelaCadastroCandidato;
import br.ufsc.ine5605.Urna.Telas.TelaCandidato;

import java.util.ArrayList;

public class ControladorCandidatos   {

    private ArrayList<Candidato> candidatos;
    private ArrayList<PartidoPolitico> partidos;
    private ControladorPartidos ctrlPartidos;
    private TelaCandidato telaCandidato;
    private TelaCadastroCandidato telaCadastroCandidato;

    public ControladorCandidatos(ControladorPartidos ctrl){
        candidatos = new ArrayList<>();
        ctrlPartidos = ctrl;
        partidos = ctrlPartidos.getPartidos();
        telaCadastroCandidato = new TelaCadastroCandidato(this);
        telaCandidato = new TelaCandidato(this);
    }

    public void inicia() {
        partidos = ctrlPartidos.getPartidos();
        telaCandidato = new TelaCandidato(this);
        telaCandidato.setVisible(true);
    }

    public ArrayList<PartidoPolitico> getPartidos() {
        return partidos;
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public void novoCadastro(){
        telaCandidato.setVisible(false);
        telaCadastroCandidato =   new TelaCadastroCandidato(this);
        telaCadastroCandidato.setVisible(true);
    }

    public Candidato adiciona(String nomeCandidato, String numeroRecebido, String partido, CARGO cargo ) throws CodigoNaoNumericoException{
        try {
            int numeroCandidato = Integer.parseInt(numeroRecebido);
            PartidoPolitico partidoPertencente = buscaPartido(partido);
            if (!(partidoPertencente == null) && !existe(nomeCandidato, numeroCandidato)){
                Candidato novoCandidato = new Candidato(nomeCandidato, numeroCandidato, cargo, partidoPertencente);
                candidatos.add(novoCandidato);
                telaCadastroCandidato.setVisible(false);
                inicia();
                return novoCandidato;
            }
        }catch (Exception e){
            throw new CodigoNaoNumericoException();
        }

        return null;
    }

    private PartidoPolitico buscaPartido(String partido) {
        for(PartidoPolitico pp : partidos){
            if (pp.getNome().equalsIgnoreCase(partido)){
                return pp;
            }
        }
        return null;
    }

    public boolean existe(String nome, int numCandidato){
        for (Candidato candidato : candidatos){
            if (candidato.getNumCandidato() == numCandidato && candidato.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

    public Candidato exclui (int index, String nome) {
        return null;
    }


/*    public String novoCadastro() {

            nomeCandidato =
            numCandidato =
            cargo =
            partido

        Candidato novoCandidato = new Candidato(nomeCandidato, numCandidato, cargo, partido);

        if (candidatos.contains(novoCandidato)){
            return "O "+ " ja existe.";
        }else{
            for (Candidato candidato : candidatos){
                if (candidato.getNumCandidato() == numCandidato || candidato.getNome().equalsIgnoreCase(nomeCandidato)){
                    return "Ja existe "+" com esse código ou nome";
                }
            }
        }

        candidatos.add(novoCandidato);
        return "Sucesso ao cadastrar " ;
    }*/






    /*public String exclui( ) {
        boolean certo = true;
        String nomeCandidato = "";
        int numCandidato = 0;
        String confirmacao = "";
        do {
            nomeCandidato = telaCadastro.recebeNome("");
            numCandidato = telaCadastro.recebeCodigo("");
            confirmacao = "Deseja excluir o "+" de nome "+nomeCandidato+" e numero "+numCandidato;
            certo = telaCadastro.confirma(confirmacao);
        }while (!certo);

        for (Candidato candidato : candidatos){
            if (candidato.getNumCandidato() == numCandidato && candidato.getNome().equalsIgnoreCase(nomeCandidato)){
                candidatos.remove(candidato);
                return "O "+" foi removido com sucesso.";
            }
        }
        return "O "+" não foi encontrado, tente novamente.";
    }
*/




}
