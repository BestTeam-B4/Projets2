/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Noeuds;
import treillis.TriangleTerrain;
/**
 *
 * @author thiba
 */
public class AppuiSimple extends Noeud {

    //Attributs
    TriangleTerrain triangle;
    private int numero;
    private double position;

    //Constructeur
   AppuiSimple(int id, TriangleTerrain t, int num, double a){
        super(id);
        this.triangle=t;
        this.numero=num;
        this.position=a;
    }
   public double getpositionSimple(){
       return this.position;
   }
    public int getnumeroSimple(){
       return this.numero;
   }
    public TriangleTerrain gettriangleSimple(){
        return this.triangle;
    }
    public void setpositionSimple(double a){
        this.position=a;
    }
    public void setnumeroSimple(int num){
        this.numero=num;
    }
    public void settriangleSimple(TriangleTerrain t){
        this.triangle=t;
    }        
    public String toString(){
        String res="Noeud simple numero"+String.valueOf(this.getid())+", ";

        return res;
        
    }
}
