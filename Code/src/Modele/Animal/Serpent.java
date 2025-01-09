package Modele.Animal;

import Modele.Carte.Carte;
import java.util.ArrayList;
import java.util.List;

import static Vue.Ihm.*;

public class Serpent extends Predateur {
    private int digestion = 0;
    private int peur = 0;

    public Serpent(int ligne, String type, int colone) {
        super(ligne, type, colone);
    }

    @Override
    public void JouerUnTour(Carte c, List<Animal> lia) {
        if (digestion > 0) {
            digestion--;
            return;
        }

        if (peur > 0) {
            peur--;
            return;
        }

        ArrayList<int[]> adjacents = AdjacentCases(ligne, colone, c);
        boolean attaqueEffectuee = false;

        for (int[] element : adjacents) {
            for (Animal animal : lia) {
                if (animal.getLigne() == element[0] && animal.getColone() == element[1]) {
                    if (!animal.sefaitattaquer(c)) {
                        animal.setVie(false);
                        digestion = 3;
                        attaqueEffectuee = true;
                        break;
                    } else {
                        peur = 3;
                        attaqueEffectuee = true;
                        break;
                    }
                }
            }
            if (attaqueEffectuee) break;
        }

        if (!attaqueEffectuee) {
            seDeplacerAleatoirement(c, 2);
        }
    }

    private ArrayList<int[]> AdjacentCases(int ligne, int colone, Carte c) {
        ArrayList<int[]> cases = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newLigne = ligne + i;
                int newCol = colone + j;
                if (newLigne >= 0 && newLigne < c.getNbLignes() && newCol >= 0 && newCol < c.getNbColonnes()) {
                    cases.add(new int[]{newLigne, newCol});
                }
            }
        }
        return cases;
    }

    private void seDeplacerAleatoirement(Carte c, int pas) {
        ArrayList<int[]> casesDisponibles = new ArrayList<>();
        for (int i = -pas; i <= pas; i++) {
            for (int j = -pas; j <= pas; j++) {
                int newLigne = ligne + i;
                int newCol = colone + j;
                if (newLigne >= 0 && newLigne < c.getNbLignes() && newCol >= 0 && newCol < c.getNbColonnes()) {
                    if (c.getLigne(newLigne).get(newCol).equals(" ")) {
                        casesDisponibles.add(new int[]{newLigne, newCol});
                    }
                }
            }
        }
        if (!casesDisponibles.isEmpty()) {
            int[] nouvelleCase = casesDisponibles.get((int) (Math.random() * casesDisponibles.size()));
            c.deplacer(ligne, colone, nouvelleCase[0], nouvelleCase[1], "R");
            ligne = nouvelleCase[0];
            colone = nouvelleCase[1];
        }
    }

    @Override
    public String toString() {
        return digestion > 0 ? ANSI_YELLOW_BACKGROUND+"🐍"+ANSI_RESET : (peur > 0 ? ANSI_RED_BACKGROUND+"🐍"+ANSI_RESET : "🐍");
    }
}