package Modele.Animal;


import Modele.Carte.Carte;
import Vue.Ihm;

import java.util.ArrayList;

public class AffameE extends EtatEcureuil {

    protected ArrayList<int[]> champigion;
    protected ArrayList<int[]> vide ;


    public AffameE(Animal animal) {
        super(animal);
    }


    @Override
    public void JouerUnTour(int ligne, int colone, Carte c) {
        vide = new ArrayList<>();
        champigion = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes() ) {
                    if (c.getLigne(i).get(j).equals("G")) {
                        c.deplacer(ligne, colone, i, j, "E");
                        animal.setNbjour(5);
                        animal.ligne = i;
                        animal.colone = j;
                        if (JoueurAdj(i, j, c)) {
                            animal.setEtat(new AmiE(animal));
                        } else {
                            animal.setEtat(new RassasieE(animal));
                        }
                        return;
                    } else if (c.getLigne(i).get(j).equals("C")) {
                        champigion.add(new int[]{i, j});
                    } else if (c.getLigne(i).get(j).equals(" ")) {
                        vide.add(new int[]{i, j});
                    }

                }

            }
        }


        if (!(champigion.isEmpty())) {
            int[] element = champigion.get(0);
            c.deplacer(ligne, colone, element[0], element[1], "E");
            animal.ligne= element[0];
            animal.colone= element[1];
            animal.setNbjour(5);
            if(JoueurAdj(element[0],element[1],c)){
                animal.setEtat(new AmiE(animal));
            }
            else{
                animal.setEtat(new RassasieE(animal));
            }
        } else if (!(vide.isEmpty())) {

            int nombreAleatoire = (int) (Math.random() * vide.size());
            int[] element = vide.get(nombreAleatoire);
            c.deplacer(ligne, colone, element[0], element[1], "E");
            animal.ligne= element[0];
            animal.colone= element[1];
        }


    }



    public boolean JoueurAdj(int ligne, int colone, Carte c) {
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {
                    if (c.getLigne(i).get(j).equals("@")) {
                        return true;
                    }
                }
            }
        }
        return false;


    }

    @Override
    public void TaperEcureuil(int ligne, int colone, Carte c) {
        Ihm.println("aiiiie ");
    }

    @Override
    public String toString() {
        return ANSI_YELLOW_BACKGROUND+ANSI_BLACK+"E"+ANSI_RESET;
    }
}

