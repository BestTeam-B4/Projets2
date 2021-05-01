/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import Noeuds.Lire;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


/**
 *
 * @author lemei
 */
public class Point extends FigureSimple{
    
    public static double RAYON_IN_DRAW=5;
    private double px;
    private double py;
    
    public Point (double px, double py,Color couleur){
        super(couleur);
        this.px=px;
        this.py=py;
    }
    public Point(double px, double py){
        this(px,py,Color.BLACK);
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
    
    @Override
    public double maxX(){
        return this.px;
    }
    @Override
    public double maxY(){
        return this.py;
    }
    @Override
    public double minX(){
        return this.px;
    }
    @Override
    public double minY(){
        return this.py;
    }
    @Override
    public double distancePoint(Point p){
        double x1 = this.getPx();
        double y1=this.getPy();
        double x2 = this.getPx();
        double y2=this.getPy();
        double distance=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        return distance;
    }
   @Override
    public void dessine(GraphicsContext context){
        context.setFill(this.getCouleur());
        context.fillOval(this.px-RAYON_IN_DRAW,this.py-RAYON_IN_DRAW,2*RAYON_IN_DRAW,2*RAYON_IN_DRAW);
    }
     @Override
    public void dessineSelection(GraphicsContext context){
        context.setFill(Figure.COULEUR_SELECTION);
        context.fillOval(this.px-RAYON_IN_DRAW,this.py-RAYON_IN_DRAW,2*RAYON_IN_DRAW,2*RAYON_IN_DRAW);
    }

    @Override
    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
        if(! num.objetExiste(this)){
            int id=num.creeID(this);
            w.append("Point;"+id+";"+this.px+";"+this.py+";"+FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }

    
}
