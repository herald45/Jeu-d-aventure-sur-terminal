package Modele.Animal;




import Modele.Carte.Carte;
import Modele.Environement.Objet;
import Vue.Ihm;

import java.util.ArrayList;

import static Vue.Ihm.*;

public class RassasieS extends EtatSinge {

    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> buisson;
    protected ArrayList<int[]> vide;
    private static RassasieS instance;

    public static  RassasieS getInstance(Animal animal){
        if (instance == null)
            instance = new RassasieS(animal);
        return instance;
    }

    public RassasieS(Animal animal) {
        super(animal);
    }

    @Override
    public void JouerUnTour(int ligne, int colone, Carte c,ArrayList<Objet> lio) {

        if (animal.getPeur()>0){
            animal.setPeur(animal.getPeur()-1);
            return ;
        }

        if (animal.getCacher()){
            for (Objet obj : lio) {
                if (obj.getLigne()== ligne && obj.getColone()== colone){
                    obj.seDetatcher();
                }
            }
        }

        vide = new ArrayList<>();

        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i!= animal.ligne || j!= animal.colone)) {

                    if (c.getLigne(i).get(j) == " ") {
                        vide.add(new int[]{i, j});
                    }
                }
            }


        }

        arbre = isDanger(ligne, colone, c);
        if (!(arbre.isEmpty())){
            int[] element = arbre.get(0);
            c.seCacher(ligne,colone);
            animal.ligne= element[0];
            animal.colone= element[1];
        }
        else if (!(vide.isEmpty())) {
            int nombreAleatoire = (int) (Math.random() * vide.size());
            int[] element = vide.get(nombreAleatoire);
            c.deplacer(ligne,colone ,element[0],element[1] ,"S");
            animal.ligne= element[0];
            animal.colone= element[1];
        }


        animal.setNbjour(animal.getNbjour() - 1);
        if (animal.getNbjour() < 1) {
            animal.setEtat(new AffameS(animal));
        }

    }

    public ArrayList<int[]> isDanger(int ligne, int colone, Carte c) {
        arbre = new ArrayList<>();
        buisson = new ArrayList<>();
        boolean danger = false;
        for (int i = (ligne - 4); i < (ligne + 5); i++) {
            for (int j = (colone - 4); j < (colone + 5); j++) {
                if (i >= 0 && i < c.getNbLignes()  && j >= 0 && j < c.getNbColonnes()){
                    if(c.getLigne(i).get(j).equals("s") || c.getLigne(i).get(j).equals("p")){
                        danger = true;
                    }
                    else if(c.getLigne(i).get(j).equals("J")){
                        arbre.add(new int[]{i, j, 0});
                    }
                    else if(c.getLigne(i).get(j).equals("P")){
                        buisson.add(new int[]{i, j, 1});
                    }
                }
            }
        }
        if (danger){
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

    public void TaperSinge(int ligne, int colone, Carte c) {
        Ihm.println("aiiiie ");
    }

    @Override
    public String toString() {
        return ANSI_YELLOW_BACKGROUND+"🐒"+ANSI_RESET;
    }

}
