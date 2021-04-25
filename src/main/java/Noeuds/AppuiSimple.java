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
    int numero;
    double position;

    //Constructeur
    AppuiSimple(int id, TriangleTerrain t, int num, double position){
        super(id);
        this.triangle=t;
        this.numero=num;
        this.position=position;
    }
    
}
