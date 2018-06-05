package br.ufsc.ine5605.Urna.Controladores;

import br.ufsc.ine5605.Urna.Elementos.*;

import java.util.ArrayList;

public class SistemaVotacao {

    private ArrayList<Integer> secoesFloripa;
    private ArrayList<Eleitor> eleitoresFloripa;
    private ArrayList<Eleitor> eleitoresSJ;
    private ArrayList<Eleitor> eleitores;
    private ArrayList<Integer> secoesSJ;
    private ArrayList<Urna> urnas;


    public SistemaVotacao(){
        secoesSJ = new ArrayList<>();
        secoesFloripa = new ArrayList<>();
        eleitoresSJ = new ArrayList<>();
        eleitoresFloripa = new ArrayList<>();
        eleitores = new ArrayList<>();
        urnas = new ArrayList<>();

    }

    public void inicia() {
    }

    /*public void iniciaVotacao(ArrayList<Eleitor> eleitores, ArrayList<Candidato> candidatos) {
        this.eleitores = eleitores;
        for (Eleitor eleitor : eleitores) {
            if (eleitor.getZona() == Zona.Florianopolis) {
                if (!secoesFloripa.contains(eleitor.getSecao())) {
                    secoesFloripa.add(eleitor.getSecao());
                    eleitoresFloripa.add(eleitor);
                }
            } else {
                if (!secoesSJ.contains(eleitor.getSecao())) {
                    secoesSJ.add(eleitor.getSecao());
                    eleitoresSJ.add(eleitor);
                }
            }
        }
        ArrayList<Candidato> candidatosDEP = new ArrayList<>();
        ArrayList<Candidato> candidatosGOV = new ArrayList<>();
        for (Candidato candidato : candidatos){
            if (candidato.getCargo() == CARGO.DEPUTADO && !candidatosDEP.contains(candidato)){
                candidatosDEP.add(candidato);
            }else if ((candidato.getCargo() == CARGO.GOVERNADOR && !candidatosGOV.contains(candidato))){
                candidatosGOV.add(candidato);
            }
        }

        boolean adiciona = true;
        for (int secao : secoesFloripa) {
            for (Urna urna : urnas){
                if (urna.getSecao() == secao){
                    adiciona = false;
                }
            }
            if (adiciona){
                Urna novaUrna = new Urna(secao, candidatos, eleitoresFloripa, Zona.Florianopolis);
                urnas.add(novaUrna);
            }
        }

        for (int secao : secoesSJ) {
            for (Urna urna : urnas){
                if (urna.getSecao() == secao){
                    adiciona = false;
                }
            }
            if (adiciona){
                Urna novaUrna = new Urna(secao, candidatos, eleitoresSJ, Zona.Sao_Jose);
                urnas.add(novaUrna);
            }
        }
        int totalVotos = 0;
        boolean termina = true;
        String confirmacao = "Deseja finalizar a votacao?";
        do {
            int codigo = telaCadastro.recebeCodigo("eleitor");
            for (Eleitor eleitor : eleitores) {
                if (eleitor.getCodigo() == codigo) {
                    int secao = eleitor.getSecao();
                    for (Urna urna : urnas) {
                        if (urna.getSecao() == secao) {
                            urna.vota(eleitor);
                            eleitor.jaVotou();
                            totalVotos++;
                        }
                    }
                }
            }
            termina = telaCadastro.confirma(confirmacao);
        }while (!termina);

        resultadoVotacao(candidatosDEP,candidatosGOV, totalVotos);
    }*/

    public void resultadoVotacao(ArrayList<Candidato> candidatosDEP, ArrayList<Candidato> candidatosGOV, int totalVotos){
        Candidato vencedor = new Candidato("a", 879816, CARGO.GOVERNADOR, new PartidoPolitico("a", 48486));
        Candidato segundo = vencedor;
        ArrayList<Candidato> c2 = new ArrayList<>();
        int totalBranco = 0;

        for (Candidato candidato : candidatosDEP){
            if (segundo.getVotos()<candidato.getVotos()){
                segundo = candidato;
                if (segundo.getVotos()>vencedor.getVotos());
                candidato = vencedor;
                vencedor = segundo;
                segundo = candidato;
            }
        }

        for (Urna urna : urnas){
            totalBranco += urna.getVotoBrancoD();
        }

        if ((totalVotos-totalBranco)/2 > vencedor.getVotos()){
            c2.clear();
            c2.add(vencedor);
            c2.add(segundo);
            for (Urna urna : urnas){
                urna.setTurno(2);
                urna.setCandidatos(c2);
            }
        }else {
            System.out.println("O vencedor eh " +vencedor.getCargo()+ vencedor.getNome());
        }

        vencedor = new Candidato("a", 879816, CARGO.GOVERNADOR, new PartidoPolitico("a", 48486));
        segundo = vencedor;
        for (Candidato candidato : candidatosGOV){
            if (segundo.getVotos()<candidato.getVotos()){
                segundo = candidato;
                if (segundo.getVotos()>vencedor.getVotos());
                candidato = vencedor;
                vencedor = segundo;
                segundo = candidato;
            }
        }

        for (Urna urna : urnas){
            totalBranco = 0;
            totalBranco += urna.getVotoBrancoG();
        }

        if ((totalVotos-totalBranco)/2 > vencedor.getVotos()){
            c2.add(vencedor);
            c2.add(segundo);
            for (Urna urna : urnas){
                urna.setTurno(2);
                urna.setCandidatos(c2);
            }
        }else {
            System.out.println("O vencedor eh " +vencedor.getCargo()+ vencedor.getNome());
        }

  /*      if (c2.size()>1){
            iniciaVotacao(eleitores, c2);
        }
*/
    }



}
