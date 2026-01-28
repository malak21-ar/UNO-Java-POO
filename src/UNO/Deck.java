package UNO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;



 public class Deck {
  private List<Carte> cartes;
  
 
  
  public Deck() {
   cartes=new ArrayList<>();
   initialiserPaquet();
   System.out.println("\n Taille du paquet =" + cartes.size());
  }
  public Deck(List<Carte> cartes) {
   this.cartes = cartes;
  }

  public int taille() {
   return cartes.size() ;
   }

  
  public List<Carte> getCartes() {
   return cartes;
  }
  
  
  private void initialiserPaquet() {
   cartes.clear();
   
   Couleur[] couleurs= {Couleur.ROUGE , Couleur.VERT , Couleur.BLEU , Couleur.JAUNE };
   
   Numero[] numeros = {
           Numero.UN, Numero.DEUX, Numero.TROIS, Numero.QUATRE,
           Numero.CINQ, Numero.SIX, Numero.SEPT, Numero.HUIT, Numero.NEUF
       };
   
   
     for(Couleur cou : couleurs) {
      cartes.add(new CarteNumero(cou , Numero.ZERO));
      for(Numero n : numeros) {
       cartes.add(new CarteNumero(cou , n));
       cartes.add(new CarteNumero(cou , n));
      }
     }
   
    for(Couleur cou : couleurs) {
       cartes.add(new SkipCarte(cou));
          cartes.add(new SkipCarte(cou));

          cartes.add(new ReverseCarte(cou));
          cartes.add(new ReverseCarte(cou));

          cartes.add(new DrawTwoCarte(cou));
          cartes.add(new DrawTwoCarte(cou));
    }
  
   
      for (int i = 0; i < 4; i++) cartes.add(new CarteWild());
      for (int i = 0; i < 4; i++) cartes.add(new CarteWildDrawFoure());
  }
   
   public void melanger() {
    Collections.shuffle(cartes);
   
  }  
   
   
  public Carte piocher() {
   if(!cartes.isEmpty()) {
    Carte c = cartes.get(0);
    cartes.remove(c);
    return c ;
   }
   return null ;
  
  }
  
  
  public boolean estVide() {
   return cartes.isEmpty();
   
  }
  
  public void ajouter(Carte c) {
   cartes.add(c);
  }
  
  
   
  public void rechargerPaquet(Stack<Carte> paquetJouer  ) {
   if(cartes.isEmpty()  &&  !paquetJouer.isEmpty()) {
    Carte dessus = paquetJouer.pop();
    while(!paquetJouer.isEmpty()) {
     this.ajouter(paquetJouer.pop());
     
    }
    
    this.melanger();
    paquetJouer.push(dessus);
   }
  
  }
 }
