/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import interfac.Figure;
import interfac.Groupe;
import interfac.Point;
import interfac.Segment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author lemei
 */
public class Controleur {

    private MainPanel vue;
    
    private int etat;

    private double[] pos1 = new double[2];

    private List<Figure> selection;
    private String[] Point=new String[100];
    private String[] Barre=new String[100];
    private String[] Groupe=new String [100];
    private int IDPoint=0;
    private int IDBarre=0;
    private int IDGroupe=0;
    public String Attributs;
    public Controleur(MainPanel vue) {
        this.vue = vue;
        this.selection = new ArrayList<>();
    }
    
    public void changeEtat(int nouvelEtat) {
        if (nouvelEtat == 20) {
            this.vue.getRbSelect().setSelected(true);
            this.selection.clear();
            this.vue.redrawAll();
        } else if (nouvelEtat == 30) {
            // creation de points
            this.vue.getRbPoints().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 40) {
            // creation de segments étape 1
            this.vue.getRbSegments().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 41) {
            // creation de segments étape 2
        }
        this.etat = nouvelEtat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        if (this.etat == 20) {
            Point pclic = new Point(t.getX(), t.getY());
            // pas de limite de distance entre le clic et l'objet selectionné
            Figure proche = this.vue.getModel().plusProche(pclic, Double.MAX_VALUE);
            // il faut tout de même prévoir le cas ou le groupe est vide
            // donc pas de plus proche
            if (proche != null) {
                if (t.isShiftDown()) {
                    this.selection.add(proche);
                } else if (t.isControlDown()) {
                    if (this.selection.contains(proche)) {
                        this.selection.remove(proche);
                    } else {
                        this.selection.add(proche);
                    }
                } else {
                    this.selection.clear();
                    this.selection.add(proche);
                }
                this.activeBoutonsSuivantSelection();
                this.vue.redrawAll();
            }
        } else if (this.etat == 30) {
            double px = t.getX();
            double py = t.getY();
            Color col = this.vue.getCpCouleur().getValue();
            Groupe model = this.vue.getModel();
            model.add(new Point(px, py, col));
            this.vue.redrawAll();
        } else if (this.etat == 40) {
            this.pos1[0] = t.getX();
            this.pos1[1] = t.getY();
            this.changeEtat(41);
        } else if (this.etat == 41) {
            double px2 = t.getX();
            double py2 = t.getY();
            Color col = this.vue.getCpCouleur().getValue();
            this.vue.getModel().add(
                    new Segment(new Point(this.pos1[0], this.pos1[1]),
                            new Point(px2, py2), col));
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
    
    
    private void activeBoutonsSuivantSelection() {
        if (this.selection.size() < 2) {
            this.vue.getbGrouper().setDisable(true);
        } else {
            this.vue.getbGrouper().setDisable(false);
        }
    }

    /**
     * @return the selection
     */
    public List<Figure> getSelection() {
        return selection;
    }

    void boutonGrouper(ActionEvent t) {
        if (this.etat == 20 && this.selection.size() > 1) {
            // normalement le bouton est disabled dans le cas contraire
            Groupe ssGroupe = this.vue.getModel().sousGroupe(selection);
            this.selection.clear();
            this.selection.add(ssGroupe);
            this.vue.redrawAll();
        }
    }

    void changeColor(Color value) {
        if (this.etat == 20 && this.selection.size() > 0) {
            for (Figure f : this.selection) {
                f.changeCouleur(value);
            }
            this.vue.redrawAll();
        }
    }

    void realSave(File f) {
        try {
            this.vue.getModel().sauvegarde(f);
            this.vue.setCurFile(f);
            this.vue.getInStage().setTitle(f.getName());
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème durant la sauvegarde");
            alert.setContentText(ex.getLocalizedMessage());

            alert.showAndWait();
        } finally {
            this.changeEtat(20);
        }
    }

    void menuSave(ActionEvent t) {
        if (this.vue.getCurFile() == null) {
            this.menuSaveAs(t);
        } else {
            this.realSave(this.vue.getCurFile());
        }
    }

    void menuSaveAs(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showSaveDialog(this.vue.getInStage());
        if (f != null) {
            this.realSave(f);
        }
    }

    void menuOpen(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(this.vue.getInStage());
        if (f != null) {
            try {
                Figure lue = Figure.lecture(f);
                Groupe glu = (Groupe) lue;
                Stage nouveau = new Stage();
                nouveau.setTitle(f.getName());
                Scene sc = new Scene(new MainPanel(nouveau, f, glu), 800, 600);
                nouveau.setScene(sc);
                nouveau.show();
            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème durant la sauvegarde");
                alert.setContentText(ex.getLocalizedMessage());

                alert.showAndWait();
            } finally {
                this.changeEtat(20);
            }
        }
    }
//    }

    void menuNouveau(ActionEvent t) {
        Stage nouveau = new Stage();
        nouveau.setTitle("Nouveau");
        Scene sc = new Scene(new MainPanel(nouveau), 800, 600);
        nouveau.setScene(sc);
        nouveau.show();
    }

    void menuApropos(ActionEvent t) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("A propos");
        alert.setHeaderText(null);
        alert.setContentText("Trop super ce micro-logiciel de dessin vectoriel 2D\n"
                + "réalisé par Thibault et Baptiste");

        alert.showAndWait();
    }

    
    
    
    void menuAddPoint(ActionEvent t) {
        Stage spoint=new Stage();
        spoint.setTitle("Creer Point");
        Button validation = new Button("OK");
        
        Button Appui=new Button("Appui");
        Appui.setOnAction((e)->{
            Stage sappui=new Stage(); 
            sappui.setTitle("Nouvel Appui");
            Label type=new Label("Type d'Appui");
             TextField Ttype = new TextField();
            
            Label position=new Label("Position par rapport au sommet :");
             TextField Tposition = new TextField();
            
            Label segment=new Label("Sur la barre :");
             TextField Tsegment = new TextField();
            
            HBox hsposition= new HBox(10,position,Tposition);
            hsposition.setAlignment(Pos.CENTER);
            
            HBox hssegment= new HBox(10,segment,Tsegment);
            hssegment.setAlignment(Pos.CENTER);
            
            HBox hsType= new HBox(10,type,Ttype);
            hsType.setAlignment(Pos.CENTER);
            
            Button validation1=new Button("OK");
            validation1.setAlignment(Pos.BOTTOM_RIGHT);
            VBox attributAppui=new VBox(10,hsType,hssegment,hsposition,validation1);
            attributAppui.setAlignment(Pos.CENTER);
            
            
            
        Scene scene1=new Scene(attributAppui,400,300);
        sappui.setScene(scene1);
        sappui.show();
         validation1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
                     Attributs=(String.valueOf(IDPoint)+1)+";Appui;"+Ttype.getText()+";"+Tsegment.getText()+";"+Tposition.getText();
            sappui.hide();
                    }
        });
        });
        Button Noeud=new Button("Noeud");
        Noeud.setOnAction((e)->{
            Stage snoeud=new Stage();  
            snoeud.setTitle("Nouveau Noeud");
            Label abscisse=new Label("Abscisse :");
            final TextField Tabscisse = new TextField();
            
            Label ordonnee=new Label("Ordonnee :");
            final TextField Tordonnee = new TextField();
            
            HBox hsabscisse= new HBox(10,abscisse,Tabscisse);
            hsabscisse.setAlignment(Pos.CENTER);
            
            HBox hsordonnee= new HBox(10,ordonnee,Tordonnee);
            hsordonnee.setAlignment(Pos.CENTER);
            
            Button validation2=new Button("OK");
            
            VBox attributNoeud=new VBox(10,hsabscisse,hsordonnee,validation2);
            
            attributNoeud.setAlignment(Pos.CENTER);            
        Scene scene2=new Scene(attributNoeud,400,300);
        snoeud.setScene(scene2);
        snoeud.show();
         validation2.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
                        Attributs=(String.valueOf(IDPoint)+1)+";Simple;"+Tabscisse.getText()+";"+Tordonnee.getText();
            snoeud.hide();
                    }
        });
        });
        
       HBox choix=new HBox(10,Appui,Noeud);
       choix.setAlignment(Pos.CENTER);
       
        VBox vchoix=new VBox(50,choix,validation);
        vchoix.setAlignment(Pos.CENTER);
        
        Scene scene=new Scene(vchoix,400,300);
         validation.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
                        Point[IDPoint]=Attributs;
                        System.out.println(Attributs);
                        IDPoint++;
            spoint.hide();
                    }
        });
         
         spoint.setScene(scene);
        spoint.show();
    }

    
    
    
    
    void menuAddSegment(ActionEvent t) {
        Stage ssegment=new Stage();
        ssegment.setTitle("Creer un Segment");
        
       Label Debut=new Label("Point du debut");
        final TextField TDebut = new TextField();
        HBox hDebut=new HBox(10,Debut,TDebut);
        hDebut.setAlignment(Pos.CENTER);
        
        Label Fin=new Label("Point de Fin");
        final TextField TFin = new TextField();
        HBox hFin=new HBox(10,Fin,TFin);
        hFin.setAlignment(Pos.CENTER);
        
        Button validation = new Button("OK");
        VBox vssegment=new VBox(10,hDebut,hFin,validation);
        vssegment.setAlignment(Pos.CENTER);
        Scene scene=new Scene(vssegment,400,300);
         validation.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
                        
                        Attributs=(String.valueOf(IDBarre)+1)+";"+TDebut.getText()+";"+TFin.getText();
                        Barre[IDBarre]=Attributs;
                        IDBarre++;
            ssegment.hide();
                    }
        });
         ssegment.setScene(scene);
        ssegment.show();    
    }

    
    
    
    
    void menuAddGroupe(ActionEvent t) {
        Stage sgroupe=new Stage();
        sgroupe.setTitle("Creer un Groupe");
        
        Button Segment=new Button("Ajouter un segment");
        
        Segment.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            Stage stage=new Stage();
            
           Label Identificateur= new Label("Segment numero :");
            final TextField TIdentificateur = new TextField();
            HBox HIdentificateur=new HBox(10,Identificateur,TIdentificateur);
            HIdentificateur.setAlignment(Pos.CENTER);
            
            Button validation1 = new Button("OK");
         validation1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
                        
            stage.hide();
                    }
        });
            
            VBox VIdentificateur=new VBox(20,HIdentificateur,validation1);
            VIdentificateur.setAlignment(Pos.CENTER);
            Scene scene=new Scene(VIdentificateur,400,300);
            stage.setScene(scene);
            stage.show();
                    }
        });
        Button Point= new Button("Ajouter un point");
        Point.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            Stage stage=new Stage();
            Label Identificateur= new Label("Point numero :");
            final TextField TIdentificateur = new TextField();
            int ID=Integer.parseInt(TIdentificateur.getText());
            HBox HIdentificateur=new HBox(10,Identificateur,TIdentificateur);
            HIdentificateur.setAlignment(Pos.CENTER);
            
            Button validation1 = new Button("OK");
         validation1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
                        
            stage.hide();
                    }
        });
            
            VBox VIdentificateur=new VBox(20,HIdentificateur,validation1);
            VIdentificateur.setAlignment(Pos.CENTER);
            Scene scene=new Scene(VIdentificateur,400,300);
            stage.setScene(scene);
            stage.show();
                    }
        });
        
        
        Button validation = new Button("OK");
         validation.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
                        Groupe[IDGroupe]=Attributs;
            sgroupe.hide();
                    }
        });
         VBox vsgroupe=new VBox(20,Point,Segment,validation);
        vsgroupe.setAlignment(Pos.CENTER);
        Scene scene=new Scene(vsgroupe,400,300);
         sgroupe.setScene(scene);
        sgroupe.show();
    }

    
    
    
    
    void menuAddPoid(ActionEvent t) {
        Stage spoid=new Stage();
        spoid.setTitle("Ajouter un Poids");
        Label Poids=new Label("Combien de kilos chacal ?");
        final TextField TPoids = new TextField();
        HBox HPoids= new HBox(10,Poids,TPoids);
        HPoids.setAlignment(Pos.CENTER);
        Button validation = new Button("OK");
        validation.setAlignment(Pos.CENTER);
        VBox vspoid=new VBox(50,HPoids,validation);
        vspoid.setAlignment(Pos.CENTER);
        Scene scene=new Scene(vspoid,400,300);
         validation.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            spoid.hide();
                    }
        });
         spoid.setScene(scene);
        spoid.show();    }

    
    
    
    
    
    void menuModifPoint(ActionEvent t) {
        Stage mpoint=new Stage();
        mpoint.setTitle("Modifier Point");
        Button validation = new Button("OK");
        
        Button Appui=new Button("Appui");
        Appui.setOnAction((e)->{
            Stage mappui=new Stage(); 
            mappui.setTitle("Modifier Appui");
            
            Label Identificateur= new Label("Point numero :");
            final TextField TIdentificateur = new TextField();
            HBox hIdentificateur=new HBox(10,Identificateur,TIdentificateur);
            hIdentificateur.setAlignment(Pos.CENTER);
            Label type=new Label("Type d'Appui");
            final TextField Ttype = new TextField();
            
            Label position=new Label("Position par rapport au sommet :");
            final TextField Tposition = new TextField();
            
            Label segment=new Label("Sur la barre :");
            final TextField Tsegment = new TextField();
            
            
            
            HBox hsposition= new HBox(10,position,Tposition);
            hsposition.setAlignment(Pos.CENTER);
            
            HBox hssegment= new HBox(10,segment,Tsegment);
            hssegment.setAlignment(Pos.CENTER);
            
            HBox hsType= new HBox(10,type,Ttype);
            hsType.setAlignment(Pos.CENTER);
            
            Button validation1=new Button("OK");
            validation1.setAlignment(Pos.BOTTOM_RIGHT);
            VBox attributAppui=new VBox(10,hIdentificateur,hsType,hssegment,hsposition,validation1);
            attributAppui.setAlignment(Pos.CENTER);
            
            
            
        Scene scene1=new Scene(attributAppui,400,300);
        mappui.setScene(scene1);
        mappui.show();
         validation1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            mappui.hide();
                    }
        });
        });
        Button Noeud=new Button("Noeud");
        Noeud.setOnAction((e)->{
            Stage mnoeud=new Stage();  
            mnoeud.setTitle("Modifier Noeud");
            Label abscisse=new Label("Abscisse :");
            final TextField Tabscisse = new TextField();
            
            Label Identificateur= new Label("Point numero :");
            final TextField TIdentificateur = new TextField();
            HBox hIdentificateur=new HBox(10,Identificateur,TIdentificateur);
            hIdentificateur.setAlignment(Pos.CENTER);
            Label ordonnee=new Label("Ordonnee :");
            final TextField Tordonnee = new TextField();
            
            HBox hsabscisse= new HBox(10,abscisse,Tabscisse);
            hsabscisse.setAlignment(Pos.CENTER);
            
            HBox hsordonnee= new HBox(10,ordonnee,Tordonnee);
            hsordonnee.setAlignment(Pos.CENTER);
            
            Button validation2=new Button("OK");
            
            VBox attributNoeud=new VBox(10,hIdentificateur,hsabscisse,hsordonnee,validation2);
            
            attributNoeud.setAlignment(Pos.CENTER);            
        Scene scene2=new Scene(attributNoeud,400,300);
        mnoeud.setScene(scene2);
        mnoeud.show();
         validation2.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            mnoeud.hide();
                    }
        });
        });
        
       HBox choix=new HBox(10,Appui,Noeud);
       choix.setAlignment(Pos.CENTER);
       
        VBox vchoix=new VBox(50,choix,validation);
        vchoix.setAlignment(Pos.CENTER);
        
        Scene scene=new Scene(vchoix,400,300);
         validation.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            mpoint.hide();
                    }
        });
         
         mpoint.setScene(scene);
        mpoint.show();
    }

    
    
    
    
    void menuModifSegment(ActionEvent t) {
        Stage ssegment=new Stage();
        ssegment.setTitle("Modifier un Segment");
        
         Label Identificateur= new Label("Point numero :");
            final TextField TIdentificateur = new TextField();
            HBox hIdentificateur=new HBox(10,Identificateur,TIdentificateur);
            hIdentificateur.setAlignment(Pos.CENTER);
            
        Label Debut=new Label("Point du debut");
        final TextField TDebut = new TextField();
        HBox hDebut=new HBox(10,Debut,TDebut);
        hDebut.setAlignment(Pos.CENTER);
        
        Label Fin=new Label("Point de Fin");
        final TextField TFin = new TextField();
        HBox hFin=new HBox(10,Fin,TFin);
        hFin.setAlignment(Pos.CENTER);
        
        Button validation = new Button("OK");
        VBox vssegment=new VBox(10,hIdentificateur,hDebut,hFin,validation);
        vssegment.setAlignment(Pos.CENTER);
        Scene scene=new Scene(vssegment,400,300);
         validation.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            ssegment.hide();
                    }
        });
         ssegment.setScene(scene);
        ssegment.show();   
    }

    
    
    
    
    void menuModifPoid(ActionEvent t) {
         Stage spoid=new Stage();
        spoid.setTitle("Modifier le Poids");
        
        Label Poids=new Label("Combien de kilos chacal ?");
        final TextField TPoids = new TextField();
        HBox HPoids= new HBox(10,Poids,TPoids);
        HPoids.setAlignment(Pos.CENTER);
        
        Button validation = new Button("OK");
        validation.setAlignment(Pos.CENTER);
        VBox vspoid=new VBox(50,HPoids,validation);
        vspoid.setAlignment(Pos.CENTER);
        Scene scene=new Scene(vspoid,400,300);
         validation.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            spoid.hide();
                    }
        });
         spoid.setScene(scene);
        spoid.show();    }

    
    
    
    
    void menuModifGroupe(ActionEvent t) {
        Stage sgroupe=new Stage();
        sgroupe.setTitle("Modifier un Groupe");
        
         Label IdentificateurG= new Label("Modifier le Groupe numero :");
            final TextField TIdentificateurG = new TextField();
            HBox HIdentificateurG=new HBox(10,IdentificateurG,TIdentificateurG);
            HIdentificateurG.setAlignment(Pos.CENTER);
        
        Button Segment=new Button("Ajouter un segment");
        Segment.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            Stage stage=new Stage();
            Label Identificateur= new Label("Segment numero :");
            final TextField TIdentificateur = new TextField();
            
            Button validation1 = new Button("OK");
         validation1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            stage.hide();
                    }
        });
            HBox HIdentificateur=new HBox(10,Identificateur,TIdentificateur);
            HIdentificateur.setAlignment(Pos.CENTER);
            VBox VIdentificateur=new VBox(20,HIdentificateur,validation1);
            VIdentificateur.setAlignment(Pos.CENTER);
            Scene scene=new Scene(VIdentificateur,400,300);
            stage.setScene(scene);
            stage.show();
                    }
        });
        Button Point= new Button("Ajouter un point");
        Point.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            Stage stage=new Stage();
            Label Identificateur= new Label("Point numero :");
            final TextField TIdentificateur = new TextField();
            Button validation1 = new Button("OK");
         validation1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            stage.hide();
                    }
        });
            HBox HIdentificateur=new HBox(10,Identificateur,TIdentificateur);
            HIdentificateur.setAlignment(Pos.CENTER);
            VBox VIdentificateur=new VBox(20,HIdentificateur,validation1);
            VIdentificateur.setAlignment(Pos.CENTER);
            Scene scene=new Scene(VIdentificateur,400,300);
            stage.setScene(scene);
            stage.show();
                    }
        });
        
        
        Button validation = new Button("OK");
         validation.setOnAction(new EventHandler<ActionEvent>() {
             @Override
                    public void handle(ActionEvent e){
            sgroupe.hide();
                    }
        });
         VBox vsgroupe=new VBox(20,HIdentificateurG,Point,Segment,validation);
        vsgroupe.setAlignment(Pos.CENTER);
        Scene scene=new Scene(vsgroupe,400,300);
         sgroupe.setScene(scene);
        sgroupe.show();    
    }

}
