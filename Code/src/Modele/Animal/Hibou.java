package Modele.Animal;

import Modele.Carte.Carte;

import java.util.ArrayList;
import java.util.List;

import static Vue.Ihm.*;

public class Hibou extends Predateur{
    protected ArrayList<int[]> ecu;
    protected ArrayList<int[]> vide;
    private int repos;

    public Hibou(int ligne, String type, int colone) {

        super(ligne, type, colone);
        repos =0;
    }

    @Override
    public void JouerUnTour(Carte c, List<Animal> lia) {
        if (repos > 0) {
            repos = 0;
        }else{
            ecu = EcuAdj(ligne, colone, c);
            if (!(ecu.isEmpty())) {
                int[] element = ecu.get(0);

                for (Animal animal : lia) {
                    if (animal.getLigne() == element[0] && animal.getColone() == element[1] && c.getLigne(animal.getLigne()).get(animal.getColone()).equals("E")) {
                        if (!animal.sefaitattaquer(c)){
                            print("Le hibou a manger ecu");
                            animal.setVie(false);
                        }
                    }
                }

                c.deplacer(ligne, colone, element[0], element[1], "H");
                ligne = element[0];
                colone = element[1];

                repos = 1;
            } else {
                sedeplacer(ligne, colone, c, 0);
            }
        }
    }

    public ArrayList<int[]> EcuAdj(int ligne, int colone, Carte c) {
        ecu = new ArrayList<>();
        for (int i = (ligne - 3); i < (ligne + 4); i++) {
            for (int j = (colone - 3); j < (colone + 5); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (c.getLigne(i).get(j).equals("E")) {
                        ecu.add(new int[]{i, j});
                    }
                }
            }
        }
        return ecu;
    }


    public void sedeplacer(int ligne, int colone, Carte c ,int nb){

        vide = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes() && (i!= this.ligne || j!= this.colone)) {

                    if (c.getLigne(i).get(j).equals(" ")) {
                        vide.add(new int[]{i, j});
                    }
                }
            }
        }
        if (!(vide.isEmpty())) {
            int nombreAleatoire = (int) (Math.random() * vide.size());
            int[] element = vide.get(nombreAleatoire);
            c.deplacer(ligne,colone ,element[0],element[1] ,"H");
            this.ligne= element[0];
            this.colone= element[1];
            if (nb < 1){
                sedeplacer(element[0], element[1], c ,nb+1);
            }
        }
    }

    @Override
    public String toString() {
        return ANSI_PURPLE_BACKGROUND+ANSI_WHITE+"H"+ANSI_RESET;
    }
}
