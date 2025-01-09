package Modele.Animal;
import Modele.Carte.Carte;

import java.util.ArrayList;
import java.util.List;

public abstract class Predateur extends Animal {


    public Predateur(int ligne, String type, int colone) {
        super(ligne,colone,type);
    }

    @Override
    public abstract void JouerUnTour(Carte c, List<Animal> lia);
}
