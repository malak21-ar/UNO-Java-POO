package UNO;


import java.util.List;

public class BotPlayer extends Player {

    public BotPlayer(String nom) {
        super(nom);
    }

    // Le bot choisit une carte à jouer selon ses priorité
    public Carte ChoisirCarteJouable(Game game) {
        // 1. Récupère la carte sur la table
        Carte carteTable = game.getCartesJouees().peek();
        
        // 2. Récupère les cartes jouables
        List<Carte> possibles = getCartesJouables(carteTable);

        if (possibles.isEmpty()) {
            return null; 
        }

        Carte aJouer = null;

        //PRIORITÉ 1 : Wild, Wild+4 ou DrawTwo
        for (Carte c : possibles) {
            if (c instanceof CarteWildDrawFoure || c instanceof CarteWild || c instanceof DrawTwoCarte) {
                aJouer = c; 
                break;
            }
        }

        // PRIORITÉ 2 : Reverse ou Skip 
        if (aJouer == null) {
            for (Carte c : possibles) {
                if (c instanceof ReverseCarte || c instanceof SkipCarte) {
                    aJouer = c; 
                    break;
                }
            }
        }

        //PRIORITÉ 3 : Couleur dominante
        if (aJouer == null) {
            Couleur dominante = couleurDominante();
            for (Carte c : possibles) {
                if (c.getCouleur() == dominante) {
                    aJouer = c; 
                    break;
                }
            }
        }

        // PRIORITÉ 4 : Première carte possible 
        if (aJouer == null) {
            aJouer = possibles.get(0);
        }

        
        // On récupère l'index dans la main pour la retirer proprement
        int index = getHand().indexOf(aJouer);
        
        // On retourne la carte via jouerCarte (qui fait le remove)
        return jouerCarte(index);
    }
    
    // Détermine la couleur la plus présente ou ROUGE par défaut
    public Couleur couleurDominante() {
        int r = 0, b = 0, j = 0, v = 0;

        for (Carte c : getHand()) {
            // On ne compte pas les Wilds qui sont noirs
            if (!(c instanceof CarteWild)) {
                if (c.getCouleur() == Couleur.ROUGE) r++;
                else if (c.getCouleur() == Couleur.BLEU) b++;
                else if (c.getCouleur() == Couleur.JAUNE) j++;
                else if (c.getCouleur() == Couleur.VERT) v++;
            }
        }

        Couleur dominante = Couleur.ROUGE; // Défaut
        int max = r;

        if (b > max) { max = b; dominante = Couleur.BLEU; }
        if (j > max) { max = j; dominante = Couleur.JAUNE; }
        if (v > max) { max = v; dominante = Couleur.VERT; }

        return dominante;
    }
    
    @Override
    public boolean direUNO() {
        return true;
    }
}
