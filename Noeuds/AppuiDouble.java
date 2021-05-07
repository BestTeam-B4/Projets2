/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Noeuds;
import treillis.TriangleTerrain;
import java.io.IOException;
import java.io.Writer;
import Sauvegarde.NumeroteurTreillis;
import treillis.*;
/**
 *
 * @author thiba
 */
public class AppuiDouble extends Appui {
    
 //Attributs
 private TriangleTerrain triangle;
 private int numero;
 private int numero2;
 private double abscisse;
 private double ordonnee;


 //Constructeur
     public AppuiDouble(int id, TriangleTerrain t, int s, double a ){

     super(id, a) ;
     this.triangle=t;
     this.numero=s;
     this.numero2=(s+1)%3;
     Point p=t.getPoint(s);
     Point p2=t.getPoint((s+1)%3);
     System.out.println("numero 1 "+ s);
     System.out.println("ordonnee de point debut "+ p.getordonnee());
     System.out.println("ordonnee de point fin "+ p2.getordonnee());
     System.out.println(" ");

     this.numero2=(s+1)%3;
     System.out.println("numero 2 "+(s+1)%3 );
     this.abscisse=a*p.getabscisse()+(1-a)*p2.getabscisse();
     this.ordonnee=a*p.getordonnee()+(1-a)*p2.getordonnee();
     

   }
  
@Override
public double getabscisse(){
    return this.abscisse;
}
@Override
public double getordonnee(){
    return this.ordonnee;
}

 public int getnumero(){
    return this.numero;
}

public int getnumero2(){
    return this.numero2;
}
 public TriangleTerrain gettriangle(){
     return this.triangle;
 }

 public int getidtriangle(){
    return this.triangle.getid();
}

 
 public void setnumero(int num){
     this.numero=num;
 }
 public void settriangle(TriangleTerrain t){
     this.triangle=t;
 }       
 public void setx(double x){
    this.abscisse=x;
}   

public void sety(double x){
    this.ordonnee=x;
}  

@Override
    public Segment getsegment(){

        TriangleTerrain t= this.gettriangle();
        int n1=this.getnumero();
        int n2= this.getnumero2();
        Point p1=t.getPoint(n1);
        Point p2=t.getPoint(n2);
        Segment seg = new Segment();
        for(int i=0;i<t.getSegment().length;i++){
            Segment s=t.getSegment(i);
            if(s.contains(p1) && s.contains(p2)){
                seg=s;
                if(this.getnumero()== i){

                }else{
                    seg.setPoint(0, s.getPoint(1));
                    seg.setPoint(1, s.getPoint(0));
                   

                }
                seg=s;
            }

        }
        return seg;

    }
   
public String toString(){
     String res="AppuiDouble numero"+String.valueOf(this.getid())+", ";

     return res;

 }

 public String toStringBNF(){
    String res="AppuiDouble;"+String.valueOf(this.getid())+";";
    res= res+String.valueOf(this.getidtriangle())+";";
    res= res+String.valueOf(this.getnumero())+";";
    res= res+String.valueOf(this.getposition())+"\n";
    return res;

}
@Override
public void save(Writer w, NumeroteurTreillis<Noeud> num) throws IOException{
    if(! num.contain(this)){
        int id = num.creeID(this);
        w.append("AppuiDouble;"+this.toStringBNF());
    }
}
}
