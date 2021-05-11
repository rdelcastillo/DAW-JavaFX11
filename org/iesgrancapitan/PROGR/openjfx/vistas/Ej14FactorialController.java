package org.iesgrancapitan.PROGR.openjfx.vistas;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.event.ActionEvent;

public class Ej14FactorialController {
  @FXML
  private TextField numero;
  @FXML
  private TextField visor;
  @FXML
  private Button botonCalcular;

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
    if (event.getCode().getName().equals("Esc") && confirmaSalir()) {
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

  @FXML
  public void initialize() {
    // Impedimos dar a calcular si no hay valor en "número"
    botonCalcular.setDisable(true);
    
    numero.textProperty().addListener((observable, oldValue, newValue) -> {
      // Impedimos que se puedan meter caracteres que no sean dígitos
      if (!newValue.matches("^[0-9]*$")) {
        numero.setText(oldValue);
      }
      // Activamos botón si hay contenido en "número"
      botonCalcular.setDisable(numero.getText().isBlank());
    });
  }
}
