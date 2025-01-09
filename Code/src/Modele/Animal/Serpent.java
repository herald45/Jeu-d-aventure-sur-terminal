package Modele.Animal;

import Modele.Carte.Carte;

import java.util.ArrayList;

public class Serpent extends Predateur{

    protected ArrayList<int[]> singe;
    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> vide;

    public Serpent(int ligne, String type, int colone) {
        super(ligne, type, colone);
    }

    @Override
    public void JouerUnTour(Carte c) {

    }


    public ArrayList<int[]> SingeAdj(int ligne, int colone, Carte c) {
        singe = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (c.getLigne(i).get(j).equals("S")) {
                        singe.add(new int[]{i, j});
                    }
                }
            }
        }
        return singe;
    }

    public ArrayList<int[]> arbreAdj(int ligne, int colone, Carte c) {
        arbre = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (c.getLigne(i).get(j).equals("A")) {
                        arbre.add(new int[]{i, j});
                    }
                }
            }
        }
        return arbre;
    }
}
