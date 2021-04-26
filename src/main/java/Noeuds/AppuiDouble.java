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
public class AppuiDouble extends Noeud {
      //Attributs
    TriangleTerrain triangle;
    int numero;
    double position;
   

    //Constructeur
   AppuiDouble(int id, TriangleTerrain t, int num, double a ){
      super(id);
        this.triangle=t;
        this.numero=num;
        this.position=a;
      }
   public double getpositionDouble(){
       return this.position;
   }
    public int getnumeroDouble(){
       return this.numero;
   }
    public TriangleTerrain gettriangleDouble(){
        return this.triangle;
    }
    public void setpositionDouble(double a){
        this.position=a;
    }
    public void setnumeroDouble(int num){
        this.numero=num;
    }
    public void settriangleDouble(TriangleTerrain t){
        this.triangle=t;
    }        
   public String toString(){
        String res="Noeud simple numero"+String.valueOf(this.getid())+", ";

        return res;
        
    }
}
