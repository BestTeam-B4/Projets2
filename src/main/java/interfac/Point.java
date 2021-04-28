/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import Noeuds.Lire;
import java.awt.Color;

/**
 *
 * @author lemei
 */
public class Point extends FigureSimple{
    private double px;
    private double py;
    
    public Point (double px, double py,Color couleur){
        super(couleur);
        this.px=px;
        this.py=py;
    }
    public Point(double px, double py){
        this(px,py,Color.black);
    }
    
    public Point(){
        this(0,0);
    }
    
    public double getPx(){
        return px;
    }
    
    public double getPy(){
        return py;
    }
    
    public void SetPx(double px){
        this.px=px;
    }
    
    public void SetPy(double py){
        this.py=py;
    }
    
    @Override
    public String toString(){
        return "Point ("+px+","+py+")";
    }
    
    public static Point demandePoint(){
        System.out.println("abscisse : ");
        double px=Lire.d();
        System.out.println("ordonee : ");
        double py=Lire.d();
        return new Point (px,py);
    }
}
