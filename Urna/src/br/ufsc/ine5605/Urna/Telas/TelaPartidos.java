package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorPartidos;
import br.ufsc.ine5605.Urna.DAOs.PartidoDAO;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaPartidos extends JFrame {

    private JTable partidos;
    private DefaultTableModel modeloPartidos;
    private JLabel label;
    private JButton cadastro;
    private JButton excluir;
    private JButton voltar;

    public TelaPartidos( ){

        //Inicialização JFrame
        super("Partidos");
        criaTela();
        criaTabela();
    }

    private void criaTabela(){
        Container container = getContentPane();
        GridBagConstraints c = new GridBagConstraints();

        //Criação da lista nomes
        modeloPartidos = new DefaultTableModel();
        modeloPartidos.addColumn("Nome");
        modeloPartidos.addColumn("Codigo");
        for (PartidoPolitico pp : ControladorPartidos.getInstance().getPartidos()){
            modeloPartidos.addRow( new Object [] {pp.getNome(), pp.getCodigo()});
        }

        //Iniciando JTable
        partidos = new JTable(modeloPartidos);
        partidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //inserindo no JSPane
        JScrollPane listScroller = new JScrollPane(partidos);
        listScroller.setPreferredSize(new Dimension(175, 75));
        c.gridx = 1;
        c.gridy = 1;
        container.add(listScroller, c);
    }

    private void criaTela() {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 200);
        setLocationRelativeTo(null);
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Criação e posicionamento label
        label = new JLabel("Esses são os atuais partidos:");
        c.gridx = 1;
        c.gridy = 0;
        container.add(label, c);

        //Config botão voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("volta");
        voltar.addActionListener(btManager);
        c.gridx = 0;
        c.gridy = 2;
        container.add(voltar, c);

        //Config botão cadastro
        cadastro = new JButton("Cadastrar");
        cadastro.setActionCommand("cadastro");
        cadastro.addActionListener(btManager);
        c.gridx = 1;
        container.add(cadastro, c);

        //Config botão exclusao
        excluir = new JButton("Excluir");
        excluir.setActionCommand("exclusao");
        excluir.addActionListener(btManager);
        c.gridx = 2;
        container.add(excluir, c);

    }

    private void exclui(){
        int index = partidos.getSelectedRow();
        Object nome = modeloPartidos.getValueAt(index, 0);
        if (!(ControladorPartidos.getInstance().exclui(nome) == null)){
            modeloPartidos.removeRow(index);
        }else {
            JOptionPane.showMessageDialog(null, "Problema ao deletar partido.");
        }
    }


    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {

            switch (ae.getActionCommand()) {
                case "cadastro":
                    ControladorPartidos.getInstance().novoCadastro();
                    break;
                case "exclusao":
                    if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o partido selecionado?", "Confirme", 2) == 0){
                        exclui();
                    }
                    break;
                case "volta":
                    ControladorPartidos.getInstance().volta();
            }
        }
    }
}
