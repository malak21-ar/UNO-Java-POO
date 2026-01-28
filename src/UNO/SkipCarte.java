package UNO;

public class SkipCarte extends Carte implements Actionable {
	  
	   
	   
	   public SkipCarte(Couleur couleur) {
	       super(couleur);
	  }
	       
	   @Override 
	   public boolean jouerSur(Carte carteTable) {
	       // Jouable si même couleur que le jeu OU si la carte sur table est aussi un Skip
	       if (this.getCouleur() == Game.couleurCourante || carteTable instanceof SkipCarte) {
	           return true;
	       } 
	       return false;
	   }
	      
	      
	      @Override
	      public void appliquerEffet(Game game) {
	          game.sauterProchainJoueur();
	      }
	      
	      
	      @Override
	      public String toString() {
	          return "[" + getCouleur() + " SKIP]";
	      }
	  }
