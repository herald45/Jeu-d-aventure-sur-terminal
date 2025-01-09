package Modele.PierresPrecieuses;

import Modele.Animal.Animal;
import Modele.Carte.Carte;
import Modele.Personnage;
import Vue.Ihm;

import java.util.*;

public class Jeu {
    private List<Animal> animaux;
    private Personnage personnage;
    private Carte carte;
    private Stack<EtatJeu> historique;
    private List<Commande> commandes;

    public Jeu(Carte carte, List<Animal> animaux, Personnage personnage) {
        this.carte = carte;
        this.animaux = animaux;
        this.personnage = personnage;
        this.historique = new Stack<>();
        this.commandes = new ArrayList<>();
    }

    public void sauvegarderEtat() {
        historique.push(new EtatJeu(carte.clone(), cloneListe(animaux), personnage.clone()));
    }

    public void restaurerEtat(int tours) {
        for (int i = 0; i < tours && !historique.isEmpty(); i++) {
            EtatJeu etat = historique.pop();
            this.carte = etat.getCarte();
            this.animaux = etat.getAnimaux();
            this.personnage = etat.getPersonnage();
        }
    }

    private List<Animal> cloneListe(List<Animal> animaux) {
        List<Animal> copie = new ArrayList<>();
        for (Animal a : animaux) {
            copie.add(a.clone());
        }
        return copie;
    }

    public void ajouterCommande(Commande commande) {
        commandes.add(commande);
    }

    public void executerCommandes() {
        for (Commande commande : commandes) {
            commande.executer();
        }
        commandes.clear();
    }

    public void ramasserPierrePrecieux(int tours) {
        ajouterCommande(() -> {
            restaurerEtat(tours);
            Ihm.println("⏳ Le temps a reculé de " + tours + " tours !");
        });
    }



    private class EtatJeu {//pour sauvegarder l'etat du jeu
        private final Carte carte;
        private final List<Animal> animaux;
        private final Personnage personnage;

        public EtatJeu(Carte carte, List<Animal> animaux, Personnage personnage) {
            this.carte = carte;
            this.animaux = animaux;
            this.personnage = personnage;
        }

        public Carte getCarte() {
            return carte;
        }

        public List<Animal> getAnimaux() {
            return animaux;
        }

        public Personnage getPersonnage() {
            return personnage;
        }
    }


}


