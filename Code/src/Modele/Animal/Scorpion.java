package Modele.Animal;

import Modele.Carte.Carte;
import java.util.ArrayList;
import java.util.List;

import static Vue.Ihm.*;

public class Scorpion extends Predateur {
    private int cacheSousRocher = 0;
    private int reposApresAttaque = 0;

    public Scorpion(int ligne, String type, int colone) {
        super(ligne, type, colone);
    }

    @Override
    public void JouerUnTour(Carte c, List<Animal> lia) {
        if (cacheSousRocher > 0) {
            cacheSousRocher--;
            return;
        }

        if (reposApresAttaque > 0) {
            reposApresAttaque--;
        }

        ArrayList<int[]> adjacents = AdjacentCases(ligne, colone, c);
        boolean attaqueEffectuee = false;

        for (int[] element : adjacents) {
            for (Animal animal : lia) {
                if (animal.getLigne() == element[0] && animal.getColone() == element[1]) {
                    if (reposApresAttaque == 0) {
                        animal.setVie(false);
                        reposApresAttaque = 2;
                        attaqueEffectuee = true;
                        break;
                    }
                }
            }
            if (attaqueEffectuee) break;
        }

        if (!attaqueEffectuee) {
            seDeplacerAleatoirement(c, 1);
        }

        if (c.getLigne(ligne).get(colone).equals("P")) {
            cacheSousRocher = 5;
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
            c.deplacer(ligne, colone, nouvelleCase[0], nouvelleCase[1], "H");
            ligne = nouvelleCase[0];
            colone = nouvelleCase[1];
        }
    }

    @Override
    public String toString() {
        return cacheSousRocher > 0 ? ANSI_YELLOW_BACKGROUND+"🪨"+ANSI_RESET : (reposApresAttaque > 0 ? ANSI_BLUE_BACKGROUND+"🦂"+ANSI_RESET : "🦂");
    }
}
