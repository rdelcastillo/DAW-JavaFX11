package org.iesgrancapitan.PROGR.openjfx;

/**
 * Ejemplo de binding y uso de eventos basados en propiedades.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ej15BindindigYEventosConProperty extends Application {

  @Override
  public void start(Stage primaryStage) {
    
    TextField top = new TextField("Soy un TextField (no me borres)");
    top.textProperty().addListener((observable, oldValue, newValue) -> {
      System.out.println("Antes..: " + oldValue);
      System.out.println("Después: " + newValue);
      System.out.println("--------------------");
      if (newValue.isEmpty()) {
        top.setText("¡No me borres!");
      }
    });
    
    TextField bottom = new TextField();
    bottom.textProperty().bindBidirectional(top.textProperty());
    
    Button button = new Button("Botón");
    button.textProperty().bind(top.textProperty());
    
    VBox root = new VBox(10, top, bottom, button);
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("vistas/estilos.css").toExternalForm());
    
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}
