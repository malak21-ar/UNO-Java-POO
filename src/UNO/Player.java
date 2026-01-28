package UNO;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class Player {

    private String nom;
    private List<Carte> hand;
    private List<Carte> cartesJouables;
    private Scanner scanner;

    public Player(String nom) {
        this.nom = nom;
        this.hand = new ArrayList<>();
        this.cartesJouables = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void ajouterCarte(Carte carte) {
        hand.add(carte);
    }

    public Carte jouerCarte(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.remove(index);
        }
        System.out.println("Indice invalide !");
        return null;
    }

    public boolean aGagne() {
        return hand.isEmpty();
    }

    public boolean direUNO() {
        if (hand.size() == 1) {
            System.out.print("Voulez-vous dire UNO ? (oui/non) : ");
            String rep = scanner.nextLine();
            return rep.equalsIgnoreCase("oui");
        }
        return false;
    }

    public List<Carte> getCartesJouables(Carte carteTable) {
        cartesJouables.clear();

        for (Carte c : hand) {

            // CAS 1 : la carte sur table est une Wild
            if (carteTable.getCouleur() == Couleur.NOIR) {

                // On compare avec la couleur choisie
                if (c.getCouleur() == Game.couleurCourante || c instanceof CarteWild) {
                    cartesJouables.add(c);
                }

            } 
            // CAS 2 : carte normale
            else {
                if (c.jouerSur(carteTable)) {
                    cartesJouables.add(c);
                }
            }
        }
        return cartesJouables;
    }


    public Carte choisirCarteJouable() {
        if (cartesJouables.isEmpty()) {
            System.out.println("Aucune carte jouable.");
            return null;
        }

        int choix = -1;

        while (choix < 1 || choix > cartesJouables.size()) {
            System.out.print("Choisissez la carte (1 à " + cartesJouables.size() + ") : ");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Entrez un nombre valide !");
                continue;
            }

            choix = scanner.nextInt();
            scanner.nextLine();

            if (choix < 1 || choix > cartesJouables.size()) {
                System.out.println("Choix hors limites !");
            }
        }

        Carte choisie = cartesJouables.get(choix - 1);
        return jouerCarte(hand.indexOf(choisie));
    }

  
    public String getNom() {
        return nom;
    }

    public List<Carte> getHand() {
        return hand;
    }
}
