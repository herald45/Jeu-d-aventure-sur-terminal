package Modele.Carte;

import Modele.Animal.Animal;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.List;


public class Carte {

    private List<List<String>> map = new ArrayList<>();

    private String theme;

    private int nbLignes;

    private int nbColonnes;
    private CarteFactory fabrique;

    public Carte(String fichier){
        fabrique = new Fichier(fichier);
        fabrique.genererCarte(this);
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
                fabrique=new Jungle();
                break;
            case "F":
                fabrique=new Foret();
                break;
            default:
                Ihm.println("le Theme choisie n'est pas bon choisir entre J et F");
                break;
        }
        fabrique.genererCarte(this);
    }
    public Carte(Carte c){
        this.nbLignes = c.getNbLignes();
        this.nbColonnes = c.getNbColonnes();
        this.theme = c.getTheme();
        for (int i = 0; i <nbLignes; i++){
            map.add(new ArrayList<>());
            for(int j = 0; j < nbColonnes; j++){
                map.get(i).add(j,c.map.get(i).get(j));
            }
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

    public void seCacher(Animal ani,int ligne ,int colone){
        if (map.get(ligne).get(colone).equals("A")){
            map.get(ligne).set(colone, "x");
        } else if (map.get(ligne).get(colone).equals("B")) {
            map.get(ligne).set(colone, "y");
        } else if (map.get(ligne).get(colone).equals("J")) {
            map.get(ligne).set(colone, "z");
        } else if (map.get(ligne).get(colone).equals("P")) {
            map.get(ligne).set(colone, "w");
        }else if (map.get(ligne).get(colone).equals("@")) {
            map.get(ani.getLigne()).set(ani.getColone()," ");
            return;
        }
        map.get(ani.getLigne()).set(ani.getColone()," ");
        ani.setCacher(true);
        ani.setPeur(3);

    }
    public void seDetatcher(Animal ani,int ligne,int colone){
        map.get(ligne).set(colone, ani.getType());

        if ( map.get(ani.getLigne()).get(ani.getColone()).equals("w")){
            map.get(ani.getLigne()).set(ani.getColone(),"P");
        } else if (map.get(ani.getLigne()).get(ani.getColone()).equals("x")) {
            map.get(ani.getLigne()).set(ani.getColone(),"A");
        } else if (map.get(ani.getLigne()).get(ani.getColone()).equals("y")) {
            map.get(ani.getLigne()).set(ani.getColone(),"B");
        } else if (map.get(ani.getLigne()).get(ani.getColone()).equals("z")) {
            map.get(ani.getLigne()).set(ani.getColone(),"J");
        }

        ani.setCacher(false);
    }
    public Carte clone() {//pour les pierre présieuse
        // Implémentation de la méthode clone pour copier toutes les données pertinentes
        Carte nouvelleCarte = new Carte(this);
        // Copiez ici les informations de la carte, comme les cases, les objets, etc.
        return nouvelleCarte;
    }

    public void supprimer(int ligne, int colone){
        map.get(ligne).set(colone," ");
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