package Modele.Carte;
import java.util.Random;

public class Foret implements CarteFactory {

    private final String[] arbreBuisson = {"A","B"};
    private final String[] nourriture = {"G","C"};

    private final int pourcentARBRE_BUISSON = 3;
    private final int pourcentNOURRITURE = 2;
    private final int pourcentECUREUIL = 1;


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


        int nbCaseAB = casesTotal * pourcentARBRE_BUISSON /100;
        int nbCaseN = casesTotal * pourcentNOURRITURE/100;
        int nbCaseE = casesTotal * pourcentECUREUIL /100;

        carte.setTheme("F");

        // DÃ©limiter la carte avec COCOTIER et ROCHER
        for(int i = 0; i < carte.getNbLignes(); i++){
            for(int j = 0; j < carte.getNbColonnes(); j++){
                if(i == 0 || i == carte.getNbLignes() - 1 || j == 0 || j == carte.getNbColonnes() - 1){
                    carte.setCase(i,j, arbreBuisson[r.nextInt(arbreBuisson.length)]);
                }
                else{
                    carte.setCase(i,j," ");
                }
            }
        }

        remplirMap(carte, arbreBuisson[r.nextInt(arbreBuisson.length)], nbCaseAB, r);
        remplirMap(carte, nourriture[r.nextInt(nourriture.length)], nbCaseN, r);
        remplirMap(carte, "E", nbCaseE, r);
        remplirMap(carte,"@",1,r);


    }
}