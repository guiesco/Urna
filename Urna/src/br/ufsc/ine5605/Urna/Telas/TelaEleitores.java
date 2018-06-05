package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Controladores.ControladorEleitores;

import javax.swing.*;
import java.awt.*;

public class TelaEleitores extends JFrame {

    private ControladorEleitores ctrlEleitores;
    private JButton cadastra;

    public TelaEleitores(ControladorEleitores ctrl){
        super("Eleitores");
        ctrlEleitores = ctrl;
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setSize(400, 150);
        setLocationRelativeTo(null);

        cadastra = new JButton("Cadastra");


    }
}
