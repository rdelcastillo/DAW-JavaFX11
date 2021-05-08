module hellofx {
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.base;
  
  opens org.iesgrancapitan.PROGR.openjfx to javafx.fxml;
  opens org.iesgrancapitan.PROGR.openjfx.vistas to javafx.fxml;
  opens org.iesgrancapitan.PROGR.openjfx.ejercicios.vistas to javafx.fxml;
  
  exports org.iesgrancapitan.PROGR.openjfx;
  exports org.iesgrancapitan.PROGR.openjfx.dialogos;
  exports org.iesgrancapitan.PROGR.openjfx.ejercicios;
  exports org.iesgrancapitan.PROGR.openjfx.ejercicios.vistas;
}
