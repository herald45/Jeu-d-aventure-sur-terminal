package Vue;

import Modele.Animal.Animal;
import Modele.Carte.Carte;
import Modele.Environement.Objet;
import Modele.Pair;
import Modele.Personnage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class Ihm {
    // Strings for foreground colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    // Strings for background colors
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m" ;
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public Ihm() {
    }

    /**
     * Affiche un message.
     * @param s Le message à afficher.
     * */
    public static void println(Object s) {
        System.out.println(s);
    }
    public static void print(Object s) {
        System.out.print(s);
    }

    public void afficherCarte(Carte c, List<Animal> li_a,List<Objet> li_o){
        String caseCarte;
        boolean afficher;
        for (int i = 0; i <c.getNbLignes(); i++){
            for (int j = 0; j <c.getNbColonnes(); j++){
                afficher=true;
                caseCarte=c.getCase(i,j);
                if (c.getTheme().equals("F")){
                    switch (caseCarte) {
                        case "@" :
                            System.out.print(ANSI_WHITE_BACKGROUND+ANSI_PURPLE+caseCarte+ANSI_RESET);
                            break;
                        case "M" :
                            System.out.print(ANSI_CYAN_BACKGROUND+ANSI_YELLOW+"C"+ANSI_RESET);
                            break;
                        case "G" :
                            System.out.print(ANSI_RED_BACKGROUND+ANSI_YELLOW+caseCarte+ANSI_RESET);
                            break;
                        case "C" :
                            System.out.print(ANSI_WHITE_BACKGROUND+ANSI_PURPLE+"C"+ANSI_RESET);
                            break;
                        default:
                            for (Objet obj : li_o) {
                                if (obj.getColone() == j && obj.getLigne() == i) {
                                    print(obj);//si c un objet
                                    afficher=false;
                                }
                            }
                            for (Animal ani : li_a) {
                                if (ani.getColone()==j && ani.getLigne()==i){
                                    print(ani);//si c'est un animal
                                    afficher=false;
                                }
                            }
                            if (afficher){
                                print(ANSI_GREEN_BACKGROUND+caseCarte+ANSI_RESET);
                            }

                    }

                }else {
                    switch (caseCarte) {
                        case "@" :
                            System.out.print("🧑‍🎄");
                            break;
                        case "G" :
                            System.out.print("🍌");
                            break;
                        case "C" :
                            System.out.print("🍄‍🟫");//champinon normaux
                            break;
                        case "M" :
                            System.out.print("🍄");//champinon hallucinogène
                            break;
                        default:
                            for (Objet obj : li_o) {
                                if (obj.getColone() == j && obj.getLigne() == i) {
                                    print(obj);//si c un objet
                                    afficher=false;
                                }
                            }
                            for (Animal ani : li_a) {
                                if (ani.getColone()==j && ani.getLigne()==i){
                                    print(ani);//si c'est un animal
                                    afficher=false;
                                }
                            }
                            if (afficher){
                                print("⬛️");
                            }

                    }
                }
            }
            System.out.println();
        }
            }



    public int DemanderCreationCarte() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            println("\uD83D\uDE4B\u200D Voulez vous créer une nouvelle carte ou de charger une carte depuis un fichier ??");
            System.out.println("- 1 Crée une nouvelle carte");
            System.out.println("- 2 charger une carte \n");
            if (!scanner.hasNextInt()) {
                System.out.println("🙅‍ Saisie invalide. Veuillez essayer à nouveau: ");
                scanner.next();
            } else {
                return scanner.nextInt();
            }
        }
    }
    public String DemanderFichier() {
        Scanner scanner = new Scanner(System.in);
        println("Inscrivez le chemin d'accée a votre fichier (ex:mappe.txt) :");
        return scanner.next();
    }

    public int DemanderTheme() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\uD83D\uDE4B\u200D Quelle Theme voulez vous choisir ?");
            System.out.println("- 1 Jungle");
            System.out.println("- 2 Forêt");

            if (!scanner.hasNextInt()) {
                System.out.println("🙅‍ Saisie invalide. Veuillez essayer à nouveau: ");
                scanner.next();
            } else {
                return scanner.nextInt();
            }
        }
    }
    public Pair<Integer, Integer> DemanderTailleCarte() {
        Scanner scanner = new Scanner(System.in);
        Pair<Integer,Integer> Taille=new Pair<>(0,0);
        while (true) {
            System.out.println("\uD83D\uDE4B\u200D Quelle Taille voulez vous choisir pour la carte ?");
            System.out.println("Ligne :");
            if (!scanner.hasNextInt()) {
                System.out.println("🙅‍ Saisie invalide. Veuillez essayer à nouveau: ");
                scanner.next();
            } else {
                Taille.setFirst(scanner.nextInt());
                break;
            }
        }
        while (true) {
            System.out.println("Colonne :");
            if (!scanner.hasNextInt()) {
                System.out.println("🙅‍ Saisie invalide. Veuillez essayer à nouveau: ");
                scanner.next();
            } else {
                Taille.setSecond(scanner.nextInt());
                break;
            }
        }
        return Taille;
    }

    public Pair<Pair<Integer,Integer>,String> DemmanderChoixCaseAutourPersonnage(Carte c,Personnage p) {

        Map<Integer, Pair<Pair<Integer,Integer>,String>> liChoix = new HashMap<>();
        int nCpt = 1;
        int nCpt2=1;
        for (int i = (p.getLigne() - 1); i < (p.getLigne() + 2); i++) {
            for (int j = (p.getColone() - 1); j < (p.getColone() + 2); j++) {
                if (c.getCase(i,j).equals("A") || c.getCase(i,j).equals("B")) {
                    print("❌");
                } else if (c.getCase(i,j).equals("@")) {
                    print("🙋");
                } else {
                    print(nCpt + " ");
                    liChoix.putIfAbsent(nCpt, new Pair<>(new Pair<>(i,j),c.getCase(i,j)));
                    nCpt++;
                }
                nCpt2++;
                if ((nCpt2-1)%3==0){
                    print("\n");
                }
            }


        }
        print("\n");

        int nChoixCase;
        while (true) {
            println("\uD83D\uDE4B\u200D Quel case voulez vous choisir ??");
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextInt()) {
                System.out.println("🙅‍ Saisie invalide. Veuillez essayer à nouveau: ");
                scanner.next();
            } else {
                nChoixCase = scanner.nextInt();
                if (0>nChoixCase || nChoixCase>nCpt-1){
                    System.out.println("🙅‍ Saisie invalide. Choisir un chiffre entre 0  et "+nCpt);
                }else {
                    break;
                }
            }
        }
        return liChoix.get(nChoixCase);
    }
    public Boolean AfficherChoix(String c){
        print("Sur la case il y a ");
        switch (c) {
            case "C" -> println("un champignon");
            case "G" -> println("de la nouriture");
            case "E", "S" -> {
                println("un animal");
                return true;
            }
            default -> println("rien");
        }
        println("Se deplacer ? (O/N)");
        Scanner sc = new Scanner(System.in);
        String rep;
        while (true){
            rep= sc.next();
            if (rep.matches("[Oo]")) {
                return true;
            } else if (rep.matches("[Nn]")) {
                return false;
            } else {
                System.out.println("🙅‍ Saisir soit O ou N (O/N)");
            }

        }
    }
    public int DemmenderPoserObjet(Personnage p){
        println("voulez vous Poser un objet ? (O/N)");
        Scanner sc = new Scanner(System.in);
        String rep;
        while (true){
            rep= sc.next();
            if (rep.matches("[Oo]")) {
                int choix_pose;
                if (p.getLi_nouriture().contains("C") && p.getLi_nouriture().contains("G")) {
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                            println("1- Poser nouriture");
                            println("2- Poser Champignon");
                            if (!scanner.hasNextInt()) {
                                System.out.println("🙅‍ Saisie invalide. Veuillez essayer à nouveau: ");
                                scanner.next();
                            } else {
                                choix_pose = scanner.nextInt();
                                break;
                            }
                        }
                } else if (p.getLi_nouriture().contains("G")) {
                    return 1;
                }else {
                    return 2;
                }
                return choix_pose;
            } else if (rep.matches("[Nn]")) {
                return 0;
            } else {
                System.out.println("🙅‍ Saisir soit O ou N (O/N)");
            }
    }
    }
    public boolean demanderContinuer() {
        String rep;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\uD83D\uDE4B\u200D  Souhaitez-vous continuer? (O/N)");
            rep = sc.next();
            if (rep.matches("[Oo]")) {
                return true;
            } else if (rep.matches("[Nn]")) {
                return false;
            } else {
                System.out.println("🙅‍ Saisir soit O ou N (O/N)");
            }
        }
    }
    public boolean demanderTaper() {
        String rep;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\uD83D\uDE4B\u200D  Souhaitez-vous fraper l'animal? (O/N)");
            rep = sc.next();
            if (rep.matches("[Oo]")) {
                return true;
            } else if (rep.matches("[Nn]")) {
                return false;
            } else {
                System.out.println("🙅‍ Saisir soit O ou N (O/N)");
            }
        }
    }




}
