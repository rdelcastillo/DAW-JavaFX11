package org.iesgrancapitan.PROGR.openjfx;

/**
 * Ejemplo de uso de una hoja de estilos llamada desde el objeto Scene.
 * 
 * Usamos Pane como contenedor genérico.
 * 
 * Controlamos que no se puedan meter dígitos en el campo de texto del número.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Ej14FactorialConCSS extends Application {

  @Override
  public void start(Stage primaryStage) {
    FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/Ej14FactorialConCSS.fxml"));
    try {
      var root = fxml.<Pane>load();
      Scene scene = new Scene(root);
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
