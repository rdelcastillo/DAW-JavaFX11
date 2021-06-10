package org.iesgrancapitan.PROGR.openjfx;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {

  public static void mostrarError(String header, String content) {
    mostrar(header, content, AlertType.ERROR);
  }

  public static void mostrarError(String content) {
    mostrar(null, content, AlertType.ERROR);
  }

  public static void mostrarAviso(String header, String content) {
    mostrar(header, content, AlertType.WARNING);
  }
  
  public static void mostrarAviso(String content) {
    mostrar(null, content, AlertType.WARNING);
  }

  private static void mostrar(String header, String content, AlertType alertType) {
    Alert alert = new Alert(alertType, content);
    alert.setHeaderText(header);
    alert.showAndWait();
  }
}
