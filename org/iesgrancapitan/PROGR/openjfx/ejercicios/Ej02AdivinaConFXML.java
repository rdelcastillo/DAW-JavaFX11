package org.iesgrancapitan.PROGR.openjfx.ejercicios;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ej02AdivinaConFXML extends Application {
  
  @Override
  public void start(Stage primaryStage) {
    try {
      // Cargamos el FXML y sacamos el nodo raíz
      FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/Ej02Adivina.fxml"));
      var root = fxml.<VBox>load();

      // Desde el nodo raíz construimos la escena y desde ahí el escenario
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName());
      primaryStage.show();

    } catch (IOException e) {
      System.out.println("No se ha podido cargar el fichero FXML");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
