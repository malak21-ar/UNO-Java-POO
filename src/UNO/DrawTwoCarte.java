package UNO;

public class  DrawTwoCarte extends Carte implements Actionable  {
	  
	   
	  public  DrawTwoCarte (Couleur couleur){
	      super(couleur);
	    }
	   
	  @Override 
	  public boolean jouerSur(Carte carteTable) {
	      // Jouable si même couleur que le jeu OU si la carte sur table est aussi un +2
	      if (this.getCouleur() == Game.couleurCourante || carteTable instanceof DrawTwoCarte) {
	          return true;
	      } 
	      return false;
	  }
	     
	      @Override
	        public void appliquerEffet(Game game) {
	            game.piocherCartes(2);
	              game.sauterProchainJoueur();
	        }
	      @Override
	        public String toString() {
	            return "[" + getCouleur() + " +2]";
	        }

	     
	     }
