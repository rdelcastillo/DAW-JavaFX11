package org.iesgrancapitan.PROGR.openjfx.vistas;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class Ej16ConversionDineroController {
  @FXML
  private TextField euros;
  @FXML
  private TextField dolares;
  @FXML
  private TextField libras;

  private double cambioDolar = 0;
  private double cambioLibra = 0;

  // Event Listener on Button.onAction
  @FXML
  public void convertir(ActionEvent event) {
    try {
      double valorEuros = Double.parseDouble(euros.getText());
      dolares.setText(valorEuros * cambioDolar + "");
      libras.setText(valorEuros * cambioLibra + "");
      
    } catch (NumberFormatException e) {
      dolares.setText("No se puede convertir");
      libras.setText(dolares.getText());
    }
  }

  public void setCambioDolar(double cambio) {
    cambioDolar = cambio;
  }

  public void setCambioLibra(double cambio) {
    cambioLibra = cambio;
  }

}
