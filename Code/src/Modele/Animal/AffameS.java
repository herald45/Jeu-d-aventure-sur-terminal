package Modele.Animal;

import Modele.Carte.Carte;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.Objects;

import static Vue.Ihm.* ;

public class AffameS extends EtatSinge {

    protected ArrayList<int[]> champigion ;
    protected ArrayList<int[]> vide;
    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> buisson;


    public AffameS(Animal animal) {
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
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {
                    if (Objects.equals(c.getLigne(i).get(j), "G")) {
                        c.deplacer(ligne, colone, i, j, "S");
                        animal.setNbjour(3);
                        animal.ligne = i;
                        animal.colone = j;
                        if (JoueurAdj(i, j, c)) {
                            if (animal.cons == 1) {
                                animal.setEtat(new AmiS(animal));
                            } else {
                                animal.setEtat(new RassasieS(animal));
                                animal.cons++;
                            }
                        } else {
                            animal.setEtat(new RassasieS(animal));
                            animal.cons = 0;
                        }
                        return;
                    } else if (Objects.equals(c.getLigne(i).get(j), "C")) {
                        champigion.add(new int[]{i, j, 0});
                    }else if (c.getLigne(i).get(j).equals("M")) {
                        champigion.add(new int[]{i, j, 1});
                    } else if (Objects.equals(c.getLigne(i).get(j), "")||Objects.equals(c.getLigne(i).get(j), " ") ) {
                        vide.add(new int[]{i, j});
                    }

                }

            }
        }

        arbre = isDanger(ligne, colone, c);

        if (!(champigion.isEmpty())) {
            int[] element = champigion.get(0);
            c.deplacer(ligne, colone, element[0], element[1], "S");
            animal.ligne= element[0];
            animal.colone= element[1];
            animal.setNbjour(3);
            if (element[2] == 1){
                animal.setEtat(new JunkieS(animal));
            }
            else if(JoueurAdj(element[0],element[1],c)){
                if(animal.cons==1){
                    animal.setEtat(new PocheS(animal));
                }
                else{
                    animal.cons++;
                }
            }
            else{
                animal.setEtat(new RassasieS(animal));
                animal.cons = 0;
            }
        }
        else if (!(arbre.isEmpty())) {
            int[] element = arbre.get(0);
            c.seCacher(animal,element[0],element[1]);
            animal.ligne = element[0];
            animal.colone = element[1];
        }
        else if (!(vide.isEmpty())) {
            int nombreAleatoire = (int) (Math.random() * vide.size());
            int[] element = vide.get(nombreAleatoire);
            c.deplacer(ligne, colone, element[0], element[1], "S");
            animal.ligne= element[0];
            animal.colone= element[1];
        }


    }



    public boolean JoueurAdj(int ligne, int colone, Carte c) {
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (Objects.equals(c.getLigne(i).get(j), "@")) {
                        return true;
                    }
                }
            }
        }
        return false;


    }

    public ArrayList<int[]> isDanger(int ligne, int colone, Carte c) {
        arbre = new ArrayList<>();
        buisson = new ArrayList<>();
        boolean danger = false;
        for (int i = (ligne - 4); i < (ligne + 5); i++) {
            for (int j = (colone - 4); j < (colone + 5); j++) {
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes()){
                    if(c.getLigne(i).get(j).equals("R") || c.getLigne(i).get(j).equals("H")){
                        danger = true;
                    }
                }
            }
        }
        if (!danger){
            return new ArrayList<>();
        }
        else{

            for (int i = (ligne - 1); i < (ligne + 2); i++) {
                for (int j = (colone - 1); j < (colone + 2); j++) {
                    if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                        if (Objects.equals(c.getLigne(i).get(j), "J")) {
                            arbre.add(new int[]{i, j});
                        } else if (Objects.equals(c.getLigne(i).get(j), "P")) {
                            buisson.add(new int[]{i, j});
                        }
                    }
                }
            }

            if (arbre.isEmpty()){
                return buisson;
            }
            else {
                return arbre;
            }
        }
    }

    @Override
    public void TaperSinge(int ligne, int colone, Carte c) {
        Ihm.println("aiiiie ");

    }

    @Override
    public String toString() {
        if (danger) {
            return ANSI_RED_BACKGROUND+"🐒"+ANSI_RESET;
        }else {
            return ANSI_YELLOW_BACKGROUND+"🐒"+ANSI_RESET;
        }
    }

}

