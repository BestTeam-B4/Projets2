/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import java.awt.Color;
/**
 *
 * @author lemei
 */
public abstract class FigureSimple extends Figure{
    
    private Color couleur;
    
    public FigureSimple(Color couleur){
        this.couleur=couleur;
    }
}

