package Noeuds;

public abstract class Noeud {
    // On définit cette classe comme étant abstraite car un point appartient obligatoirement à une sous-classe de la classe point
    //Attributs
    private int id; 
    
  
    //Constructeur
    Noeud(int id)
    {
       
        this.id= id;
    }
    
   //méthode get
    public int getid(){
    return this.id;
    
    }
    //méthode set
    public void setid(int i){
        this.id=i;
    }

    //Méthode toString


}
