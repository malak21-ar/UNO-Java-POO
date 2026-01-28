package UNO;


	public enum Numero {
	    ZERO(0), UN(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5), 
	    SIX(6), SEPT(7), HUIT(8), NEUF(9);

	    private final int valeur;

	    // Constructeur pour lier le mot au chiffre
	    Numero(int valeur) {
	        this.valeur = valeur;
	    }

	    // Cette méthode change l'affichage partout dans le jeu
	    @Override
	    public String toString() {
	        return String.valueOf(valeur);
	    }
	}
