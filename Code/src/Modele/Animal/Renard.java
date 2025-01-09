package Modele.Animal;

import Modele.Carte.Carte;

import java.util.ArrayList;
import java.util.List;

import static Vue.Ihm.*;

public class Renard extends Predateur {
    protected ArrayList<int[]> ecu;
    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> vide;


    public Renard(int ligne, String type, int colone) {
        super(ligne, type, colone);
    }

    @Override
    public void JouerUnTour(Carte c, List<Animal> lia) {
        vide = new ArrayList<>();
        ecu = EcuAdj(ligne, colone, c);
        if (!(ecu.isEmpty())) {
            int[] element = ecu.get(0);

            for (Animal animal : lia) {
                if (animal.getLigne() == element[0] && animal.getColone() == element[1] && c.getLigne(animal.getLigne()).get(animal.getColone()).equals("E")) {
                    if (!animal.sefaitattaquer(c)){
                        print("renard a manger ecu");
                        animal.setVie(false);
                    }
                }
            }
            c.deplacer(ligne, colone, element[0], element[1], "R");
            ligne = element[0];
            colone = element[1];

        } else {
            for (int i = (ligne - 1); i < (ligne + 2); i++) {
                for (int j = (colone - 1); j < (colone + 2); j++) {
                    if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= ligne || j!= colone)) {
                        if (c.getLigne(i).get(j).equals(" ")) {
                            vide.add(new int[]{i, j});
                        }
                    }
                }
            }
            if (!(vide.isEmpty())) {
                int nombreAleatoire = (int) (Math.random() * vide.size());
                int[] element = vide.get(nombreAleatoire);
                c.deplacer(ligne, colone, element[0], element[1], "R");
                this.ligne = element[0];
                this.colone = element[1];

            }
        }
    }

    public ArrayList<int[]> EcuAdj(int ligne, int colone, Carte c) {
        ecu = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (c.getLigne(i).get(j).equals("E") ) {
                        ecu.add(new int[]{i, j,0});
                    }
                }
            }
        }
        return ecu;
    }


    @Override
    public String toString() {
        return ANSI_ORANGE_BACKGROUND+ANSI_BLACK+"R"+ANSI_RESET;
    }
}

