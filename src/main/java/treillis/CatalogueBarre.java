package treillis;
import java.util.*;
import treillis.typeBarre;

public class CatalogueBarre{

 //Attributs


 private ArrayList<typeBarre> catalogue; //on stockera ici les différentes barres contenues dans le catalogue

 //Constructeur
 CatalogueBarre(typeBarre t){
    this.catalogue.add(t);
 }

 //méthode get
 public ArrayList<typeBarre> getTypeBarre(){
    return this.catalogue;
 }
 //surchage: méthode get qui ne retourne qu'un TypeBarre particulier; en fonction de l'identificateur

 public ArrayList<typeBarre> getTypeBarre(typeBarre t){
   return this.catalogue;
}

  //méthode set
  public void setTypeBarre(int p, typeBarre t){
      this.catalogue.set(p, t);
  }
  //méthode contains, qui permet de savoir si le catalogue contient un typeBarre particulier, en se basant sur l'identificateur



 //méthode add, qui permettra d'ajouter un élement au catalogue
 public void add(typeBarre t){
   this.catalogue.add(t);
 }

 //méthode remove, qui permettra de supprimer un élément du catalogue; pour cela, on se servira de l'identificateur du typeBarre qui est unique
 public void delete(typeBarre t){

   int id=t.getid();
   

 }
}