package org.iesgrancapitan.PROGR.openjfx.examenes.vistas;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

public class CajaController {
  private static final String SALIDA = "SALIDA";
  private static final String INGRESO = "INGRESO";
  private static final String EXP_REG_EUROS = "\\d*\\.?\\d*";
  @FXML
  private TextField importe;
  @FXML
  private TextField concepto;
  @FXML
  private TextArea visor;
  @FXML
  private TextField saldo;

  private int numApunte = 1;
  private boolean seHanGuardadoLosCambios = true;

  // Event Listener on Button.onAction
  @FXML
  public void ingresar(ActionEvent event) {
    registra(INGRESO);
  }

  // Event Listener on Button.onAction
  @FXML
  public void sacar(ActionEvent event) {
    registra(SALIDA);
  }

  private void registra(String tipoApunte) {
    try {
      ponSaldo(tipoApunte);
      ponApunte(tipoApunte);
      seHanGuardadoLosCambios = false;
      importe.clear();
      concepto.clear();

    } catch (NumberFormatException e) {
      Mensaje("Importe erróneo", "No hay un valor numérico", AlertType.ERROR);
    }
  }

  private void ponSaldo(String tipoApunte) {
    int signoImporte = tipoApunte.equals(INGRESO) ? 1 : -1;
    double importeEuros = signoImporte * Double.parseDouble(importe.getText());
    double saldoEuros =
        Double.parseDouble(saldo.getText().substring(0, saldo.getText().length() - 2));
    saldo.setText(String.format(Locale.ROOT, "%.2f", saldoEuros + importeEuros) + " €");
  }

  private void ponApunte(String tipoApunte) {
    visor.appendText("Apunte nº " + numApunte++ + " - ");
    visor.appendText(fechaHoraActual("dd/MM/yyyy HH:mm:ss") + ".\n");
    visor.appendText("Concepto: "
        + (concepto.getText().isBlank() ? "<SIN CONCEPTO>" : concepto.getText()) + "\n");
    visor.appendText(tipoApunte + " de " + importe.getText() + " €\n");
    visor.appendText("SALDO ACTUAL: " + saldo.getText() + "\n");
    visor.appendText("---\n");
  }

  // Event Listener on Button.onActionnull
  @FXML
  public void guardar(ActionEvent event) {
    File directorio = escogerDirectorio();
    if (directorio != null) {
      guardarFicheroEnDisco(directorio);
    } else {
      Mensaje("No se han guardado los movimientos", "Operación cancelada por el usuario",
          AlertType.WARNING);
    }
  }

  private File escogerDirectorio() {
    Stage primaryStage = (Stage) visor.getScene().getWindow();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    directoryChooser.setTitle("Escoge la carpeta donde se almacenarán los movimientos");
    return directoryChooser.showDialog(primaryStage);
  }

  private void guardarFicheroEnDisco(File directorio) {
    try {
      String fichero = "movimientos_" + fechaHoraActual("yyyyMMdd_HHmmss") + ".txt";
      Files.writeString(Paths.get(directorio.getCanonicalPath() + "/" + fichero), visor.getText());
      seHanGuardadoLosCambios = true;
      Mensaje("Operación realizada con éxito", "Movimientos guardados en " + fichero,
          AlertType.INFORMATION);

    } catch (IOException e) {
      Mensaje("La operación ha provocado una excepción", e.toString(), AlertType.ERROR);
      e.printStackTrace();
    }
  }

  private String fechaHoraActual(String formato) {
    LocalDateTime now = LocalDateTime.now();
    return now.format(DateTimeFormatter.ofPattern(formato));
  }

  @SuppressWarnings("exports")
  public static void Mensaje(String header, String content, AlertType type) {
    Alert alert = new Alert(type, content);
    alert.setHeaderText(header);
    alert.setResizable(true);
    alert.showAndWait();
  }

  public void setPrimaryStage(Stage stage) {
    stage.setOnCloseRequest(event -> {
      if (!seHanGuardadoLosCambios) {
        confirmarSalirApp(event);
      }
    });
  }

  private void confirmarSalirApp(WindowEvent event) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setHeaderText("Has dado a finalizar la aplicación");
    alert.setContentText("Los últimos cambios no se han guardado\n¿estás seguro de terminar?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.CANCEL) {
      event.consume();
    }
  }

  public void initialize() {
    importe.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches(EXP_REG_EUROS)) {
        importe.setText(oldValue);
      }
    });
  }

}
