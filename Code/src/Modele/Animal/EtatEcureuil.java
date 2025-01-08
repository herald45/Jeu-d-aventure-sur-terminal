package Modele.Animal;

import Modele.Carte.Carte;

import java.util.ArrayList;

public abstract class EtatEcureuil {

    protected Animal animal;
    protected boolean danger;

    public EtatEcureuil(Animal animal) {
        this.animal = animal;
    }


    public abstract void JouerUnTour(int ligne, int colone, Carte c);
    public abstract void TaperEcureuil(int ligne, int colone, Carte c);
    public abstract String toString();


}



