package Modele.Carte;
import java.util.Random;

public class Foret implements CarteFactory {

    private final String[] arbreBuisson = {"A","B"};
    private final String[] nourriture = {"G","C","M"};
    private final String[] predateurs = {"R","H"};

    private final int pourcentARBRE_BUISSON = 10;
    private final int pourcentNOURRITURE = 2;
    private final int pourcentECUREUIL = 1;
    private final int pourcentPREDATEUR = 1;


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
        int nbCaseP = casesTotal * pourcentPREDATEUR/100;

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

        for (int i = 0; i<nbCaseAB; i++){
            remplirMap(carte, arbreBuisson[r.nextInt(arbreBuisson.length)], 1, r);
        }

        for (int i = 0; i<nbCaseN; i++){
            remplirMap(carte, nourriture[r.nextInt(nourriture.length)], 1, r);
        }

        for (int i = 0; i<nbCaseP; i++){
            remplirMap(carte, predateurs[r.nextInt(predateurs.length)], 1, r);
        }
        remplirMap(carte, "E", nbCaseE, r);
        remplirMap(carte,"@",1,r);


    }
}