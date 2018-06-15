/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Urna.Telas;

import javax.swing.JFrame;
import br.ufsc.ine5605.Urna.Controladores.ControladorEleitores;
import br.ufsc.ine5605.Urna.DAOs.EleitorDAO;
import br.ufsc.ine5605.Urna.Elementos.Zona;
import br.ufsc.ine5605.Urna.Elementos.Eleitor;
import br.ufsc.ine5605.Urna.Exceptions.CodigoNaoNumericoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Caroline Ignácio
 */
public class TelaCadastroEleitor extends JFrame {
    
    private ControladorEleitores ctrlEleitores;
    private JLabel lTitulo;
    private JTextField titulo;
    private JLabel lZona;
    private JTextField zona;
    private JLabel lEleitores;
    private DefaultComboBoxModel<String> zonasCadastradas;
    private DefaultComboBoxModel<String> secoesCadastradas;
    private JLabel lSecao;
    private JTextField secao;
    private JComboBox secoes;
    private JComboBox zonas;
    private JButton salvar;
    private JButton cancelar;


    public TelaCadastroEleitor (ControladorEleitores ctrl){
        //Inicialização JFrame
        super("Eleitores");;
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 200);
        setLocationRelativeTo(null);
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Campo para digitar o titulo do novo eleitor
        lTitulo = new JLabel("Titulo:");
        c.gridx = 0;
        c.gridy = 0;
        container.add(lTitulo,c);

        titulo = new JTextField();
        titulo.setColumns(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        container.add(titulo,c);

        /*Campo para definir a zona do novo eleitor
        lZona = new JLabel("Zona:");
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.CENTER;
        container.add(lZona,c);

        zona = new JTextField();
        zona.setColumns(20);
        c.gridx = 1;
        c.gridy = 1;
        container.add(zona, c); */

        //Criação para definir a secao do novo eleitor
        lZona = new JLabel("Zona:");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.CENTER;
        container.add(lZona,c);

        zonasCadastradas = new DefaultComboBoxModel();
        zonasCadastradas.addElement("Florianopolis");
        zonasCadastradas.addElement("Sao Jose");
        
        zonas = new JComboBox(zonasCadastradas);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        container.add(zonas, c);
  
        //Definir a secao de votacao do eleitor
        lSecao = new JLabel("Secao:");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.CENTER;
        container.add(lSecao, c);
        
        secoesCadastradas = new DefaultComboBoxModel();
        secoesCadastradas.addElement("1");
        secoesCadastradas.addElement("2");
        
        secoes = new JComboBox(secoesCadastradas); //tem que descobrir qual a treta aqui
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        container.add(secoes, c);

        //config Botao salva
        salvar = new JButton("Salvar");
        salvar.setActionCommand("salva");
        salvar.addActionListener(btManager);
        c.gridx = 1;
        c.gridy = 5;
        container.add(salvar, c);

        //config botao cancela
        cancelar = new JButton("Cancelar");
        cancelar.setActionCommand("cancela");
        cancelar.addActionListener(btManager);
        c.gridx = 0;
        c.gridy = 5;
        container.add(cancelar, c);



    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "salva":
                    try {
                        Eleitor novoEleitor = new Eleitor(0, 0, Zona.Florianopolis);
                        int secaoEleitor = 0;
                        int tituloEleitor = Integer.parseInt(titulo.getText());
                        String zonaEleitor = zonas.getSelectedItem().toString();
                        if (secoes.getSelectedItem().toString().equals("1")) {
                            secaoEleitor = 1;
                        }else{
                            secaoEleitor = 2;
                        }
                        if (zonaEleitor.equals("Florianopolis")) {
                            novoEleitor = ControladorEleitores.getInstance().adiciona(secaoEleitor, tituloEleitor, Zona.Florianopolis);
                        }else{
                            novoEleitor = ControladorEleitores.getInstance().adiciona(secaoEleitor, tituloEleitor, Zona.Sao_Jose);
                        }
                        if (novoEleitor == null){
                            JOptionPane.showMessageDialog(null, "Candidato ja existente.");
                        }else {
                            JOptionPane.showMessageDialog(null, "Candidato criado com sucesso.");
                        }
                    } catch (CodigoNaoNumericoException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }finally {
                        //titulo.setText("");
                        //secao.setText("");
                        //zona.setText("");
                    }
                    break;
                case "cancela":
                    setVisible(false);
                    secao.setText("");
                    titulo.setText("");
                    zona.setText("");
                    ControladorEleitores.getInstance().inicia();
                    break;
            }
        }
    }
}
