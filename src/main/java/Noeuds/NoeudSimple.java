package Noeuds;
import treillis.Terrain;

public class NoeudSimple extends Noeud {
    
    //Attributs
    
	private double abscisse;
    private double ordonnee;

    //Constructeur
    NoeudSimple(int id, double x, double y){
        super(id);
        this.abscisse=x;
        this.ordonnee=y;
    }

    ///méthode get
    public double getabscisse(){
        return this.abscisse;
    }
    public double getordonnee(){
        return this.ordonnee;
    }

    //méthode set
    public void setAbscisse(double x){
        this.abscisse=x;
    }
    public void setOrdonnee(double y){
        this.ordonnee=y;
    }

    //méthode toString
    public String toString(){
        String res="Noeud simple numero"+String.valueOf(this.getid())+", abscisse : "+String.valueOf(abscisse)+", ordonnee : "+String.valueOf(ordonnee);

        return res;
        
    }
}
