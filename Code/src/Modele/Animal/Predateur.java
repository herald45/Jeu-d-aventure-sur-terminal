package Modele.Animal;
import Modele.Carte.Carte;
import Modele.Environement.Objet;

import java.util.ArrayList;

public abstract class Predateur {
    protected int ligne;
    protected int colone;
    protected String type;

    public Predateur(int ligne, String type, int colone) {
        this.ligne = ligne;
        this.type = type;
        this.colone = colone;
    }

    public abstract void JouerUnTour(int ligne, int colone, Carte c, ArrayList<Animal> lia , ArrayList<Objet> lio);
}
