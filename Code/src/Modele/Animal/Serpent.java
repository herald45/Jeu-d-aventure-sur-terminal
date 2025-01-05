package Modele.Animal;

import Modele.Carte.Carte;
import Modele.Environement.Objet;

import java.util.ArrayList;

public class Serpent extends Predateur{

    protected ArrayList<int[]> singe;
    protected ArrayList<int[]> arbre;
    protected ArrayList<int[]> vide;

    public Serpent(int ligne, String type, int colone) {
        super(ligne, type, colone);
    }

    @Override
    public void JouerUnTour(int ligne, int colone, Carte c, ArrayList<Animal> lia, ArrayList<Objet> lio) {
            singe = SingeAdj(ligne, colone, c);
            if (!(singe.isEmpty())) {
                int[] element = singe.get(0);
                arbre = arbreAdj(element[0], element[1], c);
                if (!(arbre.isEmpty())) {
                    int[] coord = arbre.get(0);
                    for (Animal anim : lia) {
                        if (anim.getLigne() == element[0] && anim.getColone() == element[1]) {
                            anim.setPeur(3);
                            c.seCacher(element[0], element[1]);
                            anim.ligne = coord[0];
                            anim.colone = coord[1];

                            for (Objet obj : lio) {
                                if (obj.getLigne()== coord[0] && obj.getColone()== coord[1]){
                                    obj.seCacher(anim);
                                }
                            }

                        }
                    }

                } else {
                    c.supprimer(element[0], element[1]);
                }
            } else {
                vide = new ArrayList<>();
                for (int i = (ligne - 1); i < (ligne + 2); i++) {
                    for (int j = (colone - 1); j < (colone + 2); j++) {
                        if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes() && (i != this.ligne || j != this.colone)) {

                            if (c.getLigne(i).get(j) == " ") {
                                vide.add(new int[]{i, j});
                            }
                        }
                    }
                }
                if (!(vide.isEmpty())) {
                    int nombreAleatoire = (int) (Math.random() * vide.size());
                    int[] element = vide.get(nombreAleatoire);
                    c.deplacer(ligne, colone, element[0], element[1], "Serpent");
                    this.ligne = element[0];
                    this.colone = element[1];

                }
        }
    }

    public ArrayList<int[]> SingeAdj(int ligne, int colone, Carte c) {
        singe = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (c.getLigne(i).get(j).equals("S")) {
                        singe.add(new int[]{i, j});
                    }
                }
            }
        }
        return singe;
    }

    public ArrayList<int[]> arbreAdj(int ligne, int colone, Carte c) {
        arbre = new ArrayList<>();
        for (int i = (ligne - 1); i < (ligne + 2); i++) {
            for (int j = (colone - 1); j < (colone + 2); j++) {
                if (i >= 0 && i < c.getNbLignes() && j >= 0 && j < c.getNbColonnes()) {
                    if (c.getLigne(i).get(j).equals("A")) {
                        arbre.add(new int[]{i, j});
                    }
                }
            }
        }
        return arbre;
    }
}
