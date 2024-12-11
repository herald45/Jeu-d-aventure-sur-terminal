package Modele.Animal;

import Modele.Carte.Carte;

import java.util.ArrayList;

public abstract class EtatEcureuil {

    protected Animal animal;
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m" ;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    public EtatEcureuil(Animal animal) {
        this.animal = animal;
    }


    public abstract void JouerUnTour(int ligne, int colone, Carte c);
    public abstract void TaperEcureuil(int ligne, int colone, Carte c);
    public abstract String toString();


}



