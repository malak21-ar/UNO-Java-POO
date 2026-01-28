package UNO;


public class CarteWild extends Carte implements Actionable {
	  public CarteWild() {
	    super(Couleur.NOIR);
	  }
	 
	  
	  @Override
	  public boolean jouerSur(Carte carteTable) { return true; }
	  
	 
	  
	  @Override
	  public void appliquerEffet(Game game) {
	      // 1. Récupérer le joueur qui est en train de jouer
	      Player joueurActuel = game.getJoueurs().get(game.getJoueurCourant());
	      Couleur nouvelleCouleur;

	      // 2. Vérifier si c'est un Bot ou un Humain
	      if (joueurActuel instanceof BotPlayer) {
	          // Le Bot utilise sa propre logique pour choisir
	          nouvelleCouleur = ((BotPlayer) joueurActuel).couleurDominante();
	          System.out.println(joueurActuel.getNom() + " (BOT) choisit la couleur : " + nouvelleCouleur);
	      } else {
	          // Pour l'humain, on affiche le menu de sélection
	          System.out.println("Le joueur " + joueurActuel.getNom() + " doit choisir une nouvelle couleur.");
	          nouvelleCouleur = game.demanderCouleurAuJoueur();
	      }

	      // 3. Appliquer le changement au jeu
	      game.changerCouleur(nouvelleCouleur);
	  }
	   @Override
	      public String toString() {
	          return "[NOIR:Wild]";
	      }


	  }

