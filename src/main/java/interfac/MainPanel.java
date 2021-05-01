/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import interfac.Groupe;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
/**
 *
 * @author lemei
 */
public class MainPanel extends BorderPane {

    /**
     * @return the rbSelect
     */
    public RadioButton getRbSelect() {
        return rbSelect;
    }

    /**
     * @return the rbPoints
     */
    public RadioButton getRbPoints() {
        return rbPoints;
    }

    /**
     * @return the rbSegments
     */
    public RadioButton getRbSegments() {
        return rbSegments;
    }

    /**
     * @return the bGrouper
     */
    public Button getbGrouper() {
        return bGrouper;
    }


    /**
     * @return the cDessin
     */
    public DessinCanvas getcDessin() {
        return cDessin;
    }
    /**
     * @return the model
     */
    public Groupe getModel() {
        return model;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }
    /**
     * @return the cpCouleur
     */
    public ColorPicker getCpCouleur() {
        return cpCouleur;
    }
    
    private Groupe model;
    private Controleur controleur;
    
    private RadioButton rbSelect;
    private RadioButton rbPoints;
    private RadioButton rbSegments;
    
    private Button bGrouper;
    private ColorPicker cpCouleur;
    
    private DessinCanvas cDessin;
    public MainPanel(){
        this(new Groupe());
    }
    public  MainPanel(Groupe model){
        this.model=model;
        this.controleur=new Controleur(this);
        
    this.rbSelect=new RadioButton("Select");
    this.rbSelect.setOnAction((t)->{
        this.controleur.boutonSelect(t);
    });
    this.rbPoints=new RadioButton("Points");
    this.rbPoints.setOnAction((t)->{
        this.controleur.boutonPoints(t);
    });
    this.rbSegments=new RadioButton("Segments");
    this.rbSegments.setOnAction((t)->{
        this.controleur.boutonSegments(t);
    });
    
    ToggleGroup bgEtat= new ToggleGroup();
    this.rbSelect.setToggleGroup(bgEtat);
    this.rbPoints.setToggleGroup(bgEtat);
    this.rbSegments.setToggleGroup(bgEtat);
    this.rbPoints.setSelected(true);
    
    VBox vbGauche=new VBox(this.getRbSelect(), this.getRbPoints(), this.getRbSegments());
    this.setLeft(vbGauche);
    
    this.bGrouper=new Button("Grouper");
    this.bGrouper.setOnAction((t)->{
       this.controleur.boutonGrouper(t);    
    });
        
    
    this.cpCouleur=new ColorPicker(Color.BLACK);
    this.cpCouleur.setOnAction((t)->{
        this.controleur.changeColor(this.cpCouleur.getValue());
    });
    
    VBox vbDroit=new VBox(this.getbGrouper(), this.getCpCouleur());
    this.setRight(vbDroit);
    
    this.cDessin=new DessinCanvas(this);
    this.setCenter(this.cDessin);
    this.controleur.changeEtat(30);
}
    
    
    public void redrawAll(){
        this.cDessin.redrawAll();
    }

    

        

    
}
