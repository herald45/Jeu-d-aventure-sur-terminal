package Modele.Animal;

import Modele.Carte.Carte;

public abstract class EtatSinge {

    protected Animal animal;

    public EtatSinge(Animal animal) {
        this.animal = animal;
    }


    public abstract void JouerUnTour(int ligne, int colone, Carte c);
    public abstract void TaperSinge(int ligne, int colone, Carte c);

    @Override
    public abstract String toString();
}




