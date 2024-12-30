package Controleur;

import Modele.Animal.Animal;
import Modele.Carte.Carte;
import Modele.Environement.Objet;
import Modele.Pair;
import Modele.Personnage;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.List;

public class Controleur {
    private final Ihm ihm;


    public Controleur(Ihm ihm) {
        this.ihm = ihm;
    }

    public void jouerPartie() {
        Carte carte;
        List<Animal> li_A=new ArrayList<>();
        List<Objet> li_O=new ArrayList<>();
        if(ihm.DemanderCreationCarte()==1){
            Pair<Integer,Integer> taille=ihm.DemanderTailleCarte();
            if (ihm.DemanderTheme()==1){
                carte=new Carte(taille.getFirst(),taille.getSecond(),"J");
            }else {
                carte=new Carte(taille.getFirst(),taille.getSecond(),"F");
            }
        }else {
            carte=new Carte(ihm.DemanderFichier());
        }
        if (carte.getTheme().equals("F")){
            for (int i = 0; i <carte.getNbLignes(); i++){
                for (int j = 0; j <carte.getNbColonnes(); j++){
                    if (carte.getCase(i,j).equals("E")){
                        li_A.add(new Animal(i,j,"E"));
                    }else if (carte.getCase(i,j).equals("A")){
                        li_O.add(new Objet.Arbre(i,j));
                    }else if (carte.getCase(i,j).equals("B")){
                        li_O.add(new Objet.Buisson(i,j));
                    }//todo ajouter renard et hibou

                }
            }
        }else {
            for (int i = 0; i <carte.getNbLignes(); i++){
                for (int j = 0; j <carte.getNbColonnes(); j++){
                    if (carte.getCase(i,j).equals("S")){
                        li_A.add(new Animal(i,j,"S"));
                    }
                    if (carte.getCase(i,j).equals("J")){
                        li_O.add(new Objet.Palemier(i,j));
                    }
                    if (carte.getCase(i,j).equals("P")){
                        li_O.add(new Objet.Rocher(i,j));
                    }//todo ajouter serpent  et scorpion
                }
            }
        }
        Personnage P = null;
        for (int i = 0; i <carte.getNbLignes(); i++){
            for (int j = 0; j <carte.getNbColonnes(); j++){
                if (carte.getCase(i,j).equals("@")){
                    P=new Personnage(i,j);
                }
            }
        }
        Ihm.println("voici la carte :");
        ihm.afficherCarte(carte,li_A,li_O);
        Ihm.println("----------- Debut Partie ---------");
        lancerPartie(carte,li_A,li_O,P);
    }

    private void lancerPartie(Carte carte, List<Animal> liA,List<Objet> liO, Personnage P) {
        boolean Fin=false;
        int nCpt=1;
        while (!Fin){
            for (Animal animal : liA) {
                animal.JouerUnTour(carte, (ArrayList<Objet>) liO);
            }
            Ihm.println("Tour "+nCpt);
            ihm.afficherCarte(carte,liA,liO);
            boolean continu=true;
            Pair<Pair<Integer,Integer>,String> choixCoup;
            Pair<Integer,Integer> coorDuCoup;
            while (continu){
                choixCoup=ihm.DemmanderChoixCaseAutourPersonnage(carte,P);
                coorDuCoup=choixCoup.getFirst();
                continu=ihm.AfficherChoix(choixCoup.getSecond());
                if (continu){
                    if (choixCoup.getSecond().equals("C")||choixCoup.getSecond().equals("G")){
                        P.getLi_nouriture().add(choixCoup.getSecond());
                        carte.deplacer(P.getLigne(),P.getColone(),coorDuCoup.getFirst(),coorDuCoup.getSecond(),"@");
                        P.setLigne(coorDuCoup.getFirst());P.setColone(coorDuCoup.getSecond());
                    } else if (choixCoup.getSecond().equals("E")||choixCoup.getSecond().equals("S")) {
                        if (ihm.demanderTaper()){
                            for (Animal ani :liA){
                                if (ani.getLigne()==coorDuCoup.getFirst() && ani.getColone()==coorDuCoup.getSecond()){
                                    ani.TaperAnimal(coorDuCoup.getFirst(),coorDuCoup.getSecond(),carte);
                                }
                            }
                        }

                    } else {
                        carte.deplacer(P.getLigne(),P.getColone(),coorDuCoup.getFirst(),coorDuCoup.getSecond(),"@");
                        P.setLigne(coorDuCoup.getFirst());P.setColone(coorDuCoup.getSecond());
                    }
                    continu=false;
                }else if (!P.getLi_nouriture().isEmpty()){
                    int choixPose = ihm.DemmenderPoserObjet(P);
                    if (choixPose==1){
                        P.getLi_nouriture().remove("G");
                        carte.setCase(coorDuCoup.getFirst(),coorDuCoup.getSecond(),"G");
                    }else if (choixPose==2){
                        P.getLi_nouriture().remove("C");
                        carte.setCase(coorDuCoup.getFirst(),coorDuCoup.getSecond(),"C");
                    }else {
                        continu=true;
                    }
                }else {
                    Ihm.println("retour ->");
                    continu=true;
                }

            }
            Fin=!ihm.demanderContinuer();
            nCpt++;
        }
        Ihm.println("Fin de Partie merci d'avoir jouer");
    }


}
