package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorCandidatos;
import br.ufsc.ine5605.Urna.Elementos.Candidato;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCandidato extends JFrame {

    private ControladorCandidatos ctrlCandidatos;
    private JList candidatos;
    private DefaultListModel<String> candidatosCadastrados;
    private JLabel label;
    private JButton cadastro;
    private JButton excluir;

    public TelaCandidato (ControladorCandidatos ctrl){
        super("Candidato");
        ctrlCandidatos = ctrl;
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 200);
        setLocationRelativeTo(null);
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        label = new JLabel("Esses são os atuais candidatos:");
        c.gridx = 1;
        c.gridy = 0;
        container.add(label, c);

        //Criação da lista nomes
        candidatosCadastrados = new DefaultListModel();
        candidatosCadastrados.setSize(0);
        for (Candidato candidato : ctrlCandidatos.getCandidatos()){
            candidatosCadastrados.addElement(candidato.getNome());
        }
        candidatos = new JList(candidatosCadastrados);
        candidatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        candidatos.setLayoutOrientation(JList.VERTICAL);
        candidatos.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(candidatos);
        listScroller.setPreferredSize(new Dimension(100, 50));
        c.gridx = 1;
        c.gridy = 1;
        container.add(candidatos, c);

        //Config botão cadastro
        cadastro = new JButton("Cadastrar");
        cadastro.setActionCommand("cadastro");
        cadastro.addActionListener(btManager);
        c.gridx = 0;
        c.gridy = 2;
        container.add(cadastro, c);

        //Config botão exclusao
        excluir = new JButton("Excluir");
        excluir.setActionCommand("exclusao");
        excluir.addActionListener(btManager);
        c.gridx = 4;
        c.gridy = 2;
        container.add(excluir, c);
    }

    public void exclui(){
        int index = candidatos.getSelectedIndex();
        String nome = candidatosCadastrados.get(index);
        if (!(ctrlCandidatos.exclui(index, nome) == null)){
            candidatosCadastrados.remove(index);
        }else {
            JOptionPane.showMessageDialog(null, "Problema ao deletar candidato.");
        }
    }

    private class GerenciadorBotoes implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent ae) {
                switch (ae.getActionCommand()) {
                    case "cadastro":
                        ctrlCandidatos.novoCadastro();
                        break;
                    case "exclusao":
                        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o candidato selecionado?", "Confirme", 2) == 0){
                            exclui();
                        }
                        break;
                }
            }
        }
    }

