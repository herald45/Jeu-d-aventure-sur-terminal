package Modele.Animal;

import Modele.Carte.Carte;
import Vue.Ihm;

import java.util.ArrayList;

import static Vue.Ihm.*;

public class Animal {

    private Object etat; // Peut être soit un EtatEcureuil, soit un EtatSinge
    protected int cons = 0;
    protected int nbjour = 5;
    protected int ligne;
    protected int colone;
    protected String type;
    protected Boolean cacher=false;
    protected int peur=0;

    // Constructeur de l'Animal
    public Animal(int ligne, int colone, String t) {
        this.ligne = ligne;
        this.colone = colone;
        this.type = t;

        // Initialisation de l'état en fonction du type de l'animal
        if (type.equals("E")) {
            this.etat = AffameE.getInstance(this);  // L'écureuil commence avec un état "RassasieE"
        } else if (type.equals("S")) {
            this.etat = new AffameS(this);  // Le singe commence avec un état "RassasieS"
        }else {
            etat=null;
        }
    }

    // Méthode pour jouer un tour
    public void JouerUnTour(Carte c) {
        if (etat instanceof EtatEcureuil) {
            ((EtatEcureuil) etat).JouerUnTour(ligne, colone, c);
        } else if (etat instanceof EtatSinge) {
            ((EtatSinge) etat).JouerUnTour(ligne, colone, c);
        }
    }

    // Méthode pour taper un écureuil
    public void TaperAnimal(int ligne, int colone, Carte c) {
        if (etat instanceof EtatEcureuil) {
            ((EtatEcureuil) etat).TaperEcureuil(ligne, colone, c);
        } else if (etat instanceof EtatSinge) {
            ((EtatSinge) etat).TaperSinge(ligne, colone, c);
        }else {
            Ihm.println("grrrr");//on vient de taper un predateur
        }
    }

    // Setter et Getter pour l'état de l'animal
    public void setEtat(Object etat) {
        this.etat = etat;
    }


    // Getter et Setter pour les autres propriétés
    public int getNbjour() {
        return nbjour;
    }

    public void setNbjour(int nbjour) {
        this.nbjour = nbjour;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColone() {
        return colone;
    }

    @Override
    public String toString() {
        if (cacher){
            if (etat instanceof EtatEcureuil) {
                return (ANSI_CYAN_BACKGROUND+"E"+ANSI_RESET);
            } else if (etat instanceof EtatSinge) {
                return "🙈";
            }
        }
        if (etat instanceof EtatEcureuil) {
            return ((EtatEcureuil) etat).toString();
        } else if (etat instanceof EtatSinge) {
            return ((EtatSinge) etat).toString();
        }
        return "";
    }

    public Boolean getCacher() {
        return cacher;
    }

    public void setCacher(Boolean cacher) {
        this.cacher = cacher;
    }

    public int getPeur() {
        return peur;
    }

    public void setPeur(int peur) {
        this.peur = peur;
    }

    public String getType() {
        return type;
    }
}
