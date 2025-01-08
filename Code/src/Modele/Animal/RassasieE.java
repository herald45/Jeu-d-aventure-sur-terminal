package Modele.Animal;



import Modele.Carte.Carte;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.Objects;

import static Vue.Ihm.*;

public class RassasieE extends EtatEcureuil {



    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> buisson;
    protected ArrayList<int[]> vide ;

    public RassasieE(Animal animal) {
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
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {

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
                int[] element = arbre.get(0);
                c.seCacher(animal,element[0],element[1]);
                animal.ligne= element[0];
                animal.colone= element[1];
            }
            else if (!(vide.isEmpty())) {
                int[] element = vide.get(nombreAleatoire);
                c.deplacer(ligne,colone ,element[0],element[1] ,"E");
                animal.ligne= element[0];
                animal.colone= element[1];
            }
        }

    }

    public ArrayList<int[]> isDanger(int ligne, int colone, Carte c) {
        arbre = new ArrayList<>();
        ArrayList<int[]> nouveauVide = new ArrayList<>();
        danger = false;
        for (int i = (ligne - 4); i < (ligne + 5); i++) {
            for (int j = (colone - 4); j < (colone + 5); j++) {
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes()){
                    if(c.getLigne(i).get(j).equals("R") || c.getLigne(i).get(j).equals("H")){
                        danger = true;
                    }
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (!(x == 0 && y == 0)) {
                                int adjX = i + x;
                                int adjY = j + y;

                                // Vérifier si la case adjacente est dans les limites
                                if (adjX >= 0 && adjX < c.getNbLignes() && adjY >= 0 && adjY < c.getNbColonnes()) {
                                    // Ajouter cette case à la liste des cases sûres
                                    nouveauVide.add(new int[]{adjX, adjY});
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (Objects.equals(c.getLigne(i).get(j), "A")|| Objects.equals(c.getLigne(i).get(j), "B") ) {
                        arbre.add(new int[]{i, j});
                    }
                }
            }}

        if (!danger){
            return new ArrayList<>();
        }
        else{
            if (arbre.isEmpty()){
                vide=nouveauVide;

            }
            return arbre;
        }
    }

    public void TaperEcureuil(int ligne, int colone, Carte c) {
        Ihm.println("aiie l'ecureill a mal");
    }

    public String toString() {
        if (danger){
            return ANSI_RED_BACKGROUND+ANSI_BLACK+"E"+ANSI_RESET;
        }else {
            return ANSI_YELLOW_BACKGROUND+ANSI_BLUE+"E"+ANSI_RESET;
        }
    }

}
