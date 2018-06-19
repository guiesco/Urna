package br.ufsc.ine5605.Urna.DAOs;

import br.ufsc.ine5605.Urna.Elementos.Candidato;

import javax.swing.*;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class CandidatoDAO {

    private final String filename = "candidatos.dat";
    private HashMap<Integer, Candidato> cacheCandidatos = new HashMap<>();

    private static CandidatoDAO candidatoDAO;

    public static CandidatoDAO getInstancia(){
        if (candidatoDAO == null){
            candidatoDAO = new CandidatoDAO();
        }
        return candidatoDAO;
    }

    public CandidatoDAO(){
        load();
    }

    public void persist(){
        try {
            FileOutputStream fout = new FileOutputStream(filename);

            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheCandidatos);

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

            this.cacheCandidatos = (HashMap<Integer, Candidato>) oi.readObject();

            oi.close();
            fin.close();
            oi = null;
            fin = null;
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Ainda não existe nenhum candidato cadastrado.");
        }
    }

    public void put(Candidato candidato){
        cacheCandidatos.put(candidato.getCodigo(), candidato);
        persist();
    }

    public Candidato get(Integer idCandidato){
        return cacheCandidatos.get(idCandidato);
    }

    public void remove (Candidato candidato){
        cacheCandidatos.remove(candidato.getCodigo(), candidato);
        persist();
    }

    public Collection<Candidato> getList(){
        return cacheCandidatos.values();
    }
}

