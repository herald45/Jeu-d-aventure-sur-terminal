package Modele.Animal;

import Modele.Carte.Carte;
import Vue.Ihm;

import java.util.ArrayList;

import static Vue.Ihm.*;

public class PocheE extends EtatEcureuil{
    protected int nb = 3;
    protected ArrayList<int[]> vide ;
    protected ArrayList<int[]> pers ;



    public PocheE(Animal animal) {
        super(animal);
    }

    @Override
    public void JouerUnTour(int ligne, int colone, Carte c) {
        if (nb < 1){
            pers = coord(c);
            int[] coord = pers.get(0);
            Ihm.println("le personnage depose le singe");
            vide = poserE(coord[0], coord[1], c);
            int[] element = vide.get(0);
            animal.ligne=element[0];
            animal.colone=element[1];
            c.deplacer(ligne,colone,element[0],element[1],"E");
            animal.setEtat(new AffameE(animal));
        }
        else{
            nb = nb-1;
            for (int i = 0; i< c.getNbLignes();i++){
                for (int j = 0 ; j< c.getNbColonnes();j++){
                    if (c.getCase(i,j).equals("@")){
                        animal.ligne = i;
                        animal.colone = j;
                    }
                }
            }
        }
    }

    @Override
    public void TaperEcureuil(int ligne, int colone, Carte c) {
        //impossible//
    }

    public ArrayList<int[]> poserE(int ligne, int colone, Carte c) {
        vide = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {
                    if (c.getLigne(i).get(j).equals(" ")) {
                        vide.add(new int[]{i, j});
                        return vide;
                    }
                }
            }
        }
        return vide;
    }

    public ArrayList<int[]> coord(Carte c) {
        pers = new ArrayList<>();
        for (int i = 0 ; i < c.getNbLignes(); i++) {
            for (int j = 0 ; j < c.getNbColonnes(); j++) {
                if (c.getLigne(i).get(j).equals("@")) {
                    pers.add(new int[]{i, j});
                }
            }
        }
        return pers;
    }

    @Override
    public String toString() {
        return (ANSI_WHITE_BACKGROUND + ANSI_PURPLE + "@" + ANSI_RESET);
    }
}
