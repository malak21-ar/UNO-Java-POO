package UNO;
import java.util.List ;
import java.util.Stack ;
import java.util.ArrayList ;
import java.util.Scanner;

public class Game {
    private Deck paquet;
    private Stack<Carte> cartesJouees;
    private List<Player> joueurs;
    private int joueurCourant = 0;
    public static Couleur couleurCourante;
    private int direction = 1;
    private boolean gameIsOver;
    private Scanner scanner;
    
    
    
    
 // Constructeur
    public Game(List<Player> joueurs) {
        this.joueurs = joueurs;
        this.paquet = new Deck();
        this.cartesJouees = new Stack<>();
        this.joueurCourant = 0;
        this.direction = 1;
        this.gameIsOver = false; 
        this.scanner = new Scanner(System.in);
        
        // Mélanger le paquet
        this.paquet.melanger();
    }

    public void distribuerCartesInitiales() {
        for (Player P : joueurs) {
            for (int i = 0; i < 7; i++) {
                Carte C = paquet.piocher();
                P.ajouterCarte(C);
            }
        }
    }

    public void tirerPremiereCarte() {
        Carte premiere = paquet.piocher();

        while (premiere instanceof Actionable) {
            paquet.ajouter(premiere);
            paquet.melanger();
            premiere = paquet.piocher();
        }

        // Carte normale trouvée, on la pose sur la pile
        cartesJouees.push(premiere);
        couleurCourante = premiere.getCouleur();
        System.out.println("\n La 1ère carte sur la table : " + premiere);
    }

    public void demarrerPartie() {

        // Distribuer 7 cartes à chaque joueur
        distribuerCartesInitiales();

        // Tirer la première carte normale sur la table
        tirerPremiereCarte();

        // Boucle principale de la partie
        while (!gameIsOver) {
            Player joueur = joueurs.get(joueurCourant);
            
            // Jouer le tour du joueur
            jouerTour(joueur); 
        }
        
        System.out.println("\n === Fin de la partie ===");
    }
    
    
    // BOUCHRA *** la gestion d un tour ***
    public void jouerTour(Player joueur) {
        System.out.println("\n========================================");
        System.out.println("   TOUR DE : " + joueur.getNom());

        // Affichage de la main complète pour l'humain
        if (!(joueur instanceof BotPlayer)) {
            System.out.println("Ta main : ");
            for (int i = 0; i < joueur.getHand().size(); i++) {
                System.out.println("  " + (i + 1) + ". " + joueur.getHand().get(i));
            }
        }

        Carte carteSurTable = cartesJouees.peek();
        
        System.out.print("Sur la table : " + carteSurTable);
        if (carteSurTable.getCouleur() == Couleur.NOIR) {
            System.out.print(" | Couleur : " + couleurCourante);
        }
        System.out.println(); 

        // 1. Déterminer les cartes jouables
        List<Carte> jouables = joueur.getCartesJouables(carteSurTable);

        // 2. Pioche si aucune carte n'est jouable
        if (jouables.isEmpty()) {
            System.out.print("Pas de carte jouable. ");
            piocherCartes(joueur, 1);
            jouables = joueur.getCartesJouables(carteSurTable);
        }

        // 3. Jouer une carte
        if (!jouables.isEmpty()) {
            Carte carteJouee;
            if (joueur instanceof BotPlayer) {
                carteJouee = ((BotPlayer) joueur).ChoisirCarteJouable(this);
            } else {
                System.out.println("\nTes cartes jouables :");
                for (int i = 0; i < jouables.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + jouables.get(i));
                }
                carteJouee = joueur.choisirCarteJouable();
            }

            if (carteJouee != null) {
                cartesJouees.push(carteJouee);
                System.out.println(  joueur.getNom() + " joue : " + carteJouee);
                
                // Affichage du nouveau total juste après avoir joué
                System.out.println(" Nouveau total pour " + joueur.getNom() + " : " + joueur.getHand().size() + " cartes.");

                // Effets et règles
                if (!(carteJouee instanceof CarteWild)) {
                    couleurCourante = carteJouee.getCouleur();
                }

                if (carteJouee instanceof Actionable) {
                    ((Actionable) carteJouee).appliquerEffet(this);
                }

                // Gestion UNO
                if (joueur.getHand().size() == 1) {
                    if (joueur.direUNO()) System.out.println("!!! " + joueur.getNom() + " crie : UNO !");
                    else {
                        System.out.println("Oubli du UNO !");
                        piocherCartes(joueur, 2);
                    }
                }

                if (joueur.aGagne()) {
                    System.out.println( joueur.getNom().toUpperCase() + " A GAGNÉ !");
                    gameIsOver = true;
                    return;
                }
            }
        } else {
            System.out.println( joueur.getNom() + " ne peut pas jouer et passe.");
        }

        if (!gameIsOver) passerAuProchain();
    }
    
    public void changerCouleur(Couleur couleur) {
        this.couleurCourante = couleur;
    }
    
    public Couleur demanderCouleurAuJoueur() {
        System.out.println("Choisis une couleur :");
        System.out.println(" 1-Rouge");
        System.out.println(" 2-Bleu");
        System.out.println(" 3-Jaune");
        System.out.println(" 4-Vert");

        int choix = scanner.nextInt();
        scanner.nextLine(); 

        switch (choix) {
            case 1:
                System.out.println("Je choisis la couleur Rouge");
                return Couleur.ROUGE;
            case 2:
                System.out.println("Je choisis la couleur Bleu");
                return Couleur.BLEU;
            case 3:
                System.out.println("Je choisis la couleur Jaune");
                return Couleur.JAUNE;
            case 4:
                System.out.println("Je choisis la couleur Vert");
                return Couleur.VERT;
            default:
                System.out.println("Choix invalide, Rouge par défaut");
                return Couleur.ROUGE;
        }
    }
    
    

  // MALAK *** gestion du paquet et des pioches ***
    
    public void piocherCartes(Player p, int nbCartes) {
        Carte c = null;
        
        if (paquet.estVide()) {
            paquet.rechargerPaquet(cartesJouees);
        }

        if (nbCartes > 1) {
            System.out.println(p.getNom() + " doit piocher " + nbCartes + " carte(s).");
        }

        for (int i = 0; i < nbCartes; i++) {
            if (paquet.estVide()) {
                paquet.rechargerPaquet(cartesJouees);
            }

            c = paquet.piocher(); // On stocke la carte piochée
            if ( c != null) {
                p.ajouterCarte(c);
            }
        }
        
       
        if (nbCartes == 1 && c != null) {
            System.out.println( " /n " + p.getNom() + " a pioché : " + c);
        }
        
        System.out.println( p.getNom() + " a fini de piocher. Nouveau total : " + p.getHand().size() + " carte(s).");
    }

    public void piocherCartes(int nbCartes) {   // le cas de pioche drawtwo Wild Draw Four
        int indexProchain = (joueurCourant + direction + joueurs.size()) % joueurs.size();
        Player prochainJoueur = joueurs.get(indexProchain);
        piocherCartes(prochainJoueur, nbCartes);  //apprlr de la methode juste avant 
    }
    
    
    //KHADIDJA ***gestion des effets de cartes ***
    
    public void passerAuProchain() {
        joueurCourant = (joueurCourant + direction + joueurs.size()) % joueurs.size();
    }

    public void inverserSens() {
        direction *= -1;
        System.out.println("Le sens est inversé");
    }

    public void sauterProchainJoueur() {
        // On identifie celui qui doit être sauté
        int indexSaute = (joueurCourant + direction + joueurs.size()) % joueurs.size();
        System.out.println(" Le tour de " + joueurs.get(indexSaute).getNom() + " est sauté !");
        
        // On déplace l'index sur lui
        joueurCourant = indexSaute;
    }


   
    // Getters & Setters
    public Couleur getCouleurCourante() {
        return this.couleurCourante;
    }

    	public void setCouleurCourante(Couleur nouvelle) {
		 couleurCourante = nouvelle;
		
	} 

		public int getJoueurCourant() {
			return joueurCourant;
		}

		public List<Player> getJoueurs() {
			return joueurs;
		}
		  public Deck getPaquet() {
		        return paquet;
		    }

	    public Stack<Carte> getCartesJouees() {
		        return cartesJouees;
		    }
}
