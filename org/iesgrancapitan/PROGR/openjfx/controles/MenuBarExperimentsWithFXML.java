package org.iesgrancapitan.PROGR.openjfx.controles;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuBarExperimentsWithFXML extends Application {

  @Override
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("MenuBar Experiments 2");
    Parent root = FXMLLoader.load(getClass().getResource("view/MenuBarExperiments.fxml"));
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

