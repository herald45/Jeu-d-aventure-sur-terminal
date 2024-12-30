package Modele.Animal;

import Modele.Carte.Carte;
import Modele.Environement.Objet;

import java.util.ArrayList;

public abstract class EtatSinge {

    protected Animal animal;

    public EtatSinge(Animal animal) {
        this.animal = animal;
    }


    public abstract void JouerUnTour(int ligne, int colone, Carte c, ArrayList<Objet> lio);
    public abstract void TaperSinge(int ligne, int colone, Carte c);

    @Override
    public abstract String toString();
}




