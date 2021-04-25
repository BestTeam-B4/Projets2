package treillis;
import Noeuds.Noeud;


public class Barre extends Segment {

    //Définitions des attributs
    
    private int id ; //identificateur
    private typeBarre type;
    

    // ! rajouter le catalogue?
    
    Barre(int id, typeBarre type, Noeud debut, Noeud fin){
        
        super(debut, fin);
        this.type= type;
        this.id=id;
    }

    //méthode get
    public int getid(){
        return this.id;
    }

    public typeBarre gettypeBarre(){
        return this.type;
    }

    //méthode set
    public void setid(int i){
        this.id=i;
    }

    public void settypeBarre(typeBarre t){
        this.type= t;
    }

    //méthode toString

    public String toString(){
        String res="";
        res=" identificateur = "+String.valueOf(this.getid());
        res= res+ this.getPoint().toString();
        res= res+ this.type.toString();
        return res;
    }



}
