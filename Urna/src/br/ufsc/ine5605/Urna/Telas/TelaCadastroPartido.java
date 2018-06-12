package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorPartidos;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroPartido extends JFrame {

    private JLabel lNome;
    private JLabel lCod;
    private JTextField nome;
    private JTextField codigo;
    private JButton salva;
    private JButton cancela;


    public TelaCadastroPartido( ){

        //Inicialização JFrame
        super("Partidos");
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 150);
        setLocationRelativeTo(null);
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Campo de nome
        lNome = new JLabel("Nome:");
        c.gridx = 0;
        c.gridy = 0;
        container.add(lNome,c);
        nome = new JTextField();
        nome.setColumns(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        container.add(nome,c);

        //Campo de código
        lCod = new JLabel("Codigo:");
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        container.add(lCod,c);
        codigo = new JTextField();
        codigo.setColumns(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        container.add(codigo, c);

        //config Botao salva
        salva = new JButton("Salvar");
        salva.setActionCommand("salva");
        salva.addActionListener(btManager);
        c.gridx = 1;
        c.gridy = 2;
        container.add(salva, c);

        //config botao cancela
        cancela = new JButton("Cancelar");
        cancela.setActionCommand("cancela");
        cancela.addActionListener(btManager);
        c.gridx = 0;
        c.gridy = 2;
        container.add(cancela, c);
    }

    public JTextField getNome() {
        return nome;
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "salva":
                    try {
                        PartidoPolitico novoPartido = ControladorPartidos.getInstance().adiciona(nome.getText(), codigo.getText());
                        if (novoPartido == null){
                            JOptionPane.showMessageDialog(null, "Partido ja existente.");
                        }else {
                            JOptionPane.showMessageDialog(null, "Partido criado com sucesso.");
                        }
                    } catch (CodigoNaoNumericoException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }finally {
                        codigo.setText("");
                        nome.setText("");
                    }
                    break;
                case "cancela":
                    setVisible(false);
                    codigo.setText("");
                    nome.setText("");
                    ControladorPartidos.getInstance().inicia();
                    break;
            }
        }
    }
}
