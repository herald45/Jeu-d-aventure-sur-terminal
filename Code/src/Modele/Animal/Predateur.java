package Modele.Animal;
import Modele.Carte.Carte;

import java.util.ArrayList;

public abstract class Predateur extends Animal {
    protected int ligne;
    protected int colone;

    public Predateur(int ligne, String type, int colone) {
        super(ligne,colone,type);
    }

    @Override
    public abstract void JouerUnTour(Carte c);
}
