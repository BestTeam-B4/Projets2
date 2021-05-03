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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author lemei
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Scene sc = new Scene(new MainPanel(stage,Groupe.groupeTest()),800,600);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
          stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
