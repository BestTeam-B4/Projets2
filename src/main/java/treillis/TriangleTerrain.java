package treillis;
import Noeuds.Noeud;

public class TriangleTerrain {

    //Attributs

    private int id; //identificateur
    private Noeud[] sommet ;//un triangle est composé de 3 points
    private Segment[] cote;//un triangle a 3 cote

    //Constructeur
    TriangleTerrain(int id, Noeud n1, Noeud n2, Noeud n3){
        this.id=id;
        this.sommet= new Noeud[3];
        this.sommet[0]=n1;
        this.sommet[1]=n2;
        this.sommet[2]=n3;
    
    }
}
