package Modele.Animal;

import Modele.Carte.Carte;

import java.util.ArrayList;

public class Scorpion extends Predateur{
    protected ArrayList<int[]> ecu;
    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> vide;

    public Scorpion(int ligne, String type, int colone) {
        super(ligne, type, colone);
    }

    @Override
    public void JouerUnTour(Carte c) {

    }

}
