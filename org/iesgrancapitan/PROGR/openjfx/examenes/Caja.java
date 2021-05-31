package org.iesgrancapitan.PROGR.openjfx.examenes;

import java.io.IOException;
import org.iesgrancapitan.PROGR.openjfx.examenes.vistas.CajaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Caja extends Application {

  @Override
  public void start(Stage primaryStage) throws IOException {
    var fxml = new FXMLLoader(getClass().getResource("vistas/Caja.fxml"));
    Parent root = fxml.load();
    
    CajaController controller = (CajaController) fxml.getController();
    controller.setPrimaryStage(primaryStage);
    
    primaryStage.setScene(new Scene(root));
    primaryStage.setTitle(getClass().getSimpleName());
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
