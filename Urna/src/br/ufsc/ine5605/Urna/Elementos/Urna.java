package br.ufsc.ine5605.Urna.Elementos;

import br.ufsc.ine5605.Urna.Telas.TelaCadastro;
import br.ufsc.ine5605.Urna.Telas.TelaImpressao;

import java.util.ArrayList;

public class Urna {

    private int turno;
    private int secao;
    private int votoBrancoD;
    private int votoBrancoG;
    private ArrayList<Candidato> candidatos;
    private Zona zona;
    private ArrayList<Eleitor> eleitores;
    private TelaCadastro telaCadastro;
    private TelaImpressao telaImpressao;

    public Urna(int secao, ArrayList<Candidato> candidatos, ArrayList<Eleitor> eleitores, Zona zona){
        this.secao = secao;
        this.candidatos = candidatos;
        votoBrancoD = 0;
        votoBrancoG = 0;
        this.turno = 1;
        this.zona = zona;
        this.eleitores = eleitores;
        telaImpressao = new TelaImpressao();
        telaCadastro = new TelaCadastro();
    }

    public int getVotoBrancoD(){
        return votoBrancoD;
    }

    public void setCandidatos(ArrayList<Candidato> c2){
        this.candidatos = c2;
    }

    public void setTurno(int turno){
        this.turno = turno;
    }
    public int getVotoBrancoG(){
        return votoBrancoG;
    }


    public void vota(Eleitor eleitor){
        telaImpressao.infosUrna(eleitor, turno);
        String confirma = "";
        boolean eh = true;
        do {
            telaImpressao.imprimeCandidatosDEP(candidatos);
            int codDEP = telaCadastro.recebeCodigo("deputado");
            for (Candidato candidato : candidatos){
                if (candidato.getNumCandidato() == codDEP){
                    confirma = "Deseja votar no candidato " + candidato.getNome()+"?";
                    eh = telaCadastro.confirma(confirma);
                    if (eh){
                        candidato.recebeVoto();
                    }else{
                        confirma = "Deseja que o voto seja nulo?";
                        eh = telaCadastro.confirma(confirma);
                        if (eh){
                            votoBrancoD++;
                        }
                    }
                }
            }
        }while (!eh);

        do {
            telaImpressao.imprimeCandidatosGOV(candidatos);
            int codGOV = telaCadastro.recebeCodigo("governador");
            for (Candidato candidato : candidatos){
                if (candidato.getNumCandidato() == codGOV){
                    confirma = "Deseja votar no candidato " + candidato.getNome()+"?";
                    eh = telaCadastro.confirma(confirma);
                    if (eh){
                        candidato.recebeVoto();
                    }else {
                        confirma = "Deseja que o voto seja nulo?";
                        eh = telaCadastro.confirma(confirma);
                        if (eh){
                            votoBrancoG++;
                        }
                    }
                }
            }
        }while (!eh);



    }

    public int getSecao() {
        return secao;
    }
}
