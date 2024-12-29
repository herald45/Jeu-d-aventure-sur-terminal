package Modele.Environement;

import Modele.Animal.Animal;
import Vue.Ihm;

import static Vue.Ihm.*;
import static Vue.Ihm.ANSI_RESET;

public class Objet {//c'est une sorte de facade des objet

    private int ligne;
    private int colone;
    private Animal animal;
    protected boolean etat; // True = il y a un animal False = vide


    private Objet(int ligne, int colone) {//constructeur juste pour les super des class enfant
        this.ligne = ligne;
        this.colone = colone;
        this.animal = null;
        this.etat=false;
    }


    public void seCacher(Animal animal){
        this.animal = animal;

        Ihm.print("L'animal " + animal + " s'est cahée dans " + this);

        etat = true;
    }

    public Animal seDetatcher(){
        Animal a = animal;
        animal = null;

        Ihm.print("L'animal " + a + " est partis from " + this);
        etat = false;
        return a;
    }


    public int getLigne() {
        return ligne;
    }

    public int getColone() {
        return colone;
    }

    public static class Arbre extends Objet {

        public Arbre(int ligne, int colone) {
            super(ligne, colone);

        }

        @Override
        public String toString() {
            if (etat){
                return ANSI_WHITE_BACKGROUND+ANSI_GREEN+"A"+ANSI_RESET;
            }else {
                return ANSI_BLACK_BACKGROUND+ANSI_GREEN+"A"+ANSI_RESET;
            }
        }
    }
    public static class Buisson extends Objet{
        public Buisson(int ligne, int colone) {
            super(ligne, colone);
        }
        public String toString() {
            if (etat){
                return ANSI_WHITE_BACKGROUND+ANSI_GREEN+"B"+ANSI_RESET;
            }else {
                return ANSI_BLACK_BACKGROUND+ANSI_GREEN+"B"+ANSI_RESET;
            }
        }
    }
    public static class Palemier extends Objet{
        public Palemier(int ligne, int colone) {
            super(ligne, colone);
        }
        public String toString() {
            if (etat){
                return ANSI_BLUE_BACKGROUND+"🌴"+ANSI_RESET;
            }else {
                return "🌴";
            }
        }
    }
    public static class Rocher extends Objet{
        public Rocher(int ligne, int colone) {
            super(ligne, colone);
        }
        public String toString() {
            if (etat){
                return ANSI_BLUE_BACKGROUND+"🪨"+ANSI_RESET;
            }else {
                return "🪨";
            }
        }
    }
}