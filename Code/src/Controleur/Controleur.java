package Controleur;

import Modele.Animal.*;
import Modele.Carte.Carte;
import Modele.Pair;
import Modele.Personnage;
import Modele.PierresPrecieuses.Jeu;
import Vue.Ihm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controleur {
    private final Ihm ihm;


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
                    }else if (carte.getCase(i,j).equals("R")){
                        li_A.add(new Renard(i,"R",j));
                    } else if (carte.getCase(i,j).equals("H")) {
                        li_A.add(new Hibou(i,"H",j));
                    }
                }
            }
        }else {
            for (int i = 0; i <carte.getNbLignes(); i++){
                for (int j = 0; j <carte.getNbColonnes(); j++){
                    if (carte.getCase(i,j).equals("S")){
                        li_A.add(new Animal(i,j,"S"));
                    }else if (carte.getCase(i,j).equals("R")){
                        li_A.add(new Serpent(i,"R",j));
                    } else if (carte.getCase(i,j).equals("H")) {
                        li_A.add(new Scorpion(i,"H",j));
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
        Jeu sauvegarde =new Jeu(carte,liA,P);
        boolean Fin=false;
        int nCpt=1;
        while (!Fin){
            Iterator<Animal> iterator = liA.iterator();
            while (iterator.hasNext()) {
                Animal animal = iterator.next();
                if (!animal.getVie()) {
                    iterator.remove(); // Suppression sécurisée via l'itérateur
                } else {
                    animal.JouerUnTour(carte, liA);
                }
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
                    if (choixCoup.getSecond().equals("C")||choixCoup.getSecond().equals("G")||choixCoup.getSecond().equals("M")){
                        P.getLi_nouriture().add(choixCoup.getSecond());
                        carte.deplacer(P.getLigne(),P.getColone(),coorDuCoup.getFirst(),coorDuCoup.getSecond(),"@");
                        P.setLigne(coorDuCoup.getFirst());P.setColone(coorDuCoup.getSecond());
                    } else if (choixCoup.getSecond().equals("*")) {
                        carte.deplacer(P.getLigne(),P.getColone(),coorDuCoup.getFirst(),coorDuCoup.getSecond(),"@");
                        P.setLigne(coorDuCoup.getFirst());P.setColone(coorDuCoup.getSecond());
                        int nombreAleatoire = (int) (Math.random() * 2);//entre 1 et 2
                        sauvegarde.ramasserPierrePrecieux(3+nombreAleatoire);
                    } else if (choixCoup.getSecond().equals("E")||choixCoup.getSecond().equals("S")||choixCoup.getSecond().equals("R")||choixCoup.getSecond().equals("H")) {
                        //on estime qu'il est possible de fraper un predateur mais ca fait rien
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
                    }else if (choixPose==3) {
                        P.getLi_nouriture().remove("M");
                        carte.setCase(coorDuCoup.getFirst(), coorDuCoup.getSecond(), "M");
                    }else {
                        continu=true;
                    }
                }else {
                    Ihm.println("retour ->");
                    continu=true;
                }

            }
            sauvegarde.sauvegarderEtat();
            sauvegarde.executerCommandes();
            Fin=!ihm.demanderContinuer();
            nCpt++;
        }
        Ihm.println("Fin de Partie merci d'avoir jouer");
    }


}
