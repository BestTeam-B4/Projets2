package treillis;
import Noeuds.Noeud;

public class Segment{

   //Attributs
   private Noeud[] points;
   //un segment est composé de deux Noeuds

   //Constructeur

   public Segment( Noeud n1, Noeud n2){

    this.points= new Noeud[2];
    this.points[0]=n1;
    this.points[1]=n2;

   }

     //méthode get

   public Noeud getPoint(int i){
    // ! eventuellement mettre une exception si i>1
    return this.points[i];
    
}  //surcharge
   public Noeud[] getPoint(){
   
    return this.points;
}
   //méthode set
  
   public void setPoint(int i, Noeud n){
   //! idem, exception si i>1
   this.points[i]=n;
}
   //méthode toString
   public String toString(){
   String res="";
   res= res+" Noeud debut = "+String.valueOf(this.getPoint(0).getid()); //on prend l'identificateur du noeud en question
   res= res+" Noeud fin = "+String.valueOf(this.getPoint(0).getid()); //idem
   return res;
} 
//méthode contains ; méthode qui permet de savoir si un Noeud appartient à la barre

public boolean contains(Noeud n){
    boolean test=false;
    for (int i=0; i<2; i++){
        if (n.getid()==this.getPoint(i).getid()){
            test=true;
    }
}
    return test;
}
}
