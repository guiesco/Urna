package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorCandidatos;
import br.ufsc.ine5605.Urna.Elementos.Candidato;
import com.sun.deploy.panel.JreTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaUrna extends JFrame {

    private JPanel pBotoes;
    private JPanel pCandidatos;
    private JLabel configUrna;
    private JLabel lNumCandidato;
    private JTable jtCandidatos;
    private DefaultTableModel modeloCandidatos;
    private JButton bt1;
    private JButton bt2;
    private JButton bt3;
    private JButton bt4;
    private JButton bt5;
    private JButton bt6;
    private JButton bt7;
    private JButton bt8;
    private JButton bt9;
    private JButton bt0;
    private JButton salva;
    private JButton cancela;



    public TelaUrna(int secao, int codigoEleitor){
        super("Urna");
        this.configUrna = new JLabel("Bem vindo eleitor "+codigoEleitor+"\nvoce está na secao "+ secao + " do segundo turno");

        Container containerGeral = getContentPane();
        containerGeral.setLayout(new GridBagLayout());
        GridBagConstraints cGeral = new GridBagConstraints();
        setSize(1000, 750);
        setLocationRelativeTo(null);



        criaPainelBotoes();
        criaPainelCandidatos();

    }

    public void criaPainelBotoes(){
        GridBagConstraints c = new GridBagConstraints();
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        bt1 = new JButton("1");
        bt2 = new JButton("2");


    }

    public void criaPainelCandidatos(){
        GridBagConstraints c = new GridBagConstraints();
        criaTabelaCandidatos();
    }

    public void criaTabelaCandidatos(){
        Container container = getContentPane();
        GridBagConstraints c = new GridBagConstraints();

        //Criação da lista nomes
        modeloCandidatos = new DefaultTableModel();
        modeloCandidatos.addColumn("Nome");
        modeloCandidatos.addColumn("Codigo");
        modeloCandidatos.addColumn("Partido");
        modeloCandidatos.addColumn("Cargo");
        for (Candidato candidato : ControladorCandidatos.getInstancia().getCandidatos()){
            modeloCandidatos.addRow( new Object [] {candidato.getNome(), candidato.getCodigo(), candidato.getPartido().getNome(), candidato.getCargo().getNome()});
        }

        //Iniciando JTable
        jtCandidatos = new JTable(modeloCandidatos);
        jtCandidatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //inserindo no JSPane
        JScrollPane listScroller = new JScrollPane(jtCandidatos);
        listScroller.setPreferredSize(new Dimension(175, 75));
        c.gridx = 1;
        c.gridy = 1;
        container.add(listScroller, c);


    }









    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "1":
                    lNumCandidato.setText(lNumCandidato.getText()+"1");
                    getCandidato(lNumCandidato.getText());
                    break;
                case "2":
                    lNumCandidato.setText(lNumCandidato.getText()+"2");
                    break;
                case "3":
                    lNumCandidato.setText(lNumCandidato.getText()+"3");
                    break;
                case "4":
                    lNumCandidato.setText(lNumCandidato.getText()+"4");
                    break;
                case "5":
                    lNumCandidato.setText(lNumCandidato.getText()+"5");
                    break;
                case "6":
                    lNumCandidato.setText(lNumCandidato.getText()+"6");
                    break;
                case "7":
                    lNumCandidato.setText(lNumCandidato.getText()+"7");
                    break;
                case "8":
                    lNumCandidato.setText(lNumCandidato.getText()+"8");
                    break;
                case "9":
                    lNumCandidato.setText(lNumCandidato.getText()+"9");
                    break;
                case "0":
                    lNumCandidato.setText(lNumCandidato.getText()+"0");
                    break;
                case "confirma":
                    getCandidato(lNumCandidato.getText()).recebeVoto();
                    break;
                case "cancela":
                    break;
            }
        }

        public Candidato getCandidato(String numString){
            int numCandidato = Integer.parseInt(numString);
            for (Candidato  candidato : ControladorCandidatos.getInstancia().getCandidatos()){
                if (numCandidato == candidato.getCodigo()){
                    return candidato;
                }
            }
            return null;
        }
    }



}
