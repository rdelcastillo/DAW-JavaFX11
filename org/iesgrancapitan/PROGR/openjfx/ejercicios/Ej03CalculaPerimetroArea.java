package org.iesgrancapitan.PROGR.openjfx.ejercicios;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Ej03CalculaPerimetroArea extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      var fxml = new FXMLLoader(getClass().getResource("vistas/Ej03CalculaPerimetroArea.fxml"));
      var root = fxml.<Pane>load();
      var scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName());
      primaryStage.show();
      
    } catch (IOException e) {
      System.out.println("No se ha podido cargar el FXML");
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    launch(args);
  }
}
