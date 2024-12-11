package Controleur;

import Modele.Animal.Animal;
import Modele.Carte.Carte;
import Modele.Pair;
import Modele.Personnage;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.List;

public class Controleur {
    private Ihm ihm;


    public Controleur(Ihm ihm) {
        this.ihm = ihm;
    }

    public void jouerPartie() {
        Carte carte;
        List<Animal> li_A=new ArrayList<>();
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
                    }
                }
            }
        }else {
            for (int i = 0; i <carte.getNbLignes(); i++){
                for (int j = 0; j <carte.getNbColonnes(); j++){
                    if (carte.getCase(i,j).equals("S")){
                        li_A.add(new Animal(i,j,"S"));
                    }
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
        ihm.afficherCarte(carte,li_A);
        Ihm.println("----------- Debut Partie ---------");
        lancerPartie(carte,li_A,P);
    }

    private void lancerPartie(Carte carte, List<Animal> liA, Personnage P) {
        boolean Fin=false;
        int nCpt=1;
        while (!Fin){
            for (int j=0;j<liA.size();j++){
                liA.get(j).JouerUnTour(carte);
            }
            Ihm.println("Tour "+nCpt);
            ihm.afficherCarte(carte,liA);
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
                        continu=false;
                    }else if (choixPose==2){
                        P.getLi_nouriture().remove("C");
                        carte.setCase(coorDuCoup.getFirst(),coorDuCoup.getSecond(),"C");
                        continu=false;
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
