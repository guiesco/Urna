package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorPartidos;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaPartidos extends JFrame {

    private ControladorPartidos ctrlPartidos;
    private JLabel label;
    private JButton cadastro;
    private JButton excluir;

    public TelaPartidos(final ControladorPartidos ctrl, ArrayList<PartidoPolitico> partidos1){

        //Inicialização JFrame
        super("Partidos");
        ctrlPartidos = ctrl;
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 150);
        setLocationRelativeTo(null);
        GerenciadorBotoes btManager = new GerenciadorBotoes();


        //Criação e posicionamento label
        label = new JLabel("Esses são os atuais partidos:");
        c.gridx = 1;
        c.gridy = 0;
        container.add(label, c);

        //Criação da lista
        DefaultListModel<String> partidosCadastrados = new DefaultListModel();
        partidosCadastrados.addElement("p1");
        partidosCadastrados.addElement("p2");
        partidosCadastrados.addElement("p3");
        for (PartidoPolitico pp : partidos1){
            partidosCadastrados.addElement(pp.getNome());
        }
        JList partidos = new JList(partidosCadastrados);
        partidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        partidos.setLayoutOrientation(JList.VERTICAL);
        partidos.setVisibleRowCount(-1);JScrollPane listScroller = new JScrollPane(partidos);
        listScroller.setPreferredSize(new Dimension(250, 80));
        c.gridx = 1;
        c.gridy = 1;
        container.add(partidos, c);

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

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {

            switch (ae.getActionCommand()) {
                case "cadastro":
                    ctrlPartidos.novoCadastro();
                    break;
                case "exclusao":
                    ;
                    break;
                default:
                    ;
                    break;
            }
        }
    }
}
