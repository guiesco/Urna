package br.ufsc.ine5605.Urna.Exceptions;

import javax.swing.*;

public class CodigoNaoNumericoException extends Exception {

    public CodigoNaoNumericoException (){
        this("Por favor, digite apenas numeros.");
    }

    public CodigoNaoNumericoException (String msg){
        super(msg);
    }

}
