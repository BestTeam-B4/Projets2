/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;

/**
 *
 * @author lemei
 */
public class Controleur {
  
    private MainPanel vue;
    
    private int etat;
    
    private double[] pos1=new double[2];
    
    private List<Figure> selection=new ArrayList<>();
    
    public Controleur(MainPanel vue){
        this.vue=vue;
    }
    
    public void changeEtat(int nouvelEtat){
        if(nouvelEtat==30){
            
        }else if(nouvelEtat==30){
            this.vue.getbGrouper().setDisable(true);
            this.vue.getCpCouleur().setDisable(true);
        }else if(nouvelEtat==40){
            this.vue.getbGrouper().setDisable(true);
            this.vue.getCpCouleur().setDisable(true);
        }else if (nouvelEtat==41){
            
        }
        this.etat=nouvelEtat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        if (this.etat==20){
            Point pointclic=new Point(t.getX(),t.getY());
            Figure proche=this.vue.getModel().plusProche(pointclic,Double.MAX_VALUE);
            if(proche!=null){
                if(t.isShiftDown()){
                    this.getSelection().add(proche);
                }else if(t.isControlDown()){
                    if(this.getSelection().add(proche)){
                        this.getSelection().remove(proche);
                    }else{
                        this.getSelection().clear();
                        this.getSelection().add(proche);
                    }
                    this.ActiveBoutonSuivantSelection();
                    this.vue.redrawAll();
                }
            }
        }else if(this.etat ==30){
        double px=t.getX();
        double py=t.getY();
        Color couleur=Color.color(Math.random(),Math.random(),Math.random());
        Groupe model = this.vue.getModel();
        model.add(new Point(px,py,couleur));
        this.vue.redrawAll();
        }else if (this.etat==40){
            this.pos1[0]=t.getX();
            this.pos1[1]=t.getY();
            this.changeEtat(41);
        }else if(this.etat == 41){
           double px2=t.getX();
           double py2=t.getY();
            Color couleur=Color.color(Math.random(),Math.random(),Math.random());
            this.vue.getModel().add(
                new Segment(new Point(this.pos1[0],this.pos1[1]),new Point(px2,py2,couleur),couleur));
            this.vue.redrawAll();
            this.changeEtat(40);
        }
    }
    

    void boutonSelect(ActionEvent t) {
        this.changeEtat(20);
    }

    void boutonPoints(ActionEvent t) {
        this.changeEtat(30);
    }

    void boutonSegments(ActionEvent t) {
        this.changeEtat(40);
    }
    
    private void ActiveBoutonSuivantSelection(){
        if (this.getSelection().size()<2){
            this.vue.getbGrouper().setDisable(true);
        }else{
            this.vue.getbGrouper().setDisable(false);
        }
    }

    /**
     * @return the selection
     */
    public List<Figure> getSelection() {
        return selection;
    }
    
   void boutonGrouper(ActionEvent t){
       if(this.etat==20 && this.selection.size()>1){
           Groupe ssGroupe=this.vue.getModel().sousGroupe(selection);
           this.selection.clear();
           this.selection.add(ssGroupe);
           this.vue.redrawAll();
       }
   }
    
    void changeColor(Color value){
        if(this.etat==20&&this.selection.size()>0){
            for(Figure f: this.selection){
                f.changeCouleur(value);
            }
            this.vue.redrawAll();
        }
    }
  
}
