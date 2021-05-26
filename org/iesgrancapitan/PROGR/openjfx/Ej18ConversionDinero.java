package org.iesgrancapitan.PROGR.openjfx;

/**
 * Este ejemplo se basa en el Ej16ConversionDinero.java pero usa un Stage inicial para pedir los
 * valores de cambio de monedas.
 * 
 * @author Rafael del Castillo
 */

import java.io.IOException;
import org.iesgrancapitan.PROGR.openjfx.vistas.Ej16ConversionDineroController;
import org.iesgrancapitan.PROGR.openjfx.vistas.Ej18ValorCambioDivisasController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Ej18ConversionDinero extends Application {

  private static final double CAMBIO_EURO_LIBRA = 0.86;
  private static final double CAMBIO_EURO_DOLAR = 1.21;
  
  private static double cambioEuroLibra = CAMBIO_EURO_LIBRA;
  private static double cambioEuroDolar = CAMBIO_EURO_DOLAR;

  @Override
  public void start(Stage primaryStage) {
    try {
      
      // Escenario para pedir los valores de conversión de las divisas
      
      var fxmlCambio = new FXMLLoader(getClass().getResource("vistas/Ej18ValorCambioDivisas.fxml"));
      var rootCambio = fxmlCambio.<Pane>load();
      var controllerCambio = (Ej18ValorCambioDivisasController) fxmlCambio.getController();
      controllerCambio.setValoresIniciales(CAMBIO_EURO_DOLAR, CAMBIO_EURO_LIBRA, this);
      
      var sceneCambio = new Scene(rootCambio);
      sceneCambio.getStylesheets().add(getClass().getResource("vistas/estilos.css").toExternalForm());
      
      var stageCambio = new Stage();
      stageCambio.setScene(sceneCambio);
      stageCambio.setTitle("Valores para el cambio de monedas");
      stageCambio.showAndWait();
      
      // Escenario para cambio de divisas
      
      var fxmlPrincipal = new FXMLLoader(getClass().getResource("vistas/Ej16ConversionDinero.fxml"));
      var root = fxmlPrincipal.<Pane>load();

      // accedemos al controlador del FXML e inyectamos datos a través de métodos para ese fin
      var controller = (Ej16ConversionDineroController) fxmlPrincipal.getController();
      controller.setCambioDolar(cambioEuroDolar);
      controller.setCambioLibra(cambioEuroLibra);
      
      var scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("vistas/estilos.css").toExternalForm());
      
      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName());
      primaryStage.show();
      
    } catch (IOException e) {
      System.out.println("Error al cargar el fxml.");
      e.printStackTrace();
    }
  }
  
  public void setCambioDolar(double cambio) {
    cambioEuroDolar = cambio;
  }

  public void setCambioLibra(double cambio) {
    cambioEuroLibra = cambio;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
