package Modele.Animal;

import Modele.Carte.Carte;

import java.util.ArrayList;

import static Vue.Ihm.*;

public class AmiS extends EtatSinge{

    protected ArrayList<int[]> vide;
    private static AmiS instance;



    public AmiS(Animal animal) {
        super(animal);
    }

    @Override
    public void JouerUnTour(int ligne, int colone, Carte c) {

        if (animal.getPeur()>0){
            animal.setPeur(animal.getPeur()-1);
            return ;
        }

        vide = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {

                    if (c.getLigne(i).get(j) == " ") {
                        vide.add(new int[]{i, j});
                    }
                }
            }

        }

        if (!(vide.isEmpty())) {
            int nombreAleatoire = (int) (Math.random() * vide.size());
            int[] element = vide.get(nombreAleatoire);
            c.deplacer(ligne,colone ,element[0],element[1] ,"S");
        }

        animal.setNbjour(animal.getNbjour() - 1);
        if (animal.getNbjour() < 1) {
            animal.setEtat(new AffameS(animal));
        }


    }

    @Override
    public void TaperSinge(int ligne, int colone, Carte c) {
        if (animal.getNbjour() < 1) {
            animal.setEtat(new AffameS(animal));
        }
        else{
            animal.setEtat(new RassasieS(animal));
        }

    }

    public String toString() {
        if (danger) {
            return ANSI_RED_BACKGROUND+"🐒"+ANSI_RESET;
        }return ANSI_BLUE_BACKGROUND+"🐒"+ANSI_RESET;
    }
}
