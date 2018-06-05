package br.ufsc.ine5605.Urna.Telas;

import java.util.Scanner;

public class TelaOpcoes {

    private Scanner teclado;

    public TelaOpcoes(){
        teclado = new Scanner(System.in);
    }

    public int escolha(){
        int i = 0;
        try{
            do {
                System.out.println("--------- Bem-vindo as opcoes ---------");
                System.out.println("Digite o menu desejado:\n1 - Partido\n2 - Candidato\n3 - Eleitor\n4 - Voltar ao menu principal");
                i = teclado.nextInt();
            }while (i<1 || i>4);
        }catch (Exception e){
            System.err.println("Digite apenas o numero da opcao");
        }


        return i;
    }

    public int menuEscolha(String nome){
        int i = 0;
        try {
            do {
                System.out.println("--------- Bem-vindo ao menu de "+nome+" --------- ");
                System.out.println("1 - Cadastro\n2 - Listagem\n3 - Exclusão\n4 - Voltar");
                i = teclado.nextInt();
            }while (i<1 || i>4);
        }catch (Exception e){
            System.out.println("Digite apenas o numero da opcao");
        }


        return i;
    }

    public void resultado (String result){
        System.out.println(result);
    }

}
