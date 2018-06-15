/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Urna.DAOs;

import br.ufsc.ine5605.Urna.Elementos.Eleitor;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Caroline Ignácio
 */
public class EleitorDAO {
    
    private HashMap<Integer, Eleitor> cacheEleitores = new HashMap<>();
    private static EleitorDAO eleitorDAO;

    public static EleitorDAO getInstancia(){
        if (eleitorDAO == null){
            eleitorDAO = new EleitorDAO();
        }
        return eleitorDAO;
    }

    public void put (Eleitor eleitor){
        cacheEleitores.put(eleitor.getCodigo(), eleitor);
    }

    public Collection<Eleitor> getList(){
        return cacheEleitores.values();
    }
    
}
