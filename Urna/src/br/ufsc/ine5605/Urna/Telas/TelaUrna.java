package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorCandidatos;
import br.ufsc.ine5605.Urna.Elementos.Candidato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaUrna extends JFrame {

    private JPanel pBotoes;
    private JPanel pCandidatos;
    private JLabel configUrna;
    private JLabel segConfigUrna;
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
    private JButton btSalva;
    private JButton btCancela;



    public TelaUrna(int secao, int codigoEleitor){
        super("Urna");
        this.configUrna = new JLabel("Bem vindo eleitor nº"+codigoEleitor);
        this.segConfigUrna = new JLabel("Voce está na secao "+ secao + " do segundo turno para prefeito");

        Container containerGeral = getContentPane();
        containerGeral.setLayout(new GridBagLayout());
        GridBagConstraints cGeral = new GridBagConstraints();
        setSize(1000, 750);
        setLocationRelativeTo(null);

        cGeral.gridx = 0;
        cGeral.gridy = 0;
        add(configUrna, cGeral);
        cGeral.gridx = 0;
        cGeral.gridy = 1;
        add(segConfigUrna, cGeral);
        cGeral.gridx = 0;
        cGeral.gridy = 2;
        add(criaPainelBotoes(), cGeral);
        cGeral.gridx = 1;
        add(criaPainelCandidatos(), cGeral);

    }

    public JPanel criaPainelBotoes(){
        pBotoes = new JPanel();
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        pBotoes.setLayout(new GridBagLayout());
        GridBagConstraints cBotoes = new GridBagConstraints();

        pBotoes.add(bt1 = new JButton("1"));
        pBotoes.add(bt2 = new JButton("2"));
        pBotoes.add(bt3 = new JButton("3"));
        cBotoes.gridy = 1;
        pBotoes.add(bt4 = new JButton("4"),cBotoes);
        pBotoes.add(bt5 = new JButton("5"),cBotoes);
        pBotoes.add(bt6 = new JButton("6"),cBotoes);
        cBotoes.gridy = 2;
        pBotoes.add(bt7 = new JButton("7"),cBotoes);
        pBotoes.add(bt8 = new JButton("8"),cBotoes);
        pBotoes.add(bt9 = new JButton("9"),cBotoes);
        cBotoes.gridy = 3;
        pBotoes.add(btCancela = new JButton("Limpa"),cBotoes);
        pBotoes.add(bt0 = new JButton("0"),cBotoes);
        pBotoes.add(btSalva = new JButton("Confirma"),cBotoes);


        bt1.setActionCommand("1");
        bt2.setActionCommand("2");
        bt3.setActionCommand("3");
        bt4.setActionCommand("4");
        bt5.setActionCommand("5");
        bt6.setActionCommand("6");
        bt7.setActionCommand("7");
        bt8.setActionCommand("8");
        bt9.setActionCommand("9");
        bt0.setActionCommand("0");
        btCancela.setActionCommand("limpa");
        btSalva.setActionCommand("confirma");

        bt1.addActionListener(btManager);
        bt2.addActionListener(btManager);
        bt3.addActionListener(btManager);
        bt4.addActionListener(btManager);
        bt5.addActionListener(btManager);
        bt6.addActionListener(btManager);
        bt7.addActionListener(btManager);
        bt8.addActionListener(btManager);
        bt9.addActionListener(btManager);
        bt0.addActionListener(btManager);
        btSalva.addActionListener(btManager);
        btCancela.addActionListener(btManager);



        return pBotoes;
    }

    public JPanel criaPainelCandidatos(){
        pCandidatos = new JPanel();
        pCandidatos.setLayout(new GridBagLayout());
        GridBagConstraints cCandidatos = new GridBagConstraints();
        lNumCandidato = new JLabel("");

        pCandidatos.add(lNumCandidato);
        cCandidatos.gridy = 1;
        pCandidatos.add(criaTabelaCandidatos(),cCandidatos);

        return pCandidatos;
    }

    public JScrollPane criaTabelaCandidatos(){
        //Criação da lista nomes
        modeloCandidatos = new DefaultTableModel();
        modeloCandidatos.addColumn("Nome");
        modeloCandidatos.addColumn("Codigo");
        modeloCandidatos.addColumn("Partido");
        for (Candidato candidato : ControladorCandidatos.getInstancia().getCandidatos()){
            modeloCandidatos.addRow( new Object [] {candidato.getNome(), candidato.getCodigo(), candidato.getPartido().getNome()});
        }

        //Iniciando JTable
        jtCandidatos = new JTable(modeloCandidatos);
        jtCandidatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //inserindo no JSPane
        JScrollPane listScroller = new JScrollPane(jtCandidatos);
        listScroller.setPreferredSize(new Dimension(175, 75));

        return listScroller;
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "1":
                    String replace = lNumCandidato.getText() + e.getActionCommand();
                    lNumCandidato.setText(replace);
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
                    Candidato votado = getCandidato(lNumCandidato.getText());
                    if (votado == null){
                        JOptionPane.showMessageDialog(null, "Candidato nao existente.");
                    }else {
                        votado.recebeVoto();
                        JOptionPane.showMessageDialog(null, "Obrigado pelo seu voto.");
                        setVisible(false);
                    }

                    lNumCandidato.setText("");
                    break;
                case "limpa":
                    lNumCandidato.setText("");
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
