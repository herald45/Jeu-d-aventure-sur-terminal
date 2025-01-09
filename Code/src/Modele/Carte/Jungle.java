package Modele.Carte;

import java.util.Random;

public class Jungle implements CarteFactory {
    private final String[] cocotierEtRocher = {"J","P"};
    private final String[] nourriture = {"G","C"};

    private final int pourcentCOCOTIER_ROCHER = 10;
    private final int pourcentNOURRITURE = 3;
    private final int pourcentSINGE = 1;


    public void remplirMap(Carte carte, String lettre, int quantite, Random r){
        int nbPlace = 0;
        while(nbPlace < quantite){
            int ligne = r.nextInt(carte.getNbLignes());
            int colonne = r.nextInt(carte.getNbColonnes());
            if (carte.getCase(ligne,colonne).equals(" ")){
                carte.setCase(ligne,colonne,lettre);
                nbPlace++;
            }
        }
    }

    @Override
    public void genererCarte(Carte carte) {
        Random r = new Random();
        int casesTotal = carte.getNbColonnes() * carte.getNbLignes();


        int nbCaseCR = casesTotal * pourcentCOCOTIER_ROCHER/100;
        int nbCaseN = casesTotal * pourcentNOURRITURE/100;
        int nbCaseS = casesTotal * pourcentSINGE/100;
        System.out.println("dedede:" + nbCaseS);

        carte.setTheme("J");

        // Délimiter la carte avec COCOTIER et ROCHER
        for(int i = 0; i < carte.getNbLignes(); i++){
            for(int j = 0; j < carte.getNbColonnes(); j++){
                if(i == 0 || i == carte.getNbLignes() - 1 || j == 0 || j == carte.getNbColonnes() - 1){
                    carte.setCase(i,j,cocotierEtRocher[r.nextInt(cocotierEtRocher.length)]);
                }
                else{
                    carte.setCase(i,j," ");
                }
            }
        }
        for (int i=0;i<nbCaseCR;i++){
            remplirMap(carte, cocotierEtRocher[r.nextInt(cocotierEtRocher.length)], 1, r);
        }

        for (int i=0;i<nbCaseN;i++){
            remplirMap(carte, nourriture[r.nextInt(nourriture.length)],1, r);
        }


        remplirMap(carte, "S", nbCaseS, r);

        remplirMap(carte,"@",1,r);


    }
}