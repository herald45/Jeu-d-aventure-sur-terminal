package Modele;

import java.util.ArrayList;
import java.util.List;
import java.io. *;
import java.nio.file.*;
import java.util.*;

public class Carte {

    private List<List<String>> carte = new ArrayList<>();

    private String theme;

    private final String[] caseForet = {"A","B","C","G","E","@"," "};

    private final String[] caseJungle = {"CO","B","CH","R","S","@"," "};

    private int lignes;

    private int colonnes;

    public Carte(String theme, int lignes, int colonnes) {
        this.theme = theme;
        this.lignes = lignes;
        this.colonnes = colonnes;
    }

    public Carte(String nomFichier) {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(nomFichier))){
            this.theme = br.readLine();


            this.lignes = Integer.parseInt(br.readLine());
            this.colonnes = Integer.parseInt(br.readLine());


            String ligne;
            while((ligne = br.readLine()) != null) {
                List<String> ligneCarte = new ArrayList<>(Arrays.asList(ligne.split("")));
                carte.add(ligneCarte);
            }

        }
        catch (IOException ex) {
            System.out.println("Erreur de lecture du fichier : " + ex.getMessage());
        }
    }

    public List<List<String>> getCarte() {
        return carte;
    }

    public String getTheme() {
        return theme;
    }

    public int getLignes() {
        return lignes;
    }

    public int getColonnes() {
        return colonnes;
    }

}