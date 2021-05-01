/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 *
 * @author lemei
 */
public abstract class Figure {

    public static Color COULEUR_SELECTION=Color.RED;
    private Groupe groupe;
    
    public Groupe getGroupe(){
        return groupe;
    }
    
    void setGroupe(Groupe groupe){
        this.groupe=groupe;
    }
    
    public abstract double maxX();
    public abstract double maxY();
    public abstract double minX();  
    public abstract double minY();
    
    public abstract double distancePoint(Point p);
    
    public abstract void dessine(GraphicsContext context);
    
    public abstract void dessineSelection(GraphicsContext context);
    
    public abstract void changeCouleur(Color value);
    
    public abstract void save(Writer w,Numeroteur<Figure>num)throws IOException;
    
    public void sauvegarde(File fout) throws IOException{
        Numeroteur<Figure> num = new Numeroteur<Figure>();
        try(BufferedWriter bout=new BufferedWriter(new FileWriter(fout))){
            this.save(bout, num);
        }
    }
}
