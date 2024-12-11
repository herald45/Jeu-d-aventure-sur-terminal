import Controleur.Controleur;
import Modele.Animal.Animal;
import Modele.Carte.Carte;
import Modele.Carte.CarteTheme;
import Modele.Carte.Fichier;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
//
//        System.out.println("test git hub");
//        Carte c1 =new Carte("/Users/herald/Desktop/COURS/L3/S5/COO/Projet/Jeu-d-aventure-sur-terminal/code-it1/carte.txt");
//        Ihm ihm=new Ihm();
//        //int nChoix=ihm.DemanderCreationCarte();
//        Carte c2= new Carte(35,35,"J");
//        Ihm.println("le choix 1 : "+nChoix);
//        ihm.afficherCarte(c1);
//        List<List<String>> test = new ArrayList<>();
//        List<String> ligne1 = new ArrayList<>();
//        ligne1.add("A");ligne1.add("A");ligne1.add("A");
//        test.add(ligne1);
//        List<String> ligne2 = new ArrayList<>();
//        ligne2.add(" ");ligne2.add("@");ligne2.add(" ");
//        List<String> ligne3 = new ArrayList<>();
//        ligne3.add("A");ligne3.add("A");ligne3.add("A");
//        test.add(ligne2);
//        test.add(ligne3);
//        Ihm.println(ihm.DemmanderChoixCaseAutourPersonnage(test));
//
//        ihm.afficherCarte(c2);
//        List<Animal> li_A=new ArrayList<>();
//        for (int i = 0; i <c2.getNbLignes(); i++){
//            for (int j = 0; j <c2.getNbColonnes(); j++){
//                if (c2.getCase(i,j).equals("S")){
//                    li_A.add(new Animal(i,j,"S"));
//                }
//            }
//        }
//        for (int i = 0;i<10;i++){
//            System.out.println("Tour : " + i);
//            for (int j=0;j<li_A.size();j++){
//                li_A.get(j).JouerUnTour(c2);
//
//            }
//
//            ihm.afficherCarte(c2);
//        }

        Ihm ihm=new Ihm();
        Controleur controleur=new Controleur(ihm);
        controleur.jouerPartie();

    }
}