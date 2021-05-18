package org.iesgrancapitan.PROGR.openjfx;

/**
 * A veces tenemos que acceder a datos de la aplicación principal desde un controlador.
 * 
 * Este ejemplo ilustra como hacerlo, básicamente hay que inyectar a cada controlador
 * el dato, o los datos, que necesita.
 * 
 * @author Rafael del Castillo
 */

import java.io.IOException;
import org.iesgrancapitan.PROGR.openjfx.vistas.Ej16ConversionDineroController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Ej16ConversionDinero extends Application {

  private static final double CAMBIO_EURO_LIBRA = 0.86;
  private static final double CAMBIO_EURO_DOLAR = 1.21;

  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/Ej16ConversionDinero.fxml"));
      var root = fxml.<Pane>load();

      // accedemos al controlador del FXML e inyectamos datos a través de métodos para ese fin
      var controller = (Ej16ConversionDineroController) fxml.getController();
      controller.setCambioDolar(CAMBIO_EURO_DOLAR);
      controller.setCambioLibra(CAMBIO_EURO_LIBRA);
      
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
