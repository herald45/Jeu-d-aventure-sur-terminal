package Modele.Animal;

import Modele.Carte.Carte;
import Modele.Environement.Objet;

import java.util.ArrayList;

public class JunkieE extends EtatEcureuil {

    protected ArrayList<int[]> vide ;

    public JunkieE(Animal animal) {
        super(animal);
    }


    @Override
    public void JouerUnTour(int ligne, int colone, Carte c,ArrayList<Objet> lio) {



        sedeplacer(ligne, colone, c ,0);

        animal.setNbjour(animal.getNbjour() - 1);
        if (animal.getNbjour() < 1) {
            animal.setEtat(new AffameE(animal));
        }

    }

    public void sedeplacer(int ligne, int colone, Carte c ,int nb){
        vide = new ArrayList<>();
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
            if (nb < 1){
                sedeplacer(element[0], element[1], c ,nb+1);
            }
        }
    }

    public void TaperEcureuil(int ligne, int colone, Carte c) {
        /** doit rien faire mais jsp quooi mettre **/
    }

    public String toString() {
        return ANSI_YELLOW_BACKGROUND+ANSI_RED+"E"+ANSI_RESET;
    }

}