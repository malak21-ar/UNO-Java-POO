package UNO;

public class CarteWildDrawFoure extends CarteWild {
    @Override
      public void appliquerEffet(Game game) {
          // Changer la couleur (hérité de CarteWild)
           
          super.appliquerEffet(game); 

          // Piocher 4 cartes et sauter le joueur
          
          System.out.println("Prochain joueur pioche 4 cartes et passe son tour");
          game.piocherCartes(4);
          game.sauterProchainJoueur();
    }
     @Override
        public String toString() {
            return "[" + getCouleur() + " WildDraw4]";
        }

  }
