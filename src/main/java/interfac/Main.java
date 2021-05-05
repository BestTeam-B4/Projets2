/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import interfac.Groupe;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javax.swing.GroupLayout.Group;


/**
 *
 * @author lemei
 */
public class Main extends Application {
        private Label Welcome;
    @Override
    public void start(Stage PrimaryStage) throws IOException {
        PrimaryStage.setTitle("WELCOME !!!");
        Stage stage=new Stage();
        
        HBox hchoix = new HBox(15);
        VBox vinterface = new VBox(100);

        Welcome= new Label("BIENVENU DANS TREILLCRAFT !!! ");
       
     
       
        Button Dessin = new Button("Dessiner");
        Dessin.setOnAction(new EventHandler<ActionEvent>() {
           @Override
                   public void handle(ActionEvent e){
                       System.out.println("bouton1 clique");
        Scene sc = new Scene(new MainPanel(stage,Groupe.groupeTest()),800,600);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
        stage.show();
                   }
        });
        
        VBox vdessin=new VBox(100,Welcome,Dessin);
        vdessin.setAlignment(Pos.CENTER);
        HBox hdessin= new HBox(vdessin);
        hdessin.setAlignment(Pos.CENTER);
        
        
        
        
        Scene WelcomeScene = new Scene(hdessin,800,600);
        
        PrimaryStage.setScene(WelcomeScene);
        
        PrimaryStage.show();
        
    }
       

    public static void main(String[] args) {
        launch();
        
    }

    

}
