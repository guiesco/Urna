package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.SistemaVotacao;
import br.ufsc.ine5605.Urna.Elementos.Candidato;
import br.ufsc.ine5605.Urna.Elementos.Eleitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTitulo extends JFrame {

    private JLabel lTitulo;
    private JTextField txTitulo;
    private JButton btSalva;
    private JButton btFinaliza;

    public TelaTitulo(){
        super("Titulo de Eleitor");
        criaTela();
    }

    public void criaTela(){
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 150);
        setLocationRelativeTo(null);
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Label de nome
        lTitulo = new JLabel("Digite seu titulo de eleitor:");
        c.gridx = 1;
        c.gridy = 0;
        container.add(lTitulo,c);
        //TextField de nome
        txTitulo = new JTextField();
        txTitulo.setColumns(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        container.add(txTitulo,c);

        //config Botao salva
        btSalva = new JButton("Salvar");
        btSalva.setActionCommand("salva");
        btSalva.addActionListener(btManager);
        c.gridx = 0;
        c.gridy = 2;
        container.add(btSalva, c);

        //config botao cancela
        btFinaliza = new JButton("Finalizar");
        btFinaliza.setActionCommand("fim");
        btFinaliza.addActionListener(btManager);
        c.gridx = 2;
        container.add(btFinaliza, c);
    }

    public void jaVotou(){
        JOptionPane.showMessageDialog(null, "Esse eleitor ja votou.");
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "salva":
                    Eleitor eleitor = SistemaVotacao.getInstance().verificaTitulo(txTitulo.getText());
                    if (eleitor == null){
                        JOptionPane.showMessageDialog(null, "Titulo nao encontrado.");
                    }
                    txTitulo.setText("");
                    break;
                case "fim":
                    Candidato vencedor = SistemaVotacao.getInstance().verificaVencedor();
                    String msg = "O vencedor foi o candidato " + vencedor.getNome()
                            + " com o total de " + vencedor.getVotos() + " votos";
                    JOptionPane.showMessageDialog(null, msg);
                    setVisible(false);
                    txTitulo.setText("");
                    SistemaVotacao.getInstance().volta();
                    break;
            }
        }
    }
}
