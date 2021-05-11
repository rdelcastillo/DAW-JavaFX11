package org.iesgrancapitan.PROGR.openjfx;

/**
 * Ejemplo de uso de una hoja de estilos llamada desde el objeto Scene.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ej13FactorialConCSS extends Application {

  @Override
  public void start(Stage primaryStage) {
    FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/Ej13Factorial.fxml"));
    try {
      var root = fxml.<VBox>load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("vistas/estilos.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName());
      primaryStage.show();
      
    } catch (IOException e) {
      System.out.println("Error al cargar el fxml.");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
