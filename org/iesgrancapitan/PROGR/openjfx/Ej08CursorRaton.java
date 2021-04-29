package org.iesgrancapitan.PROGR.openjfx;

/**
 * Ejemplo de cambios en el cursor gráfico del ratón desde una escena.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ej08CursorRaton extends Application {
  
  int indexCursor = 0;
  Cursor[] cursoresRaton = {
      Cursor.DEFAULT,
      Cursor.OPEN_HAND,
      Cursor.CLOSED_HAND,
      Cursor.CROSSHAIR,
      Cursor.HAND,
      Cursor.WAIT,
      Cursor.E_RESIZE,
      Cursor.H_RESIZE,
      Cursor.N_RESIZE,
      Cursor.NE_RESIZE,
      Cursor.NW_RESIZE,
      Cursor.S_RESIZE,
      Cursor.SE_RESIZE,
      Cursor.SW_RESIZE,
      Cursor.V_RESIZE,
      Cursor.W_RESIZE,
      Cursor.MOVE,
      Cursor.WAIT,
      Cursor.TEXT
  };
  Scene scene;

  @Override
  public void start(Stage primaryStage) {
    createScene();
    primaryStage.setScene(scene);
    primaryStage.setTitle(getClass().getSimpleName());
    primaryStage.show();
  }

  private void createScene() {
    Label label = new Label("Pulsa en el botón\npara cambiar el cursor");
    
    Button button = new Button("Cambia cursor ratón");
    button.setOnAction(event -> {
      if (++indexCursor == cursoresRaton.length) {  // hemos recorrido todos los cursores
        indexCursor = 0;
      }
      scene.setCursor(cursoresRaton[indexCursor]);
      System.out.println("Cursor del ratón cambiado: " + scene.getCursor());
    });
    
    VBox vBox = new VBox(label, button);
    vBox.setAlignment(Pos.CENTER);
    vBox.setPadding(new Insets(10));
    vBox.setSpacing(10);
    
    scene = new Scene(vBox,250,100);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
