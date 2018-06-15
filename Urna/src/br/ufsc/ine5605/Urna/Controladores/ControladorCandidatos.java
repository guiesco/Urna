package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.DAOs.CandidatoDAO;
import br.ufsc.ine5605.Urna.Elementos.CARGO;
import br.ufsc.ine5605.Urna.Elementos.Candidato;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;
import br.ufsc.ine5605.Urna.Telas.TelaCadastroCandidato;
import br.ufsc.ine5605.Urna.Telas.TelaCandidato;

import java.util.ArrayList;
import java.util.Collection;

public class ControladorCandidatos {

    private static ControladorCandidatos controladorCandidatos;
    private TelaCandidato telaCandidato;
    private TelaCadastroCandidato telaCadastroCandidato;

    private ControladorCandidatos() {
    }

    public static ControladorCandidatos getInstancia(){
        if (controladorCandidatos == null){
            controladorCandidatos = new ControladorCandidatos();
        }
        return controladorCandidatos;
    }

    public void inicia() {
        telaCandidato = new TelaCandidato();
        telaCandidato.setVisible(true);
    }

    public Collection<Candidato> getCandidatos() {
        return CandidatoDAO.getInstancia().getList();
    }

    public void novoCadastro() {
        telaCandidato.setVisible(false);
        telaCadastroCandidato = new TelaCadastroCandidato(this);
        telaCadastroCandidato.setVisible(true);
    }

    public Candidato adiciona(String nomeCandidato, String numeroRecebido, String partido, CARGO cargo) throws CodigoNaoNumericoException {
        try {
            int numeroCandidato = Integer.parseInt(numeroRecebido);
            PartidoPolitico partidoPertencente = buscaPartido(partido);
            if (!(partidoPertencente == null) && !existe(nomeCandidato, numeroCandidato)) {
                Candidato novoCandidato = new Candidato(nomeCandidato, numeroCandidato, cargo, partidoPertencente);
                CandidatoDAO.getInstancia().put(novoCandidato);
                telaCadastroCandidato.setVisible(false);
                inicia();
                return novoCandidato;
            }
        } catch (Exception e) {
            throw new CodigoNaoNumericoException();
        }

        return null;
    }

    private PartidoPolitico buscaPartido(String partido) {
        for (PartidoPolitico pp : ControladorPartidos.getInstance().getPartidos()) {
            if (pp.getNome().equalsIgnoreCase(partido)) {
                return pp;
            }
        }
        return null;
    }

    public boolean existe(String nome, int codigo) {
        for (Candidato candidato : getCandidatos()) {
            if (candidato.getCodigo() == codigo || candidato.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public Candidato exclui(Object nomeObj) {
        String nome = nomeObj.toString();
        for (Candidato candidato : getCandidatos()){
            if (candidato.getNome().equalsIgnoreCase(nome)){
                CandidatoDAO.getInstancia().remove(candidato);
                return candidato;
            }
        }
        return null;
    }

    public void volta(){
        telaCandidato.setVisible(false);
        ControladorPrincipal.getInstancia().inicia();
    }
}