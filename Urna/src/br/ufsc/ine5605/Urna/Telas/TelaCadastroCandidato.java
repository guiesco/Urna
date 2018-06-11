package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorCandidatos;
import br.ufsc.ine5605.Urna.Elementos.CARGO;
import br.ufsc.ine5605.Urna.Elementos.Candidato;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroCandidato extends JFrame {

    private ControladorCandidatos ctrlCandidatos;
    private JLabel lNome;
    private JTextField nome;
    private JLabel lCod;
    private JTextField codigo;
    private JLabel lPartidos;
    private DefaultComboBoxModel<String> partidosCadastrados;
    private JComboBox partidos;
    private JLabel lCargo;
    private JComboBox cargo;
    private JButton salva;
    private JButton cancela;


    public TelaCadastroCandidato (ControladorCandidatos ctrl){
        //Inicialização JFrame
        super("Candidatos");
        ctrlCandidatos = ctrl;
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 200);
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
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.CENTER;
        container.add(lCod,c);

        codigo = new JTextField();
        codigo.setColumns(20);
        c.gridx = 1;
        c.gridy = 1;
        container.add(codigo, c);

        //Criação comboBox partidos
        lPartidos = new JLabel("Partido:");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.CENTER;
        container.add(lPartidos,c);

        partidosCadastrados = new DefaultComboBoxModel();
        for (PartidoPolitico pp : ctrlCandidatos.getPartidos()){
            partidosCadastrados.addElement(pp.getNome());
        }
        partidos = new JComboBox(partidosCadastrados);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        container.add(partidos, c);

        //Criação comboBox Cargo
        lCargo = new JLabel("Cargo:");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.CENTER;
        container.add(lCargo, c);
        
        String[] cargos = {"Prefeito"};
        JComboBox cargo = new JComboBox(cargos);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        container.add(cargo, c);

        //config Botao salva
        salva = new JButton("Salvar");
        salva.setActionCommand("salva");
        salva.addActionListener(btManager);
        c.gridx = 1;
        c.gridy = 5;
        container.add(salva, c);

        //config botao cancela
        cancela = new JButton("Cancelar");
        cancela.setActionCommand("cancela");
        cancela.addActionListener(btManager);
        c.gridx = 0;
        c.gridy = 5;
        container.add(cancela, c);



    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "salva":
                    try {
                        Candidato novoCandidato = ctrlCandidatos.adiciona(nome.getText(), codigo.getText(), partidosCadastrados.getElementAt(partidos.getSelectedIndex()), CARGO.PREFEITO);
                        if (novoCandidato == null){
                            JOptionPane.showMessageDialog(null, "Candidato ja existente.");
                        }else {
                            JOptionPane.showMessageDialog(null, "Candidato criado com sucesso.");
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
                    ctrlCandidatos.inicia();
                    break;
            }
        }
    }
}
