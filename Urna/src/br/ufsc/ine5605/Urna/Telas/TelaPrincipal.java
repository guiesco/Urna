package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    private JLabel bemvindo;
    private JLabel escolha;
    private JButton option1Button;
    private JButton option2Button;
    private JButton option3Button;
    private JButton votacaoButton;

    public TelaPrincipal(){
        super("Sistema de Urnas");
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        bemvindo = new JLabel();
        escolha = new JLabel();
        option1Button = new JButton();
        option2Button = new JButton();
        option3Button = new JButton();
        votacaoButton = new JButton();

        bemvindo.setText("Bem-vindo ao sistema de urnas!");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 2;
        c.gridwidth = 2;
        c.gridx=1;
        c.gridy=0;
        container.add(bemvindo, c);
        escolha.setText("Escolha uma das opcoes a seguir:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=2;
        container.add(escolha, c);
        option1Button.setText("Partidos");
        option1Button.setActionCommand("1");
        c.gridx=0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridy=4;
        container.add(option1Button, c);
        option2Button.setText("Candidatos");
        option2Button.setActionCommand("2");
        c.gridx=1;
        c.gridy=4;
        container.add(option2Button, c);
        option3Button.setText("Eleitores");
        option3Button.setActionCommand("3");
        c.gridx=2;
        c.gridy=4;
        container.add(option3Button, c);
        votacaoButton.setText("Votacao");
        votacaoButton.setActionCommand("4");
        c.gridx=3;
        c.gridy=4;
        container.add(votacaoButton, c);

        GerenciadorBotoes btManager = new GerenciadorBotoes();
        option1Button.addActionListener(btManager);
        option2Button.addActionListener(btManager);
        option3Button.addActionListener(btManager);
        votacaoButton.addActionListener(btManager);

        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private class GerenciadorBotoes implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            ControladorPrincipal.getInstancia().selecionaModo(ae.getActionCommand());
        }
    }
}
