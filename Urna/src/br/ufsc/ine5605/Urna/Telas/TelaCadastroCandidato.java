package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorCandidatos;
import br.ufsc.ine5605.Urna.DAOs.PartidoDAO;
import br.ufsc.ine5605.Urna.Elementos.CARGO;
import br.ufsc.ine5605.Urna.Elementos.Candidato;
import br.ufsc.ine5605.Urna.Elementos.PartidoPolitico;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroCandidato extends JFrame {

    private JLabel lNome;
    private JTextField txNome;
    private JLabel lCod;
    private JTextField txCodigo;
    private JLabel lPartidos;
    private DefaultComboBoxModel<String> modeloPartidos;
    private JComboBox cbPartidos;
    private JLabel lCargo;
    private JComboBox cbCargo;
    private JButton btSalva;
    private JButton btCancela;


    public TelaCadastroCandidato (ControladorCandidatos ctrl){
        //Inicialização JFrame
        super("Candidatos");
        criaTela();
        criaComboBox();
    }

    public void criaTela(){
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 200);
        setLocationRelativeTo(null);
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
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.CENTER;
        container.add(lCod,c);
        //TextField de código
        txCodigo = new JTextField();
        txCodigo.setColumns(20);
        c.gridx = 1;
        container.add(txCodigo, c);

        //Label de cargo
        lCargo = new JLabel("Cargo:");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.CENTER;
        container.add(lCargo, c);

        //Label de partido
        lPartidos = new JLabel("Partido:");
        c.gridy = 2;
        c.fill = GridBagConstraints.CENTER;
        container.add(lPartidos,c);

        //config Botao salva
        btSalva = new JButton("Salvar");
        btSalva.setActionCommand("salva");
        btSalva.addActionListener(btManager);
        c.gridx = 1;
        c.gridy = 5;
        container.add(btSalva, c);

        //config botao cancela
        btCancela = new JButton("Cancelar");
        btCancela.setActionCommand("cancela");
        btCancela.addActionListener(btManager);
        c.gridx = 0;
        c.gridy = 5;
        container.add(btCancela, c);

    }

    public void criaComboBox(){

        GridBagConstraints c = new GridBagConstraints();
        Container container = getContentPane();

        //Criação comboBox partidos
        modeloPartidos = new DefaultComboBoxModel();
        for (PartidoPolitico pp : PartidoDAO.getInstancia().getList()){
            modeloPartidos.addElement(pp.getNome());
        }
        cbPartidos = new JComboBox(modeloPartidos);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        container.add(cbPartidos, c);


        //Criação comboBox Cargo
        String[] cargos = {"Prefeito"};
        cbCargo = new JComboBox(cargos);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        container.add(cbCargo, c);

    }


    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "salva":
                    try {
                        Candidato novoCandidato = ControladorCandidatos.getInstancia().adiciona(txNome.getText(), txCodigo.getText(), modeloPartidos.getElementAt(cbPartidos.getSelectedIndex()), CARGO.PREFEITO);
                        if (novoCandidato == null){
                            JOptionPane.showMessageDialog(null, "Candidato ja existente.");
                        }else {
                            JOptionPane.showMessageDialog(null, "Candidato criado com sucesso.");
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
                    ControladorCandidatos.getInstancia().inicia();
                    break;
            }
        }
    }
}
