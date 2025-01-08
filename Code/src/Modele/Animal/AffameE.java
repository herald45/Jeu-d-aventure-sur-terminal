package Modele.Animal;


import Modele.Carte.Carte;
import Vue.Ihm;
import java.util.ArrayList;
import java.util.Objects;

import static Vue.Ihm.*;

public class AffameE extends EtatEcureuil {

    protected ArrayList<int[]> champigion;
    protected ArrayList<int[]> vide ;
    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> buisson;


    public AffameE(Animal animal) {
        super(animal);
    }


    @Override
    public void JouerUnTour(int ligne, int colone, Carte c) {
        if (animal.getPeur()>0){
            animal.setPeur(animal.getPeur()-1);
            return ;
        }

        vide = new ArrayList<>();
        champigion = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes() ) {
                    if (c.getLigne(i).get(j).equals("G")) {
                        c.deplacer(ligne, colone, i, j, "E");
                        animal.setNbjour(5);
                        animal.ligne = i;
                        animal.colone = j;
                        if (JoueurAdj(i, j, c)) {
                            animal.setEtat(new AmiE(animal));
                        } else {
                            animal.setEtat(new RassasieE(animal));
                        }
                        return;
                    } else if (c.getLigne(i).get(j).equals("C")) {
                        champigion.add(new int[]{i, j, 0});
                    } else if (c.getLigne(i).get(j).equals("M")) {
                        champigion.add(new int[]{i, j, 1});
                    } else if (c.getLigne(i).get(j).equals(" ")) {
                        vide.add(new int[]{i, j});
                    }
                }
            }
        }
        arbre = isDanger(ligne, colone, c);
        if (!(champigion.isEmpty())) {
            int[] element = champigion.get(0);
            c.deplacer(ligne, colone, element[0], element[1], "E");
            animal.ligne= element[0];
            animal.colone= element[1];
            animal.setNbjour(5);
            if (element[2] == 1){
                animal.setEtat(new JunkieE(animal));
                return;
            }
            else if(JoueurAdj(element[0],element[1],c)){
                animal.setEtat(new AmiE(animal));
                return;
            }
            else{
                animal.setEtat(new RassasieE(animal));
                return;
            }
        }
        int nombreAleatoire = (int) (Math.random() * vide.size());
        if (animal.getCacher()&& !(vide.isEmpty())){
            int[] element = vide.get(nombreAleatoire);
            animal.ligne= element[0];
            animal.colone= element[1];
            c.seDetatcher(animal);
        }else {
            arbre = isDanger(ligne, colone, c);
            if (!(arbre.isEmpty())){
                int[] element = arbre.get(0);
                c.seCacher(animal);
                animal.ligne= element[0];
                animal.colone= element[1];
                return;
            }
            if (!(vide.isEmpty())) {
                int[] element = vide.get(nombreAleatoire);
                c.deplacer(ligne,colone ,element[0],element[1] ,"E");
                animal.ligne= element[0];
                animal.colone= element[1];
            }
        }
    }

    public ArrayList<int[]> isDanger(int ligne, int colone, Carte c) {
        arbre = new ArrayList<>();
        buisson = new ArrayList<>();
        danger = false;
        for (int i = (ligne - 4); i < (ligne + 5); i++) {
            for (int j = (colone - 4); j < (colone + 5); j++) {
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes()){
                    if(c.getLigne(i).get(j).equals("R") || c.getLigne(i).get(j).equals("H")){
                        danger = true;
                    }
                }
            }
        }

        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (Objects.equals(c.getLigne(i).get(j), "A")) {
                        arbre.add(new int[]{i, j});
                    } else if (Objects.equals(c.getLigne(i).get(j), "B")) {
                        buisson.add(new int[]{i, j});
                    }
                }
            }
        }

        if (!danger){
            return new ArrayList<>();
        }
        else{
            if (arbre.isEmpty()){
                return buisson;
            }
            else {
                return arbre;
            }
        }
    }



    public boolean JoueurAdj(int ligne, int colone, Carte c) {
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {
                    if (c.getLigne(i).get(j).equals("@")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void TaperEcureuil(int ligne, int colone, Carte c) {
        Ihm.println("aiiiie ");
    }

    @Override
    public String toString() {
        if (danger){
            return ANSI_RED_BACKGROUND+ANSI_BLACK+"E"+ANSI_RESET;
        }else {
            return ANSI_YELLOW_BACKGROUND+ANSI_BLACK+"E"+ANSI_RESET;
        }
    }
}

