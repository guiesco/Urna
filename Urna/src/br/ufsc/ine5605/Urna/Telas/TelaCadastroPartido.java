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
    private JTextField txNome;
    private JTextField txCodigo;
    private JButton salva;
    private JButton cancela;


    public TelaCadastroPartido( ){
        super("Partidos");
        criaTela();
    }

    private void criaTela(){
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        setSize(400, 150);
        setLocationRelativeTo(null);
        GridBagConstraints c = new GridBagConstraints();
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Label de nome
        lNome = new JLabel("Nome:");
        c.gridx = 0;
        c.gridy = 0;
        container.add(lNome,c);
        //TextField de nome
        txNome = new JTextField();
        txNome.setColumns(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        container.add(txNome,c);

        //Label de código
        lCod = new JLabel("Codigo:");
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        container.add(lCod,c);
        //TextField de código
        txCodigo = new JTextField();
        txCodigo.setColumns(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        container.add(txCodigo, c);

        //config Botao salva
        salva = new JButton("Salvar");
        salva.setActionCommand("salva");
        salva.addActionListener(btManager);
        c.gridy = 2;
        container.add(salva, c);

        //config botao cancela
        cancela = new JButton("Cancelar");
        cancela.setActionCommand("cancela");
        cancela.addActionListener(btManager);
        c.gridx = 0;
        container.add(cancela, c);

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "salva":
                    try {
                        PartidoPolitico novoPartido = ControladorPartidos.getInstance().adiciona(txNome.getText(), txCodigo.getText());
                        if (novoPartido == null){
                            JOptionPane.showMessageDialog(null, "Partido ja existente.");
                        }else {
                            JOptionPane.showMessageDialog(null, "Partido criado com sucesso.");
                        }
                    } catch (CodigoNaoNumericoException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }finally {
                        txCodigo.setText("");
                        txNome.setText("");
                    }
                    break;
                case "cancela":
                    setVisible(false);
                    txCodigo.setText("");
                    txNome.setText("");
                    ControladorPartidos.getInstance().inicia();
                    break;
            }
        }
    }
}
