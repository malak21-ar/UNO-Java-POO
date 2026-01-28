package UNO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException ;




public class Main {

	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	       

	     //  nombre de joueurs
	        int nbJoueurs = 0;
	        while (nbJoueurs < 2 || nbJoueurs > 4) {
	            System.out.print("Combien de joueurs (2 à 4) ? ");
	            try {
	                nbJoueurs = scanner.nextInt();
	                scanner.nextLine();
	                if (nbJoueurs < 2 || nbJoueurs > 4) {
	                    System.out.println("Nombre de joueurs invalide.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Entrez un nombre !");
	                scanner.nextLine(); 
	            }
	        }
	        //  nombre de joueurs réels
	        int nbReels = -1;
	        while (nbReels < 0 || nbReels > nbJoueurs) {
	            System.out.print("Combien de joueurs réels ? ");
	            try {
	                nbReels = scanner.nextInt();
	                scanner.nextLine();
	                if (nbReels < 0 || nbReels > nbJoueurs) {
	                    System.out.println("Nombre de joueurs réels invalide.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Entrez un nombre !");
	                scanner.nextLine();
	            }
	        }

	        //  noms des joueurs réels
	        List<Player> joueurs = new ArrayList<>();
	        for (int i = 1; i <= nbReels; i++) {
	            System.out.print("Nom du joueur réel " + i + " : ");
	            String nom = scanner.nextLine();
	            joueurs.add(new Player(nom));
	        }

	        //  bots automatiques
	        for (int i = nbReels + 1; i <= nbJoueurs; i++) {
	            joueurs.add(new BotPlayer("Bot " + i));
	        }

	        //afficher la liste des joueurs
	        System.out.println("\nListe des joueurs :");
	        for (Player p : joueurs) {
	            System.out.println(p.getNom());
	        }

	    

	        // Créer le jeu et démarrer la partie
	        
	        System.out.println("\n *** La partie commence ! *** ");
	        Game game = new Game(joueurs);
	        game.demarrerPartie();

	    }
	}
