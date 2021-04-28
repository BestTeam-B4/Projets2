/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

/**
 *
 * @author lemei
 */
public abstract class Figure {
    
    private Groupe groupe;
    
    public Groupe getGroupe(){
        return groupe;
    }
    
    void setGroupe(Groupe groupe){
        this.groupe=groupe;
    }
}
