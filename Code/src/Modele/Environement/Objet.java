class Objet {

    protected int ligne;
    protected int colone;
    protected String type;
    protected Animal animal;
    protected boolean etat; // True = vide False = non vide

    public Objet(int ligne, int colone, String type) {
        this.ligne = ligne;
        this.colone = colone;
        this.type = type;
        this.animal = null;
    }

    public String getType() {
        return type;
    }

    public void seCacher(Animal animal){
        animal = animal;
        etat = false;

        Ihm.print("L'animal " + animal + " s'est cahée dans " + type)
    }

    public Animal seDetatcher(){
        etat = true;
        Animal a = animal;
        animal = null;

        Ihm.print("L'animal " + animal + " est partis from " + type)
        return a;
    }

}