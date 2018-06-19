package br.ufsc.ine5605.Urna.DAOs;

import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;

import javax.swing.*;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class PartidoDAO {

    private final String filename = "partidos.dat";

    private HashMap<Integer, PartidoPolitico> cachePartidos = new HashMap<>();
    private static PartidoDAO partidoDAO;

    public static PartidoDAO getInstancia(){
        if (partidoDAO == null){
            partidoDAO = new PartidoDAO();
        }
        return partidoDAO;
    }

    public PartidoDAO(){
        load();
    }

    public void persist(){
        try {
            FileOutputStream fout = new FileOutputStream(filename);

            ObjectOutputStream oo = new ObjectOutputStream( fout);
            oo.writeObject(cachePartidos);

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

            this.cachePartidos = (HashMap<Integer, PartidoPolitico>) oi.readObject();

            oi.close();
            fin.close();
            oi = null;
            fin = null;
        }catch ( ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Ainda não existe nenhum partido cadastrado.");
        }
    }

    public void put (PartidoPolitico partidoPolitico){
        cachePartidos.put(partidoPolitico.getCodigo(), partidoPolitico);
        persist();
    }

    public void remove (PartidoPolitico partidoPolitico){
        cachePartidos.remove(partidoPolitico.getCodigo(), partidoPolitico);
        persist();
    }

    public Collection<PartidoPolitico> getList(){
        return cachePartidos.values();
    }
}
