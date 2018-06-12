package br.ufsc.ine5605.Urna.DAOs;

import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;

import java.util.Collection;
import java.util.HashMap;

public class PartidoDAO {

    private HashMap<Integer, PartidoPolitico> cachePartidos = new HashMap<>();
    private static PartidoDAO partidoDAO;

    public static PartidoDAO getInstancia(){
        if (partidoDAO == null){
            partidoDAO = new PartidoDAO();
        }
        return partidoDAO;
    }

    public void put (PartidoPolitico partidoPolitico){
        cachePartidos.put(partidoPolitico.getCodigo(), partidoPolitico);
    }

    public void remove (PartidoPolitico partidoPolitico){
        cachePartidos.remove(partidoPolitico.getCodigo(), partidoPolitico);
    }

    public Collection<PartidoPolitico> getList(){
        return cachePartidos.values();
    }
}
