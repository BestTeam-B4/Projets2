/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import javafx.scene.paint.Color;
/**
 *
 * @author lemei
 */
public abstract class FigureSimple extends Figure{
    
    private Color couleur;
    
    public FigureSimple(Color couleur){
        this.couleur=couleur;
    }

    /**
     * @return the couleur
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    @Override
    public void changeCouleur(Color value){
        this.setCouleur(value);
    }
    
    public static String saveColor(Color c){
        return c.getRed()+";"+c.getBlue()+";"+c.getGreen();
    }
}

