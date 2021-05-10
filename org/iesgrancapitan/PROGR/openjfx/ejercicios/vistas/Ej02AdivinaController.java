package org.iesgrancapitan.PROGR.openjfx.ejercicios.vistas;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Ej02AdivinaController {
  private static final int INTENTOS_MAXIMOS = 10;
  private static final int ALEATORIO_MINIMO = 1;
  private static final int ALEATORIO_MAXIMO = 100;
  private int numeroAAdivinar;
  
  @FXML
  private TextField numeroIntroducido;
  @FXML
  private TextArea visor;
  @FXML
  private TextField intentos;
  @FXML
  private Label labelIntroduzca;

  // Event Listener on Button[#intentarButton].onAction
  @FXML
  public void intentar(ActionEvent event) {
    try {
      int n = Integer.parseInt(numeroIntroducido.getText());
      decrementaIntentos();
      if (n == numeroAAdivinar) {
        finalizarConAcierto();
      } else if (intentos.getText().equals("0")) {
        finalizarConError();
      }
      else {
        visor.appendText(n + " es " 
            + (n < numeroAAdivinar ? "menor": "mayor")
            + " que el número a adivinar.\n");
      }
      
    } catch (NumberFormatException e) {
      visor.appendText("'" + numeroIntroducido.getText() + "' no es un número.\n");
    } finally {
      numeroIntroducido.clear();
    }
  }
  
  private void decrementaIntentos() {
    int n = Integer.parseInt(intentos.getText());
    intentos.setText(--n + "");
  }
  
  private void finalizarConAcierto() {
    int intentosRealizados = INTENTOS_MAXIMOS - Integer.parseInt(intentos.getText());
    Alert mensaje = new Alert(AlertType.INFORMATION);
    mensaje.setTitle("¡¡ENHORABUENA!!");
    mensaje.setHeaderText("Adivinó el número en " + intentosRealizados + " intentos");
    mensaje.setContentText("Espero volver a verte pronto 😉");
    mensaje.showAndWait();
    cerrarApp();
  }
  
  private void finalizarConError() {
    Alert mensaje = new Alert(AlertType.ERROR);
    mensaje.setTitle("NO ACERTÓ");
    mensaje.setHeaderText("El número a adivinar era " + numeroAAdivinar);
    mensaje.setContentText("Espero volver a verte pronto\ny que tengas más suerte 👍");
    mensaje.showAndWait();
    cerrarApp();
  }
  
  private void cerrarApp() {
    Stage ventanaPrincipal = (Stage) visor.getScene().getWindow();
    ventanaPrincipal.close();
  }

  // Este método, si existe, se ejecuta al principio
  public void initialize() {
    numeroAAdivinar = ALEATORIO_MINIMO + (int) (Math.random() * (ALEATORIO_MAXIMO-ALEATORIO_MINIMO));
    labelIntroduzca.setText("Introduce un número entre " + ALEATORIO_MINIMO + " y " + ALEATORIO_MAXIMO);
    intentos.setText(INTENTOS_MAXIMOS + "");
  }
}
