package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Telas.TelaCadastro;
import br.ufsc.ine5605.Urna.Telas.TelaImpressao;

public interface IControlador {

    public String novoCadastro();
    public void imprime(TelaImpressao telaImpressao);
    public String exclui(String nome);

    void inicia();
}
