package org.iesgrancapitan.PROGR.openjfx.vistas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesgrancapitan.PROGR.openjfx.Ej18ConversionDinero;
import javafx.event.ActionEvent;

public class Ej18ValorCambioDivisasController {
  @FXML
  private TextField cambioDolar;
  @FXML
  private TextField cambioLibra;

  private Ej18ConversionDinero app;

  // Event Listener on Button.onAction
  @FXML
  public void finalizar(ActionEvent event) {
    try {
      // Devolver el cambio a la app
      double eurosDolar = Double.parseDouble(cambioDolar.getText());
      double eurosLibra = Double.parseDouble(cambioLibra.getText());
      app.setCambioDolar(eurosDolar);
      app.setCambioLibra(eurosLibra);

    } catch (NumberFormatException e) {
      Alert alert = new Alert(AlertType.ERROR, "Mantenemos los factores de conversión iniciales");
      alert.setHeaderText("Los valores introducidos no son válidos");
      alert.showAndWait();
      
    } finally {
      // cerrar Stage
      Stage stage = (Stage) cambioDolar.getScene().getWindow();
      stage.close();
    }
  }

  public void setValoresIniciales(double cambioDolares, double cambioLibras,
      Ej18ConversionDinero app) {
    cambioDolar.setText(cambioDolares + "");
    cambioLibra.setText(cambioLibras + "");
    this.app = app;
  }
}
