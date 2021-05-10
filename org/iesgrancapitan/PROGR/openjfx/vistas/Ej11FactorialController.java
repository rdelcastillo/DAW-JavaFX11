package org.iesgrancapitan.PROGR.openjfx.vistas;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.event.ActionEvent;

public class Ej11FactorialController {
  @FXML
  private TextField numero;
  @FXML
  private TextField visor;

  // Event Listener on Button.onAction
  @FXML
  public void calcular(ActionEvent event) {
    try {
      long n = Long.parseLong(numero.getText());
      long f = 1;
      for (int i = 2; i <= n; i++) {
        f *= i;
      }
      visor.setText(f + "");

    } catch (NumberFormatException e) {
      visor.setText("'" + numero.getText() + "' no es un número.");
    }
  }

  @FXML
  void salirSiEsc(KeyEvent event) {
    if (confirmaSalir()) {
      Scene scene = numero.getScene();
      Stage stage = (Stage) scene.getWindow();
      stage.close();
    }
  }

  private boolean confirmaSalir() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmación");
    alert.setHeaderText("La aplicación va a terminar ¿seguro?");

    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }
}
