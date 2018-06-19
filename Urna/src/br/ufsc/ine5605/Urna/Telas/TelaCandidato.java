package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorCandidatos;
import br.ufsc.ine5605.Urna.Elementos.Candidato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCandidato extends JFrame {

    private JTable candidatos;
    private DefaultTableModel modeloCandidatos;
    private JLabel label;
    private JButton cadastro;
    private JButton excluir;
    private JButton voltar;

    public TelaCandidato() {
        //Inicia JFrame
        super("Candidato");
        criaTela();
        criaTabela();
    }

    public void criaTela() {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 200);
        setLocationRelativeTo(null);
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Config Label titulo
        label = new JLabel("Esses são os atuais candidatos:");
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

    public void criaTabela() {

        Container container = getContentPane();
        GridBagConstraints c = new GridBagConstraints();

        //Criação da lista nomes
        modeloCandidatos = new DefaultTableModel();
        modeloCandidatos.addColumn("Nome");
        modeloCandidatos.addColumn("Codigo");
        modeloCandidatos.addColumn("Partido");
        modeloCandidatos.addColumn("Cargo");
        for (Candidato candidato : ControladorCandidatos.getInstancia().getCandidatos()) {
            modeloCandidatos.addRow(new Object[]{candidato.getNome(), candidato.getCodigo(), candidato.getPartido().getNome(), candidato.getCargo().getNome()});
        }

        //Iniciando JTable
        candidatos = new JTable(modeloCandidatos);
        candidatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //inserindo no JSPane
        JScrollPane listScroller = new JScrollPane(candidatos);
        listScroller.setPreferredSize(new Dimension(175, 75));
        c.gridx = 1;
        c.gridy = 1;
        container.add(listScroller, c);

    }

    public void exclui() {
        int index = candidatos.getSelectedRow();
        Object nome = modeloCandidatos.getValueAt(index, 0);
        if (!(ControladorCandidatos.getInstancia().exclui(nome) == null)) {
            modeloCandidatos.removeRow(index);
        } else {
            JOptionPane.showMessageDialog(null, "Problema ao deletar candidato.");
        }
    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()) {
                case "cadastro":
                    ControladorCandidatos.getInstancia().novoCadastro();
                    break;
                case "exclusao":
                    if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o candidato selecionado?", "Confirme", 2) == 0) {
                        exclui();
                    }
                    break;
                case "volta":
                    ControladorCandidatos.getInstancia().volta();
            }
        }
    }
}


