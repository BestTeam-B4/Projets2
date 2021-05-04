/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import interfac.Groupe;
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

/**
 *
 * @author lemei
 */
public class Main extends Application {
        
    @Override
    public void start(Stage PrimaryStage) {
        PrimaryStage.setTitle("WELCOME !!!");
        Stage stage=new Stage();
        
        HBox hchoix = new HBox(15);
        VBox vinterface = new VBox(100);
        
        Label Welcome= new Label("BIENVENU DANS TREILLCRAFT !!! ");
        Welcome.setFont(Font.font("Minecraft",30));
        Welcome.setTextFill(Color.GRAY);
        
       Button DessinClassique = new Button("DessinClassique");
       DessinClassique.setOnAction(new EventHandler<ActionEvent>() {
           @Override
                   public void handle(ActionEvent e){
                       System.out.println("bouton1 clique");
        Scene sc = new Scene(new MainPanel(stage,Groupe.groupeTest()),800,600);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
        stage.show();
                   }
        });
       
        Button DessinNumerique = new Button("DessinNumerique");
        DessinNumerique.setOnAction(new EventHandler<ActionEvent>() {
           @Override
                   public void handle(ActionEvent e){
                       System.out.println("bouton1 clique");
        Scene sc = new Scene(new MainPanel(stage,Groupe.groupeTest()),400,200);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
        stage.show();
                   }
        });
        hchoix.getChildren().addAll(DessinClassique,DessinNumerique);
        hchoix.setAlignment(Pos.CENTER);
        hchoix.setPadding(new Insets(25));
        
        vinterface.getChildren().addAll(Welcome,hchoix);
        vinterface.setAlignment(Pos.CENTER);
        
        
        Scene WelcomeScene = new Scene(vinterface,800,600);
        
        PrimaryStage.setScene(WelcomeScene);
        
        PrimaryStage.show();
        
        
        
    }
       

    public static void main(String[] args) {
        launch();
    }

    

}
