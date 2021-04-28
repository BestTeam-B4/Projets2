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
public class Segment extends FigureSimple {
    
    private Point debut;
    private Point fin;
    
    public Segment(Point debut, Point fin, Color couleur){
        super(couleur);
        this.debut=debut;
        this.fin=fin;
    }
    
    public Segment(Point debut, Point fin){
        this(debut,fin,Color.blue);
    }
    
    public Point getDebut(){
        return debut;
    }
    
    public Point getFin (){
        return fin;
    }
    
    public void setDebut(Point debut){
        this.debut=debut;
    }
    
    public void setFin(Point fin){
        this.fin=fin;
    }
    
    
    @Override
    public String toString(){
        return "["+this.debut.toString()+","+this.fin.toString()+"]";
    }
    
    public static Segment demandeSegment(){
        System.out.println("point debut : ");
        Point p1=Point.demandePoint();
        System.out.println("point fin : ");
        Point p2= Point.demandePoint();
        return new Segment (p1,p2);
    }
}
