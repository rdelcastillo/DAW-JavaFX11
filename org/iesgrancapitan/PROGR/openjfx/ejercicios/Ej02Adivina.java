package org.iesgrancapitan.PROGR.openjfx.ejercicios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Ej02Adivina extends Application {
  
  private static final int INTENTOS_MAXIMOS = 10;
  private static final int ALEATORIO_MINIMO = 1;
  private static final int ALEATORIO_MAXIMO = 100;
  private static final Font FUENTE = new Font("Arial",25);
  
  private int numeroAAdivinar;
  private TextArea visor = new TextArea();
  private TextField intentos = new TextField();
  private TextField numeroIntroducido = new TextField();
  private Stage ventanaPrincipal;

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Adivina un número entre " + ALEATORIO_MINIMO + " y " + ALEATORIO_MAXIMO);
    primaryStage.setScene(getEscenaAdivina());
    ventanaPrincipal = primaryStage;
    primaryStage.show();
  }
  
  private Scene getEscenaAdivina() {
    VBox root = new VBox(10, filaPeticion(), new Separator(), visor, filaIntentar());
    root.setPadding(new Insets(25));
    return new Scene(root);
  }

  private HBox filaPeticion() {
    Label label = new Label("Introduce un número entre " + ALEATORIO_MINIMO + " y " + ALEATORIO_MAXIMO);
    label.setFont(FUENTE);
    
    HBox hBox = new HBox(10, label, numeroIntroducido);
    hBox.setAlignment(Pos.BASELINE_CENTER);
    return hBox;
  }
  
  private HBox filaIntentar() {
    Label label = new Label("Intentos que quedan");
    label.setFont(FUENTE);
    
    Button button = new Button("Intentar");
    button.setFont(FUENTE);
    button.setDefaultButton(true);
    button.setOnAction(event -> compruebaIntento());
        
    HBox hBox = new HBox(10, label, intentos, button); 
    hBox.setAlignment(Pos.BASELINE_CENTER);
    return hBox;
  }

  private void compruebaIntento() {
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
    ventanaPrincipal.close();
  }
  
  private void finalizarConError() {
    Alert mensaje = new Alert(AlertType.ERROR);
    mensaje.setTitle("NO ACERTÓ");
    mensaje.setHeaderText("El número a adivinar era " + numeroAAdivinar);
    mensaje.setContentText("Espero volver a verte pronto\ny que tengas más suerte 👍");
    mensaje.showAndWait();
    ventanaPrincipal.close();
  }

  public void init() {
    numeroAAdivinar = ALEATORIO_MINIMO + (int) (Math.random() * (ALEATORIO_MAXIMO-ALEATORIO_MINIMO));
    visor.setEditable(false);
    visor.setFont(FUENTE);
    intentos.setEditable(false);
    intentos.setText(INTENTOS_MAXIMOS + "");
    intentos.setFont(FUENTE);
    numeroIntroducido.setFont(FUENTE);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
