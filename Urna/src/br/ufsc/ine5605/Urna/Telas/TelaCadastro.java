package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Elementos.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TelaCadastro {

    private Scanner teclado;

    public TelaCadastro(){
        teclado = new Scanner(System.in);
    }

    public String recebeNome(String nome){
        String nomeRecebido = "";
        try {
            System.out.println("Digite o nome do " + nome);
            nomeRecebido = teclado.next();
        } catch (Exception e) {
            System.out.println("Digite apenas letras");
        }
        return nomeRecebido;
    }

    public int recebeSecao (){
        int codigoRecebido = 0;
        try {
            System.out.println("Digite o numero da secao");
            codigoRecebido = teclado.nextInt();
        } catch (Exception e) {
            System.out.println("Digite APENAS numeros.");
        }
        return codigoRecebido;
    }

    public int recebeCodigo(String nome){
        int codigoRecebido = 0;
        if (nome.equalsIgnoreCase("deputado")||nome.equalsIgnoreCase("governador")){
            System.out.println("Digite o codigo do seu candidato, ou 00 para branco e 99 para nulo");
            codigoRecebido = teclado.nextInt();
        }else if (nome.equalsIgnoreCase("candidato")){
            System.out.println("Digite um codigo entre 1 e 98 para o " + nome);
            codigoRecebido = teclado.nextInt();
        }else {
            try {
                System.out.println("Digite o codigo do " + nome);
                codigoRecebido = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Digite APENAS numeros.");
            }
        }


        return codigoRecebido;
    }

    public CARGO recebeCargo (){
        int i = 0;
        CARGO cargo;
        try {
            do {
                System.out.println("Escolha o numero do cargo:\n1 - Deputado\n2 - Governador");
                i = teclado.nextInt();
            }while (!(i == 1 || i == 2));
        }catch (Exception e){
            System.out.println("Digite APENAS numeros.");
        }


        if (i==1){
            cargo = CARGO.DEPUTADO;
        }else {
            cargo = CARGO.GOVERNADOR;
        }
        return cargo;

    }

    public PartidoPolitico recebePartido(ArrayList<PartidoPolitico> partidos){
        String nomePartido = "";
        int codigoPartido = 0;

        System.out.println("Existem os seguintes partidos cadastrados:");
        for (PartidoPolitico partido : partidos){
            System.out.println("-"+partido.getNome()+" nº "+partido.getCodigo());
        }
        System.out.println("Se o partido desejado não aparece, cadastre um novo partido.");
        System.out.println("Digite o numero do partido ao qual quer ser filiado");
        try {
            codigoPartido = teclado.nextInt();
        }catch (Exception e){
            System.out.println("Digite APENAS o numero do partido");
        }
        for (PartidoPolitico partido : partidos){
            if (partido.getCodigo() == codigoPartido){
                return partido;
            }
        }

        return null;
    }

    public Zona recebeZona (){
        int i = 0;
        Zona zona;
        try {
            do {
                System.out.println("Escolha o numero da zona:\n1 - Florianopolis\n2 - Sao Jose");
                i = teclado.nextInt();
            }while (!(i == 1 || i == 2));
        }catch (Exception e){
            System.out.println("Digite APENAS numeros.");
        }


        if (i==1){
            zona = Zona.Florianopolis;
        }else {
            zona = Zona.Sao_Jose;
        }
        return zona;

    }

    public boolean confirma(String confirmacao){
        System.out.println(confirmacao);
        System.out.println("s/n");
        char temp = teclado.next().charAt(0);
        if (temp == 's'){
            return true;
        }else if(temp == 'n'){
            return false;
        }
        return false;
    }
}
