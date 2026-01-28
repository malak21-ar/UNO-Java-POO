package UNO;

public class CarteNumero  extends Carte {
	

	    private Numero numero;

	    public CarteNumero(Couleur couleur, Numero numero) {
	        super(couleur);
	        this.numero = numero;
	    }

	    public Numero getNumero() {
	        return numero;
	    }

	    @Override
	    public boolean jouerSur(Carte carteTable) {

	        // même couleur
	        if (this.getCouleur() == carteTable.getCouleur()) {
	            return true;
	        }

	        // même numéro
	        if (carteTable instanceof CarteNumero) {
	            CarteNumero c = (CarteNumero) carteTable;
	            return this.numero == c.getNumero();
	        }

	        return false;
	    }

	    @Override
	    public String toString() {
	        return "[" + getCouleur() + " : " + numero + "]";
	    }
	}



