package br.ufsc.ine5605.Urna.DAOs;

import br.ufsc.ine5605.Urna.Elementos.Candidato;

import java.util.Collection;
import java.util.HashMap;

public class CandidatoDAO {

    private HashMap<Integer, Candidato> cacheCandidatos = new HashMap<>();
    private static CandidatoDAO candidatoDAO;

    public static CandidatoDAO getInstancia(){
        if (candidatoDAO == null){
            candidatoDAO = new CandidatoDAO();
        }
        return candidatoDAO;
    }

    public void put(Candidato candidato){
        cacheCandidatos.put(candidato.getCodigo(), candidato);
    }

    public void remove (Candidato candidato){
        cacheCandidatos.remove(candidato.getCodigo(), candidato);
    }

    public Collection<Candidato> getList(){
        return cacheCandidatos.values();
    }
}

