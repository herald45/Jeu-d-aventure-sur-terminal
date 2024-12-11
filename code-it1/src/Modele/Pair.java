package Modele;
public class Pair<F, S> {
    private F first;  // Premier élément de la paire
    private S second; // Deuxième élément de la paire

    /**
     * Constructeur pour initialiser une paire.
     *
     * @param first  Premier élément.
     * @param second Deuxième élément.
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }


    /**
     * Récupère le premier élément de la paire.
     *
     * @return Le premier élément.
     */
    public F getFirst() {
        return first;
    }

    /**
     * Définit la valeur du premier élément.
     *
     * @param first Nouvelle valeur pour le premier élément.
     */
    public void setFirst(F first) {
        this.first = first;
    }

    /**
     * Récupère le deuxième élément de la paire.
     *
     * @return Le deuxième élément.
     */
    public S getSecond() {
        return second;
    }

    /**
     * Définit la valeur du deuxième élément.
     *
     * @param second Nouvelle valeur pour le deuxième élément.
     */
    public void setSecond(S second) {
        this.second = second;
    }


    @Override
    public String toString() {
        return "[" + first + ", " + second + ']';
    }

    // Exemple d'utilisation :

}
