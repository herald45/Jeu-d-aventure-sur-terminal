package Modele.Carte;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fichier implements CarteTheme {
    private String nomFichier;

    public Fichier(String fichier){
        this.nomFichier = fichier;
    }

    @Override
    public void genererCarte(Carte carte) {

        List<List<String>> m = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(this.nomFichier))) {

            String theme = br.readLine();
            carte.setTheme(theme);

            int lignes = Integer.parseInt(br.readLine());
            carte.setNbLignes(lignes);
            int colonnes = Integer.parseInt(br.readLine());
            carte.setNbColonnes(colonnes);

            String ligne;
            while ((ligne = br.readLine()) != null) {
                List<String> ligneCarte = new ArrayList<>(Arrays.asList(ligne.split("")));
                m.add(ligneCarte);
            }
            carte.setMap(m);

        }
        catch (IOException ex) {
            System.out.println("Erreur de lecture du fichier : " + ex.getMessage());
        }

    }

}