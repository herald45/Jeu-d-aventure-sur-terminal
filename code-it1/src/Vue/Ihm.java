package Vue;

import Modele.Carte;

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

    public void afficherCarte(Carte c){
        System.out.println(c.getTheme());
        System.out.println(c.getLignes() + "\n" + c.getColonnes());
        for (List<String> ligneCarte : c.getCarte()) {
            for (String caseCarte : ligneCarte) {
                if (c.getTheme().equals("F")){
                    switch (caseCarte) {
                        case "A" :
                            System.out.print(ANSI_BLACK_BACKGROUND+ANSI_GREEN+"A"+ANSI_RESET);
                            break;
                        case "E" :
                            System.out.print(ANSI_YELLOW_BACKGROUND+ANSI_BLACK+caseCarte+ANSI_RESET);
                            break;
                        case "@" :
                            System.out.print(ANSI_WHITE_BACKGROUND+ANSI_PURPLE+caseCarte+ANSI_RESET);
                            break;
                        case "B" :
                            System.out.print(ANSI_BLACK_BACKGROUND+ANSI_GREEN+"B"+ANSI_RESET);
                            break;
                        case "G" :
                            System.out.print(ANSI_RED_BACKGROUND+ANSI_YELLOW+caseCarte+ANSI_RESET);
                            break;
                        case "C" :
                            System.out.print(ANSI_WHITE_BACKGROUND+ANSI_PURPLE+caseCarte+ANSI_RESET);
                            break;
                        default:
                            print(ANSI_GREEN_BACKGROUND+caseCarte+ANSI_RESET);
                    }

                }else {
                    //todo
                    switch (caseCarte) {
                        case "A" :
                            System.out.print(ANSI_BLACK_BACKGROUND+ANSI_GREEN+"A"+ANSI_RESET);
                            break;
                        default:
                            print(caseCarte);
                    }
                }
            }
            System.out.println();
        }




            }

    public static Map<Integer, Integer> DemanderCreationCarte() {
        Scanner scanner = new Scanner(System.in);
        int nChoixCreations;
        while (true) {
            println("\uD83D\uDE4B\u200D Voulez vous créer une nouvelle carte ou de charger une carte depuis un fichier ??");
            System.out.println("- 1 Crée une nouvelle carte");
            System.out.println("- 2 charger une carte ");
            if (!scanner.hasNextInt()) {
                System.out.println("🙅‍ Saisie invalide. Veuillez essayer à nouveau: ");
                scanner.next();
            } else {
                nChoixCreations = scanner.nextInt();
                break;
            }
        }
        int nChoixTheme;
        while (true) {
            System.out.println("\uD83D\uDE4B\u200D Quelle Theme voulez vous choisir ?");
            System.out.println("- 1 Jungle");
            System.out.println("- 2 Forêt");

            if (!scanner.hasNextInt()) {
                System.out.println("🙅‍ Saisie invalide. Veuillez essayer à nouveau: ");
                scanner.next();
            } else {
                nChoixTheme = scanner.nextInt();
                break;
            }
        }
        return new HashMap<>(nChoixCreations, nChoixTheme);
    }
    public int AfficherCaseAutourPersonnage(List<List<String>> li){
        //todo
        return 0;
    }
    public String AfficherChoix(){
        //todo
        return "D";
    }





}
