import Modele.Carte;
import Modele.Pair;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("test git hub");
        Carte c1 =new Carte("/Users/herald/Desktop/COURS/L3/S5/COO/Projet/Jeu-d-aventure-sur-terminal/code-it1/carte.txt");
        Ihm ihm=new Ihm();
        //Pair<Integer,Integer> nChoix=ihm.DemanderCreationCarte();
        //Ihm.println("le choix 1 : "+nChoix.getFirst()+"\nle choix2 :"+ nChoix.getSecond());
        ihm.afficherCarte(c1);
        List<List<String>> test = new ArrayList<>();
        List<String> ligne1 = new ArrayList<>();
        ligne1.add("A");ligne1.add("A");ligne1.add("A");
        test.add(ligne1);
        List<String> ligne2 = new ArrayList<>();
        ligne2.add(" ");ligne2.add("@");ligne2.add(" ");
        List<String> ligne3 = new ArrayList<>();
        ligne3.add("A");ligne3.add("A");ligne3.add("A");
        test.add(ligne2);
        test.add(ligne3);
        Ihm.println(ihm.DemmanderChoixCaseAutourPersonnage(test));

    }
}