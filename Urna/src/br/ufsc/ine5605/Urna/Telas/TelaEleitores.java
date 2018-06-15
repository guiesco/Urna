package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorEleitores;
import br.ufsc.ine5605.Urna.Controladores.ControladorPrincipal;
import br.ufsc.ine5605.Urna.Elementos.Eleitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

//falta fazer o dao para eleitores

public class TelaEleitores extends JFrame {

    private JButton cadastrar;
    private JButton excluir;
    private JButton voltar;
    private JTable eleitores;
    private DefaultTableModel modeloTabelaEleitores;
    private JLabel label;

    public TelaEleitores(){
        super("Eleitores");
        criaTela();
        criaTabela();
    }

    private void criaTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 200);
        setLocationRelativeTo(null);
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Criação e posicionamento label
        label = new JLabel("Eleitores já cadastrados:");
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
        cadastrar = new JButton("Cadastrar");
        cadastrar.setActionCommand("cadastro");
        cadastrar.addActionListener(btManager);
        c.gridx = 1;
        container.add(cadastrar, c);

        //Config botão exclusao
        excluir = new JButton("Excluir");
        excluir.setActionCommand("exclusao");
        excluir.addActionListener(btManager);
        c.gridx = 2;
        container.add(excluir, c);
    }

    private void criaTabela() {
        Container container = getContentPane();
        GridBagConstraints c = new GridBagConstraints();
        
        //Criação da lista nomes
        modeloTabelaEleitores = new DefaultTableModel();
        modeloTabelaEleitores.addColumn("Titulo");
        modeloTabelaEleitores.addColumn("Zona");
        modeloTabelaEleitores.addColumn("Secao");
        for (Eleitor eleitores : ControladorEleitores.getInstance().getEleitores()){
            modeloTabelaEleitores.addRow( new Object [] {eleitores.getCodigo(), eleitores.getZona(), eleitores.getSecao()});
        }
        
        //Iniciando JTable
        eleitores = new JTable(modeloTabelaEleitores);
        eleitores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //inserindo no JSPane
        JScrollPane listScroller = new JScrollPane(eleitores);
        listScroller.setPreferredSize(new Dimension(175, 75));
        c.gridx = 1;
        c.gridy = 1;
        container.add(listScroller, c);
    }
    
    public void exclui(){
        int index = eleitores.getSelectedRow();
        Object titulo = modeloTabelaEleitores.getValueAt(index, 0);
        if (!(ControladorEleitores.getInstance().exclui(titulo) == null)){ //fazer meteodo pra excluir no controlador de eleitor como tem no de partido
            modeloTabelaEleitores.removeRow(index);
        }else {
            JOptionPane.showMessageDialog(null, "Problema ao deletar eleitor.");
        }
    }


    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()) {
                case "cadastro":
                    ControladorEleitores.getInstance().novoCadastro(); 
                    break;
                case "exclusao":
                    if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o eleitor selecionado?", "Confirme", 2) == 0){
                        exclui();
                    }
                    break;
                case "volta":
                    ControladorEleitores.getInstance().volta();
            }
        }
    }
}
