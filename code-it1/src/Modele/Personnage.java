package Modele;

import Modele.Carte.Carte;

import java.util.ArrayList;
import java.util.List;

public class Personnage {
    private int ligne;
    private int colone;
    private List<String> li_nouriture;

    public Personnage(int ligne, int colone) {
        this.ligne = ligne;
        this.colone = colone;
        this.li_nouriture=new ArrayList<>();
    }


    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColone() {
        return colone;
    }

    public void setColone(int colone) {
        this.colone = colone;
    }

    public List<String> getLi_nouriture() {
        return li_nouriture;
    }

}
