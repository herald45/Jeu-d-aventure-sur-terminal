import Controleur.Controleur;
import Modele.Animal.Animal;
import Modele.Carte.Carte;
import Modele.Carte.CarteFactory;
import Modele.Carte.Fichier;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        int i = 0;

        Ihm ihm=new Ihm();
        Controleur controleur=new Controleur(ihm);
        controleur.jouerPartie();



    }
}