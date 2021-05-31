package org.iesgrancapitan.PROGR.openjfx.vistas;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import org.iesgrancapitan.PROGR.openjfx.Ej19ConversionDinero;
import javafx.event.ActionEvent;

public class Ej19ValorCambioDivisasController {
  @FXML
  private TextField cambioDolar;
  @FXML
  private TextField cambioLibra;

  private Ej19ConversionDinero app;

  // Event Listener on Button.onAction
  @FXML
  public void aplicar(ActionEvent event) {
    double cambioDolares = Double.parseDouble(cambioDolar.getText());
    double cambioLibras = Double.parseDouble(cambioLibra.getText());
    
    // Devolver el cambio a la app
    app.setCambioDolar(cambioDolares);
    app.setCambioLibra(cambioLibras);
    
    var controller = app.getControllerCambioDivisas();
    controller.setCambioDolar(cambioDolares);
    controller.setCambioLibra(cambioLibras);
  }

  public void setValoresIniciales(double cambioDolares, double cambioLibras,
      Ej19ConversionDinero app) {
    cambioDolar.setText(cambioDolares + "");
    cambioLibra.setText(cambioLibras + "");
    this.app = app;

  }

}
