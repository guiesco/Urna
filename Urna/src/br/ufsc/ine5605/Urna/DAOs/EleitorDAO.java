/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Urna.DAOs;

import br.ufsc.ine5605.Urna.Elementos.Eleitor;

import javax.swing.*;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Caroline Ignácio
 */
public class EleitorDAO {

    private final String filename = "C:\\Users\\48687223870\\Documents\\projetos\\ufsc\\dso\\Urna\\Urna\\src\\br\\ufsc\\ine5605\\Urna\\DAOs\\eleitores.dat";
    private HashMap<Integer, Eleitor> cacheEleitores = new HashMap<>();
    private static EleitorDAO eleitorDAO;

    public static EleitorDAO getInstancia(){
        if (eleitorDAO == null){
            eleitorDAO = new EleitorDAO();
        }
        return eleitorDAO;
    }

    public EleitorDAO(){
        load();
    }

    public void persist(){
        try {
            FileOutputStream fout = new FileOutputStream(filename);

            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheEleitores);

            oo.flush();
            fout.flush();

            oo.close();
            fout.close();
            oo = null;
            fout = null;

        }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void load(){
        try {
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);

            this.cacheEleitores = (HashMap<Integer, Eleitor>) oi.readObject();

            oi.close();
            fin.close();
            oi = null;
            fin = null;
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Ainda nao existe nenhum eleitor cadastrado.");
        }
    }

    public void remove(Eleitor eleitor){
        cacheEleitores.remove(eleitor);
        persist();
    }

    public void put (Eleitor eleitor){
        cacheEleitores.put(eleitor.getCodigo(), eleitor);
        persist();
    }

    public Collection<Eleitor> getList(){
        return cacheEleitores.values();
    }
    
}
