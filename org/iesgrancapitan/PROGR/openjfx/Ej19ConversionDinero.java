package org.iesgrancapitan.PROGR.openjfx;

/**
 * Este ejemplo se basa en el Ej16ConversionDinero.java pero usa un Stage inicial para pedir los
 * valores de cambio de monedas.
 * 
 * La diferencia con el ejemplo anterior está en que el Stage que pide los valores de cambio se
 * mantiene abierto y al aplicar se inyectan los datos en el controlador del Stage principal.
 * 
 * @author Rafael del Castillo
 */

import java.io.IOException;
import org.iesgrancapitan.PROGR.openjfx.vistas.Ej16ConversionDineroController;
import org.iesgrancapitan.PROGR.openjfx.vistas.Ej19ValorCambioDivisasController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Ej19ConversionDinero extends Application {

  private static final double CAMBIO_EURO_LIBRA = 0.86;
  private static final double CAMBIO_EURO_DOLAR = 1.21;

  private double cambioEuroLibra = CAMBIO_EURO_LIBRA;
  private double cambioEuroDolar = CAMBIO_EURO_DOLAR;
  private Ej16ConversionDineroController controllerConversion;

  @Override
  public void start(Stage primaryStage) {
    try {
      // Escenario para pedir los valores de conversión de las divisas

      var fxmlCambio = new FXMLLoader(getClass().getResource("vistas/Ej19ValorCambioDivisas.fxml"));
      var rootCambio = fxmlCambio.<Pane>load();
      var controllerCambio = (Ej19ValorCambioDivisasController) fxmlCambio.getController();
      controllerCambio.setValoresIniciales(CAMBIO_EURO_DOLAR, CAMBIO_EURO_LIBRA, this);

      Scene sceneConversion = new Scene(rootCambio);
      sceneConversion.getStylesheets()
          .add(getClass().getResource("vistas/estilos.css").toExternalForm());

      Stage stageConversion = new Stage();
      stageConversion.setScene(sceneConversion);
      stageConversion.show();

      // Escenario para cambio de divisas

      var fxmlPrincipal = new FXMLLoader(getClass().getResource("vistas/Ej16ConversionDinero.fxml"));
      var rootPrincipal = fxmlPrincipal.<Pane>load();

      // accedemos al controlador del FXML e inyectamos datos a través de métodos para ese fin
      controllerConversion = (Ej16ConversionDineroController) fxmlPrincipal.getController();
      controllerConversion.setCambioDolar(cambioEuroDolar);
      controllerConversion.setCambioLibra(cambioEuroLibra);

      Scene scene = new Scene(rootPrincipal);
      scene.getStylesheets().add(getClass().getResource("vistas/estilos.css").toExternalForm());

      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName());
      primaryStage.setOnCloseRequest(e -> Platform.exit());
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

  @SuppressWarnings("exports")
  public Ej16ConversionDineroController getControllerCambioDivisas() {
    return controllerConversion;
  }


  public static void main(String[] args) {
    launch(args);
  }
}
