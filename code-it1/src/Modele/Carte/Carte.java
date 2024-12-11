package Modele.Carte;

import Vue.Ihm;

import java.util.ArrayList;
import java.util.List;


public class Carte {

    private List<List<String>> map = new ArrayList<>();

    private String theme;

    private int nbLignes;

    private int nbColonnes;

    public Carte(String fichier){
        CarteTheme t1 = new Fichier(fichier);
        t1.genererCarte(this);

    }

    public Carte(int lignes, int colonnes, String t) {

        this.nbLignes = lignes;
        this.nbColonnes = colonnes;
        this.theme = t;
        for (int i = 0; i <lignes; i++){
            map.add(new ArrayList<>());
            for(int j = 0; j < colonnes; j++){
                map.get(i).add(" ");
            }
        }
        switch (theme){
            case "J":
                CarteTheme t1 = new Jungle();
                t1.genererCarte(this);
                break;
            case "F":
                CarteTheme t2 = new Foret();
                t2.genererCarte(this);
                break;
            default:
                Ihm.println("le Theme choisie n'est pas bon choisir entre J et F");
                break;
        }
    }

    public List<String> choixCase(int x, int y){

        List<String> choix = new ArrayList<>();
        for (int i = (x - 1); i <= (x + 1); i++){
            for (int j = (y - 1); j <= (y + 1 ); j++){
                choix.add(map.get(i).get(j));
            }
        }
        return choix;
    }
    public List<String> getLigne(int ligne) {
        if (ligne >= 0 && ligne < map.size()+1) {
            return map.get(ligne);
        } else {
            throw new IndexOutOfBoundsException("Ligne hors des limites : " + ligne);
        }
    }

    public void deplacer(int ligne, int colone , int i, int j ,String c){
        map.get(ligne).set(colone," ");
        map.get(i).set(j,c);

    }


    public String getTheme() {
        return theme;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }


    public void setTheme(String newTheme){
        this.theme = newTheme;
    }

    public void setNbLignes(int nbLignes) {
        this.nbLignes = nbLignes;
    }

    public void setNbColonnes(int nbColonnes) {
        this.nbColonnes = nbColonnes;
    }

    public void setMap(List<List<String>> newMap){
        this.map = newMap;
    }

    public void setCase(int ligne, int colonne, String lettre) {
        map.get(ligne).set(colonne, lettre);
    }

    public String getCase(int ligne, int colonne){
        return map.get(ligne).get(colonne);
    }

    public List<List<String>> getMap() {
        return map;
    }
}