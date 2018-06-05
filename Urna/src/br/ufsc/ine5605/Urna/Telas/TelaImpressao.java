package br.ufsc.ine5605.Urna.Telas;

import br.ufsc.ine5605.Urna.Elementos.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TelaImpressao {

    private Scanner teclado;

    public TelaImpressao(){
        teclado = new Scanner(System.in);
    }

    public void imprimePartidos(ArrayList<PartidoPolitico> partidos) {
        String lista = "";
        for (PartidoPolitico partido : partidos){
            lista += "-"+partido.getNome()+ " nº" + partido.getCodigo() + "\n";
        }
        System.out.println(lista);
    }

    public void imprimeEleitores(ArrayList<Eleitor> eleitores) {
        String lista = "";
        for (Eleitor eleitor : eleitores){
            lista += "-"+ eleitor.getCodigo() + "\n";
        }
        System.out.println(lista);
    }

    public void imprimeCandidatos(ArrayList<Candidato> candidatos) {
        String lista = "";
        for (Candidato candidato : candidatos){
            lista += "-"+candidato.getNome()+" nº"+candidato.getNumCandidato()+"-"+candidato.getCargo()+"\n";
        }
        System.out.println(lista);
    }

    public void imprimeCandidatosGOV(ArrayList<Candidato> candidatos) {
        String lista = "";
        for (Candidato candidato : candidatos){
            if (candidato.getCargo() == CARGO.GOVERNADOR) {
                lista += "-" + candidato.getNome() + " nº" + candidato.getNumCandidato() + "-" + candidato.getCargo()+"\n";
            }
        }
        System.out.println(lista);
    }

    public void imprimeCandidatosDEP(ArrayList<Candidato> candidatos){
        String lista = "";
        for (Candidato candidato : candidatos){
            if (candidato.getCargo() == CARGO.DEPUTADO) {
                lista += "-" + candidato.getNome() + " nº" + candidato.getNumCandidato() + "-" + candidato.getCargo()+"\n";
            }
        }
        System.out.println(lista);
    }

    public void infosUrna(Eleitor eleitor, int turno) {
        System.out.println("Bem vindo eleitor " +eleitor.getCodigo()+", esta turna esta configurada para o turno " +turno);
        System.out.println("As opções de candidatos sao:");
    }
}
