package UNO;

public class ReverseCarte extends Carte  implements Actionable  {
	  //constracteur
	   public ReverseCarte (Couleur couleur){
	      super(couleur);
	    }
	   
	   @Override  
	   public boolean jouerSur(Carte carteTable) {
	       // Jouable si même couleur que le jeu OU si la carte sur table est aussi un Reverse
	       if (this.getCouleur() == Game.couleurCourante || carteTable instanceof ReverseCarte) {
	           return true;
	       } 
	       return false;
	   }
	      
	    @Override
	      public void appliquerEffet(Game game) {
	          game.inverserSens();
	      }
	    @Override
	      public String toString() {
	          return "[" + getCouleur() + " Reverse]";
	      }

	}
