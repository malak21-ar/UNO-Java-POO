package UNO;

public abstract class Carte {
	
	   private Couleur couleur;
	   
	 
	   
	   public Carte (Couleur couleur) {
	     this.couleur=couleur;
	    
	   }
	   
	   
	   public Couleur getCouleur() {
	     return couleur;
	    }
	   
	   
	  
	   public abstract boolean jouerSur(Carte carteTable) ;
	   

	   @Override
	   public String toString() {
	       return "[" + couleur + "]";
	   }
	   
	     
	    
	    
	}
