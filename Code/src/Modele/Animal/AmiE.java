package Modele.Animal;

import Modele.Carte.Carte;
import Vue.Ihm;
import static Vue.Ihm.*;

import java.util.ArrayList;

public class AmiE extends EtatEcureuil{

    protected ArrayList<int[]> vide ;
    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> buisson;
    protected ArrayList<int[]> ami;

    public AmiE(Animal animal) {
        super(animal);
    }

    @Override
    public void JouerUnTour(int ligne, int colone, Carte c) {

        if (animal.getPeur()>0){
            animal.setPeur(animal.getPeur()-1);
            animal.setNbjour(animal.getNbjour() - 1);
            if (animal.getNbjour() < 1) {
                animal.setEtat(new AffameE(animal));
            }
            return ;
        }

        vide = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {

                    if (c.getLigne(i).get(j).equals(" ")) {
                        vide.add(new int[]{i, j});
                    }
                }
            }

        }
        int nombreAleatoire = (int) (Math.random() * vide.size());
        if (animal.getCacher()&& !(vide.isEmpty())){
            int[] element = vide.get(nombreAleatoire);
            c.seDetatcher(animal,element[0],element[1]);
            animal.ligne= element[0];
            animal.colone= element[1];

        }else {
            arbre = isDanger(ligne, colone, c);
            if (!(arbre.isEmpty())){
                ami = JoueurAdjCoor(ligne, colone, c);
                if(!(ami.isEmpty())){
                    Ihm.println("l'ecureuil monte dans la poche du personnage");
                    int[] element = ami.get(0);
                    c.seCacher(animal,element[0],element[1]);
                    animal.ligne=element[0];
                    animal.colone=element[1];
                    animal.setEtat(new PocheE(animal));
                }
                else{
                    int[] element = arbre.get(0);
                    c.seCacher(animal,element[0],element[1]);
                    animal.ligne= element[0];
                    animal.colone= element[1];
                }
            }
            else if (!(vide.isEmpty())) {
                int[] element = vide.get(nombreAleatoire);
                c.deplacer(ligne,colone ,element[0],element[1] ,"E");
                animal.ligne=element[0];
                animal.colone=element[1];
            }
        }



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
                    else if(c.getLigne(i).get(j).equals("A")){
                        arbre.add(new int[]{i, j, 0});
                    }
                    else if(c.getLigne(i).get(j).equals("B")){
                        buisson.add(new int[]{i, j, 1});
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


    @Override
    public void TaperEcureuil(int ligne, int colone, Carte c) {
        println("aiiie vsy toi la!!");
        if (animal.getNbjour() < 1) {
            animal.setEtat(new AffameE(animal));
        }
        else{
            animal.setEtat(new RassasieE(animal));
        }

    }



    public ArrayList<int[]> JoueurAdjCoor(int ligne, int colone, Carte c) {
        ami = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {
                    if (c.getLigne(i).get(j).equals("@")) {
                        ami.add(new int[]{i, j});
                        return ami;
                    }
                }
            }
        }
        return ami;
    }

    public String toString() {
        return ANSI_YELLOW_BACKGROUND+ANSI_PURPLE+"E"+ANSI_RESET;
    }

}

