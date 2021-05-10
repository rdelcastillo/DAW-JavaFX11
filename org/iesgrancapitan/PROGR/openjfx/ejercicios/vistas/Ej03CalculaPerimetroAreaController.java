package org.iesgrancapitan.PROGR.openjfx.ejercicios.vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Ej03CalculaPerimetroAreaController {

  @FXML
  private TextField base;

  @FXML
  private TextField altura;

  @FXML
  private TextField area;

  @FXML
  private TextField perimetro;

  @FXML
  void calcular(ActionEvent event) {
    try {
      double baseDouble = Double.parseDouble(base.getText());
      double alturaDouble = Double.parseDouble(altura.getText());
      double areaDouble = baseDouble * alturaDouble;
      double perimetroDouble = baseDouble * 2 + alturaDouble * 2;
      area.setText(areaDouble + "");
      perimetro.setText(perimetroDouble + "");
      
    } catch (NumberFormatException e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error de datos");
      alert.setHeaderText("El formato de los datos no es numérico");
      alert.showAndWait();
    }
  }

}

