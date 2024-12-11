package Modele.Animal;



import Modele.Carte.Carte;

import java.util.ArrayList;

public class RassasieE extends EtatEcureuil {



    protected ArrayList<int[]> vide ;

    public RassasieE(Animal animal) {
        super(animal);
    }

    @Override
    public void JouerUnTour(int ligne, int colone, Carte c) {
        vide = new ArrayList<>();

        System.out.println("rassasie");

        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {

                    if (c.getLigne(i).get(j) == " ") {
                        vide.add(new int[]{i, j});
                    }
                }
            }


        }
        if (!(vide.isEmpty())) {
            int nombreAleatoire = (int) (Math.random() * vide.size());
            int[] element = vide.get(nombreAleatoire);
            c.deplacer(ligne,colone ,element[0],element[1] ,"E");
            animal.ligne= element[0];
            animal.colone= element[1];
        }
        else{
            /** exeption**/
        }

        animal.setNbjour(animal.getNbjour() - 1);
        if (animal.getNbjour() < 1) {
            animal.setEtat(new AffameE(animal));
        }

    }

    public void TaperEcureuil(int ligne, int colone, Carte c) {
        /** doit rien faire mais jsp quooi mettre **/
    }

    public String toString() {
        return ANSI_YELLOW_BACKGROUND+ANSI_BLUE+"E"+ANSI_RESET;
    }

}
