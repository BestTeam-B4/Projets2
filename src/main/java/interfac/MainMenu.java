/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author lemei
 */
public class MainMenu extends MenuBar{
    
    private MainPanel main;
    
    public MainMenu(MainPanel main) {
        
        this.main = main;
        Menu file = new Menu("Fichier");
        MenuItem nouveau = new MenuItem("Nouveau");
        nouveau.setOnAction((t) -> {
            this.main.getControleur().menuNouveau(t);
        });
        MenuItem save = new MenuItem("Sauvegarder");
        save.setOnAction((t) -> {
            this.main.getControleur().menuSave(t);
        });
        MenuItem saveAs = new MenuItem("Sauvegarder sous...");
        saveAs.setOnAction((t) -> {
            this.main.getControleur().menuSaveAs(t);
        });
        MenuItem load = new MenuItem("Ouvrir");
        load.setOnAction((t) -> {
            this.main.getControleur().menuOpen(t);
        });        
        file.getItems().addAll(nouveau,save,saveAs,load);
        Menu help = new Menu("Aide");
        MenuItem apropos = new MenuItem("A propos");
        apropos.setOnAction((t) -> {
            this.main.getControleur().menuApropos(t);
        });        
        help.getItems().addAll(apropos);
        
        Menu add=new Menu("Edition");
        
        MenuItem addPoint=new MenuItem("Nouveau Point");
        addPoint.setOnAction((t) -> {
            this.main.getControleur().menuAddPoint(t);
        });        
        MenuItem addSegment=new MenuItem("Nouveau Segment");
         addSegment.setOnAction((t) -> {
            this.main.getControleur().menuAddSegment(t);
        });
        MenuItem addGroupe=new MenuItem("Nouveau Groupe");
         addGroupe.setOnAction((t) -> {
            this.main.getControleur().menuAddGroupe(t);
        });
        MenuItem addPoid=new MenuItem("Ajouter du poids");
         addPoid.setOnAction((t) -> {
            this.main.getControleur().menuAddPoid(t);
        });
         add.getItems().addAll(addPoint,addSegment,addGroupe,addPoid);
         
        Menu Settings=new Menu("Modifier");
        
        MenuItem ModifPoint=new MenuItem("Modifier le Point");
         ModifPoint.setOnAction((t) -> {
            this.main.getControleur().menuModifPoint(t);
        });
        MenuItem ModifSegment=new MenuItem("Modifier le Segment");
        ModifSegment.setOnAction((t) -> {
            this.main.getControleur().menuModifSegment(t);
        });
        MenuItem ModifierGroupe=new MenuItem("Modifier le Groupe");
        ModifierGroupe.setOnAction((t) -> {
            this.main.getControleur().menuModifGroupe(t);
        });
        MenuItem ModifierPoid=new MenuItem("Modifier le poids");
        ModifierPoid.setOnAction((t) -> {
            this.main.getControleur().menuModifPoid(t);
        });
        Settings.getItems().addAll(ModifPoint,ModifSegment,ModifierGroupe,ModifierPoid);
        this.getMenus().addAll(file,add,Settings,help);
    
        
        
        
        
        
    
    }
    
}
