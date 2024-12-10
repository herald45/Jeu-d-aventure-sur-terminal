import Modele.Carte;
import Vue.Ihm;

public class Main {
    public static void main(String[] args) {
        System.out.println("test git hub");
        Carte c1 =new Carte("/Users/herald/Desktop/COURS/L3/S5/COO/Projet/Jeu-d-aventure-sur-terminal/code-it1/carte.txt");
        Ihm ihm=new Ihm();
        ihm.afficherCarte(c1);

    }
}